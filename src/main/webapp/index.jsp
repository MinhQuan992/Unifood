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
    <title>UNIFOOD</title>
</head>
<body>
<a href="login.jsp">HELLO WORLD</a>
<a href="${pageContext.request.contextPath}/Page/ProductDetail.jsp">ProductDetail</a>
<a href="${pageContext.request.contextPath}/Product?ItemCode=MN10100005">Test</a>
<br>
<c:if test="${not empty loginFailed}">
    <c:choose>
        <c:when test="${loginFailed == true}">
            Login failed
        </c:when>
        <c:otherwise>
            <c:out value="${userID}"/>
            <br>
            <c:out value="${fullName}"/>
            <br>
            <c:out value="${gender}"/>
            <br>
            <c:out value="${birthDate}"/>
            <br>
            <c:out value="${address}"/>
            <br>
            <c:out value="${phone}"/>
            <br>
            <c:out value="${email}"/>
        </c:otherwise>
    </c:choose>
</c:if>
</body>
</html>
