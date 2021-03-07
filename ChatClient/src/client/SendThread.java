package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread {

	private Socket send_socket;
	
	@Override
	public void run() {
		super.run();
		
		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter sendWriter = new PrintWriter(send_socket.getOutputStream());
			String sendStr = "";
			
			System.out.print("사용할 아이디를 입력하십시오 : ");
			ChatClient.userID = bReader.readLine();
			
			sendWriter.println(ChatClient.userID + " 입장");
			sendWriter.flush();
			
			while (true) {
				sendStr = bReader.readLine();
				
				if (sendStr.equals("exit"))
					break;
				
				sendWriter.println(sendStr);
				sendWriter.flush();
			}//////////////////////////end of while
			
			sendWriter.close();
			bReader.close();
			send_socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}//////////////////////////////end of try-catch
	}
	
	public void setSocket(Socket socket) {
		send_socket = socket;
	}
}
