package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;

public class BrowseItemCommand implements Command {
	private static ItemDAO itemDAO;
	
	public BrowseItemCommand() {
		itemDAO = new ItemDAOImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int itemID = Integer.parseInt(request.getParameter("item"));
			ItemBean item = itemDAO.getItemById(itemID);
			request.setAttribute("item", item);
			return "/displayItem.jsp";
			
			
		} catch(DataAccessException e) {
			e.printStackTrace();
			return "/welcome.jsp";
		}
		
		//return null;
	}

}
