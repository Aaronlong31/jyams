/**
 * 
 */
package com.jyams.util;

import java.security.SecureRandom;

/**
 * 主键生成器
 * 经过测试，生成1000,0000条ID，没有重复，1000,0000以上没有测试。
 * @author zhanglong
 *
 */
public class IdUtil {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 使用SecureRandom 随机生成long型主键
	 * @return
	 */
	public static long nextLong() {
		return random.nextLong();
	}

	/**
	 * 使用SecureRandom 随机生成int型主键
	 * @return
	 */
	public static int nextInt() {
		return random.nextInt();
	}
}
