package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// TODO Auto-generated method stub
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
					ckValue = ck.getValue() + ":" + id;
				}
				ck.setValue(ckValue);
			} else {
				ck = new Cookie("wishListCk", id);
			}

			response.addCookie(ck);
			
			RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}
}
