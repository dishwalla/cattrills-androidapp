package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuAccess extends Activity {

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.settings, menu);
		MenuItem pref = menu.findItem(R.id.action_prefs);
		MenuItem exit = menu.findItem(R.id.action_exit);

		return true;
	}
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

	public static void playMeow(Context context){
		Source source = MainActivity.getSource();
		if (source.isSoundsOn()){
			MediaPlayer mp = MediaPlayer.create(context, R.raw.meow);  
			mp.start(); 
			while(mp.isPlaying()){
				mp.isLooping();
			}
		}
	}

	public static void playPurr(Context context){
		Source source = MainActivity.getSource();
		if (source.isSoundsOn()){
			MediaPlayer mp = MediaPlayer.create(context, R.raw.purr);  
			mp.start(); 
			while(mp.isPlaying()){
				mp.isLooping();
			}
		}
	}

}
