package client;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {
	
	public static String userID = "";
	public static String userPassword = "";

	public static void main(String[] args)  {
		try {
			
			Socket cli_socket = new Socket("121.139.85.156", 9234);
			
			ReceiveThread rec_thread = new ReceiveThread();
			rec_thread.setSocket(cli_socket);
			
			SendThread send_thread = new SendThread();
			send_thread.setSocket(cli_socket);
			
			rec_thread.start();
			send_thread.start();
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
