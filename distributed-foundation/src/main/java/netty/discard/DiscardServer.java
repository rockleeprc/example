package netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

	private int port;

	public DiscardServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		/*
		 * EventLoopGroup是处理I/O操作的多线程事件循环器
		 * 一个服务端的应用，因此会有2个 NioEventLoopGroup 会被使用
		 * 		第一个经常被叫做‘boss’，用来接收进来的连接。
		 * 		第二个经常被叫做‘worker’，用来处理已经被接收的连接，一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上
		 */
		EventLoopGroup bossGroup = new NioEventLoopGroup(); 
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			/*
			 * ServerBootstrap 是一个启动 NIO 服务的辅助启动类
			 */
			ServerBootstrap b = new ServerBootstrap(); 
			/*
			 * 
			 */
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel 接收一个连接
					.childHandler(new ChannelInitializer<SocketChannel>() { //被用来处理一个最近的已经接收的 Channel
						/**
						 * 帮助使用者配置一个新的 Channel。
						 * 通过增加一些处理类比如DiscardServerHandler 来配置一个新的 Channel 
						 */
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new DiscardServerHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128) // 设置指定的 Channel 实现的配置参数
					.childOption(ChannelOption.SO_KEEPALIVE, true); // 
					/*
					 * option() 是提供给NioServerSocketChannel 用来接收进来的连接
					 * childOption() 是提供给由父管道 ServerChannel 接收到的连接，在这个例子中也是 NioServerSocketChannel。
					 */
			

			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(port).sync(); // (7)

			// 等待服务器 socket 关闭 。
			// 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new DiscardServer(port).run();
	}
}
