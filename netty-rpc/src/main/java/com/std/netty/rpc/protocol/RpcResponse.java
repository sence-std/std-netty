/**
 * @FileName:RpcProtocol
 * @Package: com.std.netty.rpc.protocol
 *
 * @author sence
 * @created 4/15/2015 5:05 PM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.rpc.protocol;

import java.io.Serializable;

/**
 *
 * <p>协议</p>
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
public class RpcResponse implements Serializable{

	private String token;
	private Object result;

	public RpcResponse () {
	}

	public RpcResponse (String token, Object result) {
		this.token = token;
		this.result = result;
	}

	public Object getResult () {
		return result;
	}

	public void setResult (Object result) {
		this.result = result;
	}

	public String getToken () {
		return token;
	}

	public void setToken (String token) {
		this.token = token;
	}
}
