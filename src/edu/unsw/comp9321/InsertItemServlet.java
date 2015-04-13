package edu.unsw.comp9321;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class InsertItemServlet
 */
@WebServlet(description = "Servlet to add an item to database", urlPatterns = { "/insertItem" })
public class InsertItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass().getName()); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----- InsertItemServlet -----");
		try {
			//Get the Item values added through addItem.jsp
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String picture = request.getParameter("picture");
			String description = request.getParameter("description");
			
			//get the address values
			String streetAddress = request.getParameter("streetAddress");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			int postCode = Integer.parseInt(request.getParameter("postCode"));
			
			//price values
			int resPrice = Integer.parseInt(request.getParameter("reservePrice"));
			String resCurrency = request.getParameter("resCurrency");
			
			int startPrice = Integer.parseInt(request.getParameter("startPrice"));
			String startCurrency = request.getParameter("startCurrency");
			
			int bidIncrement = Integer.parseInt(request.getParameter("bidIncrement"));
			
			//end time
			String endDate = request.getParameter("endDate");
			String endTime = request.getParameter("endTime");
			
			System.out.println("Name: " + name);
			System.out.println("Category: "+ category);
			System.out.println("Picture: " + picture);
			System.out.println("Description: " + description);
			System.out.println("Street Address: " + streetAddress);
			System.out.println("City: " + city);
			System.out.println("State: " + state);
			System.out.println("Country: " + country);
			System.out.println("Postcode: " + postCode);
			System.out.println("Reserve Price: " + resPrice + resCurrency);
			System.out.println("Start Price: " + startPrice + startCurrency);
			System.out.println("Bid Increment: " + bidIncrement);
			System.out.println("End Date: " + endDate);
			System.out.println("End Time: " + endTime);
			
			
			ServletContext context = getServletContext();
			String outFile = context.getRealPath("/")+"WEB-INF/AuctionItems.xml";
			InputSource xmlFile = new InputSource(context.getResourceAsStream("/WEB-INF/AuctionItems.xml"));
			ArrayList<ItemBean> itemsList = null;
			
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			ItemHandler handler = new ItemHandler();
			itemsList = handler.translateToItems(doc);
			ItemBean iteml = new ItemBean();
			
			int tempId = itemsList.size();
			tempId++;
			
			String id = Integer.toString(tempId);
			
			
			
			System.out.println("Title: " + itemsList.get(itemsList.size()-1).getTitle());
			System.out.println("Id: " + id);
			
	//////// Creating Item Tree
			Node auctionItems = doc.getFirstChild();
			Node item = doc.createElement("AuctionItem");
			
		//// Set title
			Node itemTitle = doc.createElement("Title");
			itemTitle.appendChild(doc.createTextNode(name));
			item.appendChild(itemTitle);
			iteml.setTitle(name);
		////
			
		////Set category
			Node itemCategory = doc.createElement("Category");
			itemCategory.appendChild(doc.createTextNode(category));
			item.appendChild(itemCategory);
			iteml.setCategory(category);
		////
		
		////Set picture url
			Node itemPicture = doc.createElement("Picture");
			itemPicture.appendChild(doc.createTextNode(picture));
			item.appendChild(itemPicture);
			iteml.setPicture(picture);
		////
			
		////Set description
			Node itemDescription = doc.createElement("Description");
			itemDescription.appendChild(doc.createTextNode(description));
			item.appendChild(itemDescription);
			iteml.setDescription(description);
		////
			
		//// Set postal address
			AddressBean addressBean = new AddressBean();
			Node itemPostal = doc.createElement("PostalAddress");
			
			Node itemStreet = doc.createElement("streetAddress");
			itemStreet.appendChild(doc.createTextNode(streetAddress));
			itemPostal.appendChild(itemStreet);
			addressBean.setStreetAddress(streetAddress);
			
			Node itemCity = doc.createElement("city");
			itemCity.appendChild(doc.createTextNode(city));
			itemPostal.appendChild(itemCity);
			addressBean.setCity(city);
			
			Node itemState = doc.createElement("state");
			itemState.appendChild(doc.createTextNode(state));
			itemPostal.appendChild(itemState);
			addressBean.setState(state);
			
			Node itemCountry = doc.createElement("country");
			itemCountry.appendChild(doc.createTextNode(country));
			itemPostal.appendChild(itemCountry);
			addressBean.setCountry(country);
			
			Node itemPostalCode = doc.createElement("postalCode");
			itemPostalCode.appendChild(doc.createTextNode(Integer.toString(postCode)));
			itemPostal.appendChild(itemPostalCode);
			item.appendChild(itemPostal);
			addressBean.setPostCode(postCode);
			
			iteml.setAddress(addressBean);
		////
			
		//// Set Res price
			PriceBean resPriceBean = new PriceBean();

			Node itemResPrice = doc.createElement("ReservePrice");
			NamedNodeMap resPriceAttr = itemResPrice.getAttributes();
			Attr resCurrencyAttr = doc.createAttribute("currency");
			resCurrencyAttr.setValue(resCurrency);
			resPriceAttr.setNamedItem(resCurrencyAttr);
			itemResPrice.appendChild(doc.createTextNode(Integer.toString(resPrice)));
			item.appendChild(itemResPrice);
			
			resPriceBean.setCurrency(resCurrency);
			resPriceBean.setPrice(resPrice);
			iteml.setReservePrice(resPriceBean);
		////
			
		//// Set start price
			PriceBean startPriceBean = new PriceBean();
			
			Node itemStartPrice = doc.createElement("BiddingStartPrice");
			NamedNodeMap startPriceAttr = itemStartPrice.getAttributes();
			Attr startCurrencyAttr = doc.createAttribute("currency");
			startCurrencyAttr.setValue(startCurrency);
			startPriceAttr.setNamedItem(startCurrencyAttr);
			itemStartPrice.appendChild(doc.createTextNode(Integer.toString(startPrice)));
			item.appendChild(itemStartPrice);
			
			startPriceBean.setCurrency(startCurrency);
			startPriceBean.setPrice(startPrice);
			iteml.setStartPrice(startPriceBean);
		////
			
		//// Set bid inc
			Node itemBidInc = doc.createElement("BiddingIncrements");
			itemBidInc.appendChild(doc.createTextNode(Integer.toString(bidIncrement)));
			item.appendChild(itemBidInc);
			iteml.setBidIncrements(bidIncrement);
		////
		
		//// Set end time
			Node itemEndTime = doc.createElement("EndTime");
			itemEndTime.appendChild(doc.createTextNode(endDate+"-"+endTime));
			item.appendChild(itemEndTime);
			iteml.setEndTime(endDate+"-"+endTime);
		////	
		
		////Set Id
			Node itemID = doc.createElement("ID");
			itemID.appendChild(doc.createTextNode(id));
			item.appendChild(itemID);
			iteml.setId(id);
		////
		
		  //Append to tree/list
			auctionItems.appendChild(item);
			itemsList.add(iteml);
		  //
	////////
			
			//Write the content into XML file:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			//Write back to file:
			StreamResult result = new StreamResult(new File(outFile));
	 
			//Output to console for testing
			//StreamResult result = new StreamResult(System.out);
			
			transformer.transform(source, result);
			System.out.println("File saved to : " + outFile);
			
			request.setAttribute("msg", "Your Item has <br> been added");
			RequestDispatcher rd = request.getRequestDispatcher("/itemAdded.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}

}
