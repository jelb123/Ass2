package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.AddressBean;
import com.enterprise.beans.UserBean;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.business.support.UserServiceImpl;

public class UpdateUserDetailsCommand implements Command {
	
	private static UserService userService;
	
	public UpdateUserDetailsCommand() {
		userService = new UserServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		boolean somethingUpdated = false;
		
		if(user == null){
			request.setAttribute("msg", "No user is Logged in");
			return "/displayMsg.jsp";
		}
		AddressBean addr = user.getAddress();
		try { 
			if (request.getParameter("city") != null 
					&& !request.getParameter("city").equals("")
					&& !addr.getCity().equals(request.getParameter("city"))) {
				
				addr.setCity(request.getParameter("city"));
				somethingUpdated = true;
			}			

			if (request.getParameter("streetaddress") != null 
					&& !request.getParameter("streetaddress").equals("")
					&& !addr.getStreetAddress().equals(request.getParameter("streetaddress"))) {
				
				addr.setStreetAddress(request.getParameter("streetaddress"));
				somethingUpdated = true;
			}	
			
			if (request.getParameter("state") != null 
					&& !request.getParameter("state").equals("")
					&& !addr.getState().equals(request.getParameter("state"))) {
				
				addr.setState(request.getParameter("state"));
				somethingUpdated = true;
			}	
			
			if (request.getParameter("country") != null 
					&& !request.getParameter("country").equals("")
					&& !addr.getCountry().equals(request.getParameter("country"))) {
				
				addr.setCountry(request.getParameter("country"));
				somethingUpdated = true;
			}
			
			if (request.getParameter("postcode") != null 
					&& !request.getParameter("postcode").equals("")
					&& addr.getPostCode() != Integer.parseInt(request.getParameter("postcode"))) {
				
				addr.setPostCode(Integer.parseInt(request.getParameter("postcode")));
				somethingUpdated = true;
			}
					
			user.setAddress(addr);
			
			if (request.getParameter("username") != null 
					&& !request.getParameter("username").equals("")
					&& !user.getUsername().equals(request.getParameter("username"))) {
				
				user.setUsername(request.getParameter("username"));
				somethingUpdated = true;
			}
			if (request.getParameter("firstname") != null 
					&& !request.getParameter("firstname").equals("")
					&& !user.getFirstName().equals(request.getParameter("firstname"))) {
				
				user.setFirstName(request.getParameter("firstname"));
				somethingUpdated = true;
			}
			if (request.getParameter("lastname") != null 
					&& !request.getParameter("lastname").equals("")
					&& !user.getLastName().equals(request.getParameter("lastname"))) {
				
				user.setLastName(request.getParameter("lastname"));
				somethingUpdated = true;
			}
			
			if (request.getParameter("nickname") != null 
					&& !request.getParameter("nickname").equals("")
					&& !user.getNickname().equals(request.getParameter("nickname"))) {
				
				user.setNickname(request.getParameter("nickname"));
				somethingUpdated = true;
			}

			if (request.getParameter("password") != null 
					&& !request.getParameter("password").equals("")
					&& !user.getPassword().equals(request.getParameter("password"))) {
				
				user.setPassword(request.getParameter("password"));
				somethingUpdated = true;
			}
			
			if (request.getParameter("email") != null 
					&& !request.getParameter("email").equals("")
					&& !user.getEmail().equals(request.getParameter("email"))) {
				
				user.setEmail(request.getParameter("email"));
				somethingUpdated = true;
			}

			if (request.getParameter("dateofbirth") != null 
					&& !request.getParameter("dateofbirth").equals("")
					&& !user.getDateOfBirth().equals(request.getParameter("dateofbirth"))) {
				
				user.setDateOfBirth(request.getParameter("dateofbirth"));
				somethingUpdated = true;
			}
			
			if (request.getParameter("creditcardnumber") != null 
					&& !request.getParameter("creditcardnumber").equals("")
					&& user.getCreditCardNumber() != Integer.parseInt(request.getParameter("creditcardnumber"))) {
				
				user.setCreditCardNumber(Integer.parseInt(request.getParameter("creditcardnumber")));
				somethingUpdated = true;
			}

			if(somethingUpdated == false){
				request.setAttribute("msg", "You didnt even update anything");
				return "/displayMsg.jsp";
			} else {
				userService.updateUserRecord(user);
				request.getSession().setAttribute("user", user);
				request.setAttribute("msg", "You details have been updated");
				return "/displayMsg.jsp";
			}
		} catch (UserServiceException e){
			e.printStackTrace();

			return "";	
			
		}
	}

}
