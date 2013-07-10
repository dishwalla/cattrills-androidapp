package com.zeppelin.mygame;

import java.util.Map;

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

public class Activity6 extends MenuAccess implements OnClickListener{

	protected TextView game_invite_sent;
	protected String one;
	protected String two;
	protected EditText questQuont;
	protected Button commit;
	protected String user;
	protected String myself;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity6);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		myself = source.getUser();
		one = getString(R.string.string_user);
		two = getString(R.string.string_accept);
		game_invite_sent = (TextView)findViewById(R.id.game_invite_sentmain);
		game_invite_sent.setText(one + user + two);
		questQuont = (EditText)findViewById(R.id.game_questquont);
		commit = (Button) findViewById(R.id.game_commit);
		commit.setOnClickListener(this);
		Map<String, String> gamePares = MainActivity.gamePares;
		gamePares.put(user, myself);
		gamePares.put(myself, user);

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
					Intent intent2 = new Intent(Activity6.this, Activity7.class);
					startActivity(intent2);
					Activity6.this.finish(); 
					MenuAccess.playMeow(commit.getContext());
					Source source = MainActivity.getSource();
					source.setActivityChangeCount(Integer.parseInt(questQuont.getText().toString())*2-1);}

				else if (response.contains("valid")){
					Intent intent3 = new Intent(Activity6.this, Activity6.class); 
					startActivity(intent3);
					MenuAccess.playMeow(commit.getContext());}
			}

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}