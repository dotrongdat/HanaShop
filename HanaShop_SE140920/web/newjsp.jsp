<%-- 
    Document   : login
    Created on : Jan 18, 2021, 9:30:04 PM
    Author     : TRONG DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<!--Bootstrap CDN -->
<link 
	rel="stylesheet" 
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" 
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" 
	crossorigin="anonymous">
<!--Font Awesome CDN -->
<script src="https://kit.fontawesome.com/86185bfb66.js" crossorigin="anonymous"></script>
<!--Custom Stylesheet -->
<link rel="stylesheet" href="css/index.css"/>
<link rel="stylesheet" href="css/flower.css"/>
<link rel="stylesheet" href="css/back to top trang chu.css"/>
</head>
<body>
<!--Header -->
<head>
    <c:set var="account" value="${sessionScope.ACCOUNT}"/>
	<div class="container">
<div class="row">
			<div class="col-md-1 col-sm-12 col-12 col-lg-3">
			</div>
			<div class="col-md-12 ml-sm-4 col-12 col-lg-7">
			<a href="ControllerServlet?page=1" class="my-md-1 site-title"><img class="logo" src="Image/logo.jpg"></a>
			</div>
</div>
	<div class="container-fluid p-0">
		<nav class="navbar navbar-expand-lg navbar-light ">
  			<button class="navbar-toggler" type="button" 
					data-toggle="collapse" data-target="#navbarSupportedContent" 
					aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  			</button>
  			<div class="collapse navbar-collapse" id="navbarSupportedContent">
   	 		<ul class="navbar-nav mr-auto font-montserrat">
                        <div class="btn-group">    
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Category
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:forEach items="${applicationScope.CATEGORY}" var="dto">
                                <a class="dropdown-item" href="ControllerServlet?btAction=Search&page=1&cateID=${dto.cateID}">${dto.cateName}</a>
                            </c:forEach>
                        </div>  
                        </div>
                          
                         
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            
                            <div class="navbar-nav">
                                <li class="nav-item cart-icon">
                                    <a href="ControllerServlet?btAction=PrepareCart"><i class="fas fas fa-shopping-basket" style="color: #7E7E7E" ></i></a>
                                </li>
                            </div>
                            
                            <c:if test="${empty account}">
                                <li><a href="login.html">LOGIN</a></li>
                            </c:if>
                            <c:if test="${not empty account}">
                            <div class="btn-group">
                                 <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAccount" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${account.fullname}
                                </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownAccount">
                                <a class="dropdown-item" href="OrderHistoryPage.jsp">History Order</a>
                                <a class="dropdown-item" href="ControllerServlet?btAction=LogOut">LogOut</a>
                                
                        </div>     
                            </div>
                               
                                               
                            </c:if>     
			
                    </ul>
		</div>
		</nav>
</div>
	</div>
</head>
<!--/Header -->
<!--Main Section -->
<main>
    <div class="container-fluid">
        <div class="container">
            <form action="ControllerServlet" method="POST">
                <label for="usernameInput">Username</label>
                <input id="usernameInput" class="form-control" type="text" name="txtUsername" value="${requestScope.ACCOUNTINFO.username}" required/>
                
                <font style="color: red">
                    ${requestScope.ERROR.usernameLengthError}
                    ${requestScope.ERROR.usernameDuplicate}  
                </font>  
                
                
                <label for="passwordInput">Password</label>
                <input id="passwordInput" class="form-control" type="password" name="txtPassword" value="" required/>
                
                <font style="color: red">
                    ${requestScope.ERROR.passwordLengthError}
                    ${requestScope.ERROR.passwordFormatError}
                </font>
                
                <label for="confirmInput">Confirm</label>
                <input id="confirmInput" class="form-control" type="password" name="txtConfirm" value="" required/>
                
                <font style="color: red">
                    ${requestScope.ERROR.confimNotMatched}
                </font>
                
                <label for="fullnameInput">Fullname</label>
                <input id="fullnameInput" class="form-control" type="text" name="txtFullname" value="${requestScope.ACCOUNTINFO.fullname}" required/>
                
                <font style="color: red">
                    ${requestScope.ERROR.fullnameLengthError}
                </font>

                <button type="submit" name="btAction" value="SignUp" class="btn btn-dark">Sign Up</button>
                <button type="reset" class="btn btn-dark">Reset</button>
            </form>
        </div>
  </div>
</main>
<!--/Main Section -->
<footer>
<div class="container">
	
<div class="footer-content">
	<div class="footer-content-about animate-up">
	<h4>CONTACT US</h4>
	<div class="asterisk"><i class="fas fa-id-card"></i></div>
	<p>Email: HanaShopDATDT@gmail.com <br/>
		Phone number: 0377322919 <br/>
		Address: Thu Duc City, Ho Chi Minh City.</p>
	</div>
	<div class="footer-content-divider animate-botom">
		<div class="social-media">
		<h4>FOLLOW ALONG</h4>
		<ul class="social-icons">
		<li><a href="#"><i class="fab fa-twitter"></i></a>	</li> 
		<li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
		<li><a href="#"><i class="fab fa-instagram"></i></a></li>
		</ul>
		</div>
	<div class="newsletter-container">
	<h4>Newsletter</h4>	
	<form action="" class="newsletter-form">
	<input type="text" class="newsletter-input" placeholder="Your email address...">
	<button class="newsletter-btn" type="submit">
	<i class="fas fa-envelope"></i>
	</button>
	</form>
	</div>
	</div>
</div>	
</div>
</footer>	
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<c:if test="${not empty param.msg}">
            <script> alert("${param.msg} !!!!"); </script>
</c:if>
<script>
    function controlAddView(id){
        document.getElementById(id).hidden=document.getElementById(id).hidden^true;
    }
</script>
</html>
