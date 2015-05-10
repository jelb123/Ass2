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
import com.enterprise.business.support.ItemServiceImpl;
import com.enterprise.business.support.UserServiceImpl;
import com.enterprise.mail.UserEmailService;
import com.enterprise.mail.UserEmailServiceImpl;

public class HaltAuctionCommand implements Command {
	private static ItemService itemService;
	
	public HaltAuctionCommand() {
		itemService = new ItemServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("item") == null) {
			String msg = "Auction cant be halted, item doesnt exist";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} else if (request.getSession().getAttribute("user") == null) {
			String msg = "Auction cant be halted, user doesnt exist";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		
		if(user.getIsAdmin() == false) {
			String msg = "Auction cant be halted, you're not admin";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
		
		int itemID = Integer.parseInt(request.getParameter("item"));
		ItemBean item = null;
		try {
			item = itemService.haltAuction(itemID);
			request.setAttribute("item", item);
			return "browseitem";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (ItemServiceException e) {
			e.printStackTrace();
			String msg = "Auction cant be halted";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		}
	}

}
