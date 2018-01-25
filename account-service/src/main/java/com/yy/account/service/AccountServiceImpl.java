package com.yy.account.service;

import com.yy.account.captcha.AccountCaptchaService;
import com.yy.account.email.AccountEmailService;
import com.yy.account.persist.Account;
import com.yy.account.persist.AccountPersistService;

/**
 * @author Administrator
 *
 */
public class AccountServiceImpl implements AccountService {
	private AccountEmailService accountEmailService;
	private AccountPersistService accountPersistService;
	private AccountCaptchaService accountCaptchaService;
	
	public AccountEmailService getAccountEmailService() {
		return accountEmailService;
	}

	public void setAccountEmailService(AccountEmailService accountEmailService) {
		this.accountEmailService = accountEmailService;
	}

	public AccountPersistService getAccountPersistService() {
		return accountPersistService;
	}

	public void setAccountPersistService(AccountPersistService accountPersistService) {
		this.accountPersistService = accountPersistService;
	}

	public AccountCaptchaService getAccountCaptchaService() {
		return accountCaptchaService;
	}

	public void setAccountCaptchaService(AccountCaptchaService accountCaptchaService) {
		this.accountCaptchaService = accountCaptchaService;
	}

	@Override
	public String generateCaptchaKey() {
		
		return accountCaptchaService.generateCaptchaKey();
	}

	@Override
	public byte[] generateCaptchaImage(String captchaKey) throws Exception {
		return accountCaptchaService.generateCaptchaImage(captchaKey);
	}

	@Override
	public void signUp(SignUpRequest signUpRequest) throws Exception {
		Account account=new Account();
		account.setId(signUpRequest.getId());
		account.setName(signUpRequest.getName());
		account.setEmail(signUpRequest.getEmail());
		account.setPassword(signUpRequest.getPassword());
		account.setActivated(false);
		accountPersistService.createAccount(account);
		accountEmailService.sendMail(account.getEmail(), "欢迎注册", "<h2>欢迎您注册使用！</h2>");
	}

	@Override
	public void login(String id, String password) throws Exception {
		Account account = accountPersistService.readAccount(id);
		if (account==null) {
			throw new Exception("账户："+id+"不存在");
		}
		if (!account.getPassword().equals(password)) {
			throw new Exception("两次密码输入不一致");
		}

	}

}
