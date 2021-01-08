<%@ page import="com.mvc.entities.DathangEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mvc.entities.SanphamEntity" %>
<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanh toán</title>
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
    <link type="text/css" rel="stylesheet" href="css/home.css" />
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
            <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
            <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li>
                <button id="close-image" onclick="${pageContext.request.contextPath}/Cart"><img src="Images/gio.png" style="width: auto; height: 50px;"></button>
                <button id="close-CSS"></button>
            <li class="nav-item active"><a class="nav-link" href="index.jsp">      </a></li>
            <c:if test="${not empty User}">
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">${User.hoVaTen}</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/EditInfo">My Profile</a>
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/signout">Sign Out</a>
                    </div></li>
            </c:if>
            <c:if test="${empty User}">
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardropp" data-toggle="dropdown"> Sign In - Sign Up </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item dropdown-item-custom" href="signin.jsp">Sign In</a>
                        <a class="dropdown-item dropdown-item-custom" href="signup.jsp">Sign Up</a>
                    </div></li>
            </c:if>
        </ul>
    </nav>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <%
        List<SanphamEntity> listSP = (List<SanphamEntity>) request.getAttribute("listSP");
        NguoidungEntity user = (NguoidungEntity) request.getAttribute("user");
    %>
    <div class="jumbotron">
        <div class="page-header">
            <h1>Trang thanh toán</h1>
        </div>

        <div class="container">
            <h3>Địa chỉ nhận hàng</h3>
            <p><strong>Họ tên: </strong><%= user.getHoVaTen() %></p>
            <p><strong>Điện thoại: </strong><%= user.getDienThoai() %></p>
            <p><strong>Địa chỉ: </strong><%= user.getDiaChi() %></p>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th class="text-center">Hình ảnh</th>
                    <th class="text-center">Tên sản phẩm</th>
                    <th class="text-center">Số luọng</th>
                    <th class="text-center">Đơn giá</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (SanphamEntity sp: listSP) {
                %>
                <tr>
                    <td class="text-center"><img src="<%= sp.getAnhMinhHoa()%>" width="200px"
                                                 onerror="this.onerror=null; this.src='311151.jpg'"></td>
                    <td><%= sp.getTenSanPham() %>
                    </td>
                    <td><%= sp.getSoLuong() %>
                    </td>
                    <td><%= sp.getDonGia() %>
                    </td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <th colspan="3">Tổng tiền: </th>
                    <td>${cost}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="footer">
    <p style="text-align: center">
        <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
    </p>
</div>
</div>
</body>
</html>
