package com.enterprise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.enterprise.beans.UserBean;


/** 
 * The Data Access Object for User
 *
 */
public interface UserDAO {
	
	/**
	 * Inserts a new user in the database
	 * @param userBean The user to insert
	 * @throws DataAccessException
	 */
	void insert(UserBean userBean) throws DataAccessException;
	
	/**
	 * Removes an user from the database
	 * @param id the id of the user in database to remove
	 * @throws DataAccessException
	 */
	void delete(int id) throws DataAccessException;
	
	/**
	 * Tries to locate a user with the given username and password.
	 * 
	 * @param username The username to use to find the user
	 * @param password The password to use to find the user
	 * @return The UserBean if there is a user with the username and password,
	 * null otherwise.
	 * @throws DataAccessException If error occurs while connecting and retrieving
	 * the database
	 */
	UserBean findByLoginDetails(String username, String password) throws DataAccessException;
	
	/**
	 *  Finds all the users in the database
	 * @return ArrayList of all the users in the database
	 * @throws DataAccessException
	 */
	List<UserBean> findAllUsers() throws DataAccessException;
	
	/**
	 * Locates the user given the id
	 * @param id
	 * @return a user object 
	 * @throws DataAccessException
	 */
	UserBean findUserByID(int id) throws DataAccessException;
	
	UserBean findUserByUsername(String username) throws DataAccessException;
	
	void updateUserState(int id, int newState) throws SQLException;
	
	void updateUserRecord(UserBean user) throws SQLException;
}
