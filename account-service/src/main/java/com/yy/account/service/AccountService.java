package com.yy.account.service;
/**
 * 账户业务接口
 * @author Administrator
 *
 */
public interface AccountService {
	String generateCaptchaKey();
	byte[] generateCaptchaImage(String captchaKey) throws Exception;
	void signUp(SignUpRequest signUpRequest) throws Exception;
	void login(String id,String password) throws Exception;
}
