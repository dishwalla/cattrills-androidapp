package com.zeppelin.mygame;


import java.util.List;

import android.app.ListActivity;
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
		//getListView().setOnItemClickListener(itemListener);
		
		try {	
			List<String> partys = MainActivity.service.list();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, partys);
			// getListView().setTextFilterEnabled(true);
			lv.setAdapter(adapter);
		//	getListView().setOnItemClickListener(itemListener);

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
								"�� ������� "
										+ parent.getItemAtPosition(position).toString(),
								Toast.LENGTH_SHORT).show();
						
					}
				};*/
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	/* OnItemClickListener itemListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<adapted> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getApplicationContext(),
						"�� ������� "
								+ parent.getItemAtPosition(position).toString(),
						Toast.LENGTH_SHORT).show();
				
			}
		};*/
		

}
