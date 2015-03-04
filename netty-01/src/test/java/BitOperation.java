/**
 * @FileName:BitOperation
 * @Package: PACKAGE_NAME
 *
 * @author sence
 * @created 2/28/2015 3:25 PM
 *
 * Copyright 2011-2015 Asura
 */

import org.junit.Test;

/**
 *
 * <p>位运算</p>
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
public class BitOperation {

	/**
	 * 左移多少位
	 *
	 * 0000000000000111  #5
	 * 0000000000000011  #3
	 */
	@Test
	public void test01(){
		int x = 5;
		x=x>>1;
		System.out.println(x);
	}

	/**
	 * 左移多少位
	 *
	 * 0000000000000111  #7
	 * 0000000000011100  #7*2^2=28
	 */
	@Test
	public void test02(){
		int x = 7;
		x=x<<2;
		System.out.println(x);
	}

	/**
	 * 按位与 取数字的最后8位
	 *
	 * 0101111010101001
	 *         11111111
	 *-------------------------
	 * 		   10101001
	 */
	@Test
	public void test03(){
		int x = 255;
		x=x & 0xFF;
		System.out.println(x);
		System.out.println(0xFF==255);
	}

}
