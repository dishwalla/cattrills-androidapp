package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity4 extends Activity implements OnClickListener{

	protected Button Connect;
	//protected String user;

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	//TextViev 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity4);
		//yourNameView = (TextView)findViewById(R.id.game_setyouname);
	//	String user = "";
	//	user = getIntent().getExtras().getString("username");
		Connect = (Button)findViewById(R.id.game_connect);
		Connect.setOnClickListener(this);
	}

	public void onClick(View v) {

		try {	
			switch (v.getId()) 
			{
			case R.id.game_connect:
				//MainActivity.service.sendYourName(user);
				Intent intent = new Intent(Activity4.this, Activity6.class);
				startActivity(intent);
				Activity4.this.finish();
				break;
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}


