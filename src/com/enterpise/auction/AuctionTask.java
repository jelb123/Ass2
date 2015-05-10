package com.enterpise.auction;

import java.sql.Timestamp;

import com.enterprise.beans.ItemBean;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;

public class AuctionTask implements Runnable{

	private ItemBean item;
	
	public AuctionTask(ItemBean item) {
		super();
		this.item = item;
	}
	
	@Override
	public void run() {
		try {
			ItemDAO itemDAO = new ItemDAOImpl();
			
			Timestamp closeTime = item.getEndTime();	
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());	//get current time
			
			//Check if the specified auction time has elapsed
			if(currentTime.after(closeTime)) {
				itemDAO.haltAuction(item.getItemID());	//Stop the auction
				
				//checking whether or not the highest bid is greater than the reserve price
				if(item.getHighestBid() >= item.getReservePrice().getPrice()) {
					
					//NEED TO IMPLEMENT
					//NOTIFY WINNER AND OWNER BY EMAIL
					
				} else {	//BID HAS NOT MET RESERVE PRICE
					
					//NEED TO IMPLEMENT
					//NOTIFY OWNER AND OWNER WILL EITHER ACCEPT OR REJECT THE BID
					//CHECK TO MAKE SURE THAT THE OWNER ISNT THE HIGHEST BIDDER I.E. NO ONE HAS BID ON THE ITEM
				}
				
			}
			
			
		} catch(DataAccessException e){
			e.printStackTrace();
		}
		
		
	}

}
