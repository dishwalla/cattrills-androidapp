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
import android.widget.Toast;

public class Activity4 extends Activity implements OnClickListener{

	protected Button Connect;
	protected Button checkIt;
	protected String user;
	protected TextView StandBy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity4);

		StandBy = (TextView)findViewById(R.id.game_stand_by);
		Connect = (Button)findViewById(R.id.game_connect);
		checkIt = (Button)findViewById(R.id.game_checkit);
		Connect.setOnClickListener(this);
		checkIt.setOnClickListener(this);

	}

	public void onClick(View v) {

		
		try {	
			switch (v.getId()) 
			{
			case R.id.game_connect:
				try{
					Intent intent = new Intent(Activity4.this, Activity5.class);
					startActivity(intent);
					Activity4.this.finish();
					break;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			case R.id.game_checkit:
				try {
					MainActivity.service.putString(" ");
					MainActivity.service.putString("\n");
					String response = MainActivity.service.getResponse(); // Unrecognized command
					response = MainActivity.service.getResponse();// You've been selected for game, would you participate? NAME
						if (response.contains("participate")){
							Source source = MainActivity.getSource();
							source.setInviter(getInviter(response));
							Intent intent = new Intent(Activity4.this, Activity11.class);
							startActivity(intent);
							Activity4.this.finish();
						}
						else {
							Toast.makeText(getApplicationContext(),	getString(R.string.string_stillnot), Toast.LENGTH_SHORT).show();
						}

				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getInviter(String str){
		int posOfBe = str.indexOf("parti");
		StringBuilder sb = new StringBuilder();
		for (int i=(posOfBe+13); i<str.length(); i++){
			if (str.charAt(i) != ' '){
				sb.append(str.charAt(i));}
			else break;
		}
		return sb.toString();
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


