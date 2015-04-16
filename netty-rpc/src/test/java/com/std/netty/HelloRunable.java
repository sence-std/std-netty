/**
 * @FileName:HelloRunable
 * @Package: com.std.netty
 *
 * @author sence
 * @created 4/15/2015 4:41 PM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty;

import com.std.netty.domain.Hello;
import com.std.netty.service.IHelloService;

import java.util.concurrent.Semaphore;

/**
 *
 * <p>Run</p>
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
public class HelloRunable implements Runnable {

	private final Semaphore semp = new Semaphore(500);
	/**
	 *
	 */
	private IHelloService service;

	public HelloRunable (IHelloService service) {
		this.service = service;
	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see     Thread#run()
	 */
	@Override
	public void run () {
		try {
			semp.acquire();
			Hello hello = service.sayHello(Thread.currentThread().getName(),"hello");
			System.out.println(Thread.currentThread().getName()+"-----"+hello);
			semp.release();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
