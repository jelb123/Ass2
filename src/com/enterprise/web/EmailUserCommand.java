package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.UserBean;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.business.support.UserServiceImpl;
import com.enterprise.mail.UserEmailService;
import com.enterprise.mail.UserEmailServiceImpl;
import com.enterprise.mail.exception.UserEmailServiceException;

public class EmailUserCommand implements Command {

	private static UserEmailService emailService;
	
	public EmailUserCommand() {
		emailService = new UserEmailServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		UserBean user = (UserBean) request.getAttribute("regUser");
		try {
			
			int id = user.getId();
			String url = request.getRequestURL().toString();
			String to = request.getParameter("email");
			String subject = "Account Activation";
			String text = "Go to the link to activate: \n" 
					+ url + "?operation=activate&id=" + id
					+ "\n\n Username: " + user.getUsername()
					+ "\n Password: " + user.getPassword();
			
			emailService.sendEmail(to, subject, text);
			
			request.setAttribute("msg", "You have been registered! Activate through email");
			return "/displayMsg.jsp";
		} catch (UserEmailServiceException e) {
			e.printStackTrace();
			UserService userService = new UserServiceImpl();
			try {
				userService.deleteUserRecord(user.getId());
			} catch (UserServiceException ex) {
				ex.printStackTrace();
			}
			
			request.setAttribute("emailFailed", "true");
			return "/registerUser.jsp";
			
		}
				
	}

}
