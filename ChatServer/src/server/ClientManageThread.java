package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManageThread extends Thread {

	private Socket manage_socket = null;
	private String manage_id = null;

	@Override
	public void run() {

		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(manage_socket.getInputStream()));
			// SendThread로부터 받은 스트림 msg에 저장
			String msg = "";
			boolean isFlag = false;
			String[] split = null;

			while (!isFlag) {
				msg = bufReader.readLine();

				if (msg == null) {
					System.out.println(manage_id + " 퇴장");

					for (int i = 0; i < ChatServer.messageList.size(); ++i) {
						ChatServer.messageList.get(i).println(manage_id + " 퇴장");
						ChatServer.messageList.get(i).flush();
					} ///////////////////// end of for
					break;
				} ///////////////////////// end of if
				
				else if (manage_id == null) {
					split = msg.split(" "); // 처음 접속했을 경우 '아이디', '입장'
					manage_id = split[0]; // 일단 처음에는 아이디가 저장됨
					split[0] = "";
				}

				if (split.length == 2 && split[0].equals(manage_id)) {
					System.out.println(manage_id + "입장"); // 서버에 쏴주는 입장메시지

					for (int i = 0; i < ChatServer.messageList.size(); ++i) { // 클라에게 쏴주는 입장메시지
						ChatServer.messageList.get(i).println(manage_id + " 입장");
						ChatServer.messageList.get(i).flush();
					} //////////////////// end of for
					continue;
				} //////////////////////// end of if

				for (int i = 0; i < ChatServer.messageList.size(); ++i) {
					ChatServer.messageList.get(i).println(manage_id + "] " + msg);
					ChatServer.messageList.get(i).flush();
				} //////////////////////// end of for
			} //////////////////////////// end of while

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
