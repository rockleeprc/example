package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServerDriver3 {
    public static void main(String[] args) {
        new NioServer(9999).start();
    }

    private static class NioServer {
        private Integer port;

        public NioServer(int port) {
            this.port = port;
        }

        public void start() {
            ByteBuffer buffer = ByteBuffer.allocate(1 * 1024 * 1024);
            Selector selector = null;
            ServerSocketChannel serverSocketChannel = null;
            try {
                selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(9999));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        buffer.clear();
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        try {
                            if (key.isValid()) {
                                if (key.isAcceptable()) {
                                    ServerSocketChannel serverChennel = (ServerSocketChannel) key.channel();
                                    SocketChannel channel = serverChennel.accept();
                                    channel.configureBlocking(false);

                                    channel.register(selector, SelectionKey.OP_READ);
                                } else if (key.isReadable()) {
                                    SocketChannel channel = (SocketChannel) key.channel();
                                    int length = channel.read(buffer);
                                    if (length <= 0) continue;

                                    byte[] bytes = new byte[length];
                                    buffer.flip();
                                    buffer.get(bytes);

                                    System.out.println(new String(bytes, Charset.defaultCharset()));
                                    channel.register(selector, SelectionKey.OP_WRITE).attach(bytes);
                                } else if (key.isWritable()) {
                                    SocketChannel channel = (SocketChannel) key.channel();
                                    byte[] bytes = (byte[]) key.attachment();
                                    if (bytes != null && bytes.length <= 0) continue;

                                    buffer.put(bytes);
                                    buffer.flip();
                                    channel.write(buffer);

                                    channel.register(selector, SelectionKey.OP_READ);
                                }
                            }
                        } catch (IOException e) {
                            key.cancel();
                            key.channel().close();
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    serverSocketChannel.close();
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}