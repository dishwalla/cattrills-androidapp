package com.zeppelin.mygame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
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

		resultsMes.setText(getString(R.string.string_dialog));
		quitPrompt.setText(getString(R.string.string_quit));

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
			saveResults(result, true);

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
			switch (v.getId()) 
			{
			case R.id.game_quity:
				try {
					MainActivity.service.putString("y");
					MainActivity.service.putString("\n");
					MenuAccess.playPurr(quitY.getContext());
					android.os.Process.killProcess(android.os.Process.myPid());
					super.onDestroy();
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
					MenuAccess.playMeow(quitN.getContext());
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


	public static void saveResults(String s, Boolean append) throws IOException{
		Source source = MainActivity.getSource();
		if (source.isSaveHistory()){
			try
			{
				File Root = Environment.getExternalStorageDirectory(); 
				File LogFile = new File(Root, "CatTrills_history.txt");
				//		FileWriter LogWriter = new FileWriter(LogFile, append);
				//		out = new BufferedWriter(LogWriter);
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LogFile, append),"UTF-8"));
				Date date = new Date();
				out.write("Logged at: " + String.valueOf(date) + "\n");
				byte[] cp = s.getBytes();
				String sn = new String(cp, "UTF-8");
				out.write(sn);
				out.write("\n\n"); 
			}
			catch(Exception e){
				System.out.print(e.getMessage()); }
			finally
			{
				try{
					out.close();
				}catch(IOException e)
				{System.out.print(e.getMessage()); }
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		//	MenuInflater inflater = getMenuInflater();
		//	inflater.inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.settings, menu);
		MenuItem pref = menu.findItem(R.id.action_prefs);
		MenuItem exit = menu.findItem(R.id.action_exit);

		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		item.setChecked(true);
		switch (item.getItemId())
		{
		case R.id.action_exit:
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();
			break; 
			//System.exit(1);   
		case R.id.action_prefs:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		} 

		return super.onOptionsItemSelected(item);
	}
}
