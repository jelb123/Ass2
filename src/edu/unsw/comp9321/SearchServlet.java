package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

import com.enterprise.beans.ItemBean;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(description = "Processess the search", urlPatterns = { "/Search" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass().getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			ItemHandler handler = new ItemHandler();
			itemsList = handler.translateToItems(doc);
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}

		
		List<ItemBean> items = new ArrayList<ItemBean>();
		
		String nameQuery = request.getParameter("name");
		String categoryQuery = request.getParameter("category");
		String descriptionQuery = request.getParameter("description");
		
		System.out.println("Name: " + nameQuery);
		System.out.println("Category: " + categoryQuery);
		System.out.println("Description: " + descriptionQuery);
		
		for (int i = 0;i < itemsList.size(); i++) {
			ItemBean curItem = itemsList.get(i);
			
			boolean nameMatch = true;
			boolean categoryMatch = true;
			boolean descriptionMatch = true;
			
			if (!(nameQuery == null || nameQuery.isEmpty())) {
				if(!curItem.getTitle().toLowerCase().contains(nameQuery.toLowerCase())) {
					nameMatch = false;
				}
				
			}
			if (!(categoryQuery == null || categoryQuery.isEmpty())) {
				if(!curItem.getCategory().toLowerCase().contains(categoryQuery.toLowerCase())) {
					categoryMatch = false;
				}
			}
			if (!(descriptionQuery == null || descriptionQuery.isEmpty())) {
				System.out.println("ITS IN HERE");
				if(!curItem.getDescription().toLowerCase().contains(descriptionQuery.toLowerCase())) {
					descriptionMatch = false;
				}
			}
			
			System.out.println("name:" + nameMatch + categoryMatch + descriptionMatch);
			
			if (nameMatch == true && categoryMatch == true && descriptionMatch == true){
				items.add(curItem);
			}
		}
		
		request.setAttribute("items",items);
		RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
		rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
