package impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import service.CatTrillsClientService;

public class CatTrillsClientServiceImpl implements CatTrillsClientService{

	protected InputStream is;
	protected OutputStream os;
	PrintWriter wr;
	BufferedReader br; 
	protected Socket serverSocket;

	public CatTrillsClientServiceImpl(){}

	public void connect() throws Exception{
		//InetAddress addr = InetAddress.getLocalHost();
		InetAddress addr = InetAddress.getByName("192.168.1.100");
		int port = 1234;
		this.serverSocket =	new Socket(addr, port);
		this.is = serverSocket.getInputStream();
		this.os = serverSocket.getOutputStream();
		wr = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);	
		br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	}

	@Override
	public boolean sendYourName(String name) throws Exception{
		putString(name);
		String response = getResponse();
		if (response.contains("already exists") || response.contains("space")){
			return false;
		}
		return true;
	}

	@Override
	public List<String> list() throws Exception{
		putString("list");
		putString("\n");
		String response = getResponse();
		String [] arr= response.split("\\s+");
		getResponse();		// to eat Write command
		List<String> newList = new ArrayList<String>(Arrays.asList(arr));
		java.util.Collections.sort(newList);
		return newList;
		 
	}

	@Override
	public boolean select(String name) throws Exception {
		putString("select");
		putString("\n");
		putString(name);
		putString("\n");
		String response = getResponse(); //reads "Client have been choosen"
		if (response.contains("It's yours name") || response.contains("is busy") || response.contains("no such") || response.contains("Client rejected")){
			return false;
		}
		return true;
	}

	@Override
	public String getResponse() throws Exception {
		String response = br.readLine();
		return response;
	}

	@Override
	public String getEntireResult() throws IOException, InterruptedException, ExecutionException {
		String response = "";
		String justRead = br.readLine();
		while(!(justRead.contains("Write") || justRead.contains("Do you want"))){
			response = response.concat(justRead + "\n");
			justRead = br.readLine();
		}
		return response;
		
	}
	
	@Override
	public void putString(String str) throws Exception{
		wr.write(str);
		wr.flush();
	}

}
