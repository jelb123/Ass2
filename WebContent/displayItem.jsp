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
			<div class="col-md-3" style="float: left;">
				<center><h2><b>${item.title}</b></h2></center>
				<img src="${item.picture}">
			</div>
			<div class="col-md-9">
	            <ul class="nav nav-tabs">
	                <li class="active">
	                    About
	               	</li>
	            </ul>
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
</body>
</html>