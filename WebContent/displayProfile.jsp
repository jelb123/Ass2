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
		
		<h3><b> ${account.username}'s profile </b></h3>
		<br> </br>
					
		<div class="col-md-9" style="margin-left: 10px;">
            
            <div class="tab-content col-md-12">
                <div class="tab-pane fade in active">
                    <div class="col-md-6">
                        <h4>
                            Name:
                        </h4>
                        <div class="details-column">
                            ${account.firstName} ${account.lastName} 
                        </div>      
                        <h4>
							Date of birth:
                        </h4>
                        <div class="details-column">
							${account.dateOfBirth}
                        </div>
	                    <c:if test="${user.isAdmin != true || user.id == account.id}">
	                        <h4>
	                             Credit card number:
	                        </h4>
	                        <div class="details-column">
	                            ${account.creditCardNumber}
	                        </div>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        <h4>
                             Nickname:
                        </h4>
                        <div class="details-column">
                            ${account.nickname}
                        </div>
                        <h4>
							Email address:
                        </h4>
                        <div class="details-column">
							${account.email}
                        </div>
                        <h4>
                            Address:
                        </h4>
                        <div class="details-column">
                            ${account.address.streetAddress} ${account.address.city} 
                            ${account.address.state} ${account.address.postCode} ${account.address.country} 
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