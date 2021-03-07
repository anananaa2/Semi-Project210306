package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
	
	public static ArrayList<PrintWriter> messageList;
	
	public static void main(String[] args) {
		
		messageList = new ArrayList<PrintWriter>();
		
		try {
			ServerSocket serv_socket = new ServerSocket(9234);
			System.out.println(serv_socket.getLocalPort() + " 소켓 오픈");
			
			while(true) {
				Socket cli_socket = serv_socket.accept();
				
				boolean isCon = cli_socket.isConnected();
				
				if (isCon)
					System.out.println("클라이언트 연결됨");
				
				ClientManageThread cli_thread = new ClientManageThread();
				cli_thread.setSocket(cli_socket);
				
				messageList.add(new PrintWriter(cli_socket.getOutputStream()));
				
				cli_thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}//////////////////////////////////end of try-catch
	}//////////////////////////////////////end of main
}
