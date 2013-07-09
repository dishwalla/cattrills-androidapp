package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends MenuAccess implements OnClickListener {

	protected Button Connect;
	protected Button Wait;
	protected TextView yourNameView;
	protected String user;
	protected String one;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity3);

		// Connect interface elements to properties
		Connect = (Button)findViewById(R.id.game_connect);
		Wait = (Button)findViewById(R.id.game_wait);
		yourNameView = (TextView)findViewById(R.id.game_name);
		Source source = MainActivity.getSource();
		user = source.getUser().toUpperCase();
		one = getString(R.string.string_hello);
		yourNameView.setText(one + user + "!");

		// Setup ClickListeners
		Connect.setOnClickListener(this);
		Wait.setOnClickListener(this);
	}
	
	//public void callIntent(View v){	
	public void onClick(View v) {
		try {	
			switch (v.getId()) 
			{
			case R.id.game_connect:
				MenuAccess.playMeow(Connect.getContext());
				try {
					Intent intent = new Intent(Activity3.this, Activity5.class);
					startActivity(intent);
					Activity3.this.finish();
					break;}
				catch (Exception e) {
					e.printStackTrace();
				}

			case R.id.game_wait:
				MenuAccess.playPurr(Wait.getContext());
				try {
					Intent intent2 = new Intent(Activity3.this, Activity4.class);
					startActivity(intent2);
					Activity3.this.finish();
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

}


