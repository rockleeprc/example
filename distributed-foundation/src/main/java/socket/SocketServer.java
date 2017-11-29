package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketServer {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("10.2.0.24", 9999);
//			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			while (true) {
//				String content = reader.readLine();
//				System.out.println(content);
//			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
