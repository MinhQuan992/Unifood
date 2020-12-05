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
</head>
<body>
<c:if test="${not empty authorize}">
    <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
        <h1>Edit User Information</h1>
        <form method="get" action="${pageContext.request.contextPath}/EditInfo">
            <input type="hidden" id="UserId" name="UserId" value="${userID}">

            <div class="form-group">
                <label for="userName">User Name:</label>
                <input type="text"
                       class="form-control" id="userName"
                       name="userName" value="${userName}" required>
                <p style="color: red"><c:out value="${error_userName}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password"
                       class="form-control" id="newPassword"
                       name="newPassword" value="${newPassword}" required>
                <p style="color: red"><c:out value="${error_password}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="rePassword">Reenter new Password:</label>
                <input type="password"
                       class="form-control" id="rePassword"
                       name="rePassword" value="${rePassword}" required>
                <p style="color: red"><c:out value="${error_rePassword}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text"
                       class="form-control" id="fullName"
                       name="fullName" value="${fullName}">
                <p style="color: red"><c:out value="${error_fullName}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="gender">Gender:</label>
                <input type="text"
                       class="form-control" id="gender"
                       name="gender" value="${gender}">
                <p style="color: red"><c:out value="${error_gender}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="birthDate">Birth Date:</label>
                <input type="date"
                       class="form-control" id="birthDate"
                       name="birthDate" value="${birthDate}">
                <p style="color: red"><c:out value="${error_birthDate}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text"
                       class="form-control" id="address"
                       name="address" value="${address}">
                <p style="color: red"><c:out value="${error_address}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text"
                       class="form-control" id="phone"
                       name="phone" value="${phone}">
                <p style="color: red"><c:out value="${error_phone}"></c:out></p>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text"
                       class="form-control" id="email"
                       name="email" value="${email}">
                <p style="color: red"><c:out value="${error_email}"></c:out></p>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Confirm changes</button>
                <p><c:out value="${status}"></c:out></p>
            </div>

        </form>
    </div>
</c:if>
<c:if test="${empty authorize}">
    <p>Not Authorize</p>
</c:if>
</body>
</html>
