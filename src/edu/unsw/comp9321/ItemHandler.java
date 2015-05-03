package edu.unsw.comp9321;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enterprise.beans.AddressBean;
import com.enterprise.beans.ItemBean;
import com.enterprise.beans.PriceBean;

public class ItemHandler {
	Logger logger = Logger.getLogger(this.getClass().getName());
	public ItemHandler() {
	}

	public ArrayList<ItemBean> translateToItems(Document doc){
		NodeList itemNodes = doc.getElementsByTagName("AuctionItem");
		ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
		
		for (int i = 0; i < itemNodes.getLength(); i++) {
			Node n = itemNodes.item(i);
			NodeList itemElements = n.getChildNodes();
			ItemBean item = new ItemBean();
			
			for (int j = 0; j < itemElements.getLength(); j++) {
				Node e = itemElements.item(j);
				logger.info(e.getNodeName()+":"+e.getTextContent());
				if (e.getNodeName().equalsIgnoreCase("Title")) {
					item.setTitle(e.getTextContent());
				}
				if (e.getNodeName().equalsIgnoreCase("Category")) {
					item.setCategory(e.getTextContent());
				}
				if (e.getNodeName().equalsIgnoreCase("Picture")) {
					item.setPicture(e.getTextContent());
				}
				if (e.getNodeName().equalsIgnoreCase("Description")) {
					item.setDescription(e.getTextContent());
				}
				if (e.getNodeName().equalsIgnoreCase("PostalAddress")) {
					NodeList addressElements = e.getChildNodes();
					AddressBean address = new AddressBean();
					for (int ii = 0; ii < addressElements.getLength(); ii++) {
						Node a = addressElements.item(ii);
						logger.info(a.getNodeName()+":"+a.getTextContent());
						if (a.getNodeName().equalsIgnoreCase("streetAddress")) {
							address.setStreetAddress(a.getTextContent());
						}
						if (a.getNodeName().equalsIgnoreCase("city")) {
							address.setCity(a.getTextContent());
						}
						if (a.getNodeName().equalsIgnoreCase("state")) {
							address.setState(a.getTextContent());
						}
						if (a.getNodeName().equalsIgnoreCase("country")) {
							address.setCountry(a.getTextContent());
						}
						if (a.getNodeName().equalsIgnoreCase("postalCode")) {
							address.setPostCode(Integer.parseInt(a.getTextContent()));
						}
					}
					item.setAddress(address);
				}
				if (e.getNodeName().equalsIgnoreCase("ReservePrice")) {
					PriceBean reservePrice = new PriceBean();
					reservePrice.setPrice(Float.parseFloat(e.getTextContent()));
					NamedNodeMap attr = e.getAttributes();
					reservePrice.setCurrency(attr.getNamedItem("currency").getTextContent());
					item.setReservePrice(reservePrice);
				}
				if (e.getNodeName().equalsIgnoreCase("BiddingStartPrice")) {
					PriceBean startPrice = new PriceBean();
					startPrice.setPrice(Float.parseFloat(e.getTextContent()));
					NamedNodeMap attr = e.getAttributes();
					startPrice.setCurrency(attr.getNamedItem("currency").getTextContent());
					item.setStartPrice(startPrice);
				}
				if (e.getNodeName().equalsIgnoreCase("BiddingIncrements")) {
					item.setBidIncrements(Float.parseFloat(e.getTextContent()));
				}
				if (e.getNodeName().equalsIgnoreCase("EndTime")) {
					item.setEndTime(e.getTextContent());
				}
				if (e.getNodeName().equalsIgnoreCase("ID")) {
					item.setId(e.getTextContent());
				}
			}
			
			itemList.add(item);
		}
		
		return itemList;
	}
}
