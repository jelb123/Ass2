package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class BrowseItemServlet
 */
@WebServlet(description = "Displays item page", urlPatterns = { "/BrowseItem" })
public class BrowseItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass().getName());
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		InputSource xmlFile = new InputSource(context.getResourceAsStream("WEB-INF/AuctionItems.xml"));
		ArrayList<ItemBean> itemsList = null;
		
		HttpSession session = request.getSession();
		//if (session.getAttribute("itemsList") == null) {
			try {
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				Document doc = builder.parse(xmlFile);
				ItemHandler handler = new ItemHandler();
				itemsList = handler.translateToItems(doc);
				session.setAttribute("itemsList", itemsList);
			} catch (Exception e) {
				logger.severe(e.getMessage());
			}
		//} else {
		//	itemsList = (ArrayList<ItemBean>) session.getAttribute("itemsList");
		//}
		
		ItemBean item = new ItemBean();
		String idQuery = request.getParameter("item");
		System.out.println(" idQuery: " + idQuery);
		if (idQuery == null || Integer.parseInt(idQuery) > itemsList.size() || Integer.parseInt(idQuery) <= 0){
			
			item.setTitle("No Item Found");
			item.setCategory("");
			item.setDescription("");
			item.setEndTime("");
			item.setPicture("");
			item.setId("");
			item.setBidIncrements(0);
			
			AddressBean address = new AddressBean();
			address.setCity("");
			address.setCountry("");
			address.setPostCode(0);
			address.setState("");
			address.setStreetAddress("");
			item.setAddress(address);
			
			PriceBean resPrice = new PriceBean();
			resPrice.setCurrency("AUD");
			resPrice.setPrice(0);
			item.setReservePrice(resPrice);
			
			PriceBean startPrice = new PriceBean();
			startPrice.setCurrency("AUD");
			startPrice.setPrice(0);
			item.setStartPrice(startPrice);
		
		} else {
			int id = Integer.parseInt(idQuery);
			id--;
			item = itemsList.get(id);
		}
		
		request.setAttribute("item",item);
		RequestDispatcher rd = request.getRequestDispatcher("/displayItem.jsp");
		rd.forward(request, response);
			
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
