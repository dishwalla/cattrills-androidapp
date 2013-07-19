package com.zeppelin.mygame;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncClass extends AsyncTask {

	@Override
	public Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	  public void onProgressUpdate(Integer... values) {
	        Log.d("MyAsyncTask","onProgressUpdate - " + values[0]);
	    }

	    public void onPostExecute(String result) {
	        Log.d("MyAsyncTask","onPostExecute " + result);
	    }

}
