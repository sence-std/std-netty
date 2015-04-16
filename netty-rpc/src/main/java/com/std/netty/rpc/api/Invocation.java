/**
 * @FileName:Invocation
 * @Package: com.std.netty.rpc
 *
 * @author sence
 * @created 4/14/2015 10:01 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.rpc.api;

/**
 *
 * <p>调用信息</p>
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
public interface Invocation {

	/**
	 * 每次调用的token
	 * @return
	 */
	public String getToken();

	/**
	 * 调用方法的名称
	 * @return
	 */
	public String methodName();

	/**
	 * 获得调用参数类型
	 * @return
	 */
	public Class<?>[] parameterTypes();

	/**
	 * 获得调用参数
	 * @return
	 */
	public Object[] getParameters();


}
