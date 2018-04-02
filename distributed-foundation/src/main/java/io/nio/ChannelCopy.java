package io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;


public class ChannelCopy {

	public static void main(String[] args) throws IOException {
		ReadableByteChannel sourceChannel = Channels.newChannel(System.in);
		WritableByteChannel destChannel = Channels.newChannel(System.out);
		ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);
		while (sourceChannel.read(buffer) != -1) {
			buffer.flip();
			while (buffer.hasRemaining()) {
				destChannel.write(buffer);
			}
			buffer.clear();
		}

	}

}
