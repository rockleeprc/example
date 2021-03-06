package netty.delimiter;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class EchoServerMain {

	private static final ByteBuf DELIMITER = Unpooled.copiedBuffer("$E$", CharsetUtil.UTF_8);

	public static void main(String[] args) {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker);
			bootstrap.localAddress(new InetSocketAddress(9999));
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					/**
					 * 使用DelimiterBasedFrameDecoder后，所有channelRead中读取的数据类型都是String
					 * 分隔符需要使用ByteBuf类型声明，超出maxFrameLength会抛异常
					 */
					ch.pipeline()
							// .addLast(new DelimiterBasedFrameDecoder(1024, DELIMITER))
							.addLast(new LineBasedFrameDecoder(1024))
							.addLast(new StringDecoder(Charset.forName("UTF-8"))).addLast(new EchoServerHandler());
				}
			});
			ChannelFuture channelFuture = bootstrap.bind().sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				boss.shutdownGracefully().sync();
				worker.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static class EchoServerHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			try {
				String message = msg.toString();
				System.out.println("server recieved : " + message);
				ctx.writeAndFlush(
						Unpooled.copiedBuffer(message + System.getProperty("line.separator"), CharsetUtil.UTF_8));
			} finally {
				ReferenceCountUtil.release(msg);
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
	}
}
