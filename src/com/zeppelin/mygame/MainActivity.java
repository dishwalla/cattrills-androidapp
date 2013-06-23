package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;

import java.io.File;
import java.util.Locale;

import service.CatTrillsClientService;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
	PendingIntent intent;
	private SharedPreferences preferences;
	private Locale locale;
	//	private Locale locale2 = new Locale("ru");
	//	private Locale locale3 = new Locale("ua"); 
	private String lang;
	private String hist;
	private String sound;
	//	private Locale locale = null;

	protected Button Start;
	protected Button history;
	public enum Category { HISTORYSAVE, HISTORYNOTSAVE, LANGUAGEENG, LANGUAGERUS, LANGUAGEUKR, SOUNDSON, SOUNDSOFF, EXIT; }
	private Category hCategory = Category.HISTORYSAVE;
	private Category lCategory = Category.LANGUAGEENG;
	private Category sCategory = Category.SOUNDSON;


	public static Source source = new Source();
	public static CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Connect interface elements to properties
		Start = (Button)findViewById(R.id.game_start);
		history = (Button)findViewById(R.id.game_showhistory);
		// Setup ClickListeners
		Start.setOnClickListener(this);
		history.setOnClickListener(this);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		lang = preferences.getString("lang", "default");	
		hist = preferences.getString("hist", "default");
		sound = preferences.getString("sound", "default");

		if (lang.equals("default")) {lang=getResources().getConfiguration().locale.getCountry();}
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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		MenuItem historySave = menu.findItem(R.id.history_save);
		MenuItem historyNotsave = menu.findItem(R.id.history_notsave);
		MenuItem languageEng = menu.findItem(R.id.language_eng);
		MenuItem languageRus = menu.findItem(R.id.language_rus);
		MenuItem languageUkr = menu.findItem(R.id.language_ukr);
		MenuItem soundsOn = menu.findItem(R.id.sounds_on);
		MenuItem soundsOff = menu.findItem(R.id.sounds_off);
		//	MenuItem exit = menu.findItem(R.id.action_exit);

		switch (hCategory)
		{
		case HISTORYSAVE: historySave.setChecked(true);	break;
		case HISTORYNOTSAVE: historyNotsave.setChecked(true);           break;
		}
		switch (lCategory) {
		case LANGUAGEENG:           languageEng.setChecked(true);           break;
		case LANGUAGERUS:           languageRus.setChecked(true);           break;
		case LANGUAGEUKR:           languageUkr.setChecked(true);           break;
		}
		switch (sCategory) {
		case SOUNDSON:           soundsOn.setChecked(true);           break;
		case SOUNDSOFF:           soundsOff.setChecked(true);           break;
		}
		return true;
	}

	/*	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		locale = new Locale(lang);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);     
	}	*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		item.setChecked(true);

		switch (item.getItemId())
		{
		case R.id.history_save:          
			hCategory = Category.HISTORYSAVE; 
			Source source = MainActivity.getSource();
			source.setSaveHistory(true);
			return true;
			//break;
		case R.id.history_notsave: 
			hCategory = Category.HISTORYNOTSAVE;
			source = MainActivity.getSource();
			source.setSaveHistory(false);
			return true;
			//break;
		case R.id.language_eng: 
			lCategory = Category.LANGUAGEENG; 
			Locale locale = new Locale("en");
			Locale.setDefault(locale);
			Configuration config = new Configuration();
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config, null); 
			return true;
		case R.id.language_rus:  
			lCategory = Category.LANGUAGERUS; 
			Locale locale2 = new Locale("ru");
			Locale.setDefault(locale2);
			Configuration config2 = new Configuration();
			config2.locale = locale2;
			getBaseContext().getResources().updateConfiguration(config2, null); 
			return true;
		case R.id.language_ukr:  
			lCategory = Category.LANGUAGEUKR; 
			Locale locale3 = new Locale("ua");
			Locale.setDefault(locale3);
			Configuration config3 = new Configuration();
			config3.locale = locale3;
			getBaseContext().getResources().updateConfiguration(config3, null); 
			//	Toast.makeText(getApplicationContext(),	"Restart!" Toast.LENGTH_SHORT).show();
			intent = PendingIntent.getActivity(getApplicationContext(), 0,
					new Intent(getIntent()), 0);
			AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, intent);
			System.exit(1);
			return true;

		case R.id.sounds_on:  
			sCategory = Category.SOUNDSON; 
			//break;
			return true;
		case R.id.sounds_off: 
			sCategory = Category.SOUNDSOFF; 
			//break;
			return true;
		case R.id.action_exit:
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();
			break; 
		}

		return super.onOptionsItemSelected(item);
	}
	/*	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		if (locale != null)
		{
			newConfig.locale = locale;
			Locale.setDefault(locale);
			getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
		}
	}*/


}
