<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết đơn hàng | Unifood</title>
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
        <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;">}</a>
        <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav">
            <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
            <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <a href="${pageContext.request.contextPath}/Cart?"><img class="cart" src="Images/gio.png" style="width: auto; height: 50px;"></a>
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> Sign In - Sign Up </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item dropdown-item-custom" href="signin.jsp">Sign In</a>
                    <a class="dropdown-item dropdown-item-custom" href="signup.jsp">Sign Up</a>
                </div></li>
        </ul>
    </nav>
<h1>Đơn hàng <span><c:out value="${orderID}"></c:out></span></h1>
<table style="text-align: center;">
    <thead>
    <tr>
        <td>Tên sản phẩm</td>
        <td>Số lượng</td>
        <td>Đơn giá</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${orderDetail}" var="detail">
        <tr>
            <td><c:out value="${detail.tenSanPham}"/></td>
            <td><c:out value="${detail.soLuong}"/></td>
            <td><p><c:out value="${detail.donGia}"/><span> VND</span></p></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>Trạng thái đơn hàng: <span><c:out value="${orderStatus}"/></span></p>
<p>Trạng thái thanh toán: <span><c:out value="${paymentStatus}"/></span></p>
<p>Ngày đặt: <span><c:out value="${placeOrderDate}"/></span></p>
<c:if test="${not empty shipDate}">
    <p>Ngày giao hàng: <span><c:out value="${shipDate}"/></span></p>
</c:if>
<c:if test="${not empty payDate}">
    <p>Ngày thanh toán: <span><c:out value="${payDate}"/></span></p>
</c:if>
<h2>Tổng giá trị: <span style="font-weight: bold; color: red"><c:out value="${totalCost}"/></span></h2>
<a href="${pageContext.request.contextPath}/orders.jsp">ĐƠN HÀNG CỦA TÔI</a>

<div id="footer">
    <p style="text-align: center">
        <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
    </p>
</div>
</div>
</body>
</html>
