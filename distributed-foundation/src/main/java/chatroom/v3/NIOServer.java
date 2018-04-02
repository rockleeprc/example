package chatroom.v3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NIOServer {

	private static int port = 9999;
	private Charset charset = Charset.forName("UTF-8");
	private Selector selector;

	public void start() {

		try {
			selector = Selector.open();
			ServerSocketChannel serverSocketChannel = initServerSocketChannel();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (selector.select() > 0) {
				for (SelectionKey sk : selector.selectedKeys()) {
					selector.selectedKeys().remove(sk);

					if (sk.isAcceptable()) {
						acceptable(sk);
					} else if (sk.isReadable()) {
						readable(sk);
					} else if (sk.isWritable()) {
						System.out.println("w");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readable(SelectionKey sk) throws IOException {
		SocketChannel sc = (SocketChannel) sk.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 8);

		StringBuilder sb = new StringBuilder();
		try {
			while (sc.read(buffer) > 0) {
				buffer.flip();
				sb.append(charset.decode(buffer));
			}
			System.out.println(sb.toString());
			sc.register(selector, SelectionKey.OP_WRITE);
			sk.interestOps(SelectionKey.OP_READ);
		} catch (IOException e) {
			// 如果SelectionKey出现异常，表示该Channel对应的客户端出现问题，将sk从Selector中取消注册
			sk.cancel();
			if (sk.channel() != null) {
				sk.channel().close();
			}
		}

		if (sb.length() > 0) {
			for (SelectionKey key : selector.keys()) {
				Channel targetChannel = key.channel();
				if (targetChannel instanceof SocketChannel) {
					SocketChannel dest = (SocketChannel) targetChannel;
					dest.write(charset.encode("hi client:" + sb.toString()));
				}
			}
		}

	}

	private void acceptable(SelectionKey sk) throws IOException {
		ServerSocketChannel sc = (ServerSocketChannel) sk.channel();
		SocketChannel clientChannel = sc.accept();
		clientChannel.configureBlocking(false);
		clientChannel.register(selector, SelectionKey.OP_READ);
		// 将SelectionKey设置为准备接收其他请求
		sk.interestOps(SelectionKey.OP_ACCEPT);
	}

	public ServerSocketChannel initServerSocketChannel() throws IOException {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", port);
		ssc.bind(address);
		return ssc;
	}

	public static void main(String[] args) {
		new NIOServer().start();
	}
}
