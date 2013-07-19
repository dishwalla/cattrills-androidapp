package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
	private SharedPreferences preferences; //for language
	private Locale locale; 
	private String lang;
	protected Button Start;
	protected Button history;
	
	public static Source source = new Source();

	public static CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	public static Map<String, String> gamePares = new HashMap<String, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		lang = preferences.getString("lang", "default");	
		if (lang.equals("default")) {lang=getResources().getConfiguration().locale.getCountry();}
		locale = new Locale(lang);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Start = (Button)findViewById(R.id.game_start);
		history = (Button)findViewById(R.id.game_showhistory);
		Start.setOnClickListener(this);
		history.setOnClickListener(this);
	
		boolean save = preferences.getBoolean("hist", false);
		if (save == true){
			source.setSaveHistory(true);
		}
		else if (save == false){source.setSaveHistory(false);}

		boolean on = preferences.getBoolean("snd", false);
		if (on == true){
			source.setSoundsOn(true);
		}
		else if (on == false){source.setSaveHistory(false);}
	}
	

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		locale = new Locale(lang);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);


	}		
	public static Source getSource() {
		return source;
	}
	public static void setSource(Source source) {
		MainActivity.source = source;
	}

	@Override
	public void onClick(View v) {
		//final MediaPlayer mp = MediaPlayer.create(this, R.raw.purr);
		 //mp.start();
		try {
			switch (v.getId()) 
			{
			case R.id.game_start:
			
				if (service.connect() == true){
					Intent intent = new Intent(MainActivity.this, Activity2.class);
					startActivity(intent);
					MainActivity.this.finish();
					MenuAccess.playPurr(Start.getContext());
					break;
				}
				else
				{	//Toast.makeText(getApplicationContext(),	getString(R.string.string_stillnot), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(MainActivity.this, Activity12.class);
					startActivity(intent);
					MainActivity.this.finish();
					MenuAccess.playMeow(Start.getContext());
					break;
					}
			case R.id.game_showhistory:
				MenuAccess.playMeow(history.getContext());
				File Root = Environment.getExternalStorageDirectory(); 
				File f = new File(Root, "CatTrills_history.txt");
				Intent i = new Intent();
				i.setAction(android.content.Intent.ACTION_VIEW);
				i.setDataAndType(Uri.fromFile(f), "text/plain");
				startActivity(i);
				MenuAccess.playMeow(Start.getContext());
				break;
			}		

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.settings, menu);
		MenuItem pref = menu.findItem(R.id.action_prefs);
		MenuItem exit = menu.findItem(R.id.action_exit);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		item.setChecked(true);
		switch (item.getItemId())
		{
		case R.id.action_exit:
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();
			break; 
			//System.exit(1);   
		case R.id.action_prefs:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		} 

		return super.onOptionsItemSelected(item);
	}


	public boolean saveCheckedMenuItems(String name, Boolean MI) 
	{
		preferences.edit().putBoolean(name, MI).commit();
		return MI;
	}

}
