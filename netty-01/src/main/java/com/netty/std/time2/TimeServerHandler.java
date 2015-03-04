/**
 * @FileName:DiscardServerHandler
 * @Package: com.std.hw
 *
 * @author sence
 * @created 2/13/2015 10:37 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.time2;

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

		final ChannelFuture cf = ctx.writeAndFlush(new UnixTime());
		cf.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//打印出来
		cause.printStackTrace();
		//发生异常后关闭通道
		ctx.close();
	}
}
