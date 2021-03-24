<%-- 
    Document   : Top.jsp
    Created on : Jan 20, 2021, 2:29:26 PM
    Author     : TRONG DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="account" value="${sessionScope.ACCOUNT}"/>
<div class="container">
    <div class="row">
        <div class="col-md-1 col-sm-12 col-12 col-lg-3">
        </div>
        <div class="col-md-12 ml-sm-4 col-12 col-lg-7">
            <a href="ShopPage.jsp" class="my-md-1 site-title"><img class="logo" src="Image/logo.jpg"></a>
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
                            <c:if test="${account.admin==true}">
                                <a href="#" data-toggle="modal" data-target="#exampleModal" class="dropdown-item" style="color: #E76644">Add New +</a>                                
                            </c:if>
                        </div>  
                    </div>


                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>

                    <div class="navbar-nav">
                        <li class="nav-item cart-icon">
                            <c:if test="${account.admin==false || empty account}">
                                <a href="ControllerServlet?btAction=PrepareCart" style="height: 100%;width: 100%"><i class="fas fas fa-shopping-basket" style="color: #7E7E7E;padding-top: 10%"></i></a>
                                </c:if>                            
                        </li>
                    </div>

                    <c:if test="${empty account}">
                        <li><a href="login.jsp">LOGIN</a></li>
                        </c:if>
                        <c:if test="${not empty account}">
                        <div class="btn-group">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAccount" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${account.fullname}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownAccount">
                                <c:if test="${account.admin!=true}">
                                    <a class="dropdown-item" href="OrderHistoryPage.jsp">History Order</a>
                                </c:if>
                                <c:if test="${account.admin==true}">
                                    <a class="dropdown-item" href="ControllerServlet?btAction=PrepareHistoryActivity">History Activity</a>
                                </c:if>
                                <a class="dropdown-item" href="ControllerServlet?btAction=LogOut">LogOut</a>

                            </div>     
                        </div>


                    </c:if>     

                </ul>
            </div>
        </nav>
    </div>
</div>
<form action="ControllerServlet">
                                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Create New Category</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <form>
                                                        <div class="form-group">
                                                            <label for="cateNameInput" class="col-form-label">Category Name</label>
                                                            <input type="text" class="form-control" id="cateNameInput" name="cateName" minlength="2" maxlength="50" required>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary" name="btAction" value="CreateCategory">Add</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>