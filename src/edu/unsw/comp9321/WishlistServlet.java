package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class WishlistServlet
 */
@WebServlet("/Wishlist")
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("wishListId");
		
		Cookie[] cookies = request.getCookies();
		Cookie ck = null;
		for (Cookie cookie : cookies) {
			if("wishListCk".equals(cookie.getName())) {
				ck = cookie;
			}
		}
		
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
		
		String msg = "";
		List<ItemBean> items = new ArrayList<ItemBean>();;
		
		if (ck == null || ck.getValue().isEmpty()) {
			msg = "Your wishlist is empty";
		} else {
			String[] ckValues = ck.getValue().split(":");
			System.out.println(itemsList.size());
			for (int i = 0; i < ckValues.length; i++) {
				for (ItemBean item: itemsList) {
					if (item.getId().equals(ckValues[i])) {
						items.add(item);
					}
				}
				
			}
		}
		
		request.setAttribute("items",items);
		
		
		
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("/displayWishlist.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("wishListId");
			
			String ckValue = null;
			Cookie[] cookies = request.getCookies();
			Cookie ck = null;
			for (Cookie cookie : cookies) {
				if("wishListCk".equals(cookie.getName())) {
					ck = cookie;
				}
			}
			
			if (ck != null) {
				if (ck.getValue().isEmpty()) {
					ckValue = id;
				} else {
					if(!ck.getValue().contains(id)) {
						ckValue = ck.getValue() + ":" + id;
					} else {
						ckValue = ck.getValue();
					}
				}
				ck.setValue(ckValue);
			} else {
				ck = new Cookie("wishListCk", id);
			}

			response.addCookie(ck);
			
			request.setAttribute("msg", "Item added to Wishlist");
			RequestDispatcher rd = request.getRequestDispatcher("/itemAdded.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}
}
