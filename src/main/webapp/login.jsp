<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 10/24/2020
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UNIFOOD</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/9636dbf883.js" crossorigin="anonymous"></script>
</head>
<body id="login-page">
<header>

</header>

<article>
    <img id="logo" src="Images/LOGO.png">
    <form id="frmLogin" method="get" action="">
        <h1 id="form-title">ĐĂNG NHẬP TÀI KHOẢN</h1>
        <i class="fas fa-user"></i>
        <input class="txtInfo" type="text" placeholder="Tên đăng nhập">
        <br>
        <br>
        <i class="fas fa-lock"></i>
        <input class="txtInfo" type="password" placeholder="Mật khẩu">
        <br>
        <br>
        <a id="link-forgot" href="">Quên mật khẩu?</a>
        <br>
        <br>
        <input id="btnLogin" type="submit" value="ĐĂNG NHẬP">
    </form>
    <p id="message-signup">Bạn chưa có tài khoản? <a id="link-signup" href="">Đăng kí ngay!</a></p>
</article>
</body>
</html>