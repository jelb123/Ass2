package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.AddressBean;
import com.enterprise.beans.ItemBean;
import com.enterprise.beans.PriceBean;
import com.enterprise.beans.UserBean;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.business.support.ItemServiceImpl;
import com.enterprise.dao.DataAccessException;

public class AddWishlistCommand implements Command {
	
	private static ItemServiceImpl wishlistService;
	
	public AddWishlistCommand() {
		wishlistService = new ItemServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		UserBean user = (UserBean) request.getSession().getAttribute("user"); 
		
		try {
			
			if(user == null){
				System.out.println("user = null\n");
			}
			int user_id = user.getId();
			int item_id = Integer.parseInt(request.getParameter("item"));
			if (wishlistService.isInWishlist(item_id, user_id) == false) {
				wishlistService.insertToWishlist(item_id, user_id);
				request.setAttribute("msg", "Item Added to wishlist Successfully");
			} else {
				request.setAttribute("msg", "Item already in wishlist");
			}
			return "/displayWishlist.jsp";
			
		}  catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "Item doesnt exist (id is invalid)";
			request.setAttribute("msg", msg);
			return "/displayMsg.jsp";
		} catch (DataAccessException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Adding Item to wishlist Failed");
			return "/displayWishlist.jsp";	
		} catch (ItemServiceException e){
			e.printStackTrace();
			request.setAttribute("msg", "Adding Item to wishlist Failed");
			return "/displayWishlist.jsp";
		}
	}

}
