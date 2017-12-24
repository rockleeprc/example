package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import javax.swing.text.AsyncBoxView.ChildLocator;

public class EchoServer {

	private static int port = 9999;

	public static void main(String[] args) {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;

		try {
			serverSocketChannel = ServerSocketChannel.open();
			InetSocketAddress isa = new InetSocketAddress(port);
			// 1.7直接bind地址，不用创建SocketChannel
			serverSocketChannel.bind(isa);
			serverSocketChannel.configureBlocking(false);
			selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iter = keys.iterator();
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				iter.remove();

				try {
					if (key.isAcceptable()) {
						System.out.println("isAcceptable");

						ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
						SocketChannel clientChannel = serverChannel.accept();
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						System.out.println("isReadable");

						SocketChannel clientChannel = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(4096);
						clientChannel.read(buffer);
						SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_WRITE);
						clientKey.attach(buffer);
					} else if (key.isWritable()) {
						System.out.println("isWritable");

						SocketChannel clientChannel = (SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer) key.attachment();
						buffer.flip();
						clientChannel.write(buffer);

						key.interestOps(SelectionKey.OP_READ);
					}
				} catch (Exception e) {
					key.cancel();
					try {
						key.channel().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
