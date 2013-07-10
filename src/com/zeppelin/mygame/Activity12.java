package com.zeppelin.mygame;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity12 extends MenuAccess implements OnClickListener{

	protected Button exit;
	protected TextView noConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity12);

		noConnection = (TextView)findViewById(R.id.game_noconnection);
		exit = (Button)findViewById(R.id.game_exit);
		exit.setOnClickListener(this);
	}

	public void onClick(View v) {
		try {	
			switch (v.getId()) 
			{
			case R.id.game_exit:
				MenuAccess.playPurr(exit.getContext());
				android.os.Process.killProcess(android.os.Process.myPid());
				super.onDestroy();
				//break; 
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


