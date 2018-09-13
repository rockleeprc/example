package netty.echo;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoClientMuti {

	private static final String ADDR = "127.0.0.1";
	private static final int PORT = 9999;
	private static final String CLINET_RECIEVED="client recieved : ";
	private static final int LOOP = 5000;
	private static final int POOL=300;
	
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(POOL);
		for (int i = 0; i < LOOP; i++) {
			threadPool.execute(new EchoClientTask());
		}
		threadPool.shutdown();
	}

	private static class EchoClientTask implements Runnable {

		@Override
		public void run() {
			EventLoopGroup group = new NioEventLoopGroup();
			EchoClientHandler clientHandler = new EchoClientHandler();

			try {
				Bootstrap bootstrap = new Bootstrap();
				bootstrap.group(group);
				bootstrap.remoteAddress(new InetSocketAddress(ADDR, PORT));
				bootstrap.channel(NioSocketChannel.class);
				bootstrap.handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(clientHandler);
					}
				});

				ChannelFuture channelFuture = bootstrap.connect().sync();
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

	}

	@Sharable
	private static class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8));
		}

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
			ByteBuf buf = (ByteBuf) msg;
			System.out.println(CLINET_RECIEVED+buf.toString(CharsetUtil.UTF_8));
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
	}
}
