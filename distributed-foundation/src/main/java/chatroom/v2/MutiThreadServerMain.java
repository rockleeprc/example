package chatroom.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 客户端和服务器端都采用多线程,避免一个线程阻塞后影响其它线程
 * 
 * @author Administrator
 *
 */
public class MutiThreadServerMain {

	private static ArrayList<Socket> socketList = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			Socket socket = serverSocket.accept();
			socketList.add(socket);
			new Thread(new ServerReciever(socket)).start();
		}
	}

	private static class ServerReciever implements Runnable {
		private Socket socket;
		private BufferedReader reader;

		public ServerReciever(Socket socket) {
			super();
			this.socket = socket;
			try {
				this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					for (Socket s : socketList) {
						PrintWriter ps = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
						ps.println("server:" + line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
