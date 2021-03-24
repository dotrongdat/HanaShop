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
<title>Login</title>
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
    <c:import url="Top.jsp"/>
</head>
<!--/Header -->
<!--Main Section -->
<main>
    
    <div class="container-fluid">
        <div class="container">
            <c:if test="${not empty param.msg}">
            <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Notify!</strong> ${param.msg}
            </div>
            </c:if>
            <form action="ControllerServlet" method="POST">
                <label for="usernameInput">Username</label>
                <input id="usernameInput" class="form-control" type="text" name="txtUsername" value="" required/>
                <label for="passwordInput">Password</label>
                <input id="passwordInput" class="form-control" type="password" name="txtPassword" value="" required/><br/>
                
                <font>
                <p style="color: red">${param.msgLogin}</p>
                </font>
                
                <button type="submit" name="btAction" value="Login" class="btn btn-dark">Login</button>
                <button type="reset" class="btn btn-dark">Reset</button><br/>
                <br/><a class="btn btn-outline-dark" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/HanaShop_SE140920/LoginGoogleServlet&response_type=code&client_id=930402129196-or33gv2564btkk18opqpthaukvgsooak.apps.googleusercontent.com&approval_prompt=force" role="button" style="text-transform:none">
                    <img width="20px" style="margin-bottom:3px; margin-right:5px" alt="Google sign-in" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png" />
                    Login with Google
                </a>
                <a href="SignUp.jsp" style="text-decoration: none">Sign Up</a> 
            </form>
            
        </div>
  </div>
</main>
<!--/Main Section -->
<c:import url="Bot.jsp"/>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<c:if test="${not empty param.msg}">
            <script> alert("${param.msg} !!!!"); </script>
</c:if>
<script>
    function controlAddView(id){
        document.getElementById(id).hidden=document.getElementById(id).hidden^true;
    }
</script>
</html>
