package com.enterprise.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;

public class DisplayItemsCommand implements Command {
	private static ItemDAO itemDAO;
	
	public DisplayItemsCommand() {
		itemDAO = new ItemDAOImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			try {
				List<ItemBean> allItems = itemDAO.showAllItems();	//getting all the items in the database
				List<ItemBean> items = null;
				
				
				if (allItems.size() > 10) {
					Collections.shuffle(allItems);
				    items = allItems.subList(1, 11);
				} else {
					items = allItems;
				}
				
				request.setAttribute("items", items);
				return "/welcome.jsp";
				
			} catch(DataAccessException e) {
				e.printStackTrace();
				return "/welcome.jsp";
			}
		
		//return null;
	}

}
