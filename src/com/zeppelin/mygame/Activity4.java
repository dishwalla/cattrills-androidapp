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
	//protected TextView standBy;

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	//TextViev 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity4);

		Connect = (Button)findViewById(R.id.game_connect);
		Connect.setOnClickListener(this);
	}

	public void onClick(View v) {

		try {	
			switch (v.getId()) 
			{
			case R.id.game_connect:

				Intent intent = new Intent(Activity4.this, Activity5.class);
				startActivity(intent);
				break;
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

