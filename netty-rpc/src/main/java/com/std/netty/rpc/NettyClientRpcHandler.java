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
import com.std.netty.rpc.protocol.RpcResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

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

	private volatile Channel channel;
	//这儿有一个并发的问题 生产出来的结果不是消费者想要的
//	private final BlockingQueue<Object> answer = new LinkedBlockingQueue<Object>();
	private ConcurrentHashMap<Object,Object> map = new ConcurrentHashMap<>();
	public Object sendRpcInvoke(Invocation invocation)throws RpcException{
		channel.writeAndFlush(invocation);
		Object resObj= null;
		for(;;){
			if(map.containsKey(invocation.getToken())) {
				resObj = map.get(invocation.getToken());
				break;
			}
		}
		return resObj;

	}

	/**
	 * Calls {@link io.netty.channel.ChannelHandlerContext#fireChannelRegistered()} to forward
	 * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
	 *
	 * Sub-classes may override this method to change behavior.

	 * @param ctx
	 */
	@Override
	public void channelRegistered (ChannelHandlerContext ctx) throws Exception {
		channel = ctx.channel();
	}

	@Override
	public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof RpcResponse){
			RpcResponse rr = (RpcResponse)msg;
			map.put(rr.getToken(),rr.getResult());
		}else{
			throw new RpcException("rpc failed");
		}

	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
