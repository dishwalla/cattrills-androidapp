package service;

import java.io.IOException;
import java.util.List;

public interface CatTrillsClientService {
	
	void connect() throws Exception;

	void sendYourName(String name) throws Exception;
	
	List<String> list() throws Exception;
	
	boolean select(String name)  throws Exception;
	
	String getResponse() throws Exception;
	
	void putString(String str) throws Exception;
	
	String getEntireResult() throws IOException;
	
	void goOn(String yn) throws Exception;
	
	boolean saveResult(String str);//undone	
	
	void standBy();//undone
	
	void acceptGameOffer();//undone
	
	void rejectGameOffer();//undone
	
}
