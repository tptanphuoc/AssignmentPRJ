<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Edit Product Management</title>
<link rel="shortcut icon" href="flashglass.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<style>
body {
    color: #566787;
    background: #f5f5f5;
    font-family: 'Varela Round', sans-serif;
    font-size: 13px;
}

.table-wrapper {
    min-width: 1000px;
    background: #fff;
    padding: 20px 25px;
    border-radius: 3px;
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 15px;
    background: #299be4;
    color: #fff;
    padding: 16px 30px;
    margin: -20px -25px 10px;
    border-radius: 3px 3px 0 0;
}
.table-title h2 {
    margin: 5px 0 0;
    font-size: 24px;
}

</style>
    <c:choose>               
        <c:when test = "${sessionScope.LOGIN_USER == null}">
            <c:redirect url = "Login.jsp"/>
        </c:when>
         
        <c:when test = "${sessionScope.LOGIN_USER.isAdmin != 1}">
            <c:redirect url = "HomeController"/>
        </c:when>
    </c:choose> 

</head>
<body>
<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
                        <h2>Trang Chỉnh Sửa Sản Phẩm</h2>
                    </div>
                </div>
            </div>
                        
                    <div id="editProductModal">
            <div class="modal-dialog">
		<div class="modal-content">
                    <form action="MainController" method="POST">
			<div class="modal-header">						
				<h4 class="modal-title">Chỉnh sửa sản phẩm</h4>
			</div>
			<div class="modal-body">	
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${requestScope.PRODUCT_INFOR.id}" name="id" readonly class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Tên sản phẩm</label>
                                    <input value="${requestScope.PRODUCT_INFOR.name}" name="name" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Hình ảnh</label>
                                    <input value="${requestScope.PRODUCT_INFOR.image}" name="image" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Giá</label>
                                    <input value="${requestScope.PRODUCT_INFOR.price}" name="price" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Tiêu đề</label>
                                    <input value="${requestScope.PRODUCT_INFOR.title}" name="title" type="text" class="form-control" required>
				</div>	
                            	<div class="form-group">
                                    <label>Số lượng</label>
                                    <input value="${requestScope.PRODUCT_INFOR.quantity}" name="quantity" type="text" class="form-control" required>
				</div>
                                <div class="form-group">
                                    <label>Loại mặt hàng</label>
                                        <select name="category" class="form-select" aria-label="Default select example">
                                            <c:forEach items="${requestScope.listCategory}" var="cate">
                                                <option ${requestScope.PRODUCT_INFOR.category == cate.cateID ? "selected" : ""} value="${cate.cateID}">${cate.cateName}</option>
                                            </c:forEach>
                                        </select>                                                        
				</div>                          
			</div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-info" name="action" value="UpdateProduct">Lưu</button>
                            </div>
                    </form>
		</div>
            </div>
	</div>
                <a href="MainController?action=ManageProduct" class="btn btn-secondary"><span>Quay lại</span></a>
        </div>                                
    </div>    
</div>     
</body>
</html>
