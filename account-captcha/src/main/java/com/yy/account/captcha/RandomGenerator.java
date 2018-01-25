package com.yy.account.captcha;

import java.util.Random;

/**
 * 验证码随机数生成类
 * @author Administrator
 *
 */
public class RandomGenerator {
	private static String range="0123456789abcdefghijklmnopqrstuvwxyz";
	/**
	 * 生成8位随机验证码字符串
	 * @return
	 */
	public static synchronized String getRandomString(){
		Random random=new Random();
		StringBuffer result=new StringBuffer();
		for (int i = 0; i < 8; i++) {
			result.append(range.charAt(random.nextInt(range.length()-1)));
		}
		return result.toString();
	}
}
