package com.enterprise.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.beans.UserBean;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.business.support.UserServiceImpl;

public class AdminShowUsersCommand implements Command {

	private static UserService userService;
	
	public AdminShowUsersCommand() {
		userService = new UserServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserBean user = null;
		try {
			user = (UserBean) session.getAttribute("user");
			if (user == null || user.getIsAdmin() == false) {
				request.setAttribute("msg", "You aren't an admin");
				return "/displayMsg.jsp";
			}
			
			List<UserBean> usersList = userService.getAllUsers();
			request.setAttribute("userslist", usersList);
			return "/displayUsers.jsp";
			
		} catch (UserServiceException e){
			e.printStackTrace();
			request.setAttribute("msg", "Cant Show user");
			return "/displayMsg.jsp";	
		}
		
	}

}
