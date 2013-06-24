package com.zeppelin.mygame;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApp extends Application

{ 

    protected static SharedPreferences preferences;
    protected static SharedPreferences mSettings;
    public static final String preferences_hists = "historys"; //key for history
	public static final String preferences_histns = "historyns"; //key for history
	
	//mSettings = getSharedPreferences(app_pref, Context.MODE_PRIVATE);
	
    public static SharedPreferences getmSettings() {
		return mSettings;
	}


	public static void setmSettings(SharedPreferences mSettings) {
		MyApp.mSettings = mSettings;
	}


	public static String getPreferencesHists() {
		return preferences_hists;
	}


	public static String getPreferencesHistns() {
		return preferences_histns;
	}


	public static void loadPreferences(Context context) {

    }


    public static void savePreferences(Context context) {
    	

      // save your preferences

    } 
}
