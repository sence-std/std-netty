/**
 * @FileName:NettyRpcTest
 * @Package: com.std.netty
 *
 * @author sence
 * @created 4/14/2015 12:01 PM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty;

import com.std.netty.domain.Hello;
import com.std.netty.rpc.ServiceProxyFactory;
import com.std.netty.server.NettyServer;
import com.std.netty.service.IHelloService;
import com.std.netty.service.impl.HelloServiceImpl;
import org.junit.Test;

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
public class NettyRpcTest {

	private static final int DEFAULT_PORT = 20880;
	private static final String DEFAULT_HOST = "127.0.0.1";

	@Test
	public void testProvider() throws InterruptedException {
		IHelloService helloService = new HelloServiceImpl();
		NettyServer nettyServer = new NettyServer(DEFAULT_PORT);
		nettyServer.exporter(helloService);

	}

	@Test
	public void testConsumer(){
		ServiceProxyFactory factory = new ServiceProxyFactory();
		IHelloService helloService = factory.refer(IHelloService.class,DEFAULT_HOST,DEFAULT_PORT);
		System.out.print(helloService);
		Hello hello = helloService.sayHello("sence","hello");
		System.out.print(hello);
	}
}
