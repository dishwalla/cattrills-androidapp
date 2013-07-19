package com.zeppelin.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CopyOfActivity6 extends MenuAccess implements OnClickListener{

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
					source.setActivityChangeCount(Integer.parseInt(questQuont.getText().toString())*2-1);
					MenuAccess.playMeow(commit.getContext());}
				else if (response.contains("valid")){
					Intent intent3 = new Intent(CopyOfActivity6.this, CopyOfActivity6.class); 
					startActivity(intent3);
					CopyOfActivity6.this.finish();
					MenuAccess.playMeow(commit.getContext());}
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent2 = new Intent(CopyOfActivity6.this, CopyOfActivity6.class); 
			startActivity(intent2);
			CopyOfActivity6.this.finish();
			//	 onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}