package chatroom.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			Socket accept = serverSocket.accept();
			// 调用is.Read()时,如果client没有数据,会引起阻塞
			InputStream is = accept.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				System.out.println(new String(buf, 0, len));
			}
			PrintStream ps = new PrintStream(accept.getOutputStream());
			ps.print("hi client");
			ps.close();
			ps.close();
		}

	}

}
