package com.zeppelin.mygame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Activity9 extends ListActivity  implements OnClickListener {

	protected TextView resultsMes;
	protected TextView quitPrompt;
	protected ListView entireResult;
	protected Button quitY;
	protected Button quitN;
	protected List<String> list;
	protected static String result;
	ArrayAdapter<String> adapter;
	public static BufferedWriter out;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity9);
		resultsMes = (TextView)findViewById(R.id.game_resultsMes);
		quitPrompt = (TextView)findViewById(R.id.game_quit);

		resultsMes.setText("Read Entire dialog!");
		quitPrompt.setText("Do you wanna quit?");

		quitY = (Button)findViewById(R.id.game_quity);
		quitN = (Button)findViewById(R.id.game_quitn);
		entireResult = (ListView)findViewById(android.R.id.list);
		getListView().setOnItemClickListener(itemListener);

		quitN.setOnClickListener(this);
		quitY.setOnClickListener(this);

		try {
			result = MainActivity.service.getEntireResult();
			list = Arrays.asList(result);

			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, list);
			entireResult.setAdapter(adapter);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	OnItemClickListener itemListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {

		}
	}; 


	@Override
	public void onClick(View v) {
		try {	
			//	String response = MainActivity.service.getResponse();
			switch (v.getId()) 
			{
			case R.id.game_quity:
				try {
					MainActivity.service.putString("y");
					MainActivity.service.putString("\n");
					String response = MainActivity.service.getResponse();
					Intent intent = new Intent(Activity9.this, MainActivity.class);
					startActivity(intent);
					Activity9.this.finish();
					break;}
				catch (Exception e) {
					e.printStackTrace();
				}

			case R.id.game_quitn:
				try {
					MainActivity.service.putString("n"); //do not works proper
					MainActivity.service.putString("\n");
					String response = MainActivity.service.getResponse();
					Intent intent = new Intent(Activity9.this, Activity3.class);
					startActivity(intent);
					Activity9.this.finish();
					break;}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	public static void saveResults(Boolean append) throws IOException{
		boolean flag = true;
		while (flag){
			String des = "y";
			if (des == "y"){
				try
				{
					String s = result;
			
					File Root = Environment.getExternalStorageDirectory();
					if(Root.canWrite()){
					File  LogFile = new File(Root, "Log.txt");
				//	 File  LogFile = new File("/"+ "data/app", "Log.txt");
						FileWriter LogWriter = new FileWriter(LogFile, append);
						out = new BufferedWriter(LogWriter);
						Date date = new Date();
						out.write("Logged at" + String.valueOf(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "\n"));
						//    writer = new FileWriter(path,true); 
						byte buf[]= result.getBytes();

						for (int i = 0; i < buf.length; i++){
							out.write(buf[i]);

						}
						out.write("\n\n"); 
						flag = false; 
					}
					/*    writer = new FileWriter(path,true); 
					byte buf[]= s.getBytes();

					for (int i = 0; i < buf.length; i++){
						writer.write(buf[i]);

					}
					writer.write("\n\n"); 
					flag = false; */
				}
				catch(Exception e){
					System.out.print(e.getMessage()); }
				finally
				{
					try{
						out.close();
						flag = false;
					}catch(IOException e)
					{System.out.print(e.getMessage()); }
				}
			}
			else if(des == "n"){
				flag = false;	
			}
		}
	}

}
