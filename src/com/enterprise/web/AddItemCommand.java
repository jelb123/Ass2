package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.AddressBean;
import com.enterprise.beans.ItemBean;
import com.enterprise.beans.PriceBean;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;


public class AddItemCommand implements Command {
	
	private static ItemDAO itemDAO;
	
	public AddItemCommand() {
		itemDAO = new ItemDAOImpl(); 
		
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ItemBean item = new ItemBean();
		AddressBean address = new AddressBean();
		PriceBean reserveBean = new PriceBean();
		PriceBean startBean = new PriceBean();
		
		try {
			item.setTitle(request.getParameter("name"));
			item.setCategory(request.getParameter("category"));
			item.setPicture(request.getParameter("picture"));
			item.setDescription(request.getParameter("description"));
			
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
			
			item.setBidIncrements(Float.parseFloat(request.getParameter("BidIncrement")));
			item.setAuctionLength(Integer.parseInt(request.getParameter("auctionLength")));
			
			//Date now = new Date();
			
			
			item.setEndTime(Integer.parseInt(request.getParameter("auctionLength")));	//TEMPORARY
			
			/*
			private int itemID;
			private int ownerID;
			
			private float highestBid;
			private int highestBidUserID;
			*/
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			request.setAttribute("addItemFailed", "true");
			return "/itemAdded.jsp";	
		}
		
		return null;
	}

}
