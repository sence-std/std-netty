/**
 * @FileName:NettyServer
 * @Package: com.std.netty.server
 *
 * @author sence
 * @created 4/14/2015 9:09 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.server;

import com.std.netty.rpc.NettyServerRpcHandler;
import com.std.netty.service.IHelloService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 *
 * <p>Netty server</p>
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
public class NettyServer {

	private int port;

	public NettyServer () {

	}

	public NettyServer (int port) {
		this.port = port;
	}

	/**
	 * 启动服务器
	 * @throws InterruptedException
	 */
	public void exporter(final IHelloService helloService) throws InterruptedException {

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			/**配置启动参数*/
			bootstrap.group(bossGroup,workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel (SocketChannel ch) throws Exception {
							ch.pipeline().addLast(
									new ObjectEncoder(),
									new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
									new NettyServerRpcHandler(helloService));
						}
					})
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE,true);
			ChannelFuture channelFuture = bootstrap.bind(this.port).sync();
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			channelFuture.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}
