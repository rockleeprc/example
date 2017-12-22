package io.nio;

import java.nio.IntBuffer;

public class IntBufferTest {

	public static void main(String[] args) {
		IntBuffer buffer = IntBuffer.allocate(8);
		for (int i = 0; i < buffer.capacity(); i++) {
			buffer.put(i);
		}

		buffer.flip();

		while (buffer.hasRemaining()) {
			int i = buffer.get();
			System.out.println(i);
		}

	}

}
