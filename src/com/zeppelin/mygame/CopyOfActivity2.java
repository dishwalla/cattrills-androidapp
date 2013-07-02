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

public class CopyOfActivity2 extends Activity implements OnClickListener{

	protected TextView invalidName;
	protected Button Cancel;
	protected Button Done;
	protected EditText yourName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2_2);

		// Connect interface elements to properties
		Cancel = (Button)findViewById(R.id.game_cancel);
		Done = (Button)findViewById(R.id.game_done);
		yourName = (EditText)findViewById(R.id.game_setyouname);
		invalidName = (TextView)findViewById(R.id.game_invalidname);
		invalidName.setText(getString(R.string.string_namebusy));
		
		// Setup ClickListeners
		Cancel.setOnClickListener(this);
		Done.setOnClickListener(this);
	}

	public void onClick(View v) {

		try {	
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				Intent intent = new Intent(CopyOfActivity2.this, CopyOfActivity2.class);
				startActivity(intent);
				CopyOfActivity2.this.finish();
				break;

			case R.id.game_done:
		
					MainActivity.service.putString(yourName.getText().toString());
					MainActivity.service.putString("\n");
					String response = MainActivity.service.getResponse();
					if (response.contains("already")){
						Intent intent2 = new Intent(CopyOfActivity2.this, CopyOfActivity2.class); //do not work proper!!!
						startActivity(intent2);
						CopyOfActivity2.this.finish();
					} 
					else {
						Intent intent2 = new Intent(CopyOfActivity2.this, Activity3.class);
						Source source = MainActivity.getSource();
						source.setUser(yourName.getText().toString());
						startActivity(intent2);
						CopyOfActivity2.this.finish();
						
					}
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

