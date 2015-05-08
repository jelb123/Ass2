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
	            <div class="navbar-right navbar-secondary-form">
	            	<a href="addItem.jsp" class="btn btn-primary btn-sm">Add Item</a>
	            	<a href="./Wishlist" class="btn btn-primary btn-sm">Wishlist</a>
	            </div>
        </div>
        
    </div>
</div>