package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity6 extends Activity implements OnClickListener{

	//protected Button StopWait;
	protected Button Cancel;
	protected Button Done;
	protected String user;
	protected TextView game_invite_sent;
	protected EditText questQuont;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity6);
		Cancel = (Button)findViewById(R.id.game_cancel);
		Done = (Button)findViewById(R.id.game_done);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		game_invite_sent = (TextView)findViewById(R.id.game_invite_sent);
		game_invite_sent.setText("User " + user + " accepted the game!");
		questQuont = (EditText)findViewById(R.id.game_questquont);

		Cancel.setOnClickListener(this);
		Done.setOnClickListener(this);
		//StopWait = (Button)findViewById(R.id.game_stop_wait);
		//StopWait.setOnClickListener(this);	

	}
	@Override
	public void onClick(View v) {
		try {				
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				Intent intent = new Intent(Activity6.this, Activity5.class);
				startActivity(intent);
				Activity6.this.finish();
				break;
			case R.id.game_done:
				MainActivity.service.putString(questQuont.getText().toString());
				MainActivity.service.putString("\n");
				//String response = MainActivity.service.getResponse();
				//if (response.contains("question")){
					//response = MainActivity.service.getResponse();
					Intent intent2 = new Intent(Activity6.this, Activity7.class); //do not work proper!!!
					startActivity(intent2);
					break;
				}
			//}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}