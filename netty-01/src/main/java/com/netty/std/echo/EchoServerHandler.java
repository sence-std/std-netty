/**
 * @FileName:DiscardServerHandler
 * @Package: com.std.hw
 *
 * @author sence
 * @created 2/13/2015 10:37 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * <p>
 *     Netty Hello Word
 *     构建一个用户输入什么，服务器响应什么
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
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception {
		/**
		 * ByteBuf is a reference-counted object which has to be released explicitly via the release() method.
		 * Please keep in mind that it is the handler's responsibility to release any reference-counted object passed to the handler
		 */
		ctx.writeAndFlush(msg);
		//这里不能释放msg，因为当写到wire后netty会自动为我们释放，如果此处释放会发生异常
		//ReferenceCountUtil.release(msg);

	}

	@Override
	public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//打印出来
		cause.printStackTrace();
		//发生异常后关闭通道
		ctx.close();
	}
}
