package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServerDriver {
    public static void main(String[] args) {
        NioServer server = new NioServer(9999);
        server.start();
    }

    private static class NioServer {
        private Selector selector;

        public NioServer(Integer port) {
            init(port);
        }

        public void init(int port) {
            try {
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(port));

                selector = Selector.open();
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("nio server init...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void start() {
            System.out.println("nio server start");
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 1);
            try {
                while (true) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isValid()) {
                            try {
                                if (key.isAcceptable()) {
                                    accept(key);
                                } else if (key.isReadable()) {
                                    read(buffer, key);
                                }else if(key.isWritable()){
                                    write(buffer, key);
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                                key.cancel();
                                key.channel().close();
                            } finally {
                                buffer.clear();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("nio server start fail");
                e.printStackTrace();
            }
        }

        private void write(ByteBuffer buffer, SelectionKey key) throws IOException {
            byte[] bytes = (byte[]) key.attachment();
            SocketChannel channel= (SocketChannel) key.channel();
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
            channel.register(selector,SelectionKey.OP_READ);
        }

        private void read(ByteBuffer buffer, SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            int length = channel.read(buffer);//blocking
            if(length==-1){
                channel.close();
                key.cancel();
            }
            buffer.flip();
            byte[] bytes = new byte[length];
            buffer.get(bytes);
            System.out.println(new String(bytes, Charset.defaultCharset()));
            channel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE).attach(bytes);
        }

        private void accept(SelectionKey key) throws IOException {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();//blocking
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        }
    }
}