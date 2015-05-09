package com.enterprise.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.business.support.ItemServiceImpl;
import com.enterprise.dao.DataAccessException;

public class AdvancedSearchCommand implements Command {

private static ItemServiceImpl itemASearch;
	
	public AdvancedSearchCommand() {
		itemASearch = new ItemServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String description = request.getParameter("description");
			String address = request.getParameter("address");
			float price = Float.parseFloat(request.getParameter("startPrice"));	
			
			List<ItemBean> results = itemASearch.advancedSearch(name, description, category, address, price);
			request.setAttribute("items", results);
			//if(results.isEmpty()) {}
			return "/welcome.jsp";
		
		} catch(DataAccessException e){
			e.printStackTrace();
			return "/welcome.jsp";
		
		} catch(ItemServiceException e){
			e.printStackTrace();
			return "/welcome.jsp";
		}
	}


}
