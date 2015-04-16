/**
 * @FileName:CustomInvocation
 * @Package: com.std.netty.rpc
 *
 * @author sence
 * @created 4/14/2015 11:14 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.rpc;

import com.std.netty.rpc.api.Invocation;

import java.io.Serializable;

/**
 *
 * <p>调用实体类</p>
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
public class CustomInvocation implements Invocation,Serializable {

	private static final long serialVersionUID = 1822406893404282511L;
	private String methodName;
	private Class<?>[] parameterTypes;
	private Object[] getParameters;
	private String token;

	public CustomInvocation () {

	}

	public String getMethodName () {
		return methodName;
	}

	public void setMethodName (String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes () {
		return parameterTypes;
	}

	public void setParameterTypes (Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getGetParameters () {
		return getParameters;
	}

	public void setGetParameters (Object[] getParameters) {
		this.getParameters = getParameters;
	}


	public CustomInvocation (String methodName, Class<?>[] parameterTypes, Object[] getParameters) {
		this.methodName = methodName;
		this.parameterTypes = parameterTypes;
		this.getParameters = getParameters;
	}
	/**
	 * 调用方法的名称
	 * @return
	 */
	@Override
	public String methodName () {
		return this.methodName;
	}

	/**
	 * 获得调用参数类型
	 * @return
	 */
	@Override
	public Class<?>[] parameterTypes () {
		return this.parameterTypes;
	}

	/**
	 * 获得调用参数
	 * @return
	 */
	@Override
	public Object[] getParameters () {
		return this.getParameters;
	}

	@Override
	public String getToken () {
		return token;
	}

	public void setToken (String token) {
		this.token = token;
	}
}
