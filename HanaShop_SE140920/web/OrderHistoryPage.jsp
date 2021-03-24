<%-- 
    Document   : OrderHistoryPage
    Created on : Jan 18, 2021, 3:20:24 PM
    Author     : TRONG DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>History Order</title>
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
        <form action="ControllerServlet">
            <label for="dateInput">Date</label>
            <input id="dateInput" class="form-control" type="date" name="dateSearch" value="${param.dateSearch}" /><br/>
            <input class="form-control mr-sm-2" type="search" placeholder="Search" name="proNameSearch"value="${param.proNameSearch}" /><br/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            <input type="hidden" name="btAction" value="SearchHistoryOrder" />
        </form>
    
    <c:if test="${not empty requestScope.ORDERHISTORYMAP}">
            <div id="accordion">
                <c:forEach items="${requestScope.ORDERHISTORYMAP}" var="orderMap" varStatus="orderCount">
            <c:set value="${orderMap.key}" var="order"/>
            <c:set value="${orderMap.value}" var="orderDetail"/>
            <div class="card">
    <div class="card-header" id="heading${orderCount.count}">
      <h5 class="mb-0">
        <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${orderCount.count}" aria-expanded="true" aria-controls="collapse${orderCount.count}">
            ID: #${order.orderID}    ---   Time: ${order.date}   ---    Address: ${order.address}    ---   Phone: ${order.phoneNumber}  
        </button>
      </h5>
    </div>

    <div id="collapse${orderCount.count}" class="collapse" aria-labelledby="heading${orderCount.count}" data-parent="#accordion">
      <div class="card-body">
          <table class="table">
  <thead>
      <tr>
          <th scope="col">
              #
          </th>
          <th scope="col">
              Product
          </th>
          <th scope="col">
              
          </th>
          <th scope="col">
              Price
          </th>
          <th scope="col">
              Quantity
          </th>
      </tr>
  </thead>
  <tbody>
      <c:set value="0" var="total"/>
      <c:forEach items="${orderDetail}" var="dto" varStatus="count">
          <tr>
            <th scope="row">${count.count}</th>
            <td><a href="ControllerServlet?btAction=PrepareProduct&proID=${dto.proID}"><img class="img-fluid" style="width: 8vw;height: 8vh" src="data:image/jpeg;base64,${dto.image}" alt="${dto.proName}"></a></td>
            <td>${dto.proName}</td>
            <td>${dto.price}</td>
            <td>${dto.quantity}</td>
          </tr>
          <c:set value="${total+dto.price*dto.quantity}" var="total"/>
      </c:forEach>    
          <tr>
              <td colspan="4"></td>
              <td> 
                  <p style="color: red; font-size: 150%">
                      TOTAL: ${total} VND
                  </p>
              </td>
          </tr>
  </tbody>
</table>

      </div>
    </div>
  </div>
        </c:forEach>
  
  

</div>
    </c:if>
    <c:if test="${empty requestScope.ORDERHISTORYMAP and not empty param.btAction}">
                      <p style="font-size: 250%; color: red"<br><br>No Result</p>
    </c:if>
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
