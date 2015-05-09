package com.enterprise.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprise.dao.ItemDAO;
import com.enterprise.dao.support.ItemDAOImpl;


public class AddItemCommand implements Command {
	
	private static ItemDAO itemDAO;
	
	public AddItemCommand() {
		itemDAO = new ItemDAOImpl(); 
		
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
