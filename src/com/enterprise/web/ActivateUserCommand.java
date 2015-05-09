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
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			if (userService.getUserById(id) == null) {
				request.setAttribute("msg", "Account id: " + id + " Couldnt be activated (account doesnt exist)");
				return "/displayMsg.jsp";
			} else if (userService.getUserById(id).getAccountState() == 3) {
				request.setAttribute("msg", "Account id: " + id + " Couldnt be activated (Account is banned)");
				return "/displayMsg.jsp";
			} else if (userService.getUserById(id).getAccountState() == 1) {
				request.setAttribute("msg", "Account id: " + id + " is already active m8");
				return "/displayMsg.jsp";
			} 
			
			int newState = 1;
			userService.updateUserState(id, newState);
			

				
			request.setAttribute("msg", "Your Account has been Activated, please login");
			return "/login.jsp";
		} catch (UserServiceException e) {
			e.printStackTrace();
			
			request.setAttribute("msg", "Account id: " + id + " Couldnt be activated (Doesnt exist)");
			return "/displayMsg.jsp";
			
		}
	}

}
