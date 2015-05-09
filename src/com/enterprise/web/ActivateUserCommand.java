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
	private int newState;
	public ActivateUserCommand(int state) {
		userService = new UserServiceImpl();
		newState = state;
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
				if (newState != 3) {
					request.setAttribute("msg", "Account id: " + id + " is already active m8");
					return "/displayMsg.jsp";
				}
			} 
			
			userService.updateUserState(id, newState);
			

			if (newState == 1) {
				request.setAttribute("msg", "Your Account has been Activated, please login");
				return "/login.jsp";
			} else {
				request.setAttribute("msg", userService.getUserById(id).getUsername() + "has been banned");
				return "adminshowusers";
			}
		} catch (UserServiceException e) {
			e.printStackTrace();
			
			request.setAttribute("msg", "Account id: " + id + " Couldnt be activated (Doesnt exist)");
			return "/displayMsg.jsp";
			
		}
	}

}
