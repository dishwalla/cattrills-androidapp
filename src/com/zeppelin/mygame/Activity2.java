package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends Activity implements OnClickListener{

	protected Button Cancel;
	protected Button Done;
	protected EditText yourName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);

		Cancel = (Button)findViewById(R.id.game_cancel);
		Done = (Button)findViewById(R.id.game_done);
		yourName = (EditText)findViewById(R.id.game_setyouname);

		Cancel.setOnClickListener(this);
		Done.setOnClickListener(this);
	}

	public void onClick(View v) {

		try {	
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				Intent intent = new Intent(Activity2.this, Activity2.class);
				startActivity(intent);
				Activity2.this.finish();
				break;

			case R.id.game_done:
				String response = MainActivity.service.getResponse();
				if (response.contains("Write")){

					MainActivity.service.putString(yourName.getText().toString());
					MainActivity.service.putString("\n");
					response = MainActivity.service.getResponse();
					if (response.contains("already")){
						Intent intent2 = new Intent(Activity2.this, CopyOfActivity2.class); 
						startActivity(intent2);
						Activity2.this.finish();
					} 
					else {
						Intent intent2 = new Intent(Activity2.this, Activity3.class);
						//intent2.putExtra("username", yourName.getText().toString()); // в ключ username пихаем текст из текстового поля
						Source source = MainActivity.getSource();
						source.setUser(yourName.getText().toString());
						startActivity(intent2);
						Activity2.this.finish();
						break;
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent2 = new Intent(Activity2.this, Activity2.class); 
			startActivity(intent2);
			Activity2.this.finish();
			//	 onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/*	@Override
	public void onBackPressed() {
		System.exit(1); or
		android.os.Process.killProcess(android.os.Process.myPid()); kills server
		super.onDestroy();
		return;
		}*/

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
}

