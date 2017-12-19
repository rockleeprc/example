package chatroom.v1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 9999);
		OutputStream os = socket.getOutputStream();
		os.write("aa".getBytes());
		
		os.close();
		socket.close();
	}
}
