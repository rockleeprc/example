package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServerDriver4 {
    public static void main(String[] args) {
        new NioServer(9999).start();
    }

    private static class NioServer {
        private Integer port;

        public NioServer(Integer port) {
            this.port = port;
        }

        public void start() {
            Selector selector = null;
            ServerSocketChannel serverSocketChannel = null;
            try {
                selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("nio server is starting...");

                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 1);
                while (true) {
                    selector.select();

                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        try {
                            if (key.isValid()) {
                                buffer.clear();
                                if (key.isAcceptable()) {
                                    accept(selector, key);
                                } else if (key.isReadable()) {
                                    read(selector, buffer, key);
                                } else if (key.isWritable()) {
                                    write(selector, buffer, key);
                                }
                            }
                        } catch (IOException e) {
                            key.channel().close();
                            key.cancel();
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
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

        private void write(Selector selector, ByteBuffer buffer, SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            byte[] bytes = (byte[]) key.attachment();
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
            channel.register(selector, SelectionKey.OP_READ);
        }

        private void accept(Selector selector, SelectionKey key) throws IOException {
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        }

        private void read(Selector selector, ByteBuffer buffer, SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            int length = channel.read(buffer);
            byte[] bytes = new byte[length];
            buffer.flip();
            buffer.get(bytes);
            System.out.println(new String(bytes, Charset.defaultCharset()));
            channel.register(selector, SelectionKey.OP_WRITE).attach(bytes);
        }
    }
}