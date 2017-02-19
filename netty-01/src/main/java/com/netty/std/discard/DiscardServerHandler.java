/**
 * @Package: com.netty.std.discard
 * @author liusq23
 * @created 2017/2/19 下午4:12
 * <p>
 * Copyright 2015 ziroom
 */
package com.netty.std.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //do something with msg
        while (in.isReadable()) {
            System.out.print((char) in.readByte());
            System.out.flush();
        }
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
