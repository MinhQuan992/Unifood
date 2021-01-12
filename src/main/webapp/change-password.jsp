<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đặt lại mật khẩu | UNIFOOD</title>
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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/change-password.css"/>
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
<c:if test="${not empty changePasswordFailed && changePasswordFailed == true}">
    <script type="text/javascript">
        alert("Có lỗi xảy ra, mời bạn thử lại!")
    </script>
</c:if>
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
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardropp" data-toggle="dropdown"> Sign In - Sign Up </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item dropdown-item-custom" href="signin.jsp">Sign In</a>
                    <a class="dropdown-item dropdown-item-custom" href="signup.jsp">Sign Up</a>
                </div>
            </li>
        </ul>
    </nav>

    <div align="center">
        <h1 id="title">ĐẶT LẠI MẬT KHẨU</h1>
        <form method="post" action="${pageContext.request.contextPath}/changePassword">
            <input class="textbox" type="password" name="newPassword" placeholder="Nhập mật khẩu mới"
            <c:if test="${not empty newPasswordError}">
                   style="border-color: red"
            </c:if>
            required>
            <br>
            <p class="message <c:if test="${not empty newPasswordError}">error</c:if>">Mật khẩu phải có độ dài từ 10 đến 50 kí tự, bao gồm chữ hoa, chữ thường và chữ số</p>
            <input class="textbox" type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu"
            <c:if test="${not empty confirmPasswordError}">
                   style="border-color: red"
            </c:if>
            required>
            <p class="message error"><c:out value="${confirmPasswordError}"/></p>
            <input id="btnSubmit" type="submit" value="THAY ĐỔI">
        </form>
    </div>
    <br>
    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>
</body>
</html>
