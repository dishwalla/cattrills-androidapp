package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CopyOfActivity6 extends Activity implements OnClickListener{

	protected TextView invalidNumber;
	protected EditText questQuont;
	protected Button Cancel;
	protected Button Done;
	protected String user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity6_2);
		Cancel = (Button)findViewById(R.id.game_cancel);
		Done = (Button)findViewById(R.id.game_done);
		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		questQuont = (EditText)findViewById(R.id.game_questquont);
		invalidNumber = (TextView)findViewById(R.id.game_invalidnumber);

		Cancel.setOnClickListener(this);
		Done.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		try {				
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				Intent intent = new Intent(CopyOfActivity6.this, Activity4.class);
				startActivity(intent);
				CopyOfActivity6.this.finish();
				break;
			case R.id.game_done:
				MainActivity.service.putString(questQuont.getText().toString());
				MainActivity.service.putString("\n"); 

				String response = MainActivity.service.getResponse(); //"Write your question:"
				if (response.contains("question")){
					Intent intent2 = new Intent(CopyOfActivity6.this, Activity7.class);
					startActivity(intent2);
					CopyOfActivity6.this.finish(); 
					Source source = MainActivity.getSource();
					source.setActivityChangeCount(Integer.parseInt(questQuont.getText().toString())*2-1);}
				else if (response.contains("valid")){
					Intent intent3 = new Intent(CopyOfActivity6.this, CopyOfActivity6.class); 
					startActivity(intent3);}
			}

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}