<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đặt lại mật khẩu | Unifood</title>
    <meta name="description" content="Quan Com Online Unifood" />
    <meta name="author" content="NhomHQNT">

    <script src="js/jquery-1.11.1.min.js"></script>
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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/reset-password.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>

<div id="container">
    <nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
        <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
        <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav">
            <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
            <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
        </ul>
    </nav>
    <c:if test="${not empty wrongEmail}">
        <script type="text/javascript">
            alert("Email không hợp lệ!")
        </script>
    </c:if>
    <c:if test="${not empty sendEmailFailed}">
        <script type="text/javascript">
            alert("Không thể gửi mã xác minh về email của bạn, mời bạn thử lại")
        </script>
    </c:if>
    <div align="center">
        <h1>ĐẶT LẠI MẬT KHẨU</h1>
        <p id="message">Nhập vào địa chỉ email của bạn, chúng tôi sẽ gửi một mã xác minh xác nhận yêu cầu thay đổi mật khẩu vào hộp thư!</p>

        <form id="frmReset" method="post" action="${pageContext.request.contextPath}/resetPassword">
            <input id="reset-email" type="email" name="reset-email" placeholder="Email" required>
            <br>
            <br>
            <input id="btnSubmit" type="submit" value="GỬI TÔI MÃ XÁC MINH">
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