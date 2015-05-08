package com.enterprise.mail.exception;

public class UserEmailServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public UserEmailServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserEmailServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public UserEmailServiceException(Throwable cause) {
		super(cause);
	}

}
