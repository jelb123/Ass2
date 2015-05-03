package com.enterprise.dao;

import java.util.HashMap;
import java.util.Map;

import com.enterprise.dao.support.ItemDAOImpl;
import com.enterprise.dao.support.UserDAOImpl;

public class DAOFactory {
	
	private static final String USER_DAO = "userDAO";
	
	private static final String ITEM_DAO = "itemDAO";
	
	private Map daos;
	
	private static DAOFactory instance = new DAOFactory();
	
	private DAOFactory() {
		daos = new HashMap();
		daos.put(USER_DAO, new UserDAOImpl());
		daos.put(ITEM_DAO, new ItemDAOImpl());
	}
	
	/**
	 * Finds the user dao
	 * @return
	 */
	public UserDAO getUserDAO() {
		return (UserDAO) daos.get(USER_DAO);
	}

	/**
	 * Retrieves the contacts dao
	 * @return
	 */
	public ItemDAO getItemDAO() {
		return (ItemDAO) daos.get(ITEM_DAO);
	}
	
	public static DAOFactory getInstance() {
		return instance;
	}
}
