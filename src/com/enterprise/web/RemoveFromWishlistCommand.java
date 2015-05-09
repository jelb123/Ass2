package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.business.support.ItemServiceImpl;

public class RemoveFromWishlistCommand implements Command {
	
	private static ItemServiceImpl wishlistService;
	
	public RemoveFromWishlistCommand() {
		wishlistService = new ItemServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
