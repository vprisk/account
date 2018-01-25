package com.yy.account.persist;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 账户持久化测试
 * @author Administrator
 *
 */
public class AccountPersistServiceTest {
	private AccountPersistService service;
	@Before
	public void prepare() throws Exception{
		File persistDataFile=new File("target/test-classes/persist-data.xml");
		if (persistDataFile.exists()) {
			persistDataFile.delete();
		}
		ApplicationContext ctx=new ClassPathXmlApplicationContext("account-persist.xml");
		service=(AccountPersistService) ctx.getBean("accountPersistService");
		Account account=new Account();
		account.setId("zhang");
		account.setName("zhangsan");
		account.setEmail("zhangsan@163.com");
		account.setPassword("123");
		account.setActivated(true);
		service.createAccount(account);
	}
	@Test
	public void testReadAccount() throws Exception{
		Account account = service.readAccount("zhang");
		Assert.assertNotNull(account);
		Assert.assertEquals("zhang", account.getId());
		Assert.assertEquals("zhangsan", account.getName());
		Assert.assertEquals("zhangsan@163.com", account.getEmail());
		Assert.assertEquals("123", account.getPassword());
		Assert.assertEquals(true, account.isActivated());
	}
}
