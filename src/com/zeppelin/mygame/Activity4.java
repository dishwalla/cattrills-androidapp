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
	protected String user;
	protected TextView StandBy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity4);

		Connect = (Button)findViewById(R.id.game_connect);
		Connect.setOnClickListener(this);
		StandBy = (TextView)findViewById(R.id.game_stand_by);
		StandBy.setText("You are in Stand By mode..");

	}

	public void onClick(View v) {

		try {	
			switch (v.getId()) 
			{
			case R.id.game_connect:
				try{
					Intent intent = new Intent(Activity4.this, Activity6.class);
					startActivity(intent);
					Activity4.this.finish();
					break;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}


