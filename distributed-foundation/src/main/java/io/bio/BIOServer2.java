package io.bio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer2 {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			// 阻塞，等待客户端请求
			Socket accept = serverSocket.accept();
			System.out.println("接收到请求");
			handler(accept);
			/*
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						handler(accept);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();*/
		}
	}

	private static void handler(Socket accept) throws IOException {
		BufferedReader reader =new BufferedReader(new  InputStreamReader(accept.getInputStream()));
		
		String line = null;
		while ((line =reader.readLine())!=null) {
			if("break".equals(line)){
				break;
			}
			System.out.println(line);
			
		}
		
	}

}
