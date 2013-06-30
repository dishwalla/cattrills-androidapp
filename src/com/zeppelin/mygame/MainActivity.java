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
//	private SharedPreferences mSettings;
	private Locale locale; 
	private String lang;
	//	private static final String preferences_hists = "historys"; //key for history
	//	private static final String preferences_histns = "historyns"; //key for history
	//	private static final String preferences_sounds = "sounds"; //key for sounds
	//	private static final String preferences_soundns = "soundns"; //key for sounds
	//	private static final String app_pref = "settings";
	private static final int RESULT_SETTINGS = 1;
	protected Button Start;
	protected Button history;
	public enum Category { HISTORYSAVE, HISTORYNOTSAVE, LANGUAGEDEF, LANGUAGEENG, LANGUAGERUS, LANGUAGEUKR, SOUNDSON, SOUNDSOFF, EXIT; }
	private Category hCategory = Category.HISTORYSAVE;
	private Category lCategory = Category.LANGUAGEDEF;
	private Category sCategory = Category.SOUNDSON;

	public static Source source = new Source();

	public static CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	public static String MY_PREF = "MY_PREF";

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
	//	mSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
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
		//	showUserSettings();

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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
	    MenuItem mi = menu.add(0, 1, 0, "Preferences");
	//     mi.setIntent(new Intent(this, SettingsActivity.class));
	  //    return super.onCreateOptionsMenu(menu);
	      
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

		/*	if (mSettings.contains(preferences_hists)) { 
			historySave.setChecked(mSettings.getBoolean(preferences_hists, false) );
			historyNotsave.setChecked(mSettings.getBoolean(preferences_histns, true) );
		}

		else if (mSettings.contains(preferences_histns)) { 
		//	historyNotsave.setChecked(mSettings.getBoolean(preferences_histns, false));
				//	historySave.setChecked(false);
				historyNotsave.setChecked(true);
				historySave.setChecked(false);
	} 
		 */

		switch (hCategory)
		{
		case HISTORYSAVE: 
			/*		for (int i = 0; i < menu.size(); ++i) {
				MenuItem mi = menu.getItem(i);
				// check the Id as you wish
				if (mi.getItemId() == R.id.history_save) {
					historySave.setChecked(preferences.getBoolean(preferences_hists, false)); 
				}
			}*/
			historySave.setChecked(true);           break;
			//historySave.setChecked(preferences.getBoolean("historys", false));	
		case HISTORYNOTSAVE: 
			historyNotsave.setChecked(true); break;
			//	historyNotsave.setChecked(preferences.getBoolean("historyns", false));
			/*	for (int i = 0; i < menu.size(); ++i) {
				MenuItem mi = menu.getItem(i);
				// check the Id as you wish
				if (mi.getItemId() == R.id.history_notsave) {
					historyNotsave.setChecked(preferences.getBoolean(preferences_histns, false)); 
				}
			}*/
			//	default:
			//		historySave.setChecked(true);
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
		//	case SOUNDSON:           soundsOn.setChecked(true);           break;
		//	case SOUNDSOFF:           soundsOff.setChecked(true);           break;
		case SOUNDSON:           soundsOn.setChecked(true);           break;
		case SOUNDSOFF:           soundsOff.setChecked(true);           break;
		//	default:
		//	soundsOn.setChecked(true);
		// return super.onCreateOptionsMenu(menu);
		}

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
				/*		item.setChecked(false);
			} else {
				item.setChecked(true);
			}*/
			{Editor editor = preferences.edit();
			editor.putBoolean("historys", true);
			editor.commit();}
			/*	else {Editor editor = preferences.edit();
			editor.putBoolean(preferences_hists, false);
			editor.commit();*/
			return true;
		case R.id.history_notsave: 
			hCategory = Category.HISTORYNOTSAVE;
			source = MainActivity.getSource();
			source.setSaveHistory(false);
			if(item.isChecked())
				/*		item.setChecked(false);
			} else {
				item.setChecked(true);
			}*/
			{ Editor editor = preferences.edit();
			editor.putBoolean("historyns", true);
			editor.commit();}
			/*	else {Editor editor = preferences.edit();
			editor.putBoolean(preferences_histns, false);
			editor.commit();
			}*/
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
			//	Editor editor = preferences.edit();
			//	editor.putBoolean("sound", true);
			//	editor.commit();
			return true;
		case R.id.sounds_off: 
			sCategory = Category.SOUNDSOFF; 
			//	editor = preferences.edit();
			//	editor.putBoolean("soundns", true);
			//	editor.commit();
			return true;
		case R.id.action_exit:
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();
			break; 
			//System.exit(1);
		} 
		//return true;
		return super.onOptionsItemSelected(item);
	}

	public boolean saveCheckedMenuItems(String name, Boolean MI) 
	{
		preferences.edit().putBoolean(name, MI).commit();
		return MI;
	}

}
