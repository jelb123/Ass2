package com.enterprise.dao;

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
	
}
