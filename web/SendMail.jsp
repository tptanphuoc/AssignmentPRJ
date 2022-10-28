<%@page language="java" contentType="text/html ; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <title>Send Information Of Order</title>
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
         
            <c:when test = "${sessionScope.LOGIN_USER.isAdmin != 0}">
                <c:redirect url = "HomeController"/>
            </c:when>
        </c:choose>
    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row"
                 style="border: 1px darkgray solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
                <div class="col-sm-12">
                    <h2 class="myclass">Bạn muốn nhận thông tin đơn hàng qua email?</h2>
                    <form action="MainController" method="POST">
                        <div class="form-group">
                            <label>Email của bạn:</label>
                            <input type="text" class="form-control" name="email" placeholder="Email của bạn" required autofocus>
                        </div>
                        <button type="submit" class="btn btn-primary" name="action" value="SendMail"
                                onclick="return confirm('Xác nhận gửi thông tin qua email này?')">Xác nhận</button>
                        <a href="MainController?action=Home" class="btn btn-primary"><span>Quay lại trang chủ</span></a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>