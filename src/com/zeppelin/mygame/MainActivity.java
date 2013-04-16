package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.content.Intent;


public class MainActivity extends Activity implements OnClickListener {

	protected Button Start;
	protected Button Settings;
	
	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Connect interface elements to properties
		Start = (Button)findViewById(R.id.game_start);
		Settings = (Button)findViewById(R.id.game_settings);

		// Setup ClickListeners
		Start.setOnClickListener(this);
		Settings.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
	//	if (v == Start){
			try {
			//	service.connect();
				switch (v.getId()) 
				{
				    case R.id.game_start:
				    	service.connect();
				        Intent intent = new Intent(MainActivity.this, Activity2.class);
				        startActivity(intent);
				        break;
			    }
				
				
			} 
	catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//	else if (v == Settings){

		//}
	//}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
