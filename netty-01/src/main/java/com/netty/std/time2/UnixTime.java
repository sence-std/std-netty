/**
 * @FileName:UnixTime
 * @Package: com.netty.std.time2
 *
 * @author sence
 * @created 2/28/2015 9:20 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.netty.std.time2;

import java.util.Date;

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
public class UnixTime {

	private final long value;

	public UnixTime() {
		this(System.currentTimeMillis() / 1000L);
	}

	public UnixTime(long value) {
		this.value = value;
	}

	public long value() {
		return value;
	}

	@Override
	public String toString() {
		return new Date(value()* 1000L).toString();
	}
}
