package netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable // 可以被多个channel共享
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	/**
	 * 连接channel是活跃时，被调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 通道活跃是发送一条消息
		ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8));
	}

	/**
	 * 调用完成后立刻释放ByteBuf引用
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("clinet received : " + msg.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
