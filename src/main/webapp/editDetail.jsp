<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hương Gấu
  Date: 12/24/2020
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa chi tiết sản phẩm</title>
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
<%
    String maSanPham = request.getParameter("maSanPham");
%>
<c:if test="${not empty Trangthaicapnhat}">
    <c:choose>
        <c:when test="${Trangthaicapnhat==true}">
            <script type="text/javascript">
                alert("Cập nhật thành công!");
            </script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript">
                alert("Cập nhật không thành công!");
            </script>
        </c:otherwise>
    </c:choose>
</c:if>
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

<form id="procontainer" method="post" action="${pageContext.request.contextPath}/editdetail" style="align: center;">
    <div class="form-group">
        <label for="maSanPham">Mã Sản Phẩm: </label>
        <input type="text"
               class="form-control" id="maSanPham"
               name="maSanPham" value="${maSanPham}">
        <p style="color: gray; font-family: Arial"><c:out value="${maSanPhamError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="tenSanPham">Tên Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="tenSanPham"
               name="tenSanPham" value="${tenSanPham}">
        <p style="color: gray; font-family: Arial"><c:out value="${tenSanPhamError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="donGia">Giá Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="donGia"
               name="donGia" value="${donGia}">
        <p style="color: gray; font-family: Arial"><c:out value="${donGiaError}"></c:out></p>
    </div>

    <div class="form-group">
    <label for="donViTinh">Đơn Vị Tính của Sản Phẩm:</label>
    <input type="text"
           class="form-control" id="donViTinh"
           name="donViTinh" value="${donViTinh}">
    <p style="color: gray; font-family: Arial"><c:out value="${donViTinhError}"></c:out></p>
</div>

    <div class="form-group">
        <label for="soLuong">Số Lượng Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="soLuong"
               name="soLuong" value="${soLuong}">
        <p style="color: gray; font-family: Arial"><c:out value="${soLuongError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="anhMinhHoa">Hình Ảnh Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="anhMinhHoa"
               name="anhMinhHoa" value="${anhMinhHoa}">
        <p style="color: gray; font-family: Arial"><c:out value="${anhMinhHoaError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="maNhom">Mã Nhóm của Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="maNhom"
               name="maNhom" value="${maNhom}">
        <p style="color: gray; font-family: Arial"><c:out value="${maNhomError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="maKho">Mã Kho của Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="maKho"
               name="maKho" value="${maKho}">
        <p style="color: gray; font-family: Arial"><c:out value="${maKhoError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="moTa">Mô Tả Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="moTa"
               name="moTa" value="${moTa}">
        <p style="color: gray; font-family: Arial"><c:out value="${moTaError}"></c:out></p>
    </div>

    <input type="submit" style="text-align: center; background-color: #60150c; text-decoration-color: white;" value="Xác Nhận">

</form>

</div><br>

<form method="post" action="${pageContext.request.contextPath}/getPro">
    <input type="submit" style="align: center; background-color: #60150c; text-decoration-color: white;" value="Quay lại">
</form>

<div id="footer">
    <p style="text-align: center">
        <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
    </p>
</div>
</body>
</html>
