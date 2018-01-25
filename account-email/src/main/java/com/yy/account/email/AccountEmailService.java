package com.yy.account.email;
/**
 * 用户邮件服务接口
 * @author Administrator
 *
 */
public interface AccountEmailService {
	/**
	 * 发送HTML格式电子邮件
	 * @param to 接收地址
	 * @param subject 邮件主题
	 * @param htmlText 邮件内容
	 * @throws Exception
	 */
	void sendMail(String to,String subject,String htmlText) throws Exception;
}
