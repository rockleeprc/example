package com.example.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferExample {

    @Test
    public void remaining() {



        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("a".getBytes()).put("b".getBytes()).put("c".getBytes()).put("d".getBytes()).put("e".getBytes());
        printInfo(buffer);

        /*
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.print((char)buffer.get()+"\t");
        }
        */

        System.out.println(buffer.remaining());
        for (int i = 0, len = buffer.remaining(); i < len; i++) {
            byte b = buffer.get(i);
            System.out.print((char) b + "\t");
        }
    }

    @Test
    public void rewind() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("a".getBytes()).put("b".getBytes()).put("c".getBytes()).put("d".getBytes()).put("e".getBytes());
        printInfo(buffer);

        /*
        limit=capacity
        position=0
         */
        buffer.rewind();
        printInfo(buffer);
    }

    @Test
    public void flip() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("a".getBytes()).put("b".getBytes()).put("c".getBytes()).put("d".getBytes()).put("e".getBytes());
        printInfo(buffer);

        /*
        limit=position
        position=0
         */
        buffer.flip();
        printInfo(buffer);

        /*
        limit=0
        position=0
         */
        buffer.flip();
        printInfo(buffer);

    }


    @Test
    public void put() {

        ByteBuffer buffer = ByteBuffer.allocate(5);
        printInfo(buffer);

        buffer.put("a".getBytes()).put("b".getBytes()).put("c".getBytes()).put("d".getBytes()).put("e".getBytes());
        printInfo(buffer);

        buffer.put(0, (byte) 'A').put(1, (byte) 'B');
        System.out.println((char) buffer.get(0));
        System.out.println((char) buffer.get(1));
        printInfo(buffer);
    }


    public void printInfo(ByteBuffer buffer) {
        System.out.println("-----------------");
        System.out.printf("capacity=%d \n", buffer.capacity());
        System.out.printf("limit=%d \n", buffer.limit());
        System.out.printf("position=%d \n", buffer.position());
    }
}