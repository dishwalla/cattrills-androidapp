package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;

import java.util.List;

import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Activity5 extends Activity implements OnClickListener {

	protected TextView yourNameView;
	protected TextView selection;
	//ListView list1;
	ListView lv;
	protected List<String> partys;

	protected CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);

		yourNameView = (TextView)findViewById(R.id.game_setyouname);
		String user = "";
		user = getIntent().getExtras().getString("username");
		yourNameView.setText(user + ", choose your partner:");
		//((View) partys).setOnClickListener(this);
		lv.setOnClickListener(this);
	//	List<String> partys;
		try {
			List<String> partys = MainActivity.service.list();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, partys); 
			//	list1 = (ListView) findViewById(R.id.list);
			ListView lv = (ListView)findViewById(R.id.game_list);
			lv.setAdapter(adapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	/*ListView.setOnItemClickListener(new onItemClickListener(){
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//super.onListItemClick(l, v, position, id);
		String text = " the client" + partys[position]+ "is free ";
		selection.setText(text);	
			}
*/
		/*   List<String> adapter = new List<String>(this,
	                R.layout.rowlayout, R.id.label, values);

		   setListAdapter(adapter);

		partys = (ListView)findViewById(R.id.game_list);
		partys.setAdapter(adapter);
		//partys.setAdapter(adapter);

    }
		// Setup ClickListeners
		//Invite.setOnClickListener(this);
		partys.setOnClickListener(this);*/
	}
	@Override
	public void onClick(View v){
	//	(ListView l, View v, int position, long id) 
		//	super.onListItemClick(l, v, position, id);
		//	String text = " the client" + partys[position]+ "is free ";
		//	selection.setText(text);	
				Intent intent = new Intent(Activity5.this, Activity6.class);
				startActivity(intent);
			
		}

}


