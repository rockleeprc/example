package io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import org.junit.Test;

public class NIOApi {

	private static final String FILE_PATH = "E:" + File.separator + "sync" + File.separator + "books_info_data.txt";

	/**
	 * 将文件直接映射到内存
	 * 
	 * @throws IOException
	 */
	@Test
	public void mappedBuffer() throws IOException {
		FileInputStream fis = new FileInputStream(new File(FILE_PATH));
		System.out.println(fis.available());
		FileChannel fc = fis.getChannel();
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fis.available());
		System.out.println(mbb.isReadOnly());
		while (mbb.hasRemaining()) {

			System.out.print((char) mbb.get());
		}
	}

	/**
	 * 复制缓冲区，数据相互可见，但独立维护position、limit、mark
	 */
	@Test
	public void duplicate() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		ByteBuffer duplicate = buffer.duplicate();
	}

	/**
	 * 创建Buffer的两种方式
	 */
	@Test
	public void createBuffer() {
		ByteBuffer buffer1 = ByteBuffer.allocate(1024);

		byte[] bytes = new byte[1024];
		ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
		ByteBuffer buffer3 = ByteBuffer.wrap("abc".getBytes());
	}

	@Test
	public void compact() {
		CharBuffer cb = CharBuffer.allocate(10);
		cb.put('a').put('b').put('c').put('d').put('e');
		cb.position(2);
		// cde会覆盖掉ab
		cb.compact();
		cb.flip();
		while (cb.hasRemaining()) {
			System.out.println(cb.get());
		}
	}

	/**
	 * position：从position的下一个位置开始读/写数据<br/>
	 * capacity：缓冲区的大小<br/>
	 * limit：缓冲区实际数据大小，limit<=capacity<br/>
	 * mark：在数据处理过程中可以随时记录当前位置，在任意时刻回到这个位置<br/>
	 * 
	 * flip()：limit=position，position=0，mark=0，读写转换<br/>
	 * rewind()：position=0，mark=0，读buffer<br/>
	 * clear()：position=0，limit=capacity，mark=0，写buffer<br/>
	 * makr()：记录当前位置<br/>
	 * reset()：回到makr位置<br/>
	 */
	@Test
	public void flip() {
		ByteBuffer buffer = ByteBuffer.allocate(15);
		System.out.println(
				"limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());

		for (int i = 0; i < 10; i++) {
			buffer.put((byte) i);
		}
		System.out.println(
				"limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());

		buffer.flip();
		System.out.println(
				"limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());

		for (int i = 0; i < 5; i++) {
			System.out.println(buffer.get());
		}
		System.out.println(
				"limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());

		buffer.flip();
		System.out.println(
				"limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());
	}

	/**
	 * 手动读写转换
	 */
	@Test
	public void limitAndPosistion() {
		ByteBuffer buffer = ByteBuffer.allocate(15);
		System.out.println(
				"limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());

		for (int i = 0; i < 10; i++) {
			buffer.put((byte) i);
		}

		buffer.limit(buffer.position()).position(0);
		System.out.println(
				"limit=" + buffer.limit() + " capacity=" + buffer.capacity() + " position=" + buffer.position());

		for (int i = 0; i < 5; i++) {
			System.out.println(buffer.get());
		}

		System.out.println(buffer.remaining());
	}

	/**
	 * 应用程序不能直接对接channel，必须通过buffer<br/>
	 * 
	 */
	@Test
	public void channeAndBuffer() {
		File file = new File(FILE_PATH);
		try (FileInputStream fis = new FileInputStream(file);) {
			FileChannel fc = fis.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(512);
			int len = 0;
			while ((len = fc.read(buffer)) != -1) {
				buffer.flip();
				System.out.println(new String(buffer.array(), 0, len));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Test
	public void channeAndBuffer1() {
		File file = new File(FILE_PATH);
		try (FileInputStream fis = new FileInputStream(file);) {
			FileChannel fc = fis.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(512);
			int len = 0;
			len = fc.read(buffer);
			buffer.flip();
			System.out.println(new String(buffer.array(), 0, len));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	int length = 0x8000000;

	@Test
	public void t() throws Exception {
		// 为了以可读可写的方式打开文件，这里使用RandomAccessFile来创建文件。
		FileChannel fc = new RandomAccessFile(FILE_PATH, "rw").getChannel();
		// 注意，文件通道的可读可写要建立在文件流本身可读写的基础之上
		MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, length);
		// 写128M的内容
		for (int i = 0; i < length; i++) {
			out.put((byte) 'x');
		}
		System.out.println("Finished writing");
		// 读取文件中间6个字节内容
		for (int i = length / 2; i < length / 2 + 6; i++) {
			System.out.print((char) out.get(i));
		}
		fc.close();
	}

	@Test
	public void t1() throws IOException {
		// 先将前面3554行读取
		RandomAccessFile reader = new RandomAccessFile(FILE_PATH, "r");
		for (int j = 0; j < 3554; j++) {
			System.out.println(reader.readLine());
		}
	}

	@Test
	public void t2() throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(FILE_PATH);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println(line);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}
	}

}
