package com.enterprise.mail;

import com.enterprise.mail.exception.UserEmailServiceException;

public interface UserEmailService {
	
	public void sendEmail(String to, String subject, String text) throws UserEmailServiceException;
}
