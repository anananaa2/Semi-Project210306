package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {
	
	private Socket r_socket;
	
	@Override
	public void run() {
		super.run();
		
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(r_socket.getInputStream()));
			
			String receiveStr = "";
			String[] split;
			
			while(true) {
				receiveStr = bufReader.readLine();
				
				split = receiveStr.split("]");
				
				if (split.length >= 2 && split[0].equals(ChatClient.userID)) {
					continue;
				}
				System.out.println(receiveStr);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setSocket(Socket socket) {
		r_socket = socket;
	}
}
