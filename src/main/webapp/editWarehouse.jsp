<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/16/2020
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý kho hàng</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='bootstrap.min.css'>
    <style>
        .jumbotron {
            background-color: #fafafa;
        }
    </style>
</head>
<body>
<p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
<c:if test="${fn:contains(Type, 'Add')}">
    <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
        <div class="jumbotron">
            <div class="page-header">
                <h1>Thêm kho hàng</h1>
                <p class="text-info"><c:out value="${status}"></c:out></p>
            </div>
            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                <input type="hidden" id="Type1" name="Type" value="${Type}">

                <div class="form-group">
                    <label for="MaKho1">Mã kho:</label>
                    <input type="text" required
                           class="form-control" id="MaKho1"
                           name="MaKho" value="${MaKho}">
                    <p style="color: red"><c:out value="${error.MaKho}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="TenKho1">Tên kho:</label>
                    <input type="text"
                           class="form-control" id="TenKho1"
                           name="TenKho" value="${TenKho}">
                    <p style="color: red"><c:out value="${error.TenKho}"></c:out></p>
                </div>


                <div class="form-group">
                    <label for="DiaChi1">Địa chỉ:</label>
                    <input type="text"
                           class="form-control" id="DiaChi1"
                           name="DiaChi" value="${DiaChi}">
                    <p style="color: red"><c:out value="${error.DiaChi}"></c:out></p>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Xác nhận</button>
                </div>

            </form>
        </div>
    </div>
</c:if>
<c:if test="${fn:contains(Type, 'Edit')}">
    <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
        <div class="jumbotron">
            <div class="page-header">
                <h1>Thay đổi thông tin kho hàng</h1>
                <p class="text-info"><c:out value="${status}"></c:out></p>
            </div>
            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                <input type="hidden" id="Type2" name="Type" value="${Type}">
                <input type="hidden" id="MaKho2" name="MaKho" value="${MaKho}">

                <div class="form-group">
                    <label for="TenKho2">Tên kho:</label>
                    <input type="text"
                           class="form-control" id="TenKho2"
                           name="TenKho" value="${TenKho}">
                    <p style="color: red"><c:out value="${error.TenKho}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="DiaChi2">Địa chỉ:</label>
                    <input type="text"
                           class="form-control" id="DiaChi2"
                           name="DiaChi" value="${DiaChi}">
                    <p style="color: red"><c:out value="${error.DiaChi}"></c:out></p>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Xác nhận thay đổi</button>
                </div>

            </form>
        </div>
    </div>
</c:if>

</body>
</html>
