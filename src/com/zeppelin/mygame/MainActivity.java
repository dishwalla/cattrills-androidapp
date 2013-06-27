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
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;


public class MainActivity extends Activity implements OnClickListener {
//	PendingIntent intent;
	private SharedPreferences preferences; //for language
	private SharedPreferences mSettings;
	private Locale locale; 
	private String lang;
	private static final String preferences_hists = "historys"; //key for history
	private static final String preferences_histns = "historyns"; //key for history
	private static final String preferences_sounds = "sounds"; //key for sounds
	private static final String preferences_soundns = "soundns"; //key for sounds
	private static final String app_pref = "settings";
	
	protected Button Start;
	protected Button history;
	public enum Category { HISTORYSAVE, HISTORYNOTSAVE, LANGUAGEDEF, LANGUAGEENG, LANGUAGERUS, LANGUAGEUKR, SOUNDSON, SOUNDSOFF, EXIT; }
	private Category hCategory = Category.HISTORYSAVE;
	private Category lCategory = Category.LANGUAGEDEF;
	private Category sCategory = Category.SOUNDSON;

	public static Source source = new Source();
	//public static MyApp myApp = new MyApp();
	public static CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
	//	mSettings = getSharedPreferences(app_pref, Context.MODE_PRIVATE);
		lang = preferences.getString("lang", "default");	
		if (lang.equals("default")) {lang=getResources().getConfiguration().locale.getCountry();}
		locale = new Locale(lang);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Connect interface elements to properties
		Start = (Button)findViewById(R.id.game_start);
		history = (Button)findViewById(R.id.game_showhistory);
		// Setup ClickListeners
		Start.setOnClickListener(this);
		history.setOnClickListener(this);
	

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
//	public static MyApp getMyApp(){
	//	return myApp;
	//}
	//public static void SetMyApp(MyApp myApp){
	//MainActivity.myApp = myApp;
	//}
	
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
		mSettings = getSharedPreferences(app_pref, Context.MODE_PRIVATE);
	
		MenuItem historySave = menu.findItem(R.id.history_save);
		MenuItem historyNotsave = menu.findItem(R.id.history_notsave);
		MenuItem languageDef = menu.findItem(R.id.language_def);
		MenuItem languageEng = menu.findItem(R.id.language_eng);
		MenuItem languageRus = menu.findItem(R.id.language_rus);
		MenuItem languageUkr = menu.findItem(R.id.language_ukr);
		MenuItem soundsOn = menu.findItem(R.id.sounds_on);
		MenuItem soundsOff = menu.findItem(R.id.sounds_off);
	
	//	mSettings = getSharedPreferences(app_pref, 0);
	//	if(mSettings.contains("hs")) {
			// boolean answerA = mSettings.getBoolean("hs", false);
		  //  nicknameText.setText(mSettings.getString(APP_PREFERENCES_NAME, ""));
		//	historySave.setChecked(true);
		//}
		
	//if (mSettings.getBoolean(preferences_hists, true))
	
		
		if (mSettings.contains(preferences_hists)) { 
			historySave.setChecked(mSettings.getBoolean(preferences_hists, false) );
			historyNotsave.setChecked(mSettings.getBoolean(preferences_histns, true) );
		}

	//	historySave.setChecked(mSettings.getString("checked_radio", ""));
		else if (mSettings.contains(preferences_histns)) { 
		//	historyNotsave.setChecked(mSettings.getBoolean(preferences_histns, false));
				//	historySave.setChecked(false);
				historyNotsave.setChecked(true);
				historySave.setChecked(false);
	}
	/*
		switch (hCategory)
		{
		case HISTORYSAVE: historySave.setChecked(mSettings.getBoolean(preferences_hists, false));	
		case HISTORYNOTSAVE: historyNotsave.setChecked(mSettings.getBoolean(preferences_histns, false)); 
	//	default:
          //  return super.onCreateOptionsMenu(menu);
		}
		switch (lCategory) {
		case LANGUAGEDEF:           languageDef.setChecked(true);           break;
		case LANGUAGEENG:           languageEng.setChecked(true);           break;
		case LANGUAGERUS:           languageRus.setChecked(true);           break;
		case LANGUAGEUKR:           languageUkr.setChecked(true);           break;
	//	default:
           // return super.onCreateOptionsMenu(menu);
		}
		switch (sCategory) {
		case SOUNDSON:           soundsOn.setChecked(true);           break;
		case SOUNDSOFF:           soundsOff.setChecked(true);           break;
	//	 default:
	           // return super.onCreateOptionsMenu(menu);
		}
		if (mSettings.contains(preferences_hists)) { 
			historySave.setChecked(true);
		}
		else if (mSettings.contains(preferences_histns)){
			historyNotsave.setChecked(true);
		}*/
		return true;
		
	}

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
			if(item.isChecked())
				//saveCheckedMenuItems(preferences_hists, true);
			 //}
			{Editor editor = mSettings.edit();
		//	historySave.setChecked(true);
			editor.putBoolean(preferences_hists, true);
		//	editor.putBoolean(preferences_histns, false);
			editor.commit();}
			else {Editor editor = mSettings.edit();
			editor.putBoolean(preferences_hists, false);
			editor.commit();
			}
			return true;
		case R.id.history_notsave: 
			hCategory = Category.HISTORYNOTSAVE;
			source = MainActivity.getSource();
			source.setSaveHistory(false);
			if(item.isChecked())
			{
				//saveCheckedMenuItems(preferences_histns, true);
			 //}
			Editor editor = mSettings.edit();
			editor.putBoolean(preferences_histns, true);
			//editor.putBoolean(preferences_hists, false);
			editor.commit();}
			else {Editor editor = mSettings.edit();
			editor.putBoolean(preferences_histns, false);
			editor.commit();
			}
			return true;
		case R.id.language_def: 
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
		case R.id.language_rus:  
			lCategory = Category.LANGUAGERUS; 
			Locale locale2 = new Locale("ru");
			Locale.setDefault(locale2);
			Configuration config2 = new Configuration();
			config2.locale = locale2;
			getBaseContext().getResources().updateConfiguration(config2, null); 
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
		case R.id.sounds_on:  
			sCategory = Category.SOUNDSON; 
			Editor editor = mSettings.edit();
			editor.putBoolean("sound", true);
			editor.commit();
			return true;
		case R.id.sounds_off: 
			sCategory = Category.SOUNDSOFF; 
			editor = mSettings.edit();
			editor.putBoolean("soundns", true);
			editor.commit();
			return true;
		case R.id.action_exit:
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();
			break; 
			//System.exit(1);
		}

		return super.onOptionsItemSelected(item);
	}

	public boolean saveCheckedMenuItems(String name, Boolean MI) 
	{
	   mSettings.edit().putBoolean(name, MI).commit();
	   return MI;
	}
	
}
