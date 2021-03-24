<%-- 
    Document   : ShopPage
    Created on : Jan 10, 2021, 10:14:38 PM
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
        <script>
            window.location="#first";
        </script>
    </head>
    <body>
        
        <!--Header -->
    <head>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>
        <c:set value="${sessionScope.PAGE}" var="page"/>
        <c:set value="${sessionScope.PRONAMESEARCH}" var="proNameSearch"/>
        <c:set value="${sessionScope.TOTALPAGE}" var="totalPage"/>
        <c:set value="${sessionScope.CATEID}" var="cateID"/>
        <c:set value="${sessionScope.FROMPRICE}" var="fromPrice"/>
        <c:set value="${sessionScope.TOPRICE}" var="toPrice"/>
        <c:set value="${sessionScope.STATUS}" var="status"/>
        <c:set value="${sessionScope.PROLIST}" var="proList"/>
        
        <c:import url="Top.jsp"/>
</head>
<!--/Header -->
<!--Main Section -->
<main>
    
    <!--First Slider-->

    <div class="container">
        <div id="carouselIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselIndicators" data-slide-to="1"></li>
                <li data-target="#carouselIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" >
                <div class="carousel-item active">
                    <img src="Image/Wild(1).jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="Image/Wild(2).jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="Image/Wild(3).jpg" class="d-block w-100" alt="...">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" style="background-color:  #878787" aria-hidden="true"></span>
                <span class="sr-only"  >Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" style="background-color:  #878787" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <!--/First Slider-->
    <div class="container-fluid">        


        <div class="container">
            <div style="padding-bottom: 10vh">
                <form action="ControllerServlet">
                    <c:if test="${account.admin==true}">
                        <label for="statusOption">Status</label>
                        <select id="statusOption" class="form-control" name="status">                                  
                            <option value="1"
                                    <c:if test="${status==1}">
                                        selected 
                                    </c:if>      
                                    >
                                Supply
                            </option>
                            <option value="0"
                                    <c:if test="${status==0}">
                                        selected 
                                    </c:if>        
                                    >
                                Non Supply
                            </option>
                        </select><br/> 
                    </c:if> 
                    <label for="cateOption">Category</label>

                    <select id="cateOption" class="form-control" name="cateID">
                        <option value="">None</option>
                        <c:forEach items="${applicationScope.CATEGORY}" var="cate">
                            <option value="${cate.cateID}"
                                    <c:if test="${cate.cateID==cateID}">
                                        selected
                                    </c:if>        
                                    >
                                ${cate.cateName}
                            </option>
                        </c:forEach>
                    </select><br/>
                    <label for="fromPriceInput">From Price</label>
                    <input class="form-control" id="fromPriceInput" placeholder="From" min="0" type="number" name="fromPrice" value="${fromPrice}"/>
                    <label for="toPriceInput">To Price</label>
                    <input class="form-control" id="toPriceInput" placeholder="To" type="number" min="0" name="toPrice" value="${toPrice}"/><br/>
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" name="proNameSearch" value="${proNameSearch}"><br/>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    <input type="hidden" name="btAction" value="Search" />
                    <input type="hidden" name="page" value="1" />
                </form>
            </div>



                    <div class="product" id="first">
                <a href="ControllerServlet?page=1">PRODUCTS</a> 
            </div>
            <c:if test="${account.admin==true}">
                <button onclick="controlAddView('AddNew')" class="btn btn-primary" >Add New</button>
                <form action="ControllerServlet" method="POST" enctype="multipart/form-data" id="AddNew" hidden="true">
                    <label for="exampleFormControlInput1">Name</label>
                    <input type="text" class="form-control" id="exampleFormControlInput1" name="proName" value="" minlength="2" maxlength="50" required>

                    <label for="exampleFormControlFile1">Image</label>
                    <input type="file" class="form-control-file" id="exampleFormControlFile1" name="image">

                    <label for="priceFormInput">Price</label>
                    <input type="number" class="form-control" id="priceFormInput" name="price" value="" min="1" required>

                    <label for="exampleFormControlTextarea1">Description</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="1" name="description" value="" maxlength="2000"></textarea>

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



                    <label for="quantityFormInput">Quantity</label>
                    <input type="number" class="form-control" id="quantityFormInput" name="quantity" min="0" value="" required>
                    <input type="hidden" name="btAction" value="CreateProduct" />
                    <input type="submit" value="Create" />
                    <input type="reset" value="Reset" />
                </form>
            </c:if>
            <c:if test="${not empty proList}">

                <br/><br/><form action="ControllerServlet">
                    <input type="hidden" name="page" value="${page}" />
                    <input type="hidden" name="proNameSearch" value="${proNameSearch}" />
                    <input type="hidden" name="cateID" value="${cateID}" />
                    <input type="hidden" name="fromPrice" value="${fromPrice}" />
                    <input type="hidden" name="toPrice" value="${toPrice}" />

                    <c:if test="${account.admin==true}">
                        <c:if test="${status==1 || empty status}">
                            <!--            <input type="hidden" name="btAction" value="DeleteProduct" />
                                        <button type="submit"><i class="fa fa-trash"></i> Remove</button>-->
                            <!-- Button trigger modal -->
                            <input type="hidden" name="btAction" value="DeleteProduct" />
                            <input type="hidden" name="status" value="1" />
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                                Remove
                            </button>

                            <!-- Modal -->

                            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">WARNING!!!</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Do you want to remove these these products ???  
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Remove</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${status==0}">


                            <input type="hidden" name="btAction" value="ActiveProduct" />
                            <input type="hidden" name="status" value="0" />
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                                Re-Create
                            </button>
                            <!-- Modal -->

                            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">WARNING!!!</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Do you want to re-create these these products ???  
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Re-Create</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:if>    
                    <div class="row">




                        <c:forEach items="${proList}" var="dto">
                            <div class="col-md-3 col-sm-6">
                                <div class="product-item">
                                    <div class="product-img">
                                        <img class="img-fluid" src="data:image/jpeg;base64,${dto.image}" alt="${dto.proName}">
                                        <ul class="social">
                                            <c:if test="${account.admin==true}">
                                                <li>
                                                    <input type="checkbox" name="selectedItem" value="${dto.proID}"/>
                                                </li>
                                            </c:if>     
                                            <li><a href="ControllerServlet?btAction=PrepareProduct&proID=${dto.proID}"><i class="fas fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                    <div class="product-content">
                                        <h3 class="name-product"><a href="ControllerServlet?btAction=PrepareProduct&proID=${dto.proID}">${dto.proName}</a></h3>
                                        <div class="price">${dto.price}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>




                    </div>
                </form>
                <div class="PagingButton" style="height: 10vh">
                    <div style="width: 40%;height: auto; float: left; text-align: right">

                        <form action="ControllerServlet">
                            <input type="hidden" name="proNameSearch" value="${proNameSearch}" />
                            <input type="hidden" name="cateID" value="${cateID}" />
                            <input type="hidden" name="fromPrice" value="${fromPrice}" />
                            <input type="hidden" name="toPrice" value="${toPrice}"/>
                            <input type="hidden" name="page" value="${page-1}" />
                            <input type="hidden" name="btAction" value="Search" />
                            <button type="submit" class="btn btn-danger"
                                    <c:if test="${page<=1}">
                                        disabled
                                    </c:if>        
                                    >
                                Back
                            </button>
                        </form>
                    </div>
                    <div style="width: 20%;height: auto; float: left; text-align: center">
                        <p style="color: red">
                            ${page}
                        </p>
                    </div>
                    <div style="width: auto;height: auto; float: left; text-align: left">
                        <form action="ControllerServlet">
                            <input type="hidden" name="proNameSearch" value="${proNameSearch}" />
                            <input type="hidden" name="cateID" value="${cateID}" />
                            <input type="hidden" name="fromPrice" value="${fromPrice}" />
                            <input type="hidden" name="toPrice" value="${toPrice}"/>
                            <input type="hidden" name="page" value="${page+1}" />
                            <input type="hidden" name="btAction" value="Search" />
                            <button type="submit" class="btn btn-danger"
                                    <c:if test="${totalPage <= page}">
                                        disabled
                                    </c:if>            
                                    >
                                Next
                            </button>
                        </form> 
                    </div>


                </div>	    
            </c:if>
            <c:if test="${empty proList}">
                <p style="font-size: 250%; color: red"<br><br>No Result</p>
                </c:if>
        </div>


    </div>

    <!--Sale Slider-->


</main>
<!--/Main Section -->
    <c:import url="Bot.jsp"/>	
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<c:if test="${not empty param.msg}">
    <script> alert("${param.msg} !!!!");</script>
</c:if>
<script>
    function controlAddView(id) {
        document.getElementById(id).hidden = document.getElementById(id).hidden ^ true;
    }
</script>
</html>

