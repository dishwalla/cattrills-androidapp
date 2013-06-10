package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity11 extends Activity implements OnClickListener {

	protected Button accept;
	protected Button reject;
	protected TextView invite;
	protected String user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity11);

		// Connect interface elements to properties
		accept = (Button)findViewById(R.id.game_accept);
		reject = (Button)findViewById(R.id.game_reject);
		invite = (TextView)findViewById(R.id.game_youreinvited);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		//	invite.setText("User "+ user + " invited you to play the game");
		invite.setText("Some user invited you to play the game");
		// Setup ClickListeners
		accept.setOnClickListener(this);
		reject.setOnClickListener(this);
	}

	/* You've been selected for game, would you participate? Y/N
y
You've accept the game, there will be 2 questions
The question is: xff�, write your answer
gyhjg ghj
Write your question: 
fghf gfgh
The question is: �����, write your answer


You've been selected for game, would you participate? Y/N
n
Write command:*/

	public void onClick(View v) {
		try {	
			switch (v.getId()) 
			{
			case R.id.game_accept:
				try {
					MainActivity.service.putString("y");
					MainActivity.service.putString("\n");
					String response = MainActivity.service.getResponse(); //You've accept the game, there will be 2 questions
					Intent intent = new Intent(Activity11.this, Activity8.class);
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

