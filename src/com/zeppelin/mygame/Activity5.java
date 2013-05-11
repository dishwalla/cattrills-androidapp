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

public class Activity5 extends Activity implements OnClickListener {

	protected TextView choosePartner;
	//protected TextView selection;
	protected String user;
	protected ListView lv;
	protected List<String> partys;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);

		choosePartner = (TextView)findViewById(R.id.game_choose);
		Source source = MainActivity.getSource();
		user = source.getUser();
		choosePartner.setText("Hi, "+ user + ", choose your partner!");
		lv = (ListView)findViewById(R.id.game_list);
		//((View) partys).setOnClickListener(this);
		
		//lv.setOnClickListener(this);

		try {	
			/*MainActivity.service.putString("list");
			String response = MainActivity.service.getResponse();
			String [] arr= response.split("\\s+");
			//String [] arr= response.split(" ");
			List<String> partys = Arrays.asList(arr);*/
			
			List<String> partys = MainActivity.service.list();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, partys);
			lv.setAdapter(adapter);
		
		/*	String response = MainActivity.service.getResponse();
			if (response.contains("Write")){
				Intent intent2 = new Intent(Activity5.this, Activity2.class); //do not work proper!!!
				startActivity(intent2);
			}
			else {Intent intent3 = new Intent(Activity5.this, Activity2.class); //do not work proper!!!
			startActivity(intent3);}*/

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
	}
	@Override
	public void onClick(View v){
		//	(ListView l, View v, int position, long id) 
		//	super.onListItemClick(l, v, position, id);
		//	String text = " the client" + partys[position]+ "is free ";
		//	selection.setText(text);
		/*try {	
			if (v.getId() == R.id.game_list){
				Intent intent = new Intent(Activity5.this, Activity7.class);
				startActivity(intent);
				Activity5.this.finish();
			}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}*/
		}

}
