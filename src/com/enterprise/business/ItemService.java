package com.enterprise.business;

import java.util.List;

import com.enterprise.beans.ItemBean;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.dao.DataAccessException;

public interface ItemService {
	/**
	 * Inserts a new item in the database
	 * @param itemBean The item to insert
	 * @throws DataAccessException
	 */
	void insert(ItemBean itemBean) throws ItemServiceException;
	
	/**
	 * Removes an item from the database
	 * @param id the id of the item in database to remove
	 * @throws DataAccessException
	 */
	void delete(int id) throws ItemServiceException;
	
	/**
	 * Returns a list of all items in the database
	 * @return a list<ItemBean>
	 * @throws DataAccessException
	 */
	public List<ItemBean> showAllItems() throws ItemServiceException;
	
	/**
	 * This is used when "search" button is activated, the input string is searched in the database
	 * if it is contained in "title" "category" "description"
	 * @param searchString
	 * @return a list<ItemBean> that contains "searchString" input
	 */
	public List<ItemBean> findItemByString(String searchString) throws ItemServiceException;
	
	/**
	 * ADVANCED search, very narrow, all the fields must equal the table fields in the database
	 * @param name			of the product
	 * @param desc			
	 * @param category
	 * @param addr			of the postal area possibilities
	 * @param start_price
	 * @return				a list of the <itembean>
	 * @throws DataAccessException
	 */
	public List<ItemBean> advancedSearch(String name, String desc, String category, String addr, float start_price) throws ItemServiceException;
	
	/**
	 * Simply retrieves the itemBean that is equal to the id passed in (item_id)
	 * @param id refers to the item_id
	 * @return itemBean that contains this item_id
	 */
	public ItemBean getItemById(int id) throws ItemServiceException;
	
	/**
	 * Updates the database item if and only if the NEW bid_value > then the CURRENT big_value in the database  
	 * @param item_id
	 * @param bid_value		new highest bid value
	 * @param bidder_id 	the user_id of the new highest bidder
	 * @return if we update the value (ie. bid_value > current db_bid_value), ret = 1 else ret = 0
	 */
	public int updateBid(int item_id, float bid_value, int bidder_id) throws ItemServiceException;
	
	/**
	 * To be written and determined how to be handled
	 * @param item_id
	 * @param upTime
	 * 
	 */
	public void haltAuction(int item_id, int upTime) throws ItemServiceException;
	
	/*
	 * WISHLIST STUFF BELOW!!
	 */
	public List<ItemBean> showWishlist(int user_id) throws ItemServiceException;
	public void insertToWishlist(int item_id, int user_id) throws ItemServiceException;
	public void deleteFromWishlist(int item_id) throws ItemServiceException;
	public boolean isInWishlist(int itemID, int userID) throws ItemServiceException;
	
}
