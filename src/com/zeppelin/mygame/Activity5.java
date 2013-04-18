package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Activity5 extends Activity implements OnClickListener{

	protected TextView yourNameView;
	protected Button Invite;
	protected ListView partys;

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	//TextViev 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);

		// Connect interface elements to properties
		Invite = (Button)findViewById(R.id.game_invite);
		yourNameView = (TextView)findViewById(R.id.game_setyouname);
		String user = "";
		user = getIntent().getExtras().getString("username");
		yourNameView.setText(user + ", choose your partner:");


		// Setup ClickListeners
		Invite.setOnClickListener(this);
	}

	public void onClick(View v) {


		try {	
			switch (v.getId()) 
			{
			case R.id.game_invite:
				//service.list();
				//  Intent intent = new Intent(MainActivity.this, Activity2.class);
				// startActivity(intent);
				break;
			}


		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


