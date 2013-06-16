package com.zeppelin.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CopyOfActivity2_3 extends Activity implements OnClickListener{

	protected TextView invalidName;
	protected Button Cancel;
	protected Button Done;
	protected EditText yourName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2_3);

		// Connect interface elements to properties
		Cancel = (Button)findViewById(R.id.game_cancel);
		Done = (Button)findViewById(R.id.game_done);
		yourName = (EditText)findViewById(R.id.game_setyouname);
		invalidName = (TextView)findViewById(R.id.game_invalidname);
		invalidName.setText(getString(R.string.string_space));
		
		// Setup ClickListeners
		Cancel.setOnClickListener(this);
		Done.setOnClickListener(this);
	}

	public void onClick(View v) {

		try {	
			switch (v.getId()) 
			{
			case R.id.game_cancel:
				Intent intent = new Intent(CopyOfActivity2_3.this, MainActivity.class);
				startActivity(intent);
				CopyOfActivity2_3.this.finish();
				break;

			case R.id.game_done:
			//	String response = MainActivity.service.getResponse();
			//	if (response.contains("Write")){

					MainActivity.service.putString(yourName.getText().toString());
					MainActivity.service.putString("\n");
					String response = MainActivity.service.getResponse();
					if (response.contains("already")){
						Intent intent2 = new Intent(CopyOfActivity2_3.this, CopyOfActivity2_3.class); //do not work proper!!!
						startActivity(intent2);
						CopyOfActivity2_3.this.finish();
					} 
					else if (response.contains("space")){
						Intent intent2 = new Intent(CopyOfActivity2_3.this, CopyOfActivity2_3.class); 
						startActivity(intent2);
						break;
					}
					else {
						Intent intent2 = new Intent(CopyOfActivity2_3.this, Activity3.class);
						Source source = MainActivity.getSource();
						source.setUser(yourName.getText().toString());
						startActivity(intent2);
						CopyOfActivity2_3.this.finish();
						
					}
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

