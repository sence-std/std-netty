/**
 * @Package: com.netty.std.time1
 * @author liusq23
 * @created 2017/2/19 下午7:43
 * <p>
 * Copyright 2015 sence
 */
package com.netty.std.time1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.util.Scanner;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class TimeClient extends Thread {

    private int port;

    private TimeClientHandler clientHandler;

    public TimeClient(int port, TimeClientHandler timeClientHandler) {
        this.port = port;
        this.clientHandler = timeClientHandler;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        TimeClientHandler clientHandler = new TimeClientHandler();
        new TimeClient(8080, clientHandler).start();
        Scanner s = new Scanner(System.in);
        for (; ; ) {
            String str = s.nextLine();
            if (str.equals("exit")) {
                break;
            }
            System.out.println(str);
            clientHandler.sendLocate(str);
        }

    }

    public TimeClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(TimeClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(clientHandler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
