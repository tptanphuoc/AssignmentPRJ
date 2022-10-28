<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
        <c:choose>       
            <c:when test = "${requestScope.alert != null}">
                <script type="text/javascript">
                    var msg = "${requestScope.alert}";
                    alert(msg);
                </script>
            </c:when>
         
            <c:when test = "${sessionScope.LOGIN_USER == null}">
                <c:redirect url = "Login.jsp"/>
            </c:when>
      </c:choose>             
    <body>      
        <c:import url="Menu.jsp"></c:import>
                        <!--Định dạng kiểu sắp xếp sp-->
                        <div class="card-header bg-info text-center text-uppercase"><i class="fa fa-list"></i>Sắp xếp theo</div>
                        <ul class="list-group category_block">
                            <li class="list-group-item "><a href="MainController?action=Ascending">Giá tăng dần</a></li>                                                                 
                            <li class="list-group-item "><a href="MainController?action=Descending">Giá giảm dần</a></li>
                        </ul>
                        <!--Định dạng kiểu sắp xếp sp-->
                    </div>
                </div>                
                <div class="col-sm-9">
                    <h4 class="text-error">${requestScope.ERROR_SEARCH}</h4>
                    <div class="row">

                        <c:forEach items="${requestScope.listProduct}" var="pd">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${pd.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title show_txt"><a href="MainController?action=Detail&product-id=${pd.id}" title="Xem chi tiết">${pd.name}</a></h5>
                                        <p class="card-text show_txt">Số lượng: ${pd.quantity}</p>  
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">
                                                    <fmt:formatNumber type="number" pattern="###,###,###" value="${pd.price}"/>đ
                                                </p>
                                            </div>
                                            <div class="col">
                                                <c:if test="${sessionScope.LOGIN_USER.isAdmin == 0 and sessionScope.LOGIN_USER != null}">
                                                    <a href="MainController?action=AddToCart&product-id=${pd.id}&name=${pd.name}&image=${pd.image}&price=${pd.price}" class="btn btn-success btn-block">Thêm vào giỏ hàng</a>
                                                </c:if>                     
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>                
            </div>
        </div>
        <!--Chân trang-->
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>

