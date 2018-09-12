package netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * channelActive() 方法将会在连接被建立并且准备进行通信时被调用。
	 * 在这个方法里完成一个代表当前时间的32位整数消息的构建工作
	 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) { 
		final ByteBuf time = ctx.alloc().buffer(4); // 通过ByteBufAllocator 分配缓冲区
		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		System.out.println(System.currentTimeMillis() / 1000L + 2208988800L);

		/*
		 * ByteBuf 之所以没有这个方法因为有两个指针，一个对应读操作一个对应写操作。
		 * 当你向 ByteBuf 里写入数据的时候写指针的索引就会增加，同时读指针的索引没有变化。
		 * 读指针索引和写指针索引分别代表了消息的开始和结束
		 * netty抛弃了NIO那种需要调用filp()的操作方式
		 * 
		 * 一个 ChannelFuture 代表了一个还没有发生的 I/O 操作。这意味着任何一个请求操作都不会马上被执行，因为在 Netty 里所有的操作都是异步的
		 */
		final ChannelFuture f = ctx.writeAndFlush(time); // (3)
		/*
		 * 监听写请求是否完成，在返回的 ChannelFuture 上增加一个ChannelFutureListener，在操作完成时关闭 Channel。
		 */
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) {
				assert f == future;
				ctx.close();
			}
		}); // f.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
