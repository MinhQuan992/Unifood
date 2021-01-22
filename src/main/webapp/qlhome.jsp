<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.mvc.dao.CartDao" %>
<%@ page import="com.mvc.entities.GiohangEntity" %>
<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.dao.UserDao" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<html>
<head>
    <meta charset="utf-8" />
    <title>MANAGER</title>
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
                    <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/EditInfo">My Profile</a>
                    <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/signout">Sign Out</a>
                </div></li>
        </ul>
    </nav>
</div>

<div id="btncontain" align="center">
    <br><br>
    <form id="editPro" method="post" action="${pageContext.request.contextPath}/getPro">
        <input type="submit" style="color: #60150c; height: 40px; width: 200px" value="Chỉnh sửa sản phẩm">
    </form><br>

    <form id="addPro" method="post" action="${pageContext.request.contextPath}/addPro.jsp">
        <input type="submit" style="color: #60150c; height: 40px; width: 200px" value="Thêm sản phẩm">
    </form><br>

    <form id="ManageOder" method="post" action="${pageContext.request.contextPath}/OrderManage">
        <input type="submit" style="color: #60150c; height: 40px; width: 200px" value="Quản lí đơn hàng">
    </form><br>

    <form id="addMan" method="post" action="${pageContext.request.contextPath}/signup.jsp">
        <input type="submit" style="color: #60150c; height: 40px; width: 200px" value="Thêm Quản Lý">
    </form><br>

    <form id="ManageWareHouse" method="post" action="${pageContext.request.contextPath}/ManageWarehouse">
        <input type="submit" style="color: #60150c; height: 40px; width: 200px" value="Quản lí kho hàng">
    </form><br>

</div>

<div id="footer">
    <p style="text-align: center">
        <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
    </p>
</div>
</body>
</html>
