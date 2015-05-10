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
		<c:if test="${item.isActive == false}">
			<h3 class="bg-warning">This auction is already over <c:if test="${user.id == item.highestBidUserID && user.id != item.ownerID}"> (You Won!) </c:if></h3>
			
		</c:if>
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
		                <c:choose>
		                <c:when test="${user.isAdmin == false}">
			                <form method="post" action="dispatcher">
			                	<input type="hidden" name="operation" value="addtowishlist" >
			                	<input type="hidden" name="item" value="${item.itemID}">
			                	<input type="submit" value="Add to Wishlist" class="btn btn-primary">
			                </form>
			                <br><br>
			                <c:choose>
							    <c:when test="${msg == null && item.isActive == true && user.id != item.ownerID}">
					                <form method="post" action="dispatcher">
					                	<input type="hidden" name="operation" value="putBid">
					                	<input type="hidden" name="item" value="${item.itemID}">
					                	<div class="row">
									    	<label class="col-md-3">Place Bid:</label>
									    	<input class="form-add-control" type="number" min="${item.highestBid + item.bidIncrements}" name="bidamount" style="width:40%; float:left;">
				                			<input type="submit" value="Place Bid" class="btn btn-primary" style="float:left;">
									    </div>
					                </form>
			                	</c:when>
			                	<c:when test="${item.isActive == false && user.id == item.ownerID && item.highestBidUserID != item.ownerID && item.highestBid < item.reservePrice.price }">
			                		<div class="row">
					                	<form method="post" action="dispatcher">
						                	<input type="hidden" name="operation" value="acceptbid" >
						                	<input type="hidden" name="item" value="${item.itemID}">
						                	<input type="submit" value="Accept Bid" class="btn btn-danger">
						                </form>
						                <form method="post" action="dispatcher">
						                	<input type="hidden" name="operation" value="rejectbid" >
						                	<input type="hidden" name="item" value="${item.itemID}">
						                	<input type="submit" value="Reject Bid" class="btn btn-danger">
						                </form>
				              		</div>
			                	</c:when>
			                	<c:when test="${user.id == item.ownerID}">
			                		<p class="bg-warning">Cant bid, you're the owner</p>
			                	</c:when>
		                		<c:otherwise>
		                			<p class="bg-warning">${msg}</p>
		                		</c:otherwise>
	                		</c:choose>
		                </c:when>
		                <c:otherwise>
						    <div class="row">
						    	<c:if test="${item.isActive == true}">
				                	<form method="post" action="dispatcher">
					                	<input type="hidden" name="operation" value="haltauction" >
					                	<input type="hidden" name="item" value="${item.itemID}">
					                	<input type="submit" value="Halt Auction" class="btn btn-danger">
					                </form>
					            </c:if>
				                <form method="post" action="dispatcher">
				                	<input type="hidden" name="operation" value="removeitem" >
				                	<input type="hidden" name="item" value="${item.itemID}">
				                	<input type="submit" value="Remove Item" class="btn btn-danger">
				                </form>
		              		</div>
		                </c:otherwise>
		                </c:choose>
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
	                             Current Bid:
	                        </h4>
	                        <div class="details-column">
	                            ${item.highestBid}
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