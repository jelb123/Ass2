package com.enterprise.mail;

import com.enterprise.mail.exception.UserEmailServiceException;

public class UserEmailServiceImpl implements UserEmailService {
	
	SendMail sendMail;
	
	public UserEmailServiceImpl() {
		super();
	}
	
	@Override
	public void sendEmail(String to, String subject, String text)
			throws UserEmailServiceException {
		
		try {
			sendMail = new SendMail(to, subject, text);
			sendMail.send();
		} catch (Exception e) {
			throw new UserEmailServiceException("Couldnt Send Email", e); 
		}
	}

}
