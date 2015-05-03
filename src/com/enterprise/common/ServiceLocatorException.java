package com.enterprise.common;

/**
 * Exception that is thrown when a service cannot be located or loaded
 */
public class ServiceLocatorException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an exception with the given message
	 * @param message
	 */
	public ServiceLocatorException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
