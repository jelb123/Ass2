package com.enterprise.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.beans.ItemBean;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;

public class SearchItemsCommand implements Command {

	private ItemDAO itemDAO;
	
	public SearchItemsCommand() {
		itemDAO = new ItemDAOImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		
		try {
			String search = request.getParameter("name");
			
			List<ItemBean> results = itemDAO.findItemByString(search);
			request.setAttribute("items", results);
			//if(results.isEmpty()) {}
			return "/welcome.jsp";
		
		} catch(DataAccessException e){
			e.printStackTrace();
			return "/welcome.jsp";
		}
		
		//return null;
	}

}
