package chatroom.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Administrator
 *
 */
public class MutiThreadServerMain {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			Socket accept = serverSocket.accept();
			// 调用is.Read()时,如果client没有数据,会引起阻塞
			InputStream is = accept.getInputStream();
			PrintWriter ps = new PrintWriter(new OutputStreamWriter(accept.getOutputStream()),true);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				String content = new String(buf, 0, len);
				System.out.println("reciever:"+content);
				ps.println("server:"+content);
				ps.flush();
			}
			ps.close();
			serverSocket.close();
		}

	}

}
