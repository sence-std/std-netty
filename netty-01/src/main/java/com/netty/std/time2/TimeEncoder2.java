/**
 * @FileName:TimeEncoder2
 * @Package: com.netty.std.time2
 *
 * @author sence
 * @created 2/28/2015 9:26 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.time2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 *
 * <p>不继承MessageToByteEncoder</p>
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
public class TimeEncoder2 extends ChannelOutboundHandlerAdapter {
	/**
	 * Calls {@link io.netty.channel.ChannelHandlerContext#write(Object)} to forward
	 * to the next {@link ChannelOutboundHandler} in the {@link ChannelPipeline}.
	 *
	 * Sub-classes may override this method to change behavior.

	 * @param
	ctx
	 * @param
	msg
	 * @param promise
	 */
	@Override
	public void write (ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		UnixTime m = (UnixTime) msg;
		ByteBuf encoder = ctx.alloc().buffer(4);
		encoder.writeInt((int)m.value());
		ctx.write(encoder,promise);
	}
}
