package com.enterprise.business;

import java.util.List;

import com.enterprise.beans.UserBean;
import com.enterprise.business.exception.UserLoginFailedException;
import com.enterprise.business.exception.UserServiceException;

public interface UserService {
	
	/**
	 * Finds a user with the username and password
	 * @param username The username to login with
	 * @param password The password to login with
	 * @return The user with those login details if exists
	 * @throws UserLoginFailedException When no such user is found
	 */
	UserBean login(String username, String password) throws UserLoginFailedException;

	/**
	 * Returns all the users in the database table
	 * @return a list of all users
	 * @throws UserServiceException When an error occurs
	 */
	List<UserBean> getAllUsers() throws UserServiceException;
	
	/**
	 * Adds a record of an user to the users table
	 * @param userBean the user to be added
	 * @throws UserServiceException When an error occurs
	 */
	void addUserRecord(UserBean userBean) throws UserServiceException;
	
	/**
	 * Deletes a user from the users table in the database
	 * @param id The id of the user to delete
	 * @return The contact that has been deleted
	 * @throws UserServiceException When an error occurs
	 */
	UserBean deleteUserRecord(int id) throws UserServiceException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws UserServiceException When an error occurs
	 */
	UserBean getUserById(int id) throws UserServiceException;
	
	UserBean getUserByUsername(String username) throws UserServiceException;
	
	void updateUserState(int id, int newState) throws UserServiceException;
	
	void updateUserRecord(UserBean user) throws UserServiceException;
	
	
}
