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
                <h1>Thêm sản phẩm vào kho hàng ${MaKho}</h1>
                <p class="text-info"><c:out value="${status}"></c:out></p>
            </div>
            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                <input type="hidden" id="Type1" name="Type" value="${Type}">
                <input type="hidden" id="MaKho1" name="MaKho" value="${MaKho}">

                <div class="form-group">
                    <label for="MaSanPham1">Mã sản phẩm:</label>
                    <input type="text"
                           class="form-control" id="MaSanPham1"
                           name="MaSanPham" value="${MaSanPham}">
                    <p style="color: red"><c:out value="${error.MaSanPham}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="TenSanPham1">Tên sản phẩm:</label>
                    <input type="text"
                           class="form-control" id="TenSanPham1"
                           name="TenSanPham" value="${TenSanPham}">
                    <p style="color: red"><c:out value="${error.TenSanPham}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="DonViTinh1">Đơn vị tính:</label>
                    <input type="text"
                           class="form-control" id="DonViTinh1"
                           name="DonViTinh" value="${DonViTinh}">
                    <p style="color: red"><c:out value="${error.DonViTinh}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="DonGia1">Đơn giá:</label>
                    <input type="number"
                           class="form-control" id="DonGia1"
                           name="DonGia" value="${DonGia}">
                    <p style="color: red"><c:out value="${error.DonGia}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="SoLuong1">Số lượng:</label>
                    <input type="number"
                           class="form-control" id="SoLuong1"
                           name="SoLuong" value="${SoLuong}">
                    <p style="color: red"><c:out value="${error.SoLuong}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="AnhMinhHoa1">Ảnh minh họa:</label>
                    <input type="text"
                           class="form-control" id="AnhMinhHoa1"
                           name="AnhMinhHoa" value="${AnhMinhHoa}">
                    <p style="color: red"><c:out value="${error.AnhMinhHoa}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="MaNhom1">Mã nhóm:</label>
                    <input type="number"
                           class="form-control" id="MaNhom1"
                           name="MaNhom" value="${MaNhom}">
                    <p style="color: red"><c:out value="${error.MaNhom}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="MoTa1">Mô tả:</label>
                    <input type="text"
                           class="form-control" id="MoTa1"
                           name="MoTa" value="${MoTa}">
                    <p style="color: red"><c:out value="${error.MoTa}"></c:out></p>
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
                <h1>Thay đổi thông tin sản phẩm trong kho hàng ${MaKho}</h1>
                <p class="text-info"><c:out value="${status}"></c:out></p>
            </div>
            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                <input type="hidden" id="Type2" name="Type" value="${Type}">
                <input type="hidden" id="MaKho2" name="MaKho" value="${MaKho}">
                <input type="hidden" id="MaSanPham2" name="MaSanPham" value="${MaSanPham}">

                <div class="form-group">
                    <label for="TenSanPham2">Tên sản phẩm:</label>
                    <input type="text"
                           class="form-control" id="TenSanPham2"
                           name="TenSanPham" value="${TenSanPham}">
                    <p style="color: red"><c:out value="${error.TenSanPham}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="DonViTinh2">Đơn vị tính:</label>
                    <input type="text"
                           class="form-control" id="DonViTinh2"
                           name="DonViTinh" value="${DonViTinh}">
                    <p style="color: red"><c:out value="${error.DonViTinh}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="DonGia2">Đơn giá:</label>
                    <input type="number"
                           class="form-control" id="DonGia2"
                           name="DonGia" value="${DonGia}">
                    <p style="color: red"><c:out value="${error.DonGia}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="SoLuong2">Số lượng:</label>
                    <input type="number"
                           class="form-control" id="SoLuong2"
                           name="SoLuong" value="${SoLuong}">
                    <p style="color: red"><c:out value="${error.SoLuong}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="AnhMinhHoa2">Ảnh minh họa:</label>
                    <input type="text"
                           class="form-control" id="AnhMinhHoa2"
                           name="AnhMinhHoa" value="${AnhMinhHoa}">
                    <p style="color: red"><c:out value="${error.AnhMinhHoa}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="MaNhom2">Mã nhóm:</label>
                    <input type="number"
                           class="form-control" id="MaNhom2"
                           name="MaNhom" value="${MaNhom}">
                    <p style="color: red"><c:out value="${error.MaNhom}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="MoTa2">Mô tả:</label>
                    <input type="text"
                           class="form-control" id="MoTa2"
                           name="MoTa" value="${MoTa}">
                    <p style="color: red"><c:out value="${error.MoTa}"></c:out></p>
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
