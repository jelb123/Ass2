package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.beans.UserBean;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserLoginFailedException;
import com.enterprise.business.support.UserServiceImpl;

public class LoginCommand implements Command {
	
	private static UserService userService;
	
	public LoginCommand() {
		userService = new UserServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			UserBean user = userService.login(username, password);

			if (user == null) {
				request.setAttribute("msg", "Username or Password Incorrect");
				return "/login.jsp";
			} else if(user.getAccountState() == 2) {
				request.setAttribute("msg", "Account is not activated (activate through email link)");
				return "/login.jsp";
			} else if(user.getAccountState() == 3) {
				request.setAttribute("msg", "Your account is banned");
				return "/login.jsp";
			} else {
				session.setAttribute("user", user);
				return "displayItemsList";
			}
		} catch (UserLoginFailedException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Username or Password Incorrect");
			return "/login.jsp";			
		}
	}
}
