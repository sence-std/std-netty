/**
 * @FileName:DiscardServer
 * @Package: com.netty.std
 *
 * @author sence
 * @created 2/13/2015 10:56 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * <p>
 *     构建一个netty server 注册discardserverhandler，作为响应逻辑
 * </p>
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
public class TimeServer {

	private int port;

	public TimeServer (int port) {
		this.port = port;
	}

	/**
	 * 启动server
	 * @throws Exception
	 */
	public void run () throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {

			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel (SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new TimeServerHandler());
						}
					})
					.option(ChannelOption.SO_BACKLOG,128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			// Bind and start to accept incoming connections.
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			channelFuture.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

	}

	public static void main (String[] args) throws Exception {

		new TimeServer(8080).run();

	}
}
