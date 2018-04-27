package io.nonblock;

import java.util.Scanner;

/**
 * @see https://blog.csdn.net/anxpp/article/details/51512200
 * @author Administrator
 *
 */
public class Test {
	// 测试主方法
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// 运行服务器
		NonBlockServer.start();
		// 避免客户端先于服务器启动前执行代码
		Thread.sleep(100);
		// 运行客户端
		NonBlockClient.start();
		while (NonBlockClient.sendMsg(new Scanner(System.in).nextLine()))
			;
	}
}