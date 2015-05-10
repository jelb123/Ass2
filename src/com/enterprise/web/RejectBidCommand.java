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

public class RejectBidCommand implements Command {
	private static ItemService itemService;
	private static UserService userService;
	private static UserEmailService emailService;
	
	public RejectBidCommand() {
		itemService = new ItemServiceImpl();
		userService = new UserServiceImpl();
		emailService = new UserEmailServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("item") == null) {
			String msg = "Invalid request (Item doesnt exist)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} else if (request.getSession().getAttribute("user") == null) {
			String msg = "Invalid request (User not logged in)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
		
		try {
			int itemID = Integer.parseInt(request.getParameter("item"));
			ItemBean item = itemService.getItemById(itemID);
			UserBean user = (UserBean) request.getSession().getAttribute("user");

			if (user.getId() == item.getOwnerID()) {
				UserBean oldWinner = userService.getUserById(item.getHighestBidUserID());
				String to = oldWinner.getEmail();
				String subject = "You won the auction";
				String url = request.getRequestURL().toString();
				String text = "Your bid was under reserve and was rejected on item: \n" 
						+ url + "?operation=browseitem&item=" + itemID;
				emailService.sendEmail(to, subject, text);
				
				String msg = "You have rejected the bid";
				request.setAttribute("item", item);
				request.setAttribute("msg", msg);
				return "/displayItem.jsp";
			} else {
				String msg = "You are not the owner of the item";
				request.setAttribute("msg", msg);
				return "/displayMsg.jsp";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
			
		} catch (ItemServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (UserServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String msg = "User doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (UserEmailServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String msg = "Couldnt email user";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
	}

}
