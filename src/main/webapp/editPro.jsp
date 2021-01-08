<%--
  Created by IntelliJ IDEA.
  User: Hương Gấu
  Date: 12/24/2020
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sửa sản phẩm</title>
    <meta name="description" content="Quan Com Online Unifood" />
    <meta name="author" content="NhomHQNT">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="css/qlpage.css" />
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<div id="container">
    <nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
        <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
        <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav">
            <li class="nav-item active"><a class="nav-link" href="qlhome.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="staffs.jsp">STAFFS</a></li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">${User.hoVaTen}</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/ManageWarehouse">My Profile</a>
                    <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/signout">Sign Out</a>
                </div></li>
        </ul>
    </nav>
<table id="procontainer" style="align: center;" border="1" cellpadding="2">
    <thead style="align: center;">
        <tr bgcolor="#7fffd4">
            <td>Mã Sản Phẩm</td>
            <td>Tên Sản Phẩm</td>
            <td>Đơn Vị Tính</td>
            <td>Đơn Giá</td>
            <td>Số Lượng</td>
            <td>Ảnh Minh Họa</td>
            <td>Mã Nhóm</td>
            <td>Mã Kho</td>
            <td>Mô tả</td>
        </tr>
    </thead>
    <tbody style="align: center;">
        <c:forEach items="${products}" var="product">
            <tr>
                <td><c:out value="${product.maSanPham}"></c:out></td>
                <td><c:out value="${product.tenSanPham}"></c:out></td>
                <td><c:out value="${product.donViTinh}"></c:out></td>
                <td><c:out value="${product.donGia}"></c:out></td>
                <td><c:out value="${product.soLuong}"></c:out></td>
                <td><c:out value="${product.anhMinhHoa}"></c:out></td>
                <td><c:out value="${product.maNhom}"></c:out></td>
                <td><c:out value="${product.maKho}"></c:out></td>
                <td><c:out value="${product.moTa}"></c:out></td>

                <td>
                    <form method="post" action="${pageContext.request.contextPath}/getprodetail">
                        <input type="hidden" name="maSanPham" value="${product.maSanPham}">
                        <input type="hidden" name="tenSanPham" value="${product.tenSanPham}">
                        <input type="hidden" name="donViTinh" value="${product.donViTinh}">
                        <input type="hidden" name="donGia" value="${product.donGia}">
                        <input type="hidden" name="soLuong" value="${product.soLuong}">
                        <input type="hidden" name="anhMinhHoa" value="${product.anhMinhHoa}">
                        <input type="hidden" name="maNhom" value="${product.maNhom}">
                        <input type="hidden" name="maKho" value="${product.maKho}">
                        <input type="hidden" name="moTa" value="${product.moTa}">
                        <input type="submit" style="text-align: center; background-color: #60150c; text-emphasis-color: white;" value="Chỉnh sửa">
                    </form>
                </td>
            </tr>
        </c:forEach>

    </tbody>
</table>

<div id="footer">
    <p style="text-align: center">
        <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
    </p>
</div>
</div>
</body>
</html>
