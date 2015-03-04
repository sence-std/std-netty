/**
 * @FileName:DiscardServerHandler
 * @Package: com.std.hw
 *
 * @author sence
 * @created 2/13/2015 10:37 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 *
 * <p>
 *     Netty Hello Word
 * 	构建一个基本的netty server 只接受请求，然后将请求抛弃掉不做任何响应
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
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception {
		/**
		 * ByteBuf is a reference-counted object which has to be released explicitly via the release() method.
		 * Please keep in mind that it is the handler's responsibility to release any reference-counted object passed to the handler
		 */
		try{
			ByteBuf in = (ByteBuf) msg;
			//do something with msg
			while(in.isReadable()){
				System.out.println((char)in.readByte());
				System.out.flush();
			}
		}finally {
			//更常用的是放在finally内释放
			ReferenceCountUtil.release(msg);
		}

	}

	@Override
	public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//打印出来
		cause.printStackTrace();
		//发生异常后关闭通道
		ctx.close();
	}
}
