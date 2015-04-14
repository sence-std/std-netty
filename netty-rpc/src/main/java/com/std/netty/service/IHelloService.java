/**
 * @FileName:IHelloService
 * @Package: com.std.netty.service
 *
 * @author sence
 * @created 4/14/2015 10:27 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.service;

import com.std.netty.domain.Hello;

/**
 *
 * <p>服务接口</p>
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
public interface IHelloService {

	/**
	 * 服务方法 sayHello
	 * @return
	 */
	public  Hello sayHello(String name,String word);

}
