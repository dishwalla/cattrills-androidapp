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

	protected TextView game_invite_sent;
	protected String one;
	protected String two;
	protected TextView first;
	protected TextView second;
	protected EditText questQuont;
	protected Button Cancel;
	protected Button Done;
	protected String user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity6);
		Cancel = (Button)findViewById(R.id.game_cancel);
		Done = (Button)findViewById(R.id.game_done);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		first = (TextView)findViewById(R.id.game_invite_sent);
		second = (TextView)findViewById(R.id.game_invite_sent2);
		one = getString(R.string.string_user);
		two = getString(R.string.string_accept);
		game_invite_sent = (TextView)findViewById(R.id.game_invite_sentmain);
		game_invite_sent.setText(one + user + two);
		questQuont = (EditText)findViewById(R.id.game_questquont);

		Cancel.setOnClickListener(this);
		Done.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		try {				
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				Intent intent = new Intent(Activity6.this, Activity4.class);
				startActivity(intent);
				Activity6.this.finish();
				break;
			case R.id.game_done:
				MainActivity.service.putString(questQuont.getText().toString());
				MainActivity.service.putString("\n"); 

				String response = MainActivity.service.getResponse(); //"Write your question:"
				if (response.contains("question")){
					Intent intent2 = new Intent(Activity6.this, Activity7.class);
					startActivity(intent2);
					Activity6.this.finish(); 
					Source source = MainActivity.getSource();
					source.setActivityChangeCount(Integer.parseInt(questQuont.getText().toString())*2-1);}

				else if (response.contains("valid")){
					Intent intent3 = new Intent(Activity6.this, CopyOfActivity6.class); 
					startActivity(intent3);}
			}

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}