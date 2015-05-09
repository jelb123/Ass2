package com.enterprise.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.AddressBean;
import com.enterprise.beans.UserBean;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.business.support.UserServiceImpl;

public class RegisterUserCommand implements Command {

	private static UserService userService;

	public RegisterUserCommand() {
		userService = new UserServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserBean user = new UserBean();
		AddressBean addr = new AddressBean();
		try { 
			addr.setCity(request.getParameter("city"));
			addr.setStreetAddress(request.getParameter("streetaddress"));
			addr.setState(request.getParameter("state"));
			addr.setCountry(request.getParameter("country"));
			addr.setPostCode(Integer.parseInt(request.getParameter("postcode")));
			
			user.setAddress(addr);
			user.setUsername(request.getParameter("username"));
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setNickname(request.getParameter("nickname"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setDateOfBirth(request.getParameter("dateofbirth"));
			System.out.println(user.getDateOfBirth());
			user.setCreditCardNumber(Integer.parseInt(request.getParameter("creditcardnumber")));
			user.setAccountState(2); // Set account to not active (for email)
			user.setIsAdmin(false);
			
			userService.addUserRecord(user);
			request.setAttribute("regUser", userService.getUserByUsername(user.getUsername()));
			
			return "emailuser";
		} catch (UserServiceException e){
			e.printStackTrace();
			request.setAttribute("registerFailed", "true");
			return "/registerUser.jsp";	
			
		}
		
		
	}

}
