/**
 * @FileName:NettyClient
 * @Package: com.std.netty.server
 *
 * @author sence
 * @created 4/14/2015 11:29 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.server;

import com.std.netty.rpc.NettyClientRpcHandler;
import com.std.netty.rpc.api.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 *
 * <p>client</p>
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
public class NettyClient {

	private Invocation invocation;

	public NettyClient (Invocation invocation) {
		this.invocation = invocation;
	}

	public Invocation getInvocation () {
		return invocation;
	}

	public void setInvocation (Invocation invocation) {
		this.invocation = invocation;
	}

	public void invoke(String host,int port)throws Exception {

		if(host == null || host.length()==0){
			throw new IllegalArgumentException("host == null");
		}
		if(port<0 || port>65535){
			throw new IllegalArgumentException("port must between 0 to 65535");
		}
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap(); // (1)
			b.group(workerGroup); // (2)
			b.channel(NioSocketChannel.class); // (3)
			b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ObjectEncoder(),
							new ObjectDecoder(ClassResolvers.cacheDisabled(null))
							,new NettyClientRpcHandler(invocation));
				}
			});
			// Start the client.
			ChannelFuture f = b.connect(host, port).sync(); // (5)
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
