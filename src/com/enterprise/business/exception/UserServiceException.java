package com.enterprise.business.exception;

/**
 * @author yunki
 */
public class UserServiceException extends Exception {

	/**
	 * @param message
	 */
	public UserServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public UserServiceException(Throwable cause) {
		super(cause);
	}

}
