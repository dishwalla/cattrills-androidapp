package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends Activity implements OnClickListener{

	protected Button Connect;
	protected Button Wait;
	protected TextView yourNameView;

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	//TextViev 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);

		// Connect interface elements to properties
		Connect = (Button)findViewById(R.id.game_connect);
		Wait = (Button)findViewById(R.id.game_wait);
		yourNameView = (TextView)findViewById(R.id.game_setyouname);
		String user = "";
		user = getIntent().getExtras().getString("username");
		yourNameView.setText("Hi, "+ user + "!");


		// Setup ClickListeners
		Connect.setOnClickListener(this);
		Wait.setOnClickListener(this);
	}

	public void onClick(View v) {


		try {	
			switch (v.getId()) 
			{
			case R.id.game_connect:
				String name = getIntent().getExtras().getString("username");
				service.sendYourName(name);
				//service.list();
				Intent intent = new Intent(Activity3.this, Activity5.class);
				startActivity(intent);
				break;
			case R.id.game_wait:
				Intent intent2 = new Intent(Activity3.this, Activity4.class);
				startActivity(intent2);
				break;
			}


		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


