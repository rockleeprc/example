package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServerDriver2 {
    public static void main(String[] args) {
        new NioServer(9999).start();
    }

    private static class NioServer {
        private Integer prot;

        public NioServer(Integer port) {
            this.prot = port;
        }

        public void start() {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1021 * 1);
            try {
                Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(9999));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                /*
                whlie(true)
                select()在循环中，阻塞
                迭代器删除
                accept中server channel要accept
                accept产生的channel对象要设置非阻塞
                传递对象要使用channel.register().attach()
                 */
                while (true) {
                    selector.select();//
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        buffer.clear();
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isValid()) {
                            if (key.isAcceptable()) {
                                ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
                                SocketChannel channel = socketChannel.accept();//
                                channel.configureBlocking(false);
                                channel.register(selector, SelectionKey.OP_READ);
                            } else if (key.isReadable()) {
                                SocketChannel channel = (SocketChannel) key.channel();
                                int length = channel.read(buffer);
                                if (length == -1)
                                    continue;
                                byte[] bytes = new byte[length];
                                buffer.flip();
                                buffer.get(bytes);
                                System.out.println(new String(bytes,Charset.defaultCharset()));
                                channel.register(selector, SelectionKey.OP_WRITE).attach(bytes);
                            } else if (key.isWritable()) {
                                SocketChannel channel = (SocketChannel) key.channel();
                                byte[] bytes = (byte[]) key.attachment();
                                buffer.put(bytes);
                                buffer.flip();
                                channel.write(buffer);
                                channel.register(selector,SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}