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
		
		ItemBean item = new ItemBean();
		AddressBean address = new AddressBean();
		PriceBean reserveBean = new PriceBean();
		PriceBean startBean = new PriceBean();
		UserBean user = (UserBean) request.getSession().getAttribute("user"); 
		
		try {
			item.setTitle(request.getParameter("name"));
			item.setCategory(request.getParameter("category"));
			item.setPicture(request.getParameter("picture"));
			item.setDescription(request.getParameter("description"));
			item.setOwnerID(user.getId());
			
			address.setStreetAddress(request.getParameter("streetAddress"));
			address.setCity(request.getParameter("city"));
			address.setState(request.getParameter("state"));
			address.setCountry(request.getParameter("country"));
			address.setPostCode(Integer.parseInt(request.getParameter("postCode")));
			item.setAddress(address);
			
			reserveBean.setCurrency(request.getParameter("resCurrency"));
			reserveBean.setPrice(Float.parseFloat(request.getParameter("reservePrice")));
			item.setReservePrice(reserveBean);
			
			startBean.setCurrency(request.getParameter("startCurrency"));
			startBean.setPrice(Float.parseFloat(request.getParameter("startPrice")));
			item.setStartPrice(startBean);
			
			item.setBidIncrements(Float.parseFloat(request.getParameter("bidIncrement")));
			item.setAuctionLength(Integer.parseInt(request.getParameter("auctionLength")));
			
			//Date now = new Date();
			
			
			item.setEndTime(Integer.parseInt(request.getParameter("auctionLength")));	//TEMPORARY
			
			/*
			private int itemID;
			private int ownerID;
			
			private float highestBid;
			private int highestBidUserID;
			*/
			
			wishlistService.insertToWishlist(item);
			request.setAttribute("msg", "Item Added to wishlist Successfully");
			return "/itemAdded.jsp";
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Adding Item to wishlist Failed");
			return "/itemAdded.jsp";	
		} catch (ItemServiceException e){
			e.printStackTrace();
			request.setAttribute("msg", "Adding Item to wishlist Failed");
			return "/itemAdded.jsp";
		}
	}

}
