<%-- 
    Document   : Menu
    Created on : Oct 28, 2022, 6:10:44 PM
    Author     : huynh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!--begin of the menu-->
        <nav class="navbar navbar-expand-md bg-dark">
            <div class="container">
                <a class="navbar-brand" href="MainController?action=Home">Trang chủ</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">

                        <c:if test="${sessionScope.LOGIN_USER.isAdmin == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=ManageProduct">Quản lý sản phẩm</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.LOGIN_USER != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=Logout">Đăng xuất</a>
                            </li>
                            <li class="nav-item">
                                <a class="bg-info">Chào ${sessionScope.LOGIN_USER.userName}</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.LOGIN_USER == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="Login.jsp">Đăng nhập</a>
                            </li>
                        </c:if>   

                    </ul>

                    <form action="MainController" method="GET" class="form-inline my-2 my-lg-0">
                        <div class="input-group input-group-sm">
                            <input name="search" type="text" value="${requestScope.NAME_SEARCH}" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Tìm kiếm sản phẩm...">
                            <div class="input-group-append">
                                <button type="submit" name="action" value="Search" class="btn btn-secondary">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                        <c:if test="${sessionScope.LOGIN_USER.isAdmin == 0}">
                            <a class="btn btn-white btn-sm ml-3" href="MainController?action=ManageCart">
                                <i class="fa fa-shopping-cart"></i> Giỏ hàng
                                <span class="badge badge-light">${sessionScope.CART_LIST.size()}</span>
                            </a>
                            <a class="btn btn-white btn-sm ml-3" href="MainController?action=ManageOrder">
                                <i class="fa fa-list"></i> Đơn hàng
                            </a>
                        </c:if>
                </div>
            </div>
        </nav>        
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Shop Kon Kưng</h1>
                <p class="lead text-muted mb-0">Ở đây có đủ đồ cho bé cần</p>
            </div>
        </section>
        <!--end of the menu-->

        <!--Định dạng body ở dưới-->
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <!--Định dạng toàn bộ sản phẩm-->
                    <div class="card bg-light mb-3">                        
                        <div class="card-header bg-info text-center text-uppercase"><i class="fa fa-star"></i>Danh mục sản phẩm</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${requestScope.listCategory}" var="ca">
                                <li class="list-group-item text-uppercase ${requestScope.ticked == ca.cateID ? "active" : ""}"><a href="MainController?action=Category&category-id=${ca.cateID}">${ca.cateName}</a></li>
                                </c:forEach>
                        </ul>