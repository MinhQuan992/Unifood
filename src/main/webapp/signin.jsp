<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập | Unifood</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css">
    <script src="https://kit.fontawesome.com/9636dbf883.js" crossorigin="anonymous"></script>
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
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body id="login-page">
<c:if test="${not empty signupSuccess && signupSuccess == true}">
    <script type="text/javascript">
        alert("Tạo tài khoản thành công, mời bạn đăng nhập để tiếp tục!")
    </script>
</c:if>
<c:if test="${not empty signinFailed}">
    <script type="text/javascript">
        alert("Tên đăng nhập hoặc mật khẩu sai!")
    </script>
</c:if>
<c:if test="${not empty changePasswordFailed && changePasswordFailed == false}">
    <script type="text/javascript">
        alert("Đổi mật khẩu thành công, mời bạn đăng nhập để tiếp tục!")
    </script>
</c:if>
<div id="container">
    <nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
        <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
        <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav">
            <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/contact.jsp">CONTACTS</a></li>
        </ul>
    </nav>

    <form id="frmLogin" method="post" action="${pageContext.request.contextPath}/signin">
        <h1 id="form-title">ĐĂNG NHẬP TÀI KHOẢN</h1>
        <br>
        <i class="fas fa-envelope" style="font-size: 155%"></i>
        &nbsp;
        <input class="txtInfo" type="text" name="email" placeholder="Email" required>
        <br>
        <br>
        <i class="fas fa-lock" style="font-size: 170%"></i>
        &nbsp;
        <input class="txtInfo" type="password" name="password" placeholder="Mật khẩu" required>
        <br>
        <br>
        <a id="link-forgot" href="${pageContext.request.contextPath}/reset-password.jsp">Quên mật khẩu?</a>
        <br>
        <br>
        <input id="btnSignin" type="submit" value="ĐĂNG NHẬP">
    </form>
    <p id="message-signup">Bạn chưa có tài khoản? <a id="link-signup" href="${pageContext.request.contextPath}/signup.jsp">Đăng kí ngay!</a></p>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>
</body>
</html>