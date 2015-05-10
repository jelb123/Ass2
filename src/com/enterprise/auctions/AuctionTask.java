package com.enterprise.auctions;

import java.sql.Timestamp;

import com.enterprise.beans.ItemBean;
import com.enterprise.beans.UserBean;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.UserDAO;
import com.enterprise.dao.support.ItemDAOImpl;
import com.enterprise.dao.support.UserDAOImpl;
import com.enterprise.mail.UserEmailService;
import com.enterprise.mail.UserEmailServiceImpl;
import com.enterprise.mail.exception.UserEmailServiceException;

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
			UserDAO userDAO = new UserDAOImpl();
			
			Timestamp closeTime = item.getEndTime();	
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());	//get current time
			
			//Check if the specified auction time has elapsed
			if(currentTime.after(closeTime)) {
				itemDAO.haltAuction(item.getItemID());	//Stop the auction
				
				UserEmailService emailService = new UserEmailServiceImpl();
				
				int ownerID = item.getOwnerID();
				int winnerID = item.getHighestBidUserID();
				
				UserBean owner = userDAO.findUserByID(ownerID);
				UserBean winner = userDAO.findUserByID(winnerID);
				
				String ownerEmail = owner.getEmail();
				String winnerEmail = winner.getEmail();
				
				//checking whether or not the highest bid is greater than the reserve price
				if(item.getHighestBid() >= item.getReservePrice().getPrice() && item.getOwnerID() != item.getHighestBidUserID()) {
					
					String toWinner = winnerEmail;
					String subject = "You have won your auction!";
					String url = "http://localhost:8080/Ass2/dispatcher";
					String text = "You have won the item: \n" 
							+ url + "?operation=browseitem&item=" + item.getItemID()
							+ "\n\n Your bid was: " + item.getHighestBid();
					emailService.sendEmail(toWinner, subject, text);
					
					String toOwner = ownerEmail;
					subject = "Your auction has ended!";
					text = "Your auction has ended for item: \n" 
							+ url + "?operation=browseitem&item=" + item.getItemID()
							+ "\n\n The winner was: " + winner.getUsername() 
							+ " with a bid of: " + item.getHighestBid();
					emailService.sendEmail(toOwner, subject, text);
					
					// TODO - NEED TO IMPLEMENT
					//NOTIFY WINNER AND OWNER BY EMAIL
					
				} else {	//BID HAS NOT MET RESERVE PRICE
					
					if (item.getOwnerID() != item.getHighestBidUserID()) {
						String toOwner = ownerEmail;
						String url = "http://localhost:8080/Ass2/dispatcher";
						String subject = "Your auction has ended!";
						String text = "Your auction has ended but reserve hasnt benn met for item: \n" 
								+ url + "?operation=browseitem&item=" + item.getItemID()
								+ "\n\n The winner was: " + winner.getUsername() 
								+ " with a bid of: " + item.getHighestBid()
								+  "\n\n To accept the bid go to: " + url 
								+ "?operation=acceptbid&item=" + item.getItemID() 
								+ "\n\n To reject go to " + url 
								+ "?operation=rejectbid&item=" + item.getItemID();
						emailService.sendEmail(toOwner, subject, text);
					} else {
						String toOwner = ownerEmail;
						String url = "http://localhost:8080/Ass2/dispatcher";
						String subject = "Your auction has ended!";
						String text = "Your auction has ended but noone bid on your item: \n" 
								+ url + "?operation=browseitem&item=" + item.getItemID();
						emailService.sendEmail(toOwner, subject, text);
					}
					//NEED TO IMPLEMENT
					//NOTIFY OWNER AND OWNER WILL EITHER ACCEPT OR REJECT THE BID
					//CHECK TO MAKE SURE THAT THE OWNER ISNT THE HIGHEST BIDDER I.E. NO ONE HAS BID ON THE ITEM
				}
				//
			}
			
			
		} catch(DataAccessException e){
			e.printStackTrace();
		} catch (UserEmailServiceException e) {
			e.printStackTrace();
		}
		
		
	}

}
