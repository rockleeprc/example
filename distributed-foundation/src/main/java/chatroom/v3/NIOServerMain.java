package chatroom.v3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;



public class NIOServerMain {
	public static void main(String[] args) throws IOException {
		AbstractSelector selector = SelectorProvider.provider().openSelector();
		InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(),9999);

		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.bind(address);
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
	}
}
