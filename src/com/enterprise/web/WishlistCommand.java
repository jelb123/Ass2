package com.enterprise.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.beans.UserBean;
import com.enterprise.business.ItemService;
import com.enterprise.business.UserService;
import com.enterprise.business.support.ItemServiceImpl;
import com.enterprise.business.support.UserServiceImpl;
import com.enterprise.dao.DataAccessException;

public class WishlistCommand implements Command {

	private static ItemServiceImpl wishlistService;
	
	public WishlistCommand() {
		wishlistService = new ItemServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		ItemBean item = (ItemBean) request.getSession().getAttribute("item");
		if(user == null){
			request.setAttribute("msg", "No user is Logged in");
		}
		if(item == null){
			request.setAttribute("msg", "No item is available");
		}
		
		try {
			String search = request.getParameter("name");
			
			//List<ItemBean> results = itemService.findItemByString(search);
			//request.setAttribute("items", results);
			//if(results.isEmpty()) {}
			return "/welcome.jsp";
		
		} catch(DataAccessException e){
			e.printStackTrace();
			return "/welcome.jsp";
		}
		
		
		
		return null;
	}

}
