<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 12/17/2020
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đơn hàng của tôi | Unifood</title><meta name="description" content="Quan Com Online Unifood" />
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
<c:choose>
    <c:when test="${empty orders}">
        <h1>Bạn chưa có đơn hàng nào!</h1>
    </c:when>

    <c:otherwise>
        <h1>Đơn hàng của tôi</h1>
        <table id="procontainer">
            <thead>
            <tr>
                <td>Mã đơn hàng</td>
                <td>Trạng thái đơn hàng</td>
                <td>Trạng thái thanh toán</td>
                <td>Tổng giá trị</td>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td><c:out value="${order.maDon}"/></td>
                    <td><c:out value="${order.ttDonHang}"/></td>
                    <c:choose>
                        <c:when test="${order.ttThanhToan == true}">
                            <td>Đã thanh toán</td>
                        </c:when>

                        <c:otherwise>
                            <td>Chưa thanh toán</td>
                        </c:otherwise>
                    </c:choose>
                    <td><p><c:out value="${order.tongGiaTri}"/><span> VND</span></p></td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/OrderDetail">
                            <input type="hidden" name="maDon" value="${order.maDon}">
                            <input type="hidden" name="ttDonHang" value="${order.ttDonHang}">
                            <input type="hidden" name="ttThanhToan" value="${order.ttThanhToan}">
                            <input type="hidden" name="ngayDat" value="${order.ngayDat}">
                            <input type="hidden" name="ngayGiaoHang" value="${order.ngayGiaoHang}">
                            <input type="hidden" name="ngayThanhToan" value="${order.ngayThanhToan}">
                            <input type="hidden" name="tongGiaTri" value="${order.tongGiaTri}">
                            <input type="submit" style="text-align: center; background-color: #60150c; text-decoration-color: white" value="Chi tiết">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>
</body>
</html>
