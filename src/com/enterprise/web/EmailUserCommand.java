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
		// TODO Pull email from user send to him
		return null;
	}

}
