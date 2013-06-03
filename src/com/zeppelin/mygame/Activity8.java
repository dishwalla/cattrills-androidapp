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
	protected EditText writeQ;
	protected Button commit;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity8);
		
		questionIs = (TextView)findViewById(R.id.game_thequestionis);
		commit = (Button)findViewById(R.id.game_commit);
		writeQ = (EditText)findViewById(R.id.game_writequestion);

	}
	@Override
	public void onClick(View v){

		try {	
			switch (v.getId()) 
			{
			case R.id.game_writequestion:

				MainActivity.service.putString(writeQ.getText().toString());
				MainActivity.service.putString("\n");
				String response = MainActivity.service.getResponse();
				if (response.contains("is")){
					Intent intent = new Intent(Activity8.this, Activity6.class);
					startActivity(intent);
					Activity8.this.finish();
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}



