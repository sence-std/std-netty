/**
 * @FileName:TimeClientHandler
 * @Package: com.netty.std.time
 *
 * @author sence
 * @created 2/13/2015 5:40 PM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.time2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * <p>TODO</p>
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
public class TimeClientHandler extends ChannelInboundHandlerAdapter {


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			UnixTime m = (UnixTime)msg;
			System.out.println(m);
		}finally {
			//释放ctx
			ctx.close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}