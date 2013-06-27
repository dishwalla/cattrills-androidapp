package com.zeppelin.mygame;


import java.util.Iterator;
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
	protected String one;
	protected String two;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);
		choosePartner = (TextView)findViewById(R.id.game_choose);
		Source source = MainActivity.getSource();
		user = source.getUser().toUpperCase();
		one = getString(R.string.string_hello);
		two = getString(R.string.string_choosepart);
		choosePartner.setText(one + user + two);
		l = (ListView)findViewById(android.R.id.list);
		getListView().setOnItemClickListener(itemListener);

		try {	
			List<String> partys = MainActivity.service.list();
			
			Iterator iter = partys.iterator();
			while(iter.hasNext()){
				String next = iter.next().toString();
				if(next.contains(user))
				iter.remove();
				}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, partys);
			l.setAdapter(adapter);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		/*for(String g : partys){
			if (g.contains(user)){
				partys.remove(g);
			}
		}
		for (int i=0; i<=partys.size()-1; i++) {
			if (partys.get(i).equalsIgnoreCase(user)){
				partys.remove(i);
			}
		}*/

	}

	OnItemClickListener itemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {
			name = parent.getItemAtPosition(position).toString();
			try {
				Source source = MainActivity.getSource();
				source.setSelectedUser(name);
				Toast.makeText(getApplicationContext(),	"Вы выбрали " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

				boolean res;
				res = MainActivity.service.select(name);

				String response = MainActivity.service.getResponse(); //"How many questions will be there in the game?" or "Client rejected the game, chose another partner!"
				if (response.contains("many")){

					Intent intent = new Intent(Activity5.this, Activity6.class); 
					startActivity(intent);
					Activity5.this.finish(); 
				}

				else{ //"Client rejected the game, chose another partner!" or "he is busy"
					Intent intent2 = new Intent(Activity5.this, Activity10.class); 
					startActivity(intent2);
					Activity5.this.finish();}
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	};

}
