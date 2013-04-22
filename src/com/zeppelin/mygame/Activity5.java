package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;

import java.util.List;

import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Activity5 extends Activity implements OnClickListener{

	protected TextView yourNameView;
	//protected Button Invite;
	protected ListView partys;

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();
	//TextViev 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);

		// Connect interface elements to properties
		//Invite = (Button)findViewById(R.id.game_invite);
		yourNameView = (TextView)findViewById(R.id.game_setyouname);
		String user = "";
		user = getIntent().getExtras().getString("username");
		yourNameView.setText(user + ", choose your partner:");
		
		   List<String> adapter = new List<String>(this,
	                R.layout.rowlayout, R.id.label, values);
	       
		   setListAdapter(adapter);
		
		partys = (ListView)findViewById(R.id.game_list);
		partys.setAdapter(adapter);
		//partys.setAdapter(adapter);
		
		   // Использование собственного шаблона
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.rowlayout, R.id.label, values);
        setListAdapter(adapter);
    }
		
		// Setup ClickListeners
		//Invite.setOnClickListener(this);
		partys.setOnClickListener(this);
	}


	/*/   

	    @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	        String item = (String) getListAdapter().getItem(position);
	        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	    }*/



	public void onClick(View v) {

		try {
			switch (v.getId()) 
			{
			case R.id.game_list:
				MainActivity.service.list();
				Intent intent = new Intent(Activity5.this, Activity4.class);
				startActivity(intent);
				break;
			} 
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
}


