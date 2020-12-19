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
</head>
<body>
<c:choose>
    <c:when test="${empty signinSuccess}">
        <a href="${pageContext.request.contextPath}/signin.jsp">HELLO WORLD</a>
    </c:when>

    <c:otherwise>
        <form method="post" action="${pageContext.request.contextPath}/signout">
            <input type="submit" value="SIGN OUT">
        </form>
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
                <form method="post" action="${pageContext.request.contextPath}/order">
                    <input type="submit" value="Đơn hàng của tôi">
                </form>
            </c:when>

            <c:otherwise>
                <form method="post" action="${pageContext.request.contextPath}/signup.jsp">
                    <input type="submit" value="Thêm quản lý">
                </form>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>
