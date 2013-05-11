package impl;

import java.io.IOException;
import java.util.List;

import service.CatTrillsClientService;
import android.os.AsyncTask;

public class CatTrillsAsyncClientServiceImpl implements CatTrillsClientService {

	private CatTrillsClientService service = new CatTrillsClientServiceImpl(); 
	//protected boolean res;
	@Override
	public void connect() throws Exception {
		AsyncTask task = new AsyncTask(){
			@Override
			protected Object doInBackground(Object... arg0) {
				try {
					service.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		task.execute().get();
	}

	@Override
	public boolean sendYourName(final String name) throws Exception {
		AsyncTask task = new AsyncTask(){
			boolean res;
			protected Object doInBackground(Object... arg0){
				try {
					res = service.sendYourName(name);
					return res;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}
		};
		boolean R = (Boolean) task.execute().get();
		return R;
	}

	@Override
	public List<String> list() throws Exception {
		AsyncTask task = new AsyncTask(){
			List<String> list;
			protected List<String> doInBackground(Object... params) {
				try {
					list = service.list();
					return list;
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
		};
		List<String> lst = (List<String>) task.execute().get();
		return lst;
	}

	@Override
	public String getResponse() throws Exception {

		AsyncTask task = new AsyncTask() {
			String res;
			protected String doInBackground(Object...params) {
				try {
					res = service.getResponse();
					return res;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}
		};
	
	String h = (String) task.execute().get();
	return h;
	}


	@Override
	public boolean select(String name) throws Exception {

		return false;
	}

	@Override
	public void putString(final String str) throws Exception {
		AsyncTask task = new AsyncTask(){
			@Override
			protected Object doInBackground(Object... arg0) {
				try {
					service.putString(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		task.execute().get();

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
