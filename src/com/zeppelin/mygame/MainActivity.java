package com.zeppelin.mygame;

import impl.CatTrillsAsyncClientServiceImpl;
import service.CatTrillsClientService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

	protected Button Start;
	protected Button history;
	public enum Category { HISTORYSAVE, HISTORYNOTSAVE, LANGUAGEENG, LANGUAGERUS, LANGUAGEUKR, SOUNDSON, SOUNDSOFF; }
	private Category mCategory = Category.HISTORYSAVE;


	public static Source source = new Source();
	public static CatTrillsClientService service = new CatTrillsAsyncClientServiceImpl();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Connect interface elements to properties
		Start = (Button)findViewById(R.id.game_start);
		history = (Button)findViewById(R.id.game_showhistory);

		// Setup ClickListeners
		Start.setOnClickListener(this);
		history.setOnClickListener(this);
	}

	public static Source getSource() {
		return source;
	}

	public static void setSource(Source source) {
		MainActivity.source = source;
	}

	@Override
	public void onClick(View v) {
		//	if (v == Start){
		try {
			switch (v.getId()) 
			{
			case R.id.game_start:
				service.connect();
				Intent intent = new Intent(MainActivity.this, Activity2.class);
				startActivity(intent);
				MainActivity.this.finish();

				break;
			case R.id.game_showhistory:
			}		
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// If you are using ActionBarSherlock, call getSupportMenuInflater() instead
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		MenuItem historySave = menu.findItem(R.id.history_save);
		MenuItem historyNotsave = menu.findItem(R.id.history_notsave);
		MenuItem languageEng = menu.findItem(R.id.language_eng);
		MenuItem languageRus = menu.findItem(R.id.language_rus);
		MenuItem languageUkr = menu.findItem(R.id.language_ukr);
		MenuItem soundsOn = menu.findItem(R.id.sounds_on);
		MenuItem soundsOff = menu.findItem(R.id.sounds_off);

		switch (mCategory)
		{
		case HISTORYSAVE: historySave.setChecked(true);	break;
		case HISTORYNOTSAVE: historyNotsave.setChecked(true);           break;
		case LANGUAGEENG:           languageEng.setChecked(true);           break;
		case LANGUAGERUS:           languageRus.setChecked(true);           break;
		case LANGUAGEUKR:           languageUkr.setChecked(true);           break;
		case SOUNDSON:           soundsOn.setChecked(true);           break;
		case SOUNDSOFF:           soundsOff.setChecked(true);           break;
		}

		return true;
	}
	
/*	public static BufferedWriter out;
    private void createFileOnDevice(Boolean append) throws IOException {
            /*
             * Function to initially create the log file and it also writes the time of creation to file.
             
            File Root = Environment.getExternalStorageDirectory();
            if(Root.canWrite()){
                 File  LogFile = new File(Root, "Log.txt");
                 FileWriter LogWriter = new FileWriter(LogFile, append);
                 out = new BufferedWriter(LogWriter);
                 Date date = new Date();
                 out.write("Logged at" + String.valueOf(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "\n"));
            }
        } 
*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		item.setChecked(true);

		switch (item.getItemId())
		{
		case R.id.history_save:            mCategory = Category.HISTORYSAVE; 
		
		
		
		
		break;
		case R.id.history_notsave: 
			mCategory = Category.HISTORYNOTSAVE; break;
		case R.id.language_eng: 
			mCategory = Category.LANGUAGEENG; break;
		case R.id.language_rus:  
			mCategory = Category.LANGUAGERUS; break;
		case R.id.language_ukr:  
			mCategory = Category.LANGUAGEUKR; break;
		case R.id.sounds_on:  
			mCategory = Category.SOUNDSON; break;
		case R.id.sounds_off: 
			mCategory = Category.SOUNDSOFF; break;
		}

		return super.onOptionsItemSelected(item);
	}

}
