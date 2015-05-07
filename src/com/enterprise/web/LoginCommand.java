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
				request.setAttribute("loginFailed", "true");
				return "/login.jsp";
			} else {
				session.setAttribute("user", user);
				return "/welcome.jsp";
			}
		} catch (UserLoginFailedException e) {
			e.printStackTrace();
			request.setAttribute("loginFailed", "true");
			return "/login.jsp";			
		}
	}
}
