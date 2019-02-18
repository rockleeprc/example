package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClientDriver {

    public static void main(String[] args) {
        NioClient client = new NioClient(9999);
        client.start();

    }

    private static  class NioClient{
        private Integer port;

        public NioClient(int port){
            this.port = port;
        }
        public void start(){
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024*1);
            SocketChannel channel = null;
            try {
                channel = SocketChannel.open();
                channel.connect(new InetSocketAddress(port));
                Scanner scanner = new Scanner(System.in);
                while(true) {
                    System.out.print("put message to server:");
                    String message = scanner.nextLine();
                    if(message==null || message.length()<=0){
                        continue;
                    }
                    if("exit".equals(message)){
                        break;
                    }
                    buffer.clear();
                    buffer.put(message.getBytes());
                    buffer.flip();
                    channel.write(buffer);


                    buffer.clear();
                    int length = channel.read(buffer);
                    if(length<=0){
                        continue;
                    }
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    System.out.println(new String(bytes));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("nio client end of message delivery");

        }
    }
}