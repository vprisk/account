package com.yy.account.captcha;

import java.util.List;

/**
 * 验证码服务接口
 * @author Administrator
 *
 */
public interface AccountCaptchaService {
	String generateCaptchaKey();
	byte[] generateCaptchaImage(String captchaKey) throws Exception;
	boolean validateCaptcha(String captchaKey,String captchaValue) throws Exception;
	List<String> getPreDefinedTexts();
	void setPreDefinedTexts(List<String> definedTexts);
}
