/**
 * @FileName: TimeClientHandler.java
 * @Package: com.ziroom.std.time1
 * @author liusq23
 * @created 2017/2/19 下午7:43
 * <p>
 * Copyright 2015 ziroom
 */
package com.ziroom.std.time1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private volatile Channel channel;

    public void sendLocate(String str) throws UnsupportedEncodingException {
        ByteBuf buffer = ByteBufUtil.encodeString(channel.alloc(), CharBuffer.wrap(str),Charset.forName("UTF-8"),0);
        channel.writeAndFlush(buffer);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String str = ((ByteBuf) msg).toString(Charset.forName("UTF-8"));
        System.out.println(str);
        ReferenceCountUtil.release(msg);
    }


}
