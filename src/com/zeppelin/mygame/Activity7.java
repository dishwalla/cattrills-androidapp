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
	//protected String user;
	protected String anotherUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity7);
		commit = (Button) findViewById(R.id.game_commit);
		writeQ = (EditText) findViewById(R.id.game_writequestion);
		commit.setOnClickListener(this);
		Source source = MainActivity.getSource();
		//user = source.getUser();
		anotherUser = source.getSelectedUser();
	}

	@Override
	public void onClick(View v) {
				//	Map<String, String> gamePares = MainActivity.gamePares;
				//		if (gamePares.containsKey(user) && gamePares.containsKey(anotherUser)){
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
							MenuAccess.playMeow(commit.getContext());
						} else {
							source.setActivityChangeCount(--acc);
							Intent intent2 = new Intent(Activity7.this, Activity8.class);
							startActivity(intent2);
							Activity7.this.finish();
							MenuAccess.playMeow(commit.getContext());
						}
						break;
					}
				}
				catch (Exception e) {
					e.printStackTrace();}
	/*try {
		if (MainActivity.service.permanentCheck(anotherUser)) {
				Intent intent2 = new Intent(Activity7.this, Activity12.class);
				startActivity(intent2);
				Activity7.this.finish();
				MenuAccess.playPurr(commit.getContext());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

}


}
