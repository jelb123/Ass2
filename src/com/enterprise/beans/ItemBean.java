package com.enterprise.beans;

import java.io.Serializable;

public class ItemBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int itemID;
	private int ownerID;
	private String title;
	private String category;
	private String picture;
	private String description;
	private AddressBean address;
	private PriceBean reservePrice;
	private PriceBean startPrice;
	private float bidIncrements;
	private int auctionLength;	//Length of auction in minutes > 3, < 60 
	private int endTime;		//end time = current time + auction length
	private float highestBid;
	private int highestBidUserID;
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	
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


	public int getAuctionLength() {
		return auctionLength;
	}

	public void setAuctionLength(int auctionLength) {
		this.auctionLength = auctionLength;
	}

	public float getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(float highestBid) {
		this.highestBid = highestBid;
	}

	public int getHighestBidUserID() {
		return highestBidUserID;
	}

	public void setHighestBidUserID(int highestBidUserID) {
		this.highestBidUserID = highestBidUserID;
	}
	
	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	
	

}
