<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="shortcut icon" href="flashglass.png">
        <link href="//cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <style>
            body{
                background-color: #000;
            }

            .card{
                width: 400px;
                border:none;
            }

            .btr{
                border-top-right-radius: 5px !important;
            }

            .btl{
                border-top-left-radius: 5px !important;
            }

            .btn-dark {
                color: #fff;
                background-color: #0d6efd;
                border-color: #0d6efd;
            }

            .btn-dark:hover {
                color: #fff;
                background-color: #0d6efd;
                border-color: #0d6efd;
            }

            .nav-pills{
                display:table !important;
                width:100%;
            }

            .nav-pills .nav-link {
                border-radius: 0px;
                border-bottom: 1px solid #0d6efd40;
            }

            .nav-item{
                display: table-cell;
                background: #0d6efd2e;
            }

            .form{
                padding: 10px;
                height: 300px;
            }

            .form input{
                margin-bottom: 12px;
                border-radius: 3px;
            }

            .form input:focus{
                box-shadow: none;
            }

            .form button{
                margin-top: 20px;
            }   

            .text-danger{
                text-align: center;
            }

            .text-success{
                color: green;
                text-align: center;
            }

        </style>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        
        <!--nếu có nội dung trong message thì mới hiện-->
        <c:if test="${requestScope.alert != null}" >
        <script type="text/javascript">
            var msg = "${requestScope.alert}";
            alert(msg);
        </script>
        </c:if>

    </head>

    <body>
        <div class="d-flex justify-content-center align-items-center mt-5">

            <div class="card">

                <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                    <li class="nav-item text-center">
                        <a class="nav-link active btl" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Login</a>
                    </li>
                    <li class="nav-item text-center">
                        <a class="nav-link btr" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Signup</a>
                    </li>
                </ul>

                <div class="tab-content" id="pills-tabContent">

                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                        <form class="form-login" action="MainController" method="post"> 
                            <div class="form px-4">

                                <input type="text" name="userName" class="form-control" placeholder="Username" required autofocus>

                                <input type="password" name="password" class="form-control" placeholder="Password" required autofocus>
                                <!-- reCAPTCHA -->
                                <div class="g-recaptcha" data-callback="recaptchaCallback" data-sitekey="6LdV2rYiAAAAAHIWwgyEDv4N4a99m0EBDXPM-5vs">
                                </div>
                                <button class="btn btn-success btn-block" type="submit" name="action" value="Login">Login</button>
                                <h6 class="text-danger">${requestScope.ERROR_LOGIN}</h6> <!--hiển thị lỗi khi nhập sai account-->
                            </div>
                        </form>
                    </div>

                    <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                        <form class="form-signin" action="MainController" method="post"> 
                            <div class="form px-4">

                                <input type="text" name="userName" class="form-control" placeholder="Username">

                                <input type="text" name="phone" class="form-control" placeholder="Phone Number">

                                <input type="password" name="password" class="form-control" placeholder="Password">

                                <input type="password" name="repassword" class="form-control" placeholder="Repeat Password">
                                <!-- reCAPTCHA -->
                                <div class="g-recaptcha" data-callback="recaptchaCallback" data-sitekey="6LdV2rYiAAAAAHIWwgyEDv4N4a99m0EBDXPM-5vs">
                                </div>
                                <button class="btn btn-dark btn-block" type="submit" name="action" value="Signup">Signup</button>
                                <h6 class="text-danger">${requestScope.ERROR_SIGNUP}</h6> <!--chỉ hiển thị khi đăng ký KHÔNG thành công-->
                                <h6 class="text-success">${requestScope.MESSAGE}</h6> <!--chỉ hiển thị khi đăng ký thành công-->
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
