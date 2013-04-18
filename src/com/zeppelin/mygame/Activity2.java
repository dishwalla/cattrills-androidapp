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

public class Activity2 extends Activity implements OnClickListener{

	//protected Button Connect;
	//protected Button Wait;
	protected Button Cancel;
	protected Button Done;
	public static String Name;
	protected EditText yourName;
	

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	//TextViev 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);

		// Connect interface elements to properties
		Cancel = (Button)findViewById(R.id.game_cancel);
		Done = (Button)findViewById(R.id.game_done);
		yourName = (EditText)findViewById(R.id.game_setyouname);

		// Setup ClickListeners
		Cancel.setOnClickListener(this);
		Done.setOnClickListener(this);
	}


	public void onClick(View v) {

		try {	
	
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				Intent intent = new Intent(Activity2.this, MainActivity.class);
				
				startActivity(intent);
				break;

			case R.id.game_done:
				Intent intent2 = new Intent(Activity2.this, Activity3.class);
				intent2.putExtra("username", yourName.getText().toString()); // в ключ username пихаем текст из текстового поля
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


