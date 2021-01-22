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
    if (user.getMaNguoiDung().startsWith("QL"))
        request.getRequestDispatcher("qlhome.jsp").forward(request,response);
%>
<html>
<head>
    <title>Trang chủ | Unifood</title>
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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <script>
        function SendToCart()
        {
            document.SendToPostRequest.submit();
        }
    </script>
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
                <button id="close-image" onclick="SendToCart()"><img src="Images/gio.png" style="width: auto; height: 50px;"></button>
                <button id="close-CSS"></button>
            <li class="nav-item active"><a class="nav-link" href="index.jsp"> </a></li>
            <c:if test='${not User.maNguoiDung.equals("KH0000000")}'>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">${User.hoVaTen}</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/EditInfo">My Profile</a>
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/orders">Orders</a>
                        <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/signout">Sign Out</a>
                    </div></li>
            </c:if>
            <c:if test='${User.maNguoiDung.equals("KH0000000")}'>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardropp" data-toggle="dropdown"> Sign In - Sign Up </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item dropdown-item-custom" href="signin.jsp">Sign In</a>
                        <a class="dropdown-item dropdown-item-custom" href="signup.jsp">Sign Up</a>
                    </div></li>
            </c:if>
        </ul>
    </nav>

    <div class="slider-container">
        <div class="menu">
            <label for="slide-dot-1"></label> <label for="slide-dot-2"></label>
            <label for="slide-dot-3"></label>
        </div>

        <input id="slide-dot-1" type="radio" name="slides" checked>
        <div class="slide slide-1"></div>

        <input id="slide-dot-2" type="radio" name="slides">
        <div class="slide slide-2"></div>

        <input id="slide-dot-3" type="radio" name="slides">
        <div class="slide slide-3"></div>
    </div>

    <h1 style="text-align: center; color: #60150c;"><b>ABOUT US</b></h1>
    <div class="row" style="margin-left: 0px; margin-right: 0px">
        <div id="box1" class="col">
            <h2><b>General</b></h2>
            <img src="Images/gate.jpg" alt="CSS" />
            <p>We are pleased to serve you with the best quality. Our
                retaurant is experienced in food industry, expecially COM TAM. We
                hope you will have good experience in our retaurant.</p>
        </div>
        <div id="box2" class="col">
            <h2><b>Facilities</b></h2>
            <img src="Images/kit.jpg" alt="URL" />
            <p>With the highest quality facilities, we commit serving you
                well. All the dishes are made from the fresh ingredients, which is
                classified carefully from the famous farms. High technology will
                make you comfortable. Thank you for choosing us.</p>
        </div>
        <div id="box3" class="col">
            <h2><b>Staffs</b></h2>
            <img src="Images/staff.jpg" alt="HTML" />
            <p>The team is appreciated to be with you in all meals.
                Throughout the best training, we know that our attitude will decide
                your attitude. So we always try our best to understand what you
                need, from now on, we continue changing everything to be suitable.</p>
        </div>
    </div>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>

    <form name="SendToPostRequest" method="Post" style="visibility: hidden;" action="${pageContext.request.contextPath}/Cart">
    </form>
</div>
</body>
</html>