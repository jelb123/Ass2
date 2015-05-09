package com.enterprise.business.exception;

import com.enterprise.dao.DataAccessException;

public class ItemServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ItemServiceException(String message, DataAccessException cause) {
		super(message, cause);
	}
	
	public ItemServiceException(String message) {
		super(message);
	}
	
	public ItemServiceException(Throwable cause) {
		super(cause);
	}
	
}
