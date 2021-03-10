package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {

	private Socket rec_socket;

	@Override
	public void run() {

		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(rec_socket.getInputStream()));

			String receiveStr = "";
			String[] split;
			boolean isFlag = false;

			while (!isFlag) {
				receiveStr = bufReader.readLine();

				split = receiveStr.split("]");

				if (split.length >= 2 && split[0].equals(ChatClient.userID)) {
					continue;
				}
				System.out.println(receiveStr);
			}

		} catch (IOException e) {
			System.out.println("연결이 종료되었습니다.");
		}
	}

	public void setSocket(Socket socket) {
		rec_socket = socket;
	}
}
