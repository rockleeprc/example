package netty.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ChannelInboundHandler 提供了处理事件的接口方法
 * ChannelInboundHandlerAdapter实现了ChannelInboundHandler
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用
	 * ByteBuf是个引用对象，该对象必须显示地调用 release() 方法来释放
	 以下为模版代码：
	  try {
            // Do something with msg
        } finally {
            ReferenceCountUtil.release(msg);
        }
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/*
		 *  write(Object) 方法来逐字地把接受到的消息写入
		 *  当写入的时候 Netty 已经帮我们释放了资源
		 */
		ctx.write(msg); 
		/*
		 * ctx.write(Object) 方法不会使消息写入到通道上，他被缓冲在了内部，需要调用 ctx.flush() 方法来把缓冲区中数据强行输出
		 */
		ctx.flush(); //  cxt.writeAndFlush(msg) 
		
		/*
		ByteBuf buf=(ByteBuf)msg;
		try {
			while(buf.isReadable()) {
				System.out.println((char)buf.readByte());
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.refCnt(msg);
		}
		*/
	}
	
	/**
	 * 当出现 Throwable 对象才会被调用， Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
