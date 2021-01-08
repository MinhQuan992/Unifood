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
</div>

<div id="footer">
    <p style="text-align: center">
        <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
    </p>
</div>

</body>
</html>
