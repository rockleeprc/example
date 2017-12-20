package chatroom.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MutiThreadClientMain {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 9999);
		new Thread(new Reciever(socket)).start();
		PrintWriter print = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = buf.readLine()) != null) {
			if ("break".equals(line)) {
				break;
			}
			print.println(line);
		}
		print.close();
		socket.close();
	}

	public static class Reciever implements Runnable {
		private Socket socket;
		private BufferedReader reader;

		public Reciever(Socket socket) {
			super();
			this.socket = socket;
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					System.out.println();
				}
				System.out.println("exit");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
