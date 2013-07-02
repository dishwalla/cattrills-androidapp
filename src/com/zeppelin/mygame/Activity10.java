package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity10 extends Activity implements OnClickListener {

	protected Button Connect;
	protected Button Wait;
	protected TextView rejected;
	protected String user;
	protected String one;
	protected String two;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity10);

		// Connect interface elements to properties
		Connect = (Button)findViewById(R.id.game_connect);
		Wait = (Button)findViewById(R.id.game_wait);
		rejected = (TextView)findViewById(R.id.game_rejected);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		one = getString(R.string.string_user);
		two = getString(R.string.string_reject);
		rejected.setText(one + user + two);

		// Setup ClickListeners
		Connect.setOnClickListener(this);
		Wait.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		try {	
			switch (v.getId()) 
			{
			case R.id.game_connect:
				try {
					Intent intent = new Intent(Activity10.this, Activity5.class);
					startActivity(intent);
					Activity10.this.finish();
					break;}
				catch (Exception e) {
					e.printStackTrace();
				}

			case R.id.game_wait:
				try {
					Intent intent2 = new Intent(Activity10.this, Activity4.class);
					startActivity(intent2);
					Activity10.this.finish();
					break;}
				catch (Exception e) {
					e.printStackTrace();
				}
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
}


