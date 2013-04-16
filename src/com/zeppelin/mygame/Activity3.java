package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity3 extends Activity implements OnClickListener{

	protected Button Connect;
	protected Button Wait;
	protected TextView yourName;

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	//TextViev 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);

		// Connect interface elements to properties
		//Connect = (Button)findViewById(R.id.game_connect);
		//Wait = (Button)findViewById(R.id.game_wait);
		Connect = (Button)findViewById(R.id.game_connect);
		Wait = (Button)findViewById(R.id.game_wait);
		yourName = (EditText)findViewById(R.id.game_setyouname);

		// Setup ClickListeners
		//Connect.setOnClickListener(this);
		//Wait.setOnClickListener(this);
		Connect.setOnClickListener(this);
		Wait.setOnClickListener(this);
		}


		public void onClick(View v) {
		/*

						try {	
						//	Name = (yourName.getText().toString());
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				//service.list();
				//  Intent intent = new Intent(MainActivity.this, Activity2.class);
				// startActivity(intent);
				break;
			case R.id.game_done:
				// Intent intent = new Intent(MainActivity.this, Activity2.class);
				//  startActivity(intent);
				Intent intent = new Intent(Activity3.this, Activity3.class);
		        startActivity(intent);
		        break;
				//break;
			}


		} 
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
			
			}
	

