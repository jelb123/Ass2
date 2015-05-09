package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.business.support.UserServiceImpl;

public class ActivateUserCommand implements Command {
	
	private static UserService userService;
	
	public ActivateUserCommand() {
		userService = new UserServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			int newState = 1;
			userService.updateUserState(id, newState);
			
			
		} catch (UserServiceException e) {
			e.printStackTrace();
			
		}
		return null;
	}

}
