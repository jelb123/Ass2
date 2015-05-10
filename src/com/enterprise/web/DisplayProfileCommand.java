package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.beans.UserBean;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.business.support.UserServiceImpl;

public class DisplayProfileCommand implements Command {
	
	private static UserService userService;
	
	public DisplayProfileCommand() {
		userService = new UserServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserBean user = null;
		user = (UserBean) session.getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Not logged in");
			return "/displayMsg.jsp";
		} else if (request.getParameter("id") != null || request.getParameter("id") != "") {
			if (user.getIsAdmin() == true) {
				try {
					user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
				} catch (UserServiceException e) {
					request.setAttribute("msg", "Invalid id given");
					return "/displayMsg.jsp";
				} catch (NumberFormatException e) {
					e.printStackTrace();
					String msg = "User doesnt exist (id is invalid)";
					request.setAttribute("msg", msg);
					return "/displayMsg.jsp";
				}
			}
		}
		
		request.setAttribute("account", user);
		return "/displayProfile.jsp";
		
	}

}