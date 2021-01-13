<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/29/2020
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Info</title>
    <link rel="stylesheet" href="bootstrap.min.css">
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
    <link type="text/css" rel="stylesheet" href="css/home.css" />
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
            <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
            <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
        </ul>
    </nav>
<c:if test="${not empty authorize}">
    <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
        <div class="jumbotron">
            <div class="page-header">
                <p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
                <h1>Thay đổi thông tin người dùng</h1>
                <p class="text-info"><c:out value="${status}"></c:out></p>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/EditInfo">
                <input type="hidden" id="UserId" name="UserId" value="${userID}">
                <div class="form-group">
                    <label for="fullName">Họ và tên:</label>
                    <input type="text"
                           class="form-control" id="fullName"
                           name="fullName" value="${fullName}">
                    <p style="color: red"><c:out value="${error.fullName}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="newPassword">Mật khẩu mới:</label>
                    <input type="password"
                           class="form-control" id="newPassword"
                           name="newPassword" value="${password}" required>
                    <p style="color: red"><c:out value="${error.password}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="rePassword">Nhập lại mật khẩu mới:</label>
                    <input type="password"
                           class="form-control" id="rePassword"
                           name="rePassword" value="${rePassword}">
                    <p style="color: red"><c:out value="${error.rePassword}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="gender">Giới tính:</label>
                    <input type="text"
                           class="form-control" id="gender"
                           name="gender" value="${gender}">
                    <p style="color: red"><c:out value="${error.gender}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="birthDate">Ngày sinh:</label>
                    <input type="date"
                           class="form-control" id="birthDate"
                           name="birthDate" value="${birthDate}">
                    <p style="color: red"><c:out value="${error.birthDate}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="address">Địa chỉ:</label>
                    <input type="text"
                           class="form-control" id="address"
                           name="address" value="${address}">
                    <p style="color: red"><c:out value="${error.address}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="phone">Số điện thoại:</label>
                    <input type="text"
                           class="form-control" id="phone"
                           name="phone" value="${phone}">
                    <p style="color: red"><c:out value="${error.phone}"></c:out></p>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text"
                           class="form-control" id="email"
                           name="email" value="${email}">
                    <p style="color: red"><c:out value="${error.email}"></c:out></p>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Xác nhận thay đổi</button>
                </div>

            </form>
        </div>
    </div>
</c:if>
<c:if test="${empty authorize}">
    <p>Not Authorize</p>
</c:if>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>
</body>
</html>
