/**
 * @FileName:RpcException
 * @Package: com.std.netty.rpc.exception
 *
 * @author sence
 * @created 4/14/2015 10:33 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.rpc.exception;

/**
 *
 * <p>RPC exception</p>
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
public class RpcException extends Exception{

	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public RpcException () {
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *

	 * @param   message   the detail message. The detail message is saved for
	 *          later retrieval by the {@link #getMessage()} method.
	 */
	public RpcException (String message) {
		super(message);
	}
}
