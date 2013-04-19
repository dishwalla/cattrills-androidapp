package impl;

import java.io.IOException;
import java.util.List;

import service.CatTrillsClientService;
import android.os.AsyncTask;

public class CatTrillsAsyncClientServiceImpl implements CatTrillsClientService {

	private CatTrillsClientService service = new CatTrillsClientServiceImpl(); 

	@Override
	public void connect() throws Exception {
		AsyncTask task = new AsyncTask(){
			@Override
			protected Object doInBackground(Object... arg0) {
				try {
					service.connect();
				} catch (Exception e) {
					e.printStackTrace();
					//return false;
				}
				return null;
			}
		};
		task.execute().get();
	}

	@Override
	public boolean sendYourName(final String name) throws Exception {
		//String name = "";
		AsyncTask task = new AsyncTask(){
			@Override
			protected Object doInBackground(Object... arg0) {
				try {
					service.sendYourName(name);
				//	return true;
				} catch (Exception e) {
					e.printStackTrace();
				//	return false;
				}
				return true;
			}
		};	
		task.execute().get();
		return false;
	}

	@Override
	public List<String> list() throws Exception {

		return null;
	}

	@Override
	public boolean select(String name) throws Exception {

		return false;
	}

	@Override
	public String getResponse() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putString(String str) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getEntireResult() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void goOn(String yn) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean saveResult(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void standBy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptGameOffer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectGameOffer() {
		// TODO Auto-generated method stub

	}



}
