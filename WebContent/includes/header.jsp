<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="dispatcher?operation=browseitems" ><div class="navbar-header navbar-brand">
            Auction!
        </div></a>
        <div class="navbar-collapse collapse">
	            <form method="get" action="dispatcher" class="navbar-form navbar-left">
		            <input type="hidden" name="operation" value="searchitems">
		            <div class="form-group">
		                <input placeholder="Search for item" class="form-control" name="name" type="text" />
		            </div>
		            <input type="submit" value="Search" class="btn btn-success" />
		            <a href="advancedSearch.jsp" class="btn btn-link btn-sm">Advanced Search</a>
	            </form>
	            
	            <div class="btn-group navbar-right" >
	            
	            	<c:choose>
						<c:when test="${user != null}">
			            	<a href="addItem.jsp" class="btn btn-primary btn-sm">Add Item</a>
			            	<a href="./Wishlist" class="btn btn-primary btn-sm">Wishlist</a>
			            	<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#" style="padding-bottom: 8px">
		                        <span class="glyphicon glyphicon-cog" />
		                    </a>
		                    <ul class="dropdown-menu">
		                        <li>
		                        	<a href="">
		                            	Edit Account
		                            </a>
		                        </li>
		                        <c:if test="${user.isAdmin == true}">
			                        <li>
			                        	<a href="">
			                            	Show Users
			                            </a>
			                        </li>
			                    </c:if>
		                    </ul>
	                    </c:when>
	                    <c:when test="${user == null}">
	                    	<a href="login.jsp" class="btn btn-primary btn-sm">login</a>
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