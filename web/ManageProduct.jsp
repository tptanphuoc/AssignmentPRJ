<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Products Management Table</title>
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
.table-responsive {
    margin: 30px 0;
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
.table-title .btn {
    color: #566787;
    float: right;
    font-size: 13px;
    background: #fff;
    border: none;
    min-width: 50px;
    border-radius: 2px;
    border: none;
    outline: none !important;
    margin-left: 10px;
}
.table-title .btn:hover, .table-title .btn:focus {
    color: #566787;
    background: #f2f2f2;
}
.table-title .btn i {
    float: left;
    font-size: 21px;
    margin-right: 5px;
}
.table-title .btn span {
    float: left;
    margin-top: 2px;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
    padding: 12px 15px;
    vertical-align: middle;
}
table.table tr th:first-child {
    width: 60px;
}
table.table tr th:last-child {
    width: 100px;
}
table.table-striped tbody tr:nth-of-type(odd) {
    background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
    background: #f5f5f5;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}	
table.table td:last-child i {
    opacity: 0.9;
    font-size: 22px;
    margin: 0 5px;
}
table.table td a {
    font-weight: bold;
    color: #566787;
    display: inline-block;
    text-decoration: none;
}
table.table td a:hover {
    color: #2196F3;
}
table.table td a.settings {
    color: #2196F3;
}
table.table td a.delete {
    color: #F44336;
}
table.table td i {
    font-size: 19px;
}
table.table .avatar {
    border-radius: 50%;
    vertical-align: middle;
    margin-right: 10px;
}
.status {
    font-size: 30px;
    margin: 2px 2px 0 0;
    display: inline-block;
    vertical-align: middle;
    line-height: 10px;
}
.text-success {
    color: #10c469;
}
.text-info {
    color: #62c9e8;
}
.text-warning {
    color: #FFC107;
}
.text-danger {
    color: #ff5b5b;
}
.pagination {
    float: right;
    margin: 0 0 5px;
}
.pagination li a {
    border: none;
    font-size: 13px;
    min-width: 30px;
    min-height: 30px;
    color: #999;
    margin: 0 2px;
    line-height: 30px;
    border-radius: 2px !important;
    text-align: center;
    padding: 0 6px;
}
.pagination li a:hover {
    color: #666;
}	
.pagination li.active a, .pagination li.active a.page-link {
    background: #03A9F4;
}
.pagination li.active a:hover {        
    background: #0397d6;
}
.pagination li.disabled i {
    color: #ccc;
}
.pagination li i {
    font-size: 16px;
    padding-top: 6px
}
.hint-text {
    float: left;
    margin-top: 10px;
    font-size: 13px;
}
.avatar{
    height: 60px; 
    width: 60px;
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
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
                        <h2>Trang Quản Lý Sản Phẩm</h2>
                    </div>
                    <div class="col-sm-7">
                        <a data-target="#addProductModal" data-toggle="modal" class="btn btn-secondary"><i class="material-icons">&#xE147;</i> <span>Thêm sản phẩm mới</span></a>
                        
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th></th>
                        <th>Tên sản phẩm</th>	
                        <th>Tiêu đề</th>
                        <th>Hình ảnh</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Thiết lập</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.listProductOfSeller}" var="p">
                    <tr>
                        <td></td>
                        <td><a href="MainController?action=Detail&product-id=${p.id}">${p.name}</a></td>
                        <td>${p.title}</td>
                        <td><img src="${p.image}" class="avatar" alt="Avatar"></td>                        
                        <td>
                            <fmt:formatNumber type="number" pattern="###,###,###" value="${p.price}"/>đ
                        </td>
                        <td>${p.quantity}</td>
                        <td>
                            <a href="MainController?action=ProductInformation&product-id=${p.id}" data-toggle="tooltip" class="settings" title="Sửa sản phẩm"><i class="material-icons">&#xE8B8;</i></a>
                            <a href="MainController?action=DeleteProduct&product-id=${p.id}" class="delete" title="Xóa sản phẩm" onclick="return confirm('Bạn có chắc muốn xóa sản phẩm: ${p.name}?')"><i class="material-icons">&#xE5C9;</i></a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="MainController?action=Home" class="btn btn-secondary"><span>Quay lại trang chủ</span></a>
        </div>
    </div>
    
</div>    
        <!-- Thêm sản phẩm mới -->
    	<div id="addProductModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="MainController" method="POST">
					<div class="modal-header">			
						<h4 class="modal-title">Thêm sản phẩm</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Tên sản phẩm</label>
							<input name="name" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Hình ảnh</label>
							<input name="image" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Giá</label>
							<input name="price" type="text" class="form-control" required>
						</div>
                                            	<div class="form-group">
							<label>Tiêu đề</label>
							<input name="title" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Số lượng
							<input name="quantity" type="text" class="form-control" required>
                                                        </label>
						</div>	
                                                <div class="form-group">
							<label>Loại mặt hàng</label>
                                                        <select name="category" class="form-select" aria-label="Default select example">
                                                            <c:forEach items="${requestScope.listCategory}" var="cate">
                                                                <option value="${cate.cateID}">${cate.cateName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
						<button type="submit" class="btn btn-success" name="action" value="AddProduct">Thêm</button>
					</div>
				</form>
			</div>
		</div>
	</div> 
</body>
</html>