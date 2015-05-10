package com.enterprise.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.ItemBean;
import com.enterprise.beans.UserBean;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.business.support.ItemServiceImpl;
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
		if(user == null){
			request.setAttribute("msg", "No user is Logged in");
		}
		
		try {
			//int item_id = Integer.parseInt(request.getParameter("item"));	
			int user_id = user.getId();
			
			
			List<ItemBean> wishList = wishlistService.showWishlist(user_id);
			
			request.setAttribute("items", wishList);
			return "/displayWishlist.jsp";
		
		}  catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch(ItemServiceException e){
			e.printStackTrace();
			return "/welcome.jsp";
		} catch(DataAccessException e ){
			e.printStackTrace();
			return "/welcome.jsp";
		}
	}

}
