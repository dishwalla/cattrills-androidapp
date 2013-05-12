package com.zeppelin.mygame;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Activity7 extends Activity implements OnClickListener {

	protected TextView choosePartner;
	protected TextView selection;
	protected String user;
	ListView lv;
	protected List<String> partys;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity7);

		choosePartner = (TextView)findViewById(R.id.game_choose);
		Source source = MainActivity.getSource();
		user = source.getUser();
		choosePartner.setText("Hi, "+ user + ", choose your partner!");

		//((View) partys).setOnClickListener(this);
		//	lv.setOnClickListener(this);
		//	List<String> partys;
		//try {
		//String  response = MainActivity.service.getResponse();
		//if (response.contains("been")){
		try {
			//List<String> partysK = MainActivity.service.list();
			//String X = MainActivity.service.getResponse();
			MainActivity.service.putString(" ");
			
			List<String> partys = MainActivity.service.list();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, partys);
			ListView lv = (ListView)findViewById(R.id.game_list);
			lv.setAdapter(adapter);
			//} 
			//}
			//catch (Exception e) {
			//e.printStackTrace();
			//}
			//}

		} catch (Exception e1) {
			e1.printStackTrace();
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
		Intent intent = new Intent(Activity7.this, Activity6.class);
		startActivity(intent);
		Activity7.this.finish();
	}

}


