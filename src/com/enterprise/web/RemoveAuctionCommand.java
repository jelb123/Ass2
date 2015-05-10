package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.beans.UserBean;
import com.enterprise.business.ItemService;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.business.support.ItemServiceImpl;
import com.enterprise.business.support.UserServiceImpl;
import com.enterprise.mail.UserEmailService;
import com.enterprise.mail.UserEmailServiceImpl;
import com.enterprise.mail.exception.UserEmailServiceException;

public class RemoveAuctionCommand implements Command {
	
	private static ItemService itemService;
	private static UserEmailService emailService;
	private static UserService userService;

	public RemoveAuctionCommand() {
		itemService = new ItemServiceImpl();
		emailService = new UserEmailServiceImpl();
		userService = new UserServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("item") == null) {
			String msg = "Auction cant be removed, item doesnt exist";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} else if (request.getSession().getAttribute("user") == null) {
			String msg = "Auction cant be removed, user doesnt exist";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		
		if(user.getIsAdmin() == false) {
			String msg = "Auction cant be removed, you're not admin";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
		
		int itemID = Integer.parseInt(request.getParameter("item"));
		try {
			
			ItemBean item = itemService.getItemById(itemID);
			
			itemService.delete(itemID);
			itemService.deleteFromWishlist(itemID);
			
			UserBean owner = userService.getUserById(item.getOwnerID());
			
			int id = user.getId();
			String url = request.getRequestURL().toString();
			String to = owner.getEmail();
			String subject = "Your Auction has been removed";
			String text = "Your auction has been removed by an admin for the item: \n\t\t" 
					+ item.getTitle();
			
			emailService.sendEmail(to, subject, text);
			
		}  catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (ItemServiceException e) {
			e.printStackTrace();
			String msg = "Auction cant be removed";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (UserServiceException e) {
			// Auction is still removed just wasnt able to retrieve user;
			e.printStackTrace();
		} catch (UserEmailServiceException e) {
			// Auction is still removed just wasnt able to Send email;
			e.printStackTrace();
		}
		
		
		String msg = "Auction has been removed";
		request.setAttribute("msg", msg);
		return "/displayMsg.jsp";
	}

}
