<%-- 
    Document   : ProductPage
    Created on : Jan 11, 2021, 10:06:39 PM
    Author     : TRONG DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${requestScope.PRODUCT.proName}</title>
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
<link rel="stylesheet" href="css/back to top flower.css"/>
<link rel="stylesheet" href="css/product.css"/>
<link rel="stylesheet" href="css/flower.css"/>
<link rel="stylesheet" href="css/back to top product.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js
"></script>
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
<div class="container">
    <c:set value="${requestScope.PRODUCT}" var="product"/>
    <c:if test="${empty product}">
        <h2 style="color: red">ERROR !!!</h2>
    </c:if>
    <c:if test="${not empty product}">
        <c:set value="${sessionScope.ACCOUNT}" var="account"/>
	<div class="wrapper col-xs-12 col-sm-12 col-md-10 col-lg-10 col-12 m-auto">
		<div class="product-img col-sm-6 col-md-6 col-lg-6 col-6 pt-5 h-100">	
                    <div class="preview-pic tab-content">
                        <div class="tab-pane in active">
                            <img src="data:image/jpeg;base64,${product.image}" width="10vw" height="500vh"/>	
                        </div>                        
                    </div>
                    			
		</div>
		<div class="product-info col-sm-6 col-lg-6 col-md-6 col-6 h-100">
                    <c:if test="${account.admin==true}">
                        <div id="adminView" class="product-info col-sm-6 col-lg-6 col-md-6 col-6 h-100" style="position: relative">
                            <form action="ControllerServlet" method="POST" enctype="multipart/form-data">
                                <div style="position: absolute; top:0;width:100%; height:70%; padding-top: 1vh">
					<div style="width:100%; height:auto">
                                                <input type="hidden" name="proID" value="${product.proID}" />
                                                <label for="exampleFormControlInput1">Name</label>
                                                <input type="text" class="form-control" id="exampleFormControlInput1" name="proName" value="${product.proName}" minlength="2" maxlength="50" required>

    						<label for="exampleFormControlFile1">Image</label>
                                                <input type="file" class="form-control-file" id="exampleFormControlFile1" name="image">

    						<label for="priceFormInput">Price</label>
                                                <input type="number" class="form-control" id="priceFormInput" name="price" value="${product.price}" min="1" required>

    						<label for="exampleFormControlTextarea1">Description</label>
                                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="1" name="description" maxlength="2000">${product.description}</textarea>

    						<label for="exampleFormControlSelect1">Category</label>
    						<select class="form-control" id="exampleFormControlSelect1" name="cateID">
							<c:forEach items="${applicationScope.CATEGORY}" var="dto">
                                                            <option <c:if test="${dto.cateID==product.cateID}">
                                                                    selected
                                                                    </c:if>
                                                                value="${dto.cateID}">${dto.cateName}
                                                            </option>    
                                                        </c:forEach>         	      
    						</select>	

    						<label for="statusForm">Supply</label>	
    						<select class="form-control" id="statusForm" name="status">
                                                        <option <c:if test="${product.status==true}">
                                                                   selected
                                                                </c:if> 
                                                            value="1" >Yes
                                                        </option>
                                                        <option <c:if test="${product.status==false}">
                                                                   selected
                                                                </c:if> 
                                                            value="0" >No
                                                        </option>
    						</select>

    						<label for="quantityFormInput">Quantity</label>
                                                <input type="number" class="form-control" id="quantityFormInput" name="quantity" value="${product.quantity}" required>	
					</div>
				 	
				 	
				</div>
                                <div style="position: absolute; bottom:0; width:100%; height:10%">
                                    <div style="width:50%; height:100%; float:left">
                                        <input type="hidden" name="btAction" value="UpdateProduct" />
                                        <button type="submit" class="btn btn-danger">Save</button>
 
                                    </div>
                                    <div style="width:50%; height:100% ;float:left; text-align: center">
                                        <a href="ControllerServlet"><button type="button" class="btn btn-danger">Others</button></a> 
                                    </div>			
                                </div>
                            </form>
				
				
		</div>
                    </c:if>
                    <c:if test="${empty account or account.admin==false}">
                        <form action="ControllerServlet">
                            <div style="position: absolute; top:5vh;width:100%; height:60%; overflow-wrap: break-word">
					<div style="width:100%; height:30%">
                                            <h1 style="color: #E76644">${product.proName}</h1>
                                                <input type="hidden" name="proID" value="${product.proID}" />
					</div>
					<div style="width:100%; height:70%">
                                            <p>${product.description}</p>
					</div>
				 	
				 	
				</div>
				<div style="position: absolute; bottom:0; width:100%; height:40%">
						<div style="width:100%; height:25%">
						<p style="font-size: 250%">${product.price} VND</p><br/>					
						</div>
						<div style="width:100%; height:35%">
                                                    <label for="quantityInput">Quantity</label>
                                                    <input class="form-control" id="quantityInput" type="number" name="quantity" min="0" max="${product.quantity}" title="Max is ${product.quantity}" required><br/> 
						</div>
						<div style="width:100%; height:40%;">
							<div style="width:50%; height:100%; float:left">
                                                            <button type="submit" class="btn btn-danger">Add to Cart </button>
                                                            <input type="hidden" name="btAction" value="AddProductCart" />
							</div>
							<div style="width:50%; height:100% ;float:left; text-align: center">
                                                            <a href="ControllerServlet?page=1"><button type="button" class="btn btn-danger ">Others</button></a> 
							</div>				 		
						</div>	
				</div>
                        </form>
                    </c:if>
		</div>
	</div>
    </c:if>  
            <c:if test="${not empty param.msg}">
            <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Warning!</strong> ${param.msg}
            </div>
            </c:if>
         <br/><br/> <hr class="my-4">
         <c:set value="${requestScope.OFTENPURCHASEDPRODUCT}" var="proList"/>
         <c:if test="${not empty proList}">
                     <div id="multi-item-example" class="carousel slide carousel-multi-item" data-ride="carousel">

      <!--Controls-->
      <div class="controls-top">
        <a class="btn-floating" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
        <a class="btn-floating" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i></a>
      </div>
      <!--/.Controls-->

      <!--Indicators-->
      <ol class="carousel-indicators">
          <c:forEach begin="0" end="${requestScope.TOTAL-1}" var="number" varStatus="count">
              <li data-target="#multi-item-example" data-slide-to="${number}" 
                  <c:if test="${count.count==0}">
                      class="active"
                  </c:if>
                >
              </li>
          </c:forEach>
      </ol>
      <!--/.Indicators-->

      <!--Slides-->
      <div class="carousel-inner" role="listbox">
          <c:forEach items="${requestScope.OFTENPURCHASEDPRODUCT}" var="dto" varStatus="count">
              <c:if test="${count.count%3==1}">
                  <div class="carousel-item                        
                       <c:if test="${count.count==1}">
                           active
                       </c:if>    
                  ">
                      <div class="row">
              </c:if>
                          <div class="col-md-4 
                               <c:if test="${count.count div 3==1}">
                                   clearfix d-none d-md-block
                               </c:if>
                          ">
                              <div class="card mb-2" style="text-align: center">
                                  <a href="ControllerServlet?btAction=PrepareProduct&proID=${dto.proID}"><img class="card-img-top" src="data:image/jpeg;base64,${dto.image}" style="height: 20vh;width: 15vw"></a> 
                                  <div class="card-body">
                                      <h4 class="card-title">${dto.proName}</h4>
                                      <p class="card-text">${dto.price}</p>
                                  </div>
                              </div>
                          </div>     
              <c:if test="${count.count div 3==1}">
                      </div>
                  </div>
              </c:if>
          </c:forEach>
        <!--First slide-->
        <div class="carousel-item active">

        <!--/.First slide-->

        

      </div>
      <!--/.Slides-->

    </div>
    <!--/.Carousel Wrapper-->
</div>
         </c:if>


</main>
<!--/Main Section -->
<c:import url="Bot.jsp"/>	
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" 
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" 
		crossorigin="anonymous"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

</body>
</html>
