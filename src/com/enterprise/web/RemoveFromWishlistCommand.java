package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.UserBean;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.business.support.ItemServiceImpl;
import com.enterprise.dao.DataAccessException;

public class RemoveFromWishlistCommand implements Command {
	
	private static ItemServiceImpl wishlistService;
	
	public RemoveFromWishlistCommand() {
		wishlistService = new ItemServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			UserBean user = (UserBean) request.getSession().getAttribute("user"); 
		
		try {
			
			if(user == null){
				System.out.println("user = null !!\n");
			}
			//int user_id = user.getId();
			int item_id = Integer.parseInt(request.getParameter("item"));
			wishlistService.deleteFromWishlist(item_id);
			request.setAttribute("msg", "Item removed from wishlist Successfully");
			return "/displayWishlist.jsp";
			
		}  catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (DataAccessException e) {
			e.printStackTrace();
			request.setAttribute("msg", "REMOVING Item from wishlist Failed");
			return "/displayWishlist.jsp";	
		} catch (ItemServiceException e){
			e.printStackTrace();
			request.setAttribute("msg", "REMOVING Item from wishlist Failed");
			return "/displayWishlist.jsp";
		}
	}

}
