package com.enterprise.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enterprise.auctions.AuctionParser;
import com.enterprise.auctions.AuctionParserFactory;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private Map<String,Command> commands;   
    
    private AuctionParser auctionParser;
    private Thread auctionThread;

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		commands = new HashMap<String,Command>();
		commands.put("login", new LoginCommand());
		commands.put("registeruser", new RegisterUserCommand());
		commands.put("emailuser", new EmailUserCommand());
		commands.put("activate", new ActivateUserCommand(1));
		commands.put("updateuserdetails", new UpdateUserDetailsCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("acceptbid", new AcceptBidCommand());
		commands.put("rejectbid", new RejectBidCommand());
		commands.put("finduseritems", new FindUsersItems());
		commands.put("finduserbids", new FindUserBids());
		
		commands.put("adminshowusers", new AdminShowUsersCommand());
		commands.put("banuser", new ActivateUserCommand(3));
		commands.put("haltauction", new HaltAuctionCommand());
		commands.put("removeitem", new RemoveAuctionCommand());
		
		commands.put("putBid", new PlaceBidCommand());
		commands.put("wishlist", new WishlistCommand());
		commands.put("addtowishlist", new AddWishlistCommand());
		commands.put("removefromwishlist", new RemoveFromWishlistCommand());
		commands.put("browseitem", new BrowseItemCommand());
		commands.put("searchitems", new SearchItemsCommand());
		commands.put("additem", new AddItemCommand());
		commands.put("displayItemsList", new DisplayItemsCommand());
		commands.put("advancedSearch", new AdvancedSearchCommand());
		commands.put("PAGE_NOT_FOUND", new ErrorCommand());
		
		//start the auction parser runnables to run in a separate thread
		auctionParser = AuctionParserFactory.getAuctionParserService();
		auctionThread = new Thread(auctionParser);
		auctionThread.setDaemon(true);	//Run in background
		auctionThread.start();
		
		
		// TODO rest of command mapping
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		Command cmd = resolveCommand(request);
		String next = cmd.execute(request, response);
		if (next.indexOf('.') < 0) {
			cmd = (Command) commands.get(next);
			next = cmd.execute(request, response);
		}		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
		dispatcher.forward(request, response);
	}
	
	private Command resolveCommand(HttpServletRequest request) {
		Command cmd = commands.get(request.getParameter("operation"));
		if (cmd == null) {
			cmd = commands.get("PAGE_NOT_FOUND");
		}
		return cmd;
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("operation") != null && 
			request.getParameter("operation").equals("activate")) {
			processRequest(request, response);
		} else if (request.getSession().getAttribute("user") == null) {
			request.setAttribute("loginFailed", "false");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			processRequest(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
