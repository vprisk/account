package com.yy.account.persist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class AccountPersistServiceImpl implements AccountPersistService {
	private static final String ELEMENT_ROOT = "account-persist";
	private static final String ELEMENT_ACCOUNTS = "accounts";
	private static final String ELEMENT_ACCOUNT = "account";
	private static final String ELEMENT_ACCOUNT_ID = "id";
	private static final String ELEMENT_ACCOUNT_NAME = "name";
	private static final String ELEMENT_ACCOUNT_EMAIL = "email";
	private static final String ELEMENT_ACCOUNT_PASSWORD = "password";
	private static final String ELEMENT_ACCOUNT_ACTIVATED = "activated";
	private String file;
	private SAXReader reader=new SAXReader();
	
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	/**
	 * 读取文档file,若不存在则创建
	 * @return
	 * @throws Exception
	 */
	private Document readDocument() throws Exception{
		File dataFile=new File(file);
		if (!dataFile.exists()) {
			dataFile.getParentFile().mkdirs();
			Document doc=DocumentFactory.getInstance().createDocument();
			Element rootEle=doc.addElement(ELEMENT_ROOT); 
			rootEle.addElement(ELEMENT_ACCOUNTS);
			writeDocument(doc);
		}
		try {
			return reader.read(dataFile);
		} catch (DocumentException e) {
			throw new Exception("数据文档读取异常", e);
		}
	}
	/**
	 * 将文档写入XML
	 * @param doc
	 * @throws Exception
	 */
	private void writeDocument(Document doc) throws Exception {
		Writer out=null;
		try {
			out=new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			XMLWriter writer=new XMLWriter(out, OutputFormat.createPrettyPrint());
			writer.write(doc);
		}catch (Exception e) {
			throw new Exception("数据文档写入异常", e);
		}finally {
			if (out!=null) {
				out.close();
			}
		}
		
	}
	/**
	 * 解析账户节点
	 * @param ele
	 * @return
	 */
	private Account buildAccount(Element ele){
		Account account=new Account();
		account.setId(ele.elementText(ELEMENT_ACCOUNT_ID));
		account.setName(ele.elementText(ELEMENT_ACCOUNT_NAME));
		account.setEmail(ele.elementText(ELEMENT_ACCOUNT_EMAIL));
		account.setPassword(ele.elementText(ELEMENT_ACCOUNT_PASSWORD));
		account.setActivated("true".equals(ele.elementText(ELEMENT_ACCOUNT_ACTIVATED))?true:false);
		return account;
	}
	@Override
	public Account createAccount(Account account) throws Exception {
		Document doc = readDocument();
		Element accountsEle = doc.getRootElement().element(ELEMENT_ACCOUNTS);
		Element accountEle = accountsEle.addElement(ELEMENT_ACCOUNT);
		accountEle.addElement(ELEMENT_ACCOUNT_ID).addText(account.getId());
		accountEle.addElement(ELEMENT_ACCOUNT_NAME).addText(account.getName());
		accountEle.addElement(ELEMENT_ACCOUNT_EMAIL).addText( account.getEmail());
		accountEle.addElement(ELEMENT_ACCOUNT_PASSWORD).addText( account.getPassword());
		accountEle.addElement(ELEMENT_ACCOUNT_ACTIVATED).addText( account.isActivated()?"true":"false");
		writeDocument(doc);
		return account;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Account readAccount(String id) throws Exception {
		Document doc = readDocument();
		Element accountsEle = doc.getRootElement().element(ELEMENT_ACCOUNTS);
		for(Element accountEle:(List<Element>)accountsEle.elements()){
			if (id.equals(accountEle.elementText(ELEMENT_ACCOUNT_ID))) {
				return buildAccount(accountEle);
			}
		}
		return null;
	}

	@Override
	public Account updateAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(String id) throws Exception {
		// TODO Auto-generated method stub

	}

}
