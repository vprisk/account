package com.yy.account.email;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * 账户邮件测试
 * @author Administrator
 *
 */
public class AccoutEmailServerTest {
	private GreenMail greenMail;
	@Before
	public void startMailServer(){
		greenMail=new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("yuanl88@sina.com", "yuan8888");
		greenMail.start();
	}
	
	@Test
	public void testSendMail() throws Exception{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("account-email.xml");
		AccountEmailService accountEmailService=(AccountEmailService) ctx.getBean("accountEmailService");
		String subject="test subject";
		String htmlText="<h3>test text</h3>";
		accountEmailService.sendMail("991718434@qq.com", subject, htmlText);
		greenMail.waitForIncomingEmail(2000, 1);
		//MimeMessage[] msgs = greenMail.getReceivedMessages();
		/*Assert.assertEquals(1, msgs.length);
		Assert.assertEquals(subject, msgs[0].getSubject());
		Assert.assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());*/
	}
	
	@After
	public void stopMailServer(){
		greenMail.stop();
	}
}
