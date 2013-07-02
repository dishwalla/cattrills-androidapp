package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CopyOfActivity6 extends Activity implements OnClickListener{

	protected TextView invalidNumber;
	protected EditText questQuont;
	protected String user;
	protected Button commit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity6_2);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		questQuont = (EditText)findViewById(R.id.game_questquont);
		invalidNumber = (TextView)findViewById(R.id.game_invalidnumber);
		commit = (Button) findViewById(R.id.game_commit);

		commit.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		try {				
			switch (v.getId()) 
			{
			case R.id.game_commit:
				MainActivity.service.putString(questQuont.getText().toString());
				MainActivity.service.putString("\n"); 

				String response = MainActivity.service.getResponse(); //"Write your question:"
				if (response.contains("question")){
					Intent intent2 = new Intent(CopyOfActivity6.this, Activity7.class);
					startActivity(intent2);
					CopyOfActivity6.this.finish(); 
					Source source = MainActivity.getSource();
					source.setActivityChangeCount(Integer.parseInt(questQuont.getText().toString())*2-1);}
				else if (response.contains("valid")){
					Intent intent3 = new Intent(CopyOfActivity6.this, CopyOfActivity6.class); 
					startActivity(intent3);}
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