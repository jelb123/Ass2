<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
	        <a href="dispatcher?operation=displayItemsList" >
	        	<div class="navbar-header navbar-brand">
	          		Auction!
	        	</div>
        	</a>
        
        <div class="navbar-collapse collapse">
	            <form method="get" action="dispatcher" class="navbar-form navbar-left">
		            <input type="hidden" name="operation" value="searchitems">
		            <div class="form-group">
		                <input placeholder="Search for item" class="form-control" name="name" type="text" />
		            </div>
		            <input type="submit" value="Search" class="btn btn-success" />
		            <a href="advancedSearch.jsp" class="btn btn-link btn-sm">Advanced Search</a>
	            </form>
	            
	            <div class="btn-group navbar-right" style="padding-top: 8px">
	            
	            	<c:choose>
						<c:when test="${user != null}">
			            	<a href="addItem.jsp" class="btn btn-primary btn-sm">Add Item</a>
			            	<a href="dispatcher?operation=wishlist" class="btn btn-primary btn-sm">Wishlist</a>
			            	<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#" style="padding-bottom: 8px">
		                        <span class="glyphicon glyphicon-cog" />
		                    </a>
		                    <ul class="dropdown-menu">
		                        <li>
		                        	<a href="updateDetails.jsp">
		                            	Edit Account
		                            </a>
		                        </li>
		                        <li>
		                        	<a href="dispatcher?operation=finduseritems">
		                        		My Items
		                        	</a>
		                        </li>
		                        <li>
		                        	<a href="dispatcher?operation=finduserbids">
		                        		My Bids
		                        	</a>
		                        </li>
		                        <li>
		                    		<a href="dispatcher?operation=displayprofile&id=${user.id}">
		                        		User Profile
		                        	</a>
								</li>
								<c:if test="${user.isAdmin == 'true'}">
			                        <li>
			                        	<a href="dispatcher?operation=adminshowusers">
			                            	Show Users
			                            </a>
			                        </li>
			                    </c:if>                      
		                        <li>
		                        	<a href="dispatcher?operation=logout">
		                        		Logout
		                        	</a>
		                        </li>
		                    </ul>
	                    </c:when>
	                    <c:when test="${user == null}">
	                    	<a href="login.jsp" class="btn btn-primary btn-sm">Login</a>
	                    </c:when>
					</c:choose>
	            </div>
	            
	            <!-- <div class="navbar-right navbar-secondary-form">
	            	<a href="addItem.jsp" class="btn btn-primary btn-sm">Add Item</a>
	            	<a href="./Wishlist" class="btn btn-primary btn-sm">Wishlist</a>
	            </div> -->
        </div>
        
    </div>
</div>