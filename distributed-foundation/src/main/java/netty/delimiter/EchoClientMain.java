package netty.delimiter;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

public class EchoClientMain {
	// private static final ByteBuf DELIMITER = Unpooled.copiedBuffer("$E$",CharsetUtil.UTF_8);
	private static final ByteBuf DELIMITER = Unpooled.copiedBuffer("$E$".getBytes());

	public static void main(String[] args) throws UnsupportedEncodingException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group);
			bootstrap.remoteAddress(new InetSocketAddress("127.0.0.1", 9999));
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline()
							// .addLast(new DelimiterBasedFrameDecoder(1024, DELIMITER))
							.addLast(new LineBasedFrameDecoder(1024))
							.addLast(new StringDecoder(Charset.forName("UTF-8"))).addLast(new EchoClientHandler());
				}
			});
			ChannelFuture channelFuture = bootstrap.connect().sync();

			// String line = "AAA \r\n BBB \r\n CCC \r\n EEE \r\n";
			String line = "AAA " + System.getProperty("line.separator") + " BBB " + System.getProperty("line.separator")
					+ " CCC " + System.getProperty("line.separator") + " EEE" + System.getProperty("line.separator");
			channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(line, CharsetUtil.UTF_8));
			TimeUnit.SECONDS.sleep(1);

			channelFuture.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				group.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static class EchoClientHandler extends SimpleChannelInboundHandler<String> {

		@Override
		public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
			System.out.println("client recieved  " + msg.toString());
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
	}
}
