<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 11/12/2020
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link type="text/css" rel="stylesheet" href="../../../../Unifood-master/target/UniFoods-1.0-SNAPSHOT/css/conpage.css" />
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
        <a href="${pageContext.request.contextPath}/Cart?"><img class="cart" src="Images/gio.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> Sign In - Sign Up </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item dropdown-item-custom" href="signin.jsp">Sign In</a>
                    <a class="dropdown-item dropdown-item-custom" href="signup.jsp">Sign Up</a>
                </div></li>
        </ul>
    </nav>
<c:choose>
    <c:when test="${empty signinSuccess}">
        <a href="${pageContext.request.contextPath}/signin.jsp">HELLO WORLD</a>
    </c:when>

    <c:otherwise>
        <c:out value="${userID}"/>
        <br>
        <c:out value="${fullName}"/>
        <br>
        <c:out value="${gender}"/>
        <br>
        <c:out value="${birthdate}"/>
        <br>
        <c:out value="${address}"/>
        <br>
        <c:out value="${phone}"/>
        <br>
        <c:out value="${email}"/>

        <c:choose>
            <c:when test="${userType == 'Customer'}">
                <form method="post" action="${pageContext.request.contextPath}/Orders">
                    <input type="submit" value="Đơn hàng của tôi">
                </form>
            </c:when>

            <c:otherwise>
                <form method="post" action="${pageContext.request.contextPath}/signup.jsp">
                    <input type="submit" value="Thêm quản lý">
                </form>
            </c:otherwise>
        </c:choose>

        <form method="post" action="${pageContext.request.contextPath}/signout">
            <input type="submit" value="SIGN OUT">
        </form>

        <form method="post" action="${pageContext.request.contextPath}/EditInfo">
            <input type="hidden" id="userId" name="userId" value="${userID}">
            <input type="submit" value="Edit Info">
        </form>

        <c:if test="${userType!='Customer'}">
            <form method="post" action="${pageContext.request.contextPath}/ManageWarehouse">
                <input type="submit" value="Manage Warehouse">
            </form>
        </c:if>

        <c:if test="${userType!='Customer'}">
            <form method="post" action="${pageContext.request.contextPath}/ManageOrder">
                <input type="submit" value="Manage Warehouse">
            </form>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/Payment">
            <input type="hidden" name="MaGio" value="1000000">
            <input type="hidden" name="UserId" value="${userID}">
            <input type="submit" value="Thanh toán">
        </form>

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