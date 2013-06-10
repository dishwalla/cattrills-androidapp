package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
		StandBy.setText("You are in Stand By mode..");
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
					response = MainActivity.service.getResponse();// You've been selected for game, would you participate? Y/N
						if (response.contains("participate")){
							Intent intent = new Intent(Activity4.this, Activity11.class);
							startActivity(intent);
							Activity4.this.finish();
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

}


