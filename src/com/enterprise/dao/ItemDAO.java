package com.enterprise.dao;

import java.util.List;

import com.enterprise.beans.ItemBean;


/** 
 * The Data Access Object for Auction Items
 *
 */
public interface ItemDAO {
	
	/**
	 * Inserts a new item in the database
	 * @param itemBean The item to insert
	 * @throws DataAccessException
	 */
	void insert(ItemBean itemBean) throws DataAccessException;
	
	/**
	 * Removes an item from the database
	 * @param id the id of the item in database to remove
	 * @throws DataAccessException
	 */
	void delete(int id) throws DataAccessException;
	
	/**
	 * Returns a list of all items in the database
	 * @return a list<ItemBean>
	 * @throws DataAccessException
	 */
	public List<ItemBean> showAllItems() throws DataAccessException;
	
	/**
	 * This is used when "search" button is activated, the input string is searched in the database
	 * if it is contained in "title" "category" "description"
	 * @param searchString
	 * @return a list<ItemBean> that contains "searchString" input
	 */
	public List<ItemBean> findItemByString(String searchString) throws DataAccessException;
	
	/**
	 * Simply retrieves the itemBean that is equal to the id passed in (item_id)
	 * @param id refers to the item_id
	 * @return itemBean that contains this item_id
	 */
	public ItemBean getItemById(int id) throws DataAccessException;;
	
	/**
	 * Updates the database item if and only if the NEW bid_value > then the CURRENT big_value in the database  
	 * @param item_id
	 * @param bid_value		new highest bid value
	 * @param bidder_id 	the user_id of the new highest bidder
	 * @return if we update the value (ie. bid_value > current db_bid_value), ret = 1 else ret = 0
	 */
	public int updateBid(int item_id, float bid_value, int bidder_id) throws DataAccessException;
	
	/**
	 * To be written and determined how to be handled
	 * @param item_id
	 * @param upTime
	 * 
	 */
	public void haltAuction(int item_id, int upTime) throws DataAccessException;
	
	
	
	
	/*
	 * 	this is for wishlist shit
	 * 
	 */
	public List<ItemBean> showWishlist(int user_id) throws DataAccessException;
	public void insertToWishlist(int item_id, int user_id) throws DataAccessException;
	public void deleteFromWishlist(int item_id) throws DataAccessException;
	
	
}
