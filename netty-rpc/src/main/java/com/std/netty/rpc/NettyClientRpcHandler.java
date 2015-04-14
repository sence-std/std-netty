/**
 * @FileName:NettyClientRpcHandler
 * @Package: com.std.netty.rpc
 *
 * @author sence
 * @created 4/14/2015 11:30 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.rpc;

import com.std.netty.rpc.api.Invocation;
import com.std.netty.rpc.exception.RpcException;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * <p>Netty Client</p>
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
public class NettyClientRpcHandler extends ChannelHandlerAdapter{


	private Invocation invocation;

	public NettyClientRpcHandler (Invocation invocation) {
		this.invocation = invocation;
	}

	/**
	 * 通道激活
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive (ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(invocation);
	}

	@Override
	public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			if(invocation instanceof CustomInvocation) {
				CustomInvocation ci = (CustomInvocation) invocation;
				ci.setResult(msg);
			}else{
				throw new RpcException("invocation error");
			}
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
