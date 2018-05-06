package netty.echo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer2 {
	private static final int port = 9999;

	public static void main(String[] args) throws Exception {
		EchoServerHandler2 handler = new EchoServerHandler2();

		EventLoopGroup master = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(master, worker).channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast(handler);

						}
					});
			ChannelFuture channelFuture = bootstrap.bind().sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			master.shutdownGracefully().sync();
			worker.shutdownGracefully().sync();
		}

	}
}
