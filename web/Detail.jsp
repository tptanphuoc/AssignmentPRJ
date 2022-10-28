<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="flashglass.png">
        <title>E-Shopper</title>

        <!--Style + Bootstrap-->        
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <!-- Include the above in your HEAD tag -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        
        <!--Style CSS-->
        <style>
            .num{
                color: red;
            }

            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }

            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }

            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }

            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }           
        </style>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <c:redirect url = "Login.jsp"/>
        </c:if>
    </head>

    <body>
        <!--begin of the menu-->
        <c:import url="Menu.jsp"></c:import>
                    </div>
                </div>  

                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="${requestScope.PRODUCT_DETAIL.image}"></a></div>
                                        </div>
                                        <div class="img-small-wrap">
                                        </div>
                                    </article>
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${requestScope.PRODUCT_DETAIL.name}</h3>
                                        <hr>
                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="num">
                                                    <fmt:formatNumber type="number" pattern="###,###,###" value="${requestScope.PRODUCT_DETAIL.price}"/>đ
                                                </span>
                                            </span>                                            
                                        </p>
                                        <dl class="item-property">
                                            <dt> Loại sản phẩm: <dd>${requestScope.PRODUCT_DETAIL.title}</dd></dt>

                                        </dl>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt> Số lượng: <a>${requestScope.PRODUCT_DETAIL.quantity}</a></dt>

                                                </dl>
                                            </div>
                                        </div>
                                        <hr>
                                        <c:if test="${sessionScope.LOGIN_USER.isAdmin == 0 and sessionScope.LOGIN_USER != null}">
                                            <a href="MainController?action=AddToCart&product-id=${requestScope.PRODUCT_DETAIL.id}&name=${requestScope.PRODUCT_DETAIL.name}&image=${requestScope.PRODUCT_DETAIL.image}&price=${requestScope.PRODUCT_DETAIL.price}" class="btn btn-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng </a>
                                        </c:if>
                                    </article>
                                </aside>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>

        <!--Chân trang-->
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>

