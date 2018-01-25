package com.yy.account.persist;
/**
 * 账户持久化
 * @author Administrator
 *
 */
public interface AccountPersistService {
	/**
	 * 添加账户到xml文档
	 * @param account
	 * @return
	 * @throws Exception
	 */
	Account createAccount(Account account) throws Exception;
	Account readAccount(String id) throws Exception;
	Account updateAccount(Account account) throws Exception;
	void deleteAccount(String id) throws Exception;
}
