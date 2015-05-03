package com.enterprise.beans;

import java.io.Serializable;

public class ItemBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String category;
	private String picture;
	private String description;
	private AddressBean address;
	private PriceBean reservePrice;
	private PriceBean startPrice;
	private float bidIncrements;
	private String endTime;
	private String id;
	
	public ItemBean() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public PriceBean getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(PriceBean reservePrice2) {
		this.reservePrice = reservePrice2;
	}

	public PriceBean getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(PriceBean startPrice) {
		this.startPrice = startPrice;
	}

	public float getBidIncrements() {
		return bidIncrements;
	}

	public void setBidIncrements(float bidIncrements) {
		this.bidIncrements = bidIncrements;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
