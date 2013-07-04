package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;

import java.io.File;
import java.util.Locale;
import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
	//	PendingIntent intent;
	private SharedPreferences preferences; //for language
	private Locale locale; 
	private String lang;
	protected Button Start;
	protected Button history;
	
	public static Source source = new Source();

	public static CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();

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
		try {
			switch (v.getId()) 
			{
			case R.id.game_start:
				service.connect();
				Intent intent = new Intent(MainActivity.this, Activity2.class);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.game_showhistory:
				File Root = Environment.getExternalStorageDirectory(); 
				File f = new File(Root, "CatTrills_history.txt");
				Intent i = new Intent();
				i.setAction(android.content.Intent.ACTION_VIEW);
				i.setDataAndType(Uri.fromFile(f), "text/plain");
				startActivity(i);
				break;
			}		

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		//	MenuInflater inflater = getMenuInflater();
		//	inflater.inflate(R.menu.main, menu);
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
		/*		case R.id.history_save:          
			hCategory = Category.HISTORYSAVE; 
			Source source = MainActivity.getSource();
			source.setSaveHistory(true);
			if(item.isChecked())
			{Editor editor = preferences.edit();
			editor.putBoolean("historys", true);
			editor.commit();}
			return true;

		/*	case R.id.language_def: 
			lCategory = Category.LANGUAGEDEF;
			Locale locale4 = new Locale("default");
			Locale.setDefault(locale4);
			Configuration config4 = new Configuration();
			config4.locale = locale;
			getBaseContext().getResources().updateConfiguration(config4, null); 
			//	System.exit(1);
			return true;
		case R.id.language_eng: 
			lCategory = Category.LANGUAGEENG; 
			Locale locale = new Locale("en");
			Locale.setDefault(locale);
			Configuration config = new Configuration();
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config, null); 
			//	System.exit(1);
			return true;
		case R.id.language_ukr:  
			lCategory = Category.LANGUAGEUKR; 
			Locale locale3 = new Locale("ua");
			Locale.setDefault(locale3);
			Configuration config3 = new Configuration();
			config3.locale = locale3;
			getBaseContext().getResources().updateConfiguration(config3, null); 
			//	editor = mSettings.edit();
			//	editor.putString(APP_PREFERENCES_LANG, "ua");
			//	editor.commit();
			//	Toast.makeText(getApplicationContext(),	"Restart!" Toast.LENGTH_SHORT).show();
			//	intent = PendingIntent.getActivity(getApplicationContext(), 0,
			//		new Intent(getIntent()), 0);
			//	AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			//	mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, intent);
			//	System.exit(1);
			return true;
*/
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
