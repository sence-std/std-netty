/**
 * @FileName:DiscardServerHandler
 * @Package: com.std.hw
 *
 * @author sence
 * @created 2/13/2015 10:37 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * <p>
 *  Netty Hello Word
 * 	构建一个基本的netty server 只接受请求，然后将请求抛弃掉不做任何响应
 * 	in this example you will learn how to construct and send a message, and to close the connection on completion.
 * </p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sence
 * @since 1.0
 * @version 1.0
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{

	/**
	 * 这里不读取任何请求的数据，只返回32位的时间，所以用channelActive
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive (final ChannelHandlerContext ctx) throws Exception {
		//分配4个字节
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis() / 1000L));
		//异步给channelFuture
		final ChannelFuture cf = ctx.writeAndFlush(time);
		//注册一个监听 事件驱动
		cf.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete (ChannelFuture channelFuture) throws Exception {
				assert cf == channelFuture;
				ctx.close();
			}
		});
	}

	@Override
	public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//打印出来
		cause.printStackTrace();
		//发生异常后关闭通道
		ctx.close();
	}
}
