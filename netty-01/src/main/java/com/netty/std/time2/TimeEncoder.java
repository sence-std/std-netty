/**
 * @FileName:TimeEncoder
 * @Package: com.netty.std.time2
 *
 * @author sence
 * @created 2/28/2015 9:17 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.time2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 *
 * <p>将字节流编码输出</p>
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
public class TimeEncoder extends MessageToByteEncoder<UnixTime>{
	/**
	 * Encode a message into a {@link io.netty.buffer.ByteBuf}. This method will be called for each written message that can be handled
	 * by this encoder.
	 *
	 * @param ctx           the {@link io.netty.channel.ChannelHandlerContext} which this {@link io.netty.handler.codec.MessageToByteEncoder} belongs to
	 * @param msg           the message to encode
	 * @param out           the {@link io.netty.buffer.ByteBuf} into which the encoded message will be written
	 * @throws Exception    is thrown if an error accour
	 */
	@Override
	protected void encode (ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
		out.writeInt((int)msg.value());
	}
}
