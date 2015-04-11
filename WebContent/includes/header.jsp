<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="welcome.jsp">Auction!</a>
        </div>
        <div class="navbar-collapse collapse">
	            <form method="post" action="search.jsp" class="navbar-form navbar-left">
		            <div class="form-group">
		                <input placeholder="Search for user" class="form-control" name="search" type="text" />
		            </div>
		            <input type="submit" name="Search" value="Search" class="btn btn-success" />
		            <a href="advancedSearch.jsp" class="btn btn-link btn-sm">Advanced Search</a>
	            </form>
	            <div class="navbar-right navbar-secondary-form">
	            	<a href="addItem.jsp" class="btn btn-primary btn-sm">Add Item</a>
	            	<a href="wishlist.jsp" class="btn btn-primary btn-sm">Wishlist</a>
	            </div>
        </div>
        
    </div>
</div>