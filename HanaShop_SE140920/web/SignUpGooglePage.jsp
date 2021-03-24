<%-- 
    Document   : SignUpGooglePage.jsp
    Created on : Jan 19, 2021, 3:55:31 PM
    Author     : TRONG DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Sign Up</title>
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
            <form action="ControllerServlet" method="POST">                               
                <label for="fullnameInput">Fullname</label>
                <input id="fullnameInput" class="form-control" type="text" name="txtFullname" value="" required/>                
                <font style="color: red">
                    ${requestScope.msg}
                </font>
                <input type="hidden" name="txtCode" value="${param.txtCode}" />
                <button type="submit" name="btAction" value="SignUpGoogle" class="btn btn-dark">Sign Up</button>
                <button type="reset" class="btn btn-dark">Reset</button>
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
<c:if test="${not empty param.msg}">
            <script> alert("${param.msg} !!!!"); </script>
</c:if>
<script>
    function controlAddView(id){
        document.getElementById(id).hidden=document.getElementById(id).hidden^true;
    }
</script>
</html>