package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity6 extends Activity implements OnClickListener{

	protected Button StopWait;
	protected String user;
	protected TextView game_invite_sent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity6);
		
		game_invite_sent = (TextView)findViewById(R.id.game_invite_sent);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		game_invite_sent.setText("Your invite was sent to " + user + "!");
		StopWait = (Button)findViewById(R.id.game_stop_wait);
		StopWait.setOnClickListener(this);	
	}
	@Override
	public void onClick(View v) {
		try {				
			switch (v.getId()) 
			{
			case R.id.game_stop_wait:
				try{
					Intent intent = new Intent(Activity6.this, Activity3.class);
					startActivity(intent);
					Activity6.this.finish();
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