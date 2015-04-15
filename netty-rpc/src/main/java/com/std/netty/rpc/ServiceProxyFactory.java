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

import java.lang.reflect.InvocationHandler;
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


	public <T> T refer(final Class<T> interfaceClass,final InvocationHandler handler){
		if(interfaceClass==null ){
			throw new IllegalArgumentException("interfaceClass is null");
		}
		if(!interfaceClass.isInterface()){
			throw new IllegalArgumentException("interfaceClass is not a interface");
		}
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[]{interfaceClass},handler);
	}

}
