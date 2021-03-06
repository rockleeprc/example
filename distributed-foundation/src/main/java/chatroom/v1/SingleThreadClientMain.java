package chatroom.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SingleThreadClientMain {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 9999);
		String line = null;
		PrintStream print = new PrintStream(socket.getOutputStream());
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		/*
		 * 采用readLine读取时，发送数据端必须发送\r\n，标志一行结束，否则将无法读取，readLine将持续阻塞
		 */
		while ((line = buf.readLine()) != null) {
			if ("break".equals(line)) {
				// break后,server.readLine会阻塞,client.readLine也会阻塞，收不到任何消息
				break;
			}
			print.println(line);
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();
		socket.close();
	}
}
