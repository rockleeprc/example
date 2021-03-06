package io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用NIO实现server端，详细见package chatroom.v3
 * 
 * @author Administrator
 *
 */
public class NIOServerMain {
	private static Selector selector;
	private static Charset charset = Charset.forName("UTF-8");

	public static void main(String[] args) throws IOException {
		selector = Selector.open();
		InetSocketAddress address = new InetSocketAddress(9999);

		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.bind(address);
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			int waitNum = selector.select();
			// 注册到Selector上的Channel都没有需要处理的IO时，selector.select()将会阻塞，以下判断没有必要
			if (waitNum == 0)
				continue;

			Set<SelectionKey> selectKeys = selector.selectedKeys();
			Iterator<SelectionKey> iter = selectKeys.iterator();
			ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				buffer.clear();
				iter.remove();

				if (key.isAcceptable()) {
					ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
					SocketChannel clientChannel = serverChannel.accept();
					clientChannel.configureBlocking(false);// 默认阻塞
					clientChannel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					SocketChannel client = (SocketChannel) key.channel();

					int len = 0;
					//socketChannel.configureBlocking(false)非阻塞时，read()返回值不会是-1，如果没读到会返回0
					while ((len = client.read(buffer)) > 0) {
						buffer.flip();
						// System.out.println(charset.decode(buffer));
						System.out.println(new String(buffer.array(), 0, len));
					}
					client.register(selector, SelectionKey.OP_WRITE);
				} else if (key.isWritable()) {
					SocketChannel clinetSocket = (SocketChannel) key.channel();
					buffer.put("server recieve your message".getBytes());
					clinetSocket.write(buffer);
				}

			}
		}

	}
}
