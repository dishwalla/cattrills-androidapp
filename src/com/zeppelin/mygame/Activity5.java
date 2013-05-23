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
	//protected TextView selection;
	protected String user;
	protected ListView lv;
	protected ListView l;
	protected List<String> partys;
	protected String name;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);
	//	setContentView(R.id.game_list);
		choosePartner = (TextView)findViewById(R.id.game_choose);
		Source source = MainActivity.getSource();
		user = source.getUser();
		choosePartner.setText("Hi, "+ user + ", choose your partner!");
		lv = (ListView)findViewById(R.id.game_list);
		//((View) partys).setOnClickListener(this);
		l = (ListView)findViewById(android.R.id.list);
		//lv.setOnClickListener((OnClickListener) this);
		getListView().setOnItemClickListener(itemListener);
		
		try {	
			List<String> partys = MainActivity.service.list();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, partys);
			// getListView().setTextFilterEnabled(true);
			lv.setAdapter(adapter);
			l.setAdapter(adapter);
			//getListView().setOnItemClickListener(itemListener);

		/*	ListView.setOnItemClickListener(new onItemClickListener(){
				@Override
				protected void onListItemClick(ListView l, View v, int position, long id) {
					//super.onListItemClick(l, v, position, id);
					String text = " the client" + partys[position]+ "is free ";
					selection.setText(text);	
				}
			}*/
			
		/*	 OnItemClickListener itemListener = new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<adapted> parent, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						Toast.makeText(
								getApplicationContext(),
								"Вы выбрали "
										+ parent.getItemAtPosition(position).toString(),
								Toast.LENGTH_SHORT).show();
						
					}
				};*/
			
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
					//MainActivity.service.select(name);
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
	//getListView().setOnItemClickListener(itemListener);

}
