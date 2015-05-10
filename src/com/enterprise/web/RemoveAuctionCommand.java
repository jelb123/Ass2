package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.beans.UserBean;
import com.enterprise.business.ItemService;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.business.support.ItemServiceImpl;

public class RemoveAuctionCommand implements Command {
	private static ItemService itemService;
	
	public RemoveAuctionCommand() {
		itemService = new ItemServiceImpl();
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
			itemService.delete(itemID);
			itemService.deleteFromWishlist(itemID);
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
		}
		
		
		String msg = "Auction has been removed";
		request.setAttribute("msg", msg);
		return "/displayMsg.jsp";
	}

}
