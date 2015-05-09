<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${item.title}</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-3" style="float: left; margin-left: -10px">
				<center><h2><b>${item.title}</b></h2></center>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="float: left; margin-left: -10px">
				<img src="${item.picture}">
				<div style="margin-left:25px; padding-top: 10px;">
                	<center>
		                <form method="post" action="./Wishlist">
		                	<input type="hidden" name="wishListId" value="${item.id}">
		                	<input type="submit" value="Add to Wishlist" class="btn btn-primary">
		                </form>
		            </center>
		        </div>
			</div>
			<div class="col-md-9" style="margin-left: 10px;">
	            
	            <div class="tab-content col-md-12">
	                <div class="tab-pane fade in active">
	                    <div class="col-md-6">
	                        <h4>
	                            Category:
	                        </h4>
	                        <div class="details-column">
	                            ${item.category}
	                        </div>
	                        <h4>
	                             Reserve Price:
	                        </h4>
	                        <div class="details-column">
	                            ${item.reservePrice.price} ${item.reservePrice.currency}
	                        </div>
	                        <h4>
								Postal Address:
	                        </h4>
	                        <div class="details-column">
								${item.address.streetAddress} ${item.address.city} ${item.address.state} ${item.address.country} ${item.address.postCode}
	                        </div>
	                    </div>
	                    <div class="col-md-6">
	                        <h4>
								Bidding Increments:
	                        </h4>
	                        <div class="details-column">
								${item.bidIncrements}
	                        </div>
	                        <h4>
								Starting Price:
	                        </h4>
	                        <div class="details-column">
								${item.startPrice.price} ${item.startPrice.currency}
	                        </div>
	                        <h4>
	                            Description:
	                        </h4>
	                        <div class="details-column">
	                            ${item.description}
	                        </div>
	                    </div>
	                </div>
	            </div>
            </div>
		</div>
	</div>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
</body>
</html>