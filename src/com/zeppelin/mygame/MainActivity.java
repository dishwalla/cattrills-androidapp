package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import impl.CatTrillsClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

	protected Button Start;
	protected Button Settings;
	

	public static Source source = new Source();
	public static CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	
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

	public static Source getSource() {
		return source;
	}

	public static void setSource(Source source) {
		MainActivity.source = source;
	}

	@Override
	public void onClick(View v) {
	//	if (v == Start){
			try {
				switch (v.getId()) 
				{
				    case R.id.game_start:
				    	service.connect();
				        Intent intent = new Intent(MainActivity.this, Activity2.class);
				        startActivity(intent);
				        MainActivity.this.finish();
				       break;
				}		
			} 
	catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
