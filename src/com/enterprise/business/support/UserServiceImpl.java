package com.enterprise.business.support;

import java.sql.SQLException;
import java.util.List;

import com.enterprise.beans.UserBean;
import com.enterprise.business.UserService;
import com.enterprise.business.exception.UserLoginFailedException;
import com.enterprise.business.exception.UserServiceException;
import com.enterprise.dao.DAOFactory;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.UserDAO;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	
	public UserServiceImpl() {
		super();
		userDAO = DAOFactory.getInstance().getUserDAO();
	}

	@Override
	public UserBean login(String username, String password)
			throws UserLoginFailedException {
		
		UserBean user = null;
		try {
			user = userDAO.findByLoginDetails(username, password);
			if (user == null) {
				throw new UserLoginFailedException("Login failed for " + username);
			}
		} catch (DataAccessException e) {
			throw new UserLoginFailedException("Login failed due to DataAccessException", e);
		}
		
		return user;
	}

	@Override
	public List<UserBean> getAllUsers() throws UserServiceException {
		try {
			return userDAO.findAllUsers();
		} catch (DataAccessException e) {
			throw new UserServiceException("Unable to find user records", e);
		}
	}

	@Override
	public void addUserRecord(UserBean userBean) throws UserServiceException {
		try {
			userDAO.insert(userBean);
		} catch (DataAccessException e) {
			throw new UserServiceException("Unable to add user record", e);
		}
	}

	@Override
	public UserBean deleteUserRecord(int id) throws UserServiceException {
		try {
			UserBean user = getUserById(id);
			userDAO.delete(id);
			return user;
		} catch (DataAccessException e) {
			throw new UserServiceException("Unable to delete user record", e);
		}
	}

	@Override
	public UserBean getUserById(int id) throws UserServiceException {
		try {
			return userDAO.findUserByID(id);
		} catch (DataAccessException e) {
			throw new UserServiceException("Unable to find user by id: " + id);
		}
	}
	
	public UserBean getUserByUsername(String username) throws UserServiceException { 
		try {
			return userDAO.findUserByUsername(username);
		} catch (DataAccessException e) {
			throw new UserServiceException("Unable to find user by username: " + username);
		}
	}
	
	public void updateUserState(int id, int newState) throws UserServiceException {
		try {
			userDAO.updateUserState(id, newState);
		} catch (SQLException e) { 
			throw new UserServiceException("Unable to update account state for user: " + id);
		} catch (DataAccessException e) {
			throw new UserServiceException("Unable to update account state for user: " + id);
		}
	}

	public void updateUserRecord(UserBean user) throws UserServiceException {
		try {
			userDAO.updateUserRecord(user);
		} catch (SQLException e) { 
			throw new UserServiceException("Unable to update account state for user: " + user.getId());
		} catch (DataAccessException e) {
			throw new UserServiceException("Unable to update account state for user: " + user.getId());
		}
	}
	
}
