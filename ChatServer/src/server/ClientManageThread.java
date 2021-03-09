package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManageThread extends Thread {
	
	private Socket manage_socket;
	private String manage_id;
	
	@Override
	public void run() {
		super.run();
		
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(manage_socket.getInputStream()));
			
			String msg;
			
			while(true) {
				msg = bufReader.readLine();
				
//				System.out.println(msg);
				
				if (msg == null) {
					System.out.println(manage_id + " 퇴장");
					
					for (int i = 0; i < ChatServer.messageList.size(); ++i) {
						ChatServer.messageList.get(i).println(manage_id + " 퇴장");
						ChatServer.messageList.get(i).flush();
					}/////////////////////end of for
					break;
				}/////////////////////////end of if
				
				String[] split = msg.split(" ");
				
				if (split.length == 2 && split[0].equals(manage_id)) {
					manage_id = split[1];
					System.out.println("아니 시발 여길 가긴 함?? " + split[1]);
					
					///////////////////여기까지 했음//////////////////
					System.out.println(manage_id + " 입장");
					
					for (int i = 0; i < ChatServer.messageList.size(); ++i) {
						ChatServer.messageList.get(i).println(manage_id + " 입장");
						ChatServer.messageList.get(i).flush();
					}////////////////////end of for
					continue;
				}////////////////////////end of if
				
				for (int i = 0; i < ChatServer.messageList.size(); ++i) {
					ChatServer.messageList.get(i).println(manage_id + "] " + msg);
					ChatServer.messageList.get(i).flush();
				}////////////////////////end of for
			}////////////////////////////end of while
			
			ChatServer.messageList.remove(new PrintWriter(manage_socket.getOutputStream()));
			manage_socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setSocket(Socket socket) {
		manage_socket = socket;
	}
}
