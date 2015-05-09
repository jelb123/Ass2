package com.enterprise.business.support;

import java.util.List;

import com.enterprise.beans.ItemBean;
import com.enterprise.business.ItemService;
import com.enterprise.business.exception.ItemServiceException;
import com.enterprise.dao.DAOFactory;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;

public class ItemServiceImpl implements ItemService{
	
	private ItemDAO itemDAO;
	
	
	public ItemServiceImpl() {
		super();
		itemDAO = DAOFactory.getInstance().getItemDAO();
	}
	
	
	@Override
	public void insert(ItemBean itemBean) throws ItemServiceException {
		try {
			itemDAO.insert(itemBean);
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to add item record", e);
		}
		
	}

	@Override
	public void delete(int id) throws ItemServiceException {
		try {
			itemDAO.delete(id);
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to delete item record", e);
		} 
		
	}

	@Override
	public List<ItemBean> showAllItems() throws ItemServiceException {
		try {
			List<ItemBean> itemList = itemDAO.showAllItems();
			return itemList;
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to return list of <ItemBean> held in db", e);
		} 
		
	}

	@Override
	public List<ItemBean> findItemByString(String searchString) throws ItemServiceException {
		try {
			List<ItemBean> itemList = itemDAO.findItemByString(searchString);
			return itemList;
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to find item by string", e);
		} 
	}

	@Override
	public ItemBean getItemById(int id) throws ItemServiceException {
		try {
			ItemBean item = itemDAO.getItemById(id);
			return item;
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to find the item by ID", e);
		} 
	}

	@Override
	public int updateBid(int item_id, float bid_value, int bidder_id) throws ItemServiceException {
		try {
			int updated = itemDAO.updateBid(item_id, bid_value, bidder_id);
			return updated;
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to update the bid", e);
		} 
	}

	@Override
	public void haltAuction(int item_id, int upTime) {
		// TODO Auto-generated method stub
		
	}

	
	/*
	 * WISHLIST STUFF BELOW!!!
	 */

	@Override
	public List<ItemBean> showWishlist(int user_id) throws ItemServiceException {
		try {
			List<ItemBean> wishList = itemDAO.showWishlist(user_id);
			return wishList;
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to return WishList of <ItemBean> held in db", e);
		} 
		
	}


	@Override
	public void insertToWishlist(int item_id, int user_id) throws ItemServiceException {
		try {
			itemDAO.insertToWishlist(item_id, user_id);
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to add item to Wishlist", e);
		}		
	}


	@Override
	public void deleteFromWishlist(int item_id) throws ItemServiceException {
		try {
			itemDAO.deleteFromWishlist(item_id);
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to delete item from Wishlist", e);
		} 
	}
	
	public boolean isInWishlist(int itemID, int userID) throws ItemServiceException {
		try {
			return itemDAO.isInWishlist(itemID, userID);
		} catch (DataAccessException e) {
			throw new ItemServiceException("Unable to find row", e);
		}
	}

}
