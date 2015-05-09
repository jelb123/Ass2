package com.enterprise.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enterprise.beans.AddressBean;
import com.enterprise.beans.ItemBean;
import com.enterprise.beans.PriceBean;
import com.enterprise.beans.UserBean;
import com.enterprise.common.DBConnectionFactory;
import com.enterprise.common.ServiceLocatorException;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.ItemDAO;

public class ItemDAOImpl implements ItemDAO {
	
	/*
	 * Initialisation of bullshit variables that are needed for connected to the Database
	 */
	private DBConnectionFactory services;
	public ItemDAOImpl() {
		try {
			services = new DBConnectionFactory();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	}
	public ItemDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}
	
	
	
	/*
	 * This prepares the SQL statements that will read user input and convert it into SQL insert statements
	 * into the AUCTION_ITEM table
	 */
	@Override
	public void insert(ItemBean itemBean) throws DataAccessException {
		try {
			Connection con = services.createConnection();
			PreparedStatement ps = con.prepareStatement(
				"insert into TBL_ITEMS (item_id, ownerID, title, category, " +
				"picture, description, address, reservePrice, startPrice, " + 
				"bidIncrements, endTime, highestBid, highest_bid_user_ID)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			/*
			 * read values in from input form
			 */
			//ps.setInt(11, itemBean.getId()); the item_id is automatically generated
			
			ps.setInt(1, itemBean.getItemID());
			ps.setInt(2, itemBean.getOwnerID());
			ps.setString(3, itemBean.getTitle());
			ps.setString(4, itemBean.getCategory());
			ps.setString(5, itemBean.getPicture());
			ps.setString(6, itemBean.getDescription());
			ps.setString(7, itemBean.getAddress().getStreetAddress());
			ps.setFloat(8, itemBean.getReservePrice().getPrice());
			ps.setFloat(9, itemBean.getStartPrice().getPrice());
			ps.setFloat(10, itemBean.getBidIncrements());
			ps.setString(11, itemBean.getEndTime());
			ps.setFloat(12, itemBean.getHighestBid());
			ps.setInt(13, itemBean.getHighestBidUserID());
	
			
			int rows = ps.executeUpdate();
			if (rows < 1) 
				throw new DataAccessException("ItemBean: " + itemBean + " not inserted");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			throw new DataAccessException("ItemBean: " + itemBean + " not inserted");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("ItemBean: " + itemBean + " not inserted");
		}
	}

	/*
	 * delete item that is pointed to by the item_id that was passed in
	 */
	public void delete(int id) throws DataAccessException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = services.createConnection();
			ps = con.prepareStatement(
				"delete from TBL_ITEMS where item_id=?");
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows < 1) 
				throw new DataAccessException("ItemBean: " + id + " not deleted");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(con);
		}
	}
	
	
	/*
	 * output the whole list of items in the database
	 */
	public List<ItemBean> showAllItems() throws DataAccessException {
		List<ItemBean> list = new ArrayList<ItemBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = services.createConnection();
			ps = con.prepareStatement(
					"select * from TBL_ITEMS");
			rs = ps.executeQuery();
			while (rs.next())
				list.add(createItemBean(rs));
		} catch (SQLException e) {
			throw new DataAccessException("Unable to find all items", e);
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("Unable to locate connection", e);
		} finally {
			close(rs);
			close(ps);
			close(con);
		}
		return list;
	}
	
	
	/*
	 * Finds and returns the itemBean that contains "string/catagory/desc"
	 */
	public List<ItemBean> findItemByString(String searchString){
		/*
		int i, found;
		List<ItemBean> list = new ArrayList<ItemBean>();
		ItemBean item = new ItemBean();
		
		//for now just get a list of all the items and iterate through it
		list = showAllItems();												
		for (i = 0, found = 0; i < list.size(); i++){
			item = list.get(i);
			
			//check all substrings
			if(item.getTitle().contains(searchString) ){
				found = 1;
			}
			if(item.getCategory().contains(searchString)){
				found = 1;
			}
			if(item.getDescription().contains(searchString)){
				found = 1;
			}
			if(found == 0){
				//remove the item from the list if it wasn't found
				list.remove(i);
				i--;
			}
		}
		*/
		
		List<ItemBean> list = new ArrayList<ItemBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = services.createConnection();
			
			//CHECK IF CORRECT %?% might throw errors hard here!!
			ps = con.prepareStatement(
					"select * from TLB_ITEMS where title like %?% or description like %?% or category like %?%");
			ps.setString(1, searchString);
			ps.setString(2, searchString);
			ps.setString(3, searchString);
			
			rs = ps.executeQuery();
			while (rs.next())
				list.add(createItemBean(rs));
		} catch (SQLException e) {
			throw new DataAccessException("Unable to find all items", e);
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("Unable to locate connection", e);
		} finally {
			close(rs);
			close(ps);
			close(con);
		}
		
		return list;
	}
	
	
	
	public ItemBean getItemById(int id){
		ItemBean item = new ItemBean();
		item = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = services.createConnection();
			ps = con.prepareStatement(
					"select * from TLB_ITEMS where item_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			item = createItemBean(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Unable to find item pointed by id", e);
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("Unable to locate connection", e);
		} finally {
			close(rs);
			close(ps);
			close(con);
		}
		return item;
	}
	
	
	/*
	 * updating to the new highest bidder value
	 */
	public int updateBid(int item_id, float bid_value, int bidder_id){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ItemBean item = new ItemBean();
		int update = 0;
		item = null;
		try {
			con = services.createConnection();
			
			//updating the table: SET [col-name] = 'newValue' , [col2-name] ...
			ps = con.prepareStatement(
					"select * from  TLB_ITEMS where item_id = ?");
			ps.setFloat(1, bid_value);
			rs = ps.executeQuery();
			//store the bean values you read from the database
			item = createItemBean(rs);					
			
			//Check if the bid_value > current database bid_value
			if (bid_value > item.getHighestBid()){
				//update to the new database value
				ps = con.prepareStatement(
						"update TLB_ITEMS "
						+ "set highestBid = ?, highest_bid_user_ID = ? "
						+ "where item_id = ? ");
				ps.setFloat(1, bid_value);
				ps.setInt(2, bidder_id);
				ps.setInt(3, item_id);
				ps.executeUpdate();
				//flag that we have update so we return = 1
				update = 1;							
			} 
			//if it's not, do nothing and return 0;
			
			
	
		} catch (SQLException e) {
			throw new DataAccessException("Unable to update the bid value and user_id", e);
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("Unable to locate connection", e);
		} finally {
			close(rs);
			close(ps);
			close(con);
		}
		return update;						//we did not update the bid value
	}
	
	
	
	
	
	/*
	 * Halt the auction ie. adding more time to the endTime column
	 */
	public void haltAuction(int item_id, int upTime){
		
	}
	
	
	private ItemBean createItemBean(ResultSet rs) throws SQLException {
		ItemBean item = new ItemBean();
		AddressBean address = new AddressBean();
		PriceBean price = new PriceBean();
		
		//Grab address of item and package it up
		address.setStreetAddress(rs.getString("address"));
		item.setAddress(address);
		
		//Grab and set item details
		item.setItemID(rs.getInt("item_id"));
		item.setOwnerID(rs.getInt("ownerID"));
		item.setTitle(rs.getString("title"));
		item.setCategory(rs.getString("category"));
		item.setPicture(rs.getString("picture"));
		item.setDescription(rs.getString("description"));
		
		//Grab price of item and package to Bean
		price.setPrice(rs.getFloat("reservePrice"));
		price.setPrice(rs.getFloat("startPrice"));
		item.setReservePrice(price);
		
		item.setBidIncrements(rs.getFloat("bidIncrements"));
		item.setEndTime(rs.getString("endTime"));
		item.setHighestBid(rs.getFloat("highestBid"));
		item.setHighestBidUserID(rs.getInt("highest_bid_user_ID"));
		
		return item;
	}
	
	
	private void close (Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
