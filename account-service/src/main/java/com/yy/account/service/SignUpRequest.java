package com.yy.account.service;
/**
 * 注册账户 信息实体类
 * @author Administrator
 *
 */
public class SignUpRequest {
	private String id;
	private String name;
	private String email;
	private String password;
	private String confirmPassword;
	private String captchaKey;
	private String captchaValue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getCaptchaKey() {
		return captchaKey;
	}
	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}
	public String getCaptchaValue() {
		return captchaValue;
	}
	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}
	
}
