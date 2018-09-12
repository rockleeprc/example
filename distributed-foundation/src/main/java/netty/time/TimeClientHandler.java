package netty.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
	
	private ByteBuf buf;

	/**
	 * ChannelHandler 有2个生命周期的监听方法：handlerAdded()和 handlerRemoved()。你可以完成任意初始化任务只要他不会被阻塞很长的时间。
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		buf = ctx.alloc().buffer(4); // (1)
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		buf.release(); // (1)
		buf = null;
	}

//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		ByteBuf m = (ByteBuf) msg;
//		buf.writeBytes(m); 
//		m.release();
//
//		/*
//		 * 处理器必须检查 buf 变量是否有足够的数据，在这个例子中是4个字节，然后处理实际的业务逻辑。
//		 * 否则，Netty 会重复调用channelRead() 当有更多数据到达直到4个字节的数据被积累
//		 */
//		if (buf.readableBytes() >= 4) { 
//			long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
//			System.out.println(new Date(currentTimeMillis));
//			ctx.close();
//		}
//	}
	    
    /**/
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf m = (ByteBuf) msg; // 在TCP/IP中，Netty 会把读到的数据放到 ByteBuf 的数据结构中
		try {
			long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(format.format(new Date(currentTimeMillis)));
			ctx.close();
		} finally {
			m.release();
		}
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
