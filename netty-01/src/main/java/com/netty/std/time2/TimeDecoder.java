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

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 *
 * <p>支持字节流的读取，并转换成ByteBuf,此后这里可以成支持各RPC协议的扩展点</p>
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
public class TimeDecoder extends ByteToMessageDecoder {

	/**
	 * Decode the from one {@link io.netty.buffer.ByteBuf} to an other. This method will be called till either the input
	 * {@link io.netty.buffer.ByteBuf} has nothing to read when return from this method or till nothing was read from the input
	 * {@link io.netty.buffer.ByteBuf}.
	 *
	 * @param ctx           the {@link io.netty.channel.ChannelHandlerContext} which this {@link io.netty.handler.codec.ByteToMessageDecoder} belongs to
	 * @param in            the {@link io.netty.buffer.ByteBuf} from which to read data
	 * @param out           the {@link java.util.List} to which decoded messages should be added
	 * @throws Exception    is thrown if an error accour
	 */
	@Override
	protected void decode (ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes()<4){
			return;
		}
		//out.add(in.readBytes(4));
		//返回成对象
		out.add(new UnixTime(in.readBytes(4).readUnsignedInt()));
	}

}