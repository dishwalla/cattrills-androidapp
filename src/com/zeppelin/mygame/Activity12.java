package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity12 extends Activity implements OnClickListener {

	protected TextView questionIs;
	protected EditText writeA;
	protected Button commit;
	//protected String question;
	protected String answer;
	String user;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity12);

		Source source = MainActivity.getSource();
		user = source.getSelectedUser();
		
		questionIs = (TextView)findViewById(R.id.game_thequestionis);
		try {
			answer =  MainActivity.service.getResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		questionIs.setText(answer);
		commit = (Button)findViewById(R.id.game_commit);
		writeA = (EditText)findViewById(R.id.game_writeanswer);

		//Source source = MainActivity.getSource();
		//question = source.getQuestion();
		commit.setOnClickListener(this);
	}
	@Override
	public void onClick(View v){

		try {	
			switch (v.getId()) 
			{
			case R.id.game_commit:
				//	String response = MainActivity.service.getResponse(); //The question is: , write your answer
				//if (response.contains("answer")){

				MainActivity.service.putString(writeA.getText().toString()); //we're writing our answer
				MainActivity.service.putString("\n");
				
				String response = MainActivity.service.getResponse(); 
				if (response.contains(user)){
					Intent intent4 = new Intent(Activity12.this, Activity9.class);
					startActivity(intent4);
					Activity12.this.finish();
					break;
				}
				else {
				Intent intent = new Intent(Activity12.this, Activity7.class);
				startActivity(intent);
				Activity12.this.finish();
				break;}
			}
			//}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}



