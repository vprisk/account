package com.yy.account.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yy.account.service.AccountService;

/**
 * Servlet implementation class Captcha
 */
public class Captcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ApplicationContext context;
    @Override
    public void init() throws ServletException {
    	super.init();
    	context= WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountService service=(AccountService) context.getBean("accountService");
		String captchaKey = service.generateCaptchaKey();
		try {
			byte[] image = service.generateCaptchaImage(captchaKey);
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			out.write(image);
			out.close();
		} catch (Exception e) {
			response.sendError(404, e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
