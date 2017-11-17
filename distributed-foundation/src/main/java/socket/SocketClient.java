package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("10.2.0.24", 9999);
			PrintWriter write = new PrintWriter(socket.getOutputStream());
			write.println("客户端发送消息");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
