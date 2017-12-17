package io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			// 阻塞，等待客户端请求
			Socket accept = serverSocket.accept();
			System.out.println("接收到请求");

			InputStream is = accept.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				System.out.println(new String(buf, 0, len));
			}
		}
	}

}
