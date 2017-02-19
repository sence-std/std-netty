/**
 * @FileName: TimeServerHandler.java
 * @Package: com.ziroom.std.time1
 * @author liusq23
 * @created 2017/2/19 下午5:54
 * <p>
 * Copyright 2015 ziroom
 */
package com.ziroom.std.time1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
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
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf timeLocateByte = (ByteBuf)msg;
        String str = timeLocateByte.toString(Charset.forName("UTF-8"));
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(str));
        String s = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ByteBuf buffer = ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(s),Charset.forName("UTF-8"),0);
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static void main(String[] args) {
        LocalDateTime _time = LocalDateTime.now();
        System.out.print(_time.getSecond());
    }
}
