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
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;
import com.enterprise.mail.SendMail;
import com.enterprise.mail.UserEmailService;
import com.enterprise.mail.UserEmailServiceImpl;
import com.enterprise.mail.exception.UserEmailServiceException;

public class PlaceBidCommand implements Command {

	private static ItemService itemService;
	private static UserService userService;
	private static UserEmailService emailService;
	
	public PlaceBidCommand() {
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
			ItemBean item= itemService.getItemById(itemID);
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			float minBid = item.getHighestBid() + item.getBidIncrements();
			
			if (request.getParameter("bidamount") == null) {
				String msg = "Need a proper bid!";
				request.setAttribute("item", item);
				request.setAttribute("msg", msg);
				return "/displayItem.jsp";
			}
			float newBid = Integer.parseInt(request.getParameter("bidamount"));
			if (item.getOwnerID() == user.getId()) {
				String msg = "Cant Bid, You own Item";
				request.setAttribute("item", item);
				request.setAttribute("msg", msg);
				return "/displayItem.jsp";
			} else if (user.getId() == item.getHighestBidUserID()) {
				String msg = "Cant Bid, You're the highest bidder";
				request.setAttribute("item", item);
				request.setAttribute("msg", msg);
				return "/displayItem.jsp";
			} else if (newBid < minBid) {
				String msg = "Your bid is too low, must be at least " + minBid;
				request.setAttribute("item", item);
				request.setAttribute("msg", msg);
				return "/displayItem.jsp";
			}
			
			itemService.updateBid(itemID, newBid, user.getId());
			
			UserBean oldWinner = userService.getUserById(item.getHighestBidUserID());
			
			if (oldWinner.getId() != item.getOwnerID()) {
				String to = oldWinner.getEmail();
				String subject = "OutBidded";
				String url = request.getRequestURL().toString();
				String text = "You have been outbidded on item: \n" 
						+ url + "?operation=browseitem&item=" + itemID;
				emailService.sendEmail(to, subject, text);
			}
			
			String msg = "You are now the highest bidder";
			request.setAttribute("item", item);
			request.setAttribute("msg", msg);
			return "/displayItem.jsp";
			
		}  catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (ItemServiceException e) {
			e.printStackTrace();
			String msg = "Error: Invalid bid";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (UserServiceException e) {
			e.printStackTrace();
			String msg = "Error: Invalid bid";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (UserEmailServiceException e) {
			e.printStackTrace();
			String msg = "Error: Invalid bid";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (NullPointerException e) {
			e.printStackTrace();
			String msg = "Error: Invalid bid";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
	}

}
