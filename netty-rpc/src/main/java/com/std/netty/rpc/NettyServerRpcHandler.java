/**
 * @FileName:NettyRpcFramework
 * @Package: com.std.netty.rpc
 *
 * @author sence
 * @created 4/14/2015 9:04 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.rpc;

import com.std.netty.rpc.api.Invocation;
import com.std.netty.rpc.exception.RpcException;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;

/**
 *
 * <p>Netty RPC记录</p>
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
public class NettyServerRpcHandler extends ChannelHandlerAdapter{

	private Object service;

	public NettyServerRpcHandler (Object service) {
		this.service = service;
	}

	@Override
	public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof Invocation){
			Invocation invocation = (Invocation) msg;
			String methodName = invocation.methodName();
			Class<?>[] parameterTypes = invocation.parameterTypes();
			Object[] args = invocation.getParameters();
			Method method = this.getService().getClass().getMethod(methodName,parameterTypes);
			Object result  = method.invoke(service,args);
			ctx.writeAndFlush(result);
		}else{
			throw new RpcException("rpc exception message not instanceof invocation");
		}
	}

	public Object getService () {
		return service;
	}

	public void setService(Object service){
		this.service = service;
	}

}
