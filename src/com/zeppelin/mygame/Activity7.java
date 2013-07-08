package com.zeppelin.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Activity7 extends MenuAccess implements OnClickListener {

	protected EditText writeQ;
	protected Button commit;
	protected String question;
	protected String user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity7);
		commit = (Button) findViewById(R.id.game_commit);
		writeQ = (EditText) findViewById(R.id.game_writequestion);

		commit.setOnClickListener(this);

		Source source = MainActivity.getSource();
		user = source.getUser();
	}

	@Override
	public void onClick(View v) {

		try {
			switch (v.getId()) {
			case R.id.game_commit:

				MainActivity.service.putString(writeQ.getText().toString()); 
				MainActivity.service.putString("\n");

				Source source = MainActivity.getSource();
				Integer acc = source.getActivityChangeCount();

				if (acc == 1) {
					Intent intent = new Intent(Activity7.this, Activity9.class);
					startActivity(intent);
					Activity7.this.finish();
				} else {
					source.setActivityChangeCount(--acc);
					Intent intent2 = new Intent(Activity7.this, Activity8.class);
					startActivity(intent2);
					Activity7.this.finish();
				}
				break;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
