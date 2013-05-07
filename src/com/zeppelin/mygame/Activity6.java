package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity6 extends Activity implements OnClickListener{

	protected Button StopWait;
protected String user;
	
	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity6);
		user = getIntent().getExtras().getString("username");
		StopWait = (Button)findViewById(R.id.game_stop_wait);

		StopWait.setOnClickListener(this);	
	}
	@Override
	public void onClick(View v) {
		try {				
			switch (v.getId()) 
			{
			case R.id.game_stop_wait:
				Intent intent = new Intent(Activity6.this, Activity3.class);
				startActivity(intent);
				Activity6.this.finish();
				break;
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}