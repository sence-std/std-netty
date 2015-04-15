/**
 * @FileName:NettyClientTest
 * @Package: com.std.netty
 *
 * @author sence
 * @created 4/14/2015 2:03 PM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty;

import com.std.netty.rpc.CustomInvocation;
import com.std.netty.rpc.NettyClientRpcHandler;
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
import org.junit.Test;

/**
 *
 * <p>TODO</p>
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
public class NettyClientTest {


	@Test
	public void test() throws InterruptedException {
		final CustomInvocation invocation = new CustomInvocation();
		invocation.setMethodName("sayHello");
		invocation.setParameterTypes(new Class<?>[] { String.class, String.class });
		invocation.setGetParameters(new Object[]{"sence","hello"});
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
							,new NettyClientRpcHandler());
				}
			});
			// Start the client.
			ChannelFuture f = b.connect("127.0.0.1", 20880).sync(); // (5)
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}
