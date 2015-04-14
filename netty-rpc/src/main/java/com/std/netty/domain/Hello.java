/**
 * @FileName:Hello
 * @Package: com.std.netty.domain
 *
 * @author sence
 * @created 4/14/2015 9:04 AM
 *
 * Copyright 2011-2015 Asura
 */
package com.std.netty.domain;

import java.io.Serializable;

/**
 *
 * <p>Hello domain</p>
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
public class Hello implements Serializable {


	private String name;

	private String word;

	public Hello () {
	}

	public Hello (String name, String word) {
		this.name = name;
		this.word = word;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getWord () {
		return word;
	}

	public void setWord (String word) {
		this.word = word;
	}

	@Override
	public String toString () {
		return this.getName()+" says: "+word;
	}
}
