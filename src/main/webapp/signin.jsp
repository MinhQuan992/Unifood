<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 10/24/2020
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập | Unifood</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/9636dbf883.js" crossorigin="anonymous"></script>
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
<header>
<a href="${pageContext.request.contextPath}/index.jsp">TRANG CHỦ</a>
</header>

<article>
    <img id="logo" src="Images/LOGO.png">
    <form id="frmLogin" method="post" action="${pageContext.request.contextPath}/signin">
        <h1 id="form-title">ĐĂNG NHẬP TÀI KHOẢN</h1>
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
        <input id="btnLogin" type="submit" value="ĐĂNG NHẬP">
    </form>
    <p id="message-signup">Bạn chưa có tài khoản? <a id="link-signup" href="${pageContext.request.contextPath}/signup.jsp">Đăng kí ngay!</a></p>
</article>
</body>
</html>