package com.zeppelin.mygame;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends MenuAccess implements OnClickListener{

	protected Button Done;
	protected EditText yourName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);

		Done = (Button)findViewById(R.id.game_done);
		yourName = (EditText)findViewById(R.id.game_setyouname);
		Done.setOnClickListener(this);		
	}

	public void onClick(View v) {

		try {	
			switch (v.getId()) 
			{
			case R.id.game_done:
				
				String response = MainActivity.service.getResponse();
				if (response.contains("Write")){

					MainActivity.service.putString(yourName.getText().toString());
					MainActivity.service.putString("\n");
					response = MainActivity.service.getResponse();
					if (response.contains("already")){
						Intent intent2 = new Intent(Activity2.this, CopyOfActivity2.class); 
						startActivity(intent2);
						Activity2.this.finish();
						MenuAccess.playMeow(Done.getContext());
					} 
					else {
						Intent intent2 = new Intent(Activity2.this, Activity3.class);
						//intent2.putExtra("username", yourName.getText().toString()); // в ключ username пихаем текст из текстового поля
						Source source = MainActivity.getSource();
						source.setUser(yourName.getText().toString());
						startActivity(intent2);
						Activity2.this.finish();
						MenuAccess.playMeow(Done.getContext());
					//	break;
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent2 = new Intent(Activity2.this, Activity2.class); 
			startActivity(intent2);
			Activity2.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}

