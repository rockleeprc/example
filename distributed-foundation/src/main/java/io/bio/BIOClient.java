package io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

public class BIOClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 9999);
		OutputStream os = socket.getOutputStream();
		for (int i = 0; i < 5; i++) {
			String send = (UUID.randomUUID().toString() + "\r\n");
			os.write(("hi server :" + send).getBytes());
		}
		// 必须close，否则Exception in thread "main" java.net.SocketException:
		// Connection reset
//		System.in.read();
//		os.close();
		socket.close();
	}

}
