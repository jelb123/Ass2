package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.business.UserService;
import com.enterprise.business.support.UserServiceImpl;
import com.enterprise.mail.UserEmailService;

public class EmailUserCommand implements Command {

	private static UserEmailService emailService;
	
	public EmailUserCommand() {
		
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getRequestURI());
		String to = request.getParameter("email");
		String subject = "Account Activation";
		String text = "Go to the link to activate: %n";
		
		return null;
	}

}
