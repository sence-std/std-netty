/**
 * @FileName:ServiceProxy
 * @Package: com.std.netty.rpc
 *
 * @author sence
 * @created 4/14/2015 11:48 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * <p></p>
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
public class ServiceProxyFactory {


	public <T> T refer(final Class<T> interfaceClass, final String host, final int port){
		if(host == null || host.length()==0){
			throw new IllegalArgumentException("host == null");
		}
		if(port<0 || port>65535){
			throw new IllegalArgumentException("port must between 0 to 65535");
		}
		if(interfaceClass==null ){
			throw new IllegalArgumentException("interfaceClass is null");
		}
		if(!interfaceClass.isInterface()){
			throw new IllegalArgumentException("interfaceClass is not a interface");
		}
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[]{interfaceClass},new InvocationHandler() {
			@Override
			public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
				final CustomInvocation invocation = new CustomInvocation();
				invocation.setResult(method.getName());
				invocation.setParameterTypes(method.getParameterTypes());
				invocation.setGetParameters(args);
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				try {
					Bootstrap b = new Bootstrap(); // (1)
					b.group(workerGroup); // (2)
					b.channel(NioSocketChannel.class); // (3)
					b.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new ObjectEncoder(),
									new ObjectDecoder(ClassResolvers.cacheDisabled(null))
									,new NettyClientRpcHandler(invocation));
						}
					});
					// Start the client.
					ChannelFuture f = b.connect("127.0.0.1", 20880).sync(); // (5)
					// Wait until the connection is closed.
					f.channel().closeFuture().sync();
				} finally {
					workerGroup.shutdownGracefully();
				}
				return null;
			}
		});

	}

}
