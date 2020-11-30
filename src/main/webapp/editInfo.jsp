<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/29/2020
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Info</title>
    <link rel="stylesheet" href="temp_styles.css">
</head>
<body>
    <c:if test="${not empty authorize}">
        <form method="get" action="/EditInfo">
            <h2>Edit User Infomation</h2>

            <input type="hidden" id="UserId" name="UserId" value="${userID}">

            <table class="center">
                <tr><th>
                    User Name:
                </th></tr>
                <tr><td>
                    <input type="text" id="userName" name="userName" value="${userName}"><br>
                    <p><c:out value="${error_userName}"></c:out></p>
                </td></tr>

                <tr><th>
                    New Password:
                </th></tr>
                <tr><td>
                    <input type="password" id="password" name="password"><br>
                    <p><c:out value="${error_password}"></c:out></p>
                </td></tr>

                <tr><th>
                    Reenter new Password:
                </th></tr>
                <tr><td>
                    <input type="password" id="rePassword" name="rePassword"><br>
                    <p><c:out value="${error_rePassword}"></c:out></p>
                </td></tr>

                <tr><th>
                    Full name:
                </th></tr>
                <tr><td>
                    <input type="text" id="fullName" name="fullName" value="${fullName}"><br>
                    <p><c:out value="${error_fullName}"></c:out></p>
                </td></tr>

                <tr><th>
                    Gender:
                </th></tr>
                <tr><td>
                    <input type="text" id="gender" name="gender" value="${gender}"><br>
                    <p><c:out value="${error_gender}"></c:out></p>
                </td></tr>

                <tr><th>
                    Birth Date:
                </th></tr>
                <tr><td>
                    <input type="date" id="birthDate" name="birthDate" value="${birthDate}"><br>
                    <p><c:out value="${error_birthDate}"></c:out></p>
                </td></tr>

                <tr><th>
                    Address:
                </th></tr>
                <tr><td>
                    <input type="text" id="address" name="address" value="${address}"><br>
                    <p><c:out value="${error_address}"></c:out></p>
                </td></tr>

                <tr><th>
                    Phone Number:
                </th></tr>
                <tr><td>
                    <input type="text" id="phone" name="phone" value="${phone}"><br>
                    <p><c:out value="${error_phone}"></c:out></p>
                </td></tr>

                <tr><th>
                    Email:
                </th></tr>
                <tr><td>
                    <input type="text" id="email" name="email" value="${email}"><br>
                    <p><c:out value="${error_email}"></c:out></p>
                </td></tr>

                <tr><th>
                    <input type="submit" value="Confirm changes">
                </th></tr>
                <tr><td>
                    <p><c:out value="${status}"></c:out></p>
                </td></tr>
            </table>
        </form>
    </c:if>
    <c:if test="${empty authorize}">
        <p>Not Authorize</p>
    </c:if>
</body>
</html>
