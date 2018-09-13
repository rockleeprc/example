package netty.echo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		EchoServerHandler echoServerHandler = new EchoServerHandler();
		//事件处理类，如连接，读写数据
		EventLoopGroup group = new NioEventLoopGroup();
		try{
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(group)
					.channel(NioServerSocketChannel.class)//指定使用的NIO Channel
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<Channel>() {//添加一个EchoServerHandler到子Channel的 ChannelPipeline 
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast(echoServerHandler);
						}
					});
		ChannelFuture channelFuture = bootstrap.bind().sync();
		channelFuture.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
		new EchoServer(9999).start();
	}
}
