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
			os.write(UUID.randomUUID().toString().getBytes());
		}
		os.close();
		socket.close();
	}

}
