package com.enterprise.beans;

import java.io.Serializable;

public class PriceBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private float price;
	private String currency;
	
	public PriceBean() {
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}
