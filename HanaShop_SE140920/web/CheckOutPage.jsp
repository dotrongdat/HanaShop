<%-- 
    Document   : CheckOutPage
    Created on : Jan 17, 2021, 11:02:32 PM
    Author     : TRONG DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Check Out</title>
<!--Bootstrap CDN -->
<link 
	rel="stylesheet" 
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" 
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" 
	crossorigin="anonymous">
<!--Slick Slider -->
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<!--Font Awesome CDN -->
<script src="https://kit.fontawesome.com/86185bfb66.js" crossorigin="anonymous"></script>
<!--Custom Stylesheet -->
<link rel="stylesheet" href="css/index.css"/>
<link rel="stylesheet" href="css/log in.css"/>
<link rel="stylesheet" href="css/flower.css"/>
<link rel="stylesheet" href="css/gio hang.css"/>
</head>
<body>
<!--Header -->
	<head>
    <c:set var="account" value="${sessionScope.ACCOUNT}"/>
    <c:import url="Top.jsp"/>
</head>
	
<!--/Header -->
<!--Main Section -->
<main>
    <c:set var="account" value="${sessionScope.ACCOUNT}"/>
    <c:if test="${not empty account and account.admin==false}">
        <c:if test="${not empty param.msg}">
            <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Warning!</strong> ${param.msg}
            </div>
        </c:if>
        <div class="container">
<table class="table table-hover">
	<thead>
	<tr class="danhmuc">
		<th>PRODUCT</th>
		<th>NAME PRODUCT</th>
		<th>PRICE</th>
		<th>QUANTITY</th>
		<th>TOTAL</th>
	</thead>
<tbody>
    <c:set var="cart" value="${sessionScope.CART}"/>
    
    <c:if test="${not empty cart}">
         <c:set var="total" value="${0}"/>
    <c:forEach items="${sessionScope.CART}" var="dto">
        <c:set var="product" value="${dto.value}"/>     
        
        <input type="hidden" name="proID" value="${product.proID}" />
        <tr class="chitiet" 
            <c:if test="${product.quantity<product.quantityInCart}">
                style="border: red"
            </c:if>    
        >        
            <td><a href="ControllerServlet?btAction=PrepareProduct&proID=${product.proID}"><img class="danhmuc-img" src="data:image/jpeg;base64,${product.image}" height="100vh" width="8vw"></a> </td>
    <td>${product.proName}</td>

    <td>${product.price}</td>
    <td> 
        <input  class="quantity" type="text" readonly min="0" name="quantity" value="${product.quantityInCart}" title="${product.quantity}" >
        <input type="hidden" name="btAction" value="UpdateProductCart" />
    </td>
    <td>
        <c:set var="totalPriceOfProduct" value="${product.price*product.quantityInCart}"/>
        <c:out value="${totalPriceOfProduct}"/>
        <c:set var="total" value="${total+totalPriceOfProduct}"/>
    </td>
        </tr>
    </c:forEach>
        <tr>
            <td colspan="5"></td>
            <td>
        
        <p style="font-size: 150%">
            TOTAL ALL: ${total} VND
        </p>
        
            </td>
        </tr>
    
    </c:if>
    <c:if test="${empty cart}">
        <td><p style="font-size: 250%;color: red">No Item</p></td>
    </c:if>
 
</tbody>
</table>
        <form action="ControllerServlet">
<div>
    <label  for="fullnameText">
           Name
    </label>
    <input id="fullnameText" class="form-control" readonly type="text" name="" value="${sessionScope.ACCOUNT.fullname}" />
    <label  for="numberPhoneText">
           Phone Number
    </label>
    <input id="numberPhoneText" class="form-control" type="number" name="phoneNumber" value="${param.phoneNumber}" />
    <label  for="addressText">
           Address
    </label>
    <textarea id="addressText" class="form-control" name="address">${param.address}</textarea>
    <input type="hidden" name="btAction" value="CheckOut" />
</div>
<div class="container-shopping-btn ">
    <div class="shopping-btn">
	<a href="#" ><button type="submit" class="button-cart">COD</button></a>
    </div>
        <a href="#" ><button type="submit" name="paypal" class="button-cart">PAYPAL</button></a>
</div>
        </form>

</div>
</div>
    </c:if>
    <c:if test="${empty account}">
        <h2 style="color: red">LOGIN IS REQUIRED. <a href="login.html">Login</a></h2>
    </c:if>

</main>
<!--/Main Section -->
<c:import url="Bot.jsp"/>	
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" 
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" 
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" 
		crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" 
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" 
		crossorigin="anonymous"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="trang chu.js"></script>
</body>
</html>
