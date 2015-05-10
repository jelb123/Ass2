package com.enterprise.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.beans.AddressBean;
import com.enterprise.beans.ItemBean;
import com.enterprise.beans.PriceBean;
import com.enterprise.beans.UserBean;
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
		UserBean user = (UserBean) request.getSession().getAttribute("user"); 
		
		if (user == null) {
			request.setAttribute("msg", "Login to add item");
			return "/login.jsp";
		}
		
		
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
			
			int auctionTime;
			if (request.getParameter("auctionLength") == null || request.getParameter("auctionLength").equals("")) {
				auctionTime = 10;
			} else {
				auctionTime = Integer.parseInt(request.getParameter("auctionLength"));
			}
			item.setAuctionLength(auctionTime);
			
			
			//Setting the endtime, using the time in millis since January 1 1970
			Date current = new Date();
			Timestamp startTime = new Timestamp(current.getTime());
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(startTime.getTime());
			cal.add(Calendar.MINUTE,auctionTime);
			Timestamp endTime = new Timestamp(cal.getTime().getTime());
			item.setEndTime(endTime);
			
			//item.setEndTime(auctionTime);	//TEMPORARY
			
			/*
			private int itemID;
			private int ownerID;
			
			private float highestBid;
			private int highestBidUserID;
			*/
			
			itemDAO.insert(item);
			request.setAttribute("msg", "Item Added Successfully");
			return "/itemAdded.jsp";
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			request.setAttribute("msg", "Adding Item Failed");
			return "/itemAdded.jsp";	
		}
		
		//return null;
	}

}
