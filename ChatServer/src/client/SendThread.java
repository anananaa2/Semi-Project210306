package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class SendThread extends Thread {
	
//	ChatDAO cDAO = null;

	private Socket send_socket;

	@Override
	public void run() {

		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter sendWriter = new PrintWriter(send_socket.getOutputStream());
			String sendStr = "";
			boolean isFlag = false;

			System.out.print("아이디를 입력하십시오 : ");
			ChatClient.userID = bufReader.readLine();
			System.out.print("비밀번호를 입력하십시오 : ");
			ChatClient.userPassword = bufReader.readLine();

			sendWriter.println(ChatClient.userID + " 입장");
			sendWriter.flush();

			while (!isFlag) {
				sendStr = bufReader.readLine();

				if (sendStr.equals("exit")) {
					isFlag = true;
					break;
				}

				sendWriter.println(sendStr);
				sendWriter.flush();
			} ////////////////////////// end of while

			sendWriter.close();
			bufReader.close();
			send_socket.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("SendThread 예외 발생");
		} ////////////////////////////// end of try-catch
	}

	public void setSocket(Socket socket) {
		send_socket = socket;
	}
}
