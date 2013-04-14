package com.zeppelin.mygame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity2 extends Activity implements OnClickListener{

	protected Button Connect;
	protected Button Wait;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);

		// Connect interface elements to properties
		Connect = (Button)findViewById(R.id.game_connect);
		Wait = (Button)findViewById(R.id.game_wait);

		// Setup ClickListeners
		Connect.setOnClickListener(this);
		Wait.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
