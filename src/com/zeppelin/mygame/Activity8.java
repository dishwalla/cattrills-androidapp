package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity8 extends Activity implements OnClickListener {

	protected TextView questionIs;
	protected EditText writeA;
	protected Button commit;
	//protected String question;
	protected String answer;
	String user;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity8);

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
				String response = MainActivity.service.getResponse(); // write your que: (if inverse)
				Source source = MainActivity.getSource(); 
				Integer acc = source.getActivityChangeCount();
				
				if (acc == 1){
					Intent intent4 = new Intent(Activity8.this, Activity9.class);
					startActivity(intent4);
					Activity8.this.finish();
				}
				else {
					source.setActivityChangeCount(--acc);
					Intent intent = new Intent(Activity8.this, Activity7.class);
					startActivity(intent);
					Activity8.this.finish();
				}
				break;
			}
			//}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}



