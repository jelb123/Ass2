package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class removeItemServlet
 */
@WebServlet("/removeItem")
public class removeItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeItemServlet() {
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
		String id = request.getParameter("wishListId");
		
		String ckValue = null;
		Cookie[] cookies = request.getCookies();
		Cookie ck = null;
		for (Cookie cookie : cookies) {
			if("wishListCk".equals(cookie.getName())) {
				ck = cookie;
			}
		}
		
		List<String> ckArrayList = null;
		
		if (ck != null) {
			if (!ck.getValue().isEmpty()) {
				ckArrayList = new ArrayList<String>(Arrays.asList(ck.getValue().split(":")));
				System.out.println(ckArrayList.indexOf(id) + " ");
				ckArrayList.remove(ckArrayList.indexOf(id));
				
				ckValue = "";

				for (String s : ckArrayList)
				{
				    ckValue += s + ":";
				}
				
				ckValue = ckValue.substring(0,Math.max(0, ckValue.length()-1));
				
			}
			ck.setValue(ckValue);
			response.addCookie(ck);
		}
		
		
		
		request.setAttribute("msg", "Item has been removed from Wishlist");
		RequestDispatcher rd = request.getRequestDispatcher("/itemAdded.jsp");
		rd.forward(request, response);
	}

}
