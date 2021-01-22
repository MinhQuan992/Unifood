<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/16/2020
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.mvc.dao.CartDao" %>
<%@ page import="com.mvc.entities.GiohangEntity" %>
<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
    if (user==null)
    {
        UserDao userDao = new UserDao();
        user = userDao.getUserByID("KH0000000");
        CartDao cartDao = new CartDao();
        GiohangEntity cart = cartDao.GetNewCart(user);
        session.setAttribute("User",user);
        session.setAttribute("ShoppingCart",cart);
        pageContext.setAttribute("User", user);
    }
    if (!user.getMaNguoiDung().startsWith("QL"))
        request.getRequestDispatcher("index.jsp").forward(request,response);
%>
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
    </style><meta name="description" content="Quan Com Online Unifood" />
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

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>
</body>
</html>