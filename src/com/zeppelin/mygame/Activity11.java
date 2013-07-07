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

public class Activity11 extends MenuAccess implements OnClickListener {

	protected Button accept;
	protected Button reject;
	protected TextView invite;
	protected String user;
	protected String us;
	protected String inv;
	protected String one;
	protected String two;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity11);

		accept = (Button)findViewById(R.id.game_accept);
		reject = (Button)findViewById(R.id.game_reject);
		invite = (TextView)findViewById(R.id.game_youreinvited);
		Source source = MainActivity.getSource();
		user = source.getInviter();
		us = getString(R.string.string_user);
		inv = getString(R.string.invite_foryou);
		one = getString(R.string.string_willbe);
		two = getString(R.string.string_quest);
		invite.setText(us + user + inv);
		//	invite.setText("Some user invited you to play the game");
		accept.setOnClickListener(this);
		reject.setOnClickListener(this);
	}

	public int getAcc(String str){
		int posOfBe = str.indexOf("be");
		StringBuilder sb = new StringBuilder();
		for (int i=(posOfBe+3); i<str.length(); i++){
			if (str.charAt(i) != ' '){
				sb.append(str.charAt(i));}
			else break;
		}
		int k = Integer.parseInt(sb.toString());
		Source source = MainActivity.getSource();
		source.setIterations(k);
		return k;
	}

	public void onClick(View v) {
		try {	
			switch (v.getId()) 
			{
			case R.id.game_accept:
				try {
					MainActivity.service.putString("y");
					MainActivity.service.putString("\n");
					String response = MainActivity.service.getResponse(); //You've accept the game, there will be 2 questions
					Source source = MainActivity.getSource();
					int acc = getAcc(response);
					source.setActivityChangeCount(acc*2-1);
					Intent intent = new Intent(Activity11.this, Activity8.class);
					 int iter = source.getIterations();
				     Toast.makeText(getApplicationContext(), one + iter + two,  Toast.LENGTH_SHORT).show();
					startActivity(intent);
					Activity11.this.finish();
					break;}
				catch (Exception e) {
					e.printStackTrace();
				}

			case R.id.game_reject:
				try {
					MainActivity.service.putString("n");
					MainActivity.service.putString("\n");
					Intent intent2 = new Intent(Activity11.this, Activity4.class);
					startActivity(intent2);
					Activity11.this.finish();
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


