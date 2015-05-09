package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.beans.UserBean;
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;

public class PlaceBidCommand implements Command {

	private static ItemDAO itemDAO;
	
	public PlaceBidCommand() {
		itemDAO = new ItemDAOImpl();
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
		
		int itemID = Integer.parseInt(request.getParameter("item"));
		ItemBean item= itemDAO.getItemById(itemID);
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		if (item.getOwnerID() == user.getId()) {
			String msg = "Cant Bid, You own Item";
			request.setAttribute("item", item);
			request.setAttribute("msg", msg);
			return "/displayItem.jsp";
		}
		
		try {
			
		} catch (Exception e) {
			
		}
		return null;
	}

}
