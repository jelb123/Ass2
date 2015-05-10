package com.enterprise.auctions;

public class AuctionParserFactory {
	private static AuctionParserFactory factory = null;
	private static AuctionParser auctionParser;
	
	/**
	 * Creates new auction parser
	 * Auction parser goes through all the auction items in the database and creates a runnable task for each item to check whether its finished
	 * or not
	 */
	private AuctionParserFactory() {
		auctionParser = new AuctionParser();
	}
	
	
	/**
	 * Initialisation
	 * @return an auctionParser
	 */
	public static AuctionParser getAuctionParserService() {
		if(factory == null) {
			factory = new AuctionParserFactory();
		}
		
		return auctionParser;
		
	}
}
