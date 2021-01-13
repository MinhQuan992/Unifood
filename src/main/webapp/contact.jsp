<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.dao.UserDao" %>
<%@ page import="com.mvc.dao.CartDao" %>
<%@ page import="com.mvc.entities.GiohangEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
    if (user==null) {
        UserDao userDao = new UserDao();
        user = userDao.getUserByID("KH0000000");
        CartDao cartDao = new CartDao();
        GiohangEntity cart = cartDao.GetNewCart(user);
        session.setAttribute("User", user);
        session.setAttribute("ShoppingCart", cart);
        pageContext.setAttribute("User", user);
    }
%>
<html>
<head>
    <meta charset="utf-8" />
    <title>UNIFOOD</title>
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
    <link type="text/css" rel="stylesheet" href="css/conpage.css" />
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

<div id="container">
    <nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
        <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
        <a class="homelogo" href="#"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav">
            <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage">PRODUCTS</a></li>
            <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li>
                <button id="close-image" onclick="${pageContext.request.contextPath}/Cart"><img src="Images/gio.png" style="width: auto; height: 50px;"></button>
                <button id="close-CSS"></button>
            </li>
            <li class="nav-item active"><a class="nav-link" href="index.jsp"> </a></li>
            <%
                if (!user.getMaNguoiDung().equals("KH0000000"))
                {
            %>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">${User.hoVaTen}</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/EditInfo">My Profile</a>
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/orders">Orders</a>
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/signout">Sign Out</a>
                    </div></li>
            <%
                }
                else
                {
            %>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardropp" data-toggle="dropdown"> Sign In - Sign Up </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item dropdown-item-custom" href="signin.jsp">Sign In</a>
                        <a class="dropdown-item dropdown-item-custom" href="signup.jsp">Sign Up</a>
                    </div></li>
            <%
                }
            %>
        </ul>
    </nav>

    <div id="contact" style="text-align: center;">
        <a style="text-align: center;"><img src="Images/LOGO.png" style="width: 400px; height: 200px; background-color: #60150c;"></a>
        <h1 style="text-align: center; font-size: 100px"><b>CONTACT US</b></h1>
        <ul>
            <a style="text-align: center;"><img src="Images/MQ.jpg" style="text-align: center; width: 250px; height: 300px;"></a><br>
            <marquee scrollamount="200" scrolldelay="2000" style="height: 100px; width: 400px;">
                <span style="color: navy; font-size: 30px; text-align: center;"><b>VÕ TRẦN MINH QUÂN</b></span>
            </marquee><br>
            <a style="text-align: center;"><img src="Images/HG.jpg" style="width: 250px; height: 300px;"></a><br>
            <marquee scrollamount="200" scrolldelay="2000" style="height: 100px; width: 400px;">
                <span style="color: navy; font-size: 30px; text-align: center;"><b>TẠ THỊ MAI HƯƠNG</b></span>
            </marquee><br>
            <a style="text-align: center;"><img src="Images/QN.jpg" style="width: 250px; height: 300px;"></a><br>
            <marquee scrollamount="200" scrolldelay="2000" style="height: 100px; width: 400px;">
                <span style="color: navy; font-size: 30px; text-align: center;"><b>NGUYỄN QUỐC NINH</b></span>
            </marquee><br>
            <a style="text-align: center";><img src="Images/DT.jpg" style="width: 250px; height: 300px"> </a><br>
            <marquee scrollamount="200" scrolldelay="2000" style="height: 100px; width: 400px;">
                <span style="color: navy; font-size: 30px; text-align: center;"><b>TRẦN ĐĂNG TÂM</b></span>
            </marquee><br>
        </ul>
    </div>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>

</body>
</html>