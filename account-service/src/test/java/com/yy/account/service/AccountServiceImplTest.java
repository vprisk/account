package com.yy.account.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class AccountServiceImplTest {
	private AccountService service;
	File persistDataFile=new File("target/test-classes/persist-data.xml");
	@Before
	public void prepare(){
		
		if (persistDataFile.exists()) {
			persistDataFile.delete();
		}
		ApplicationContext ctx=new ClassPathXmlApplicationContext(new String[]{"account-email.xml","account-persist.xml","account-captcha.xml","account-service.xml"});
		service=(AccountService) ctx.getBean("accountService");
	}
	@Test
	public void testLogin() {
		try {
			service.login("zhang", "123");
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testSignUp() throws Exception {
		SignUpRequest signUpRequest=new SignUpRequest();
		signUpRequest.setId("zhang");
		signUpRequest.setName("zhangsan");
		signUpRequest.setPassword("123");
		signUpRequest.setEmail("991718434@qq.com");
		service.signUp(signUpRequest);
		if (persistDataFile.exists()) {
			assertTrue(true);
		}
	}

}
