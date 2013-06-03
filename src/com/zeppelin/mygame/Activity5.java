package com.zeppelin.mygame;


import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity5 extends ListActivity {

	protected TextView choosePartner;
	protected String user;
	protected ListView lv;
	protected ListView l;
	protected List<String> partys;
	protected String name;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);
		choosePartner = (TextView)findViewById(R.id.game_choose);
		Source source = MainActivity.getSource();
		user = source.getUser();
		choosePartner.setText("Hi, "+ user + ", choose your partner!");
		l = (ListView)findViewById(android.R.id.list);
		getListView().setOnItemClickListener(itemListener);
		
		try {	
			List<String> partys = MainActivity.service.list();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, partys);
			l.setAdapter(adapter);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	 OnItemClickListener itemListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				name = parent.getItemAtPosition(position).toString();
				try {
					Source source = MainActivity.getSource();
					source.setSelectedUser(name);
					Toast.makeText(getApplicationContext(),	"Вы выбрали " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
					
					boolean res;
					//do {
						res = MainActivity.service.select(name);
					//} while (res != true);
					
				//	String response = MainActivity.service.getResponse();
					//if (response.contains("choosen")){
					Intent intent = new Intent(Activity5.this, Activity6.class); //do not work proper!!!
					startActivity(intent);
					Activity5.this.finish(); }
				//} 
			catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		};

}
