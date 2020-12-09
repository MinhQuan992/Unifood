<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 11/12/2020
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UNIFOOD</title>
</head>
<body>
<a href="login.jsp">HELLO WORLD</a>
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
    <form method="post" action="${pageContext.request.contextPath}/EditInfo">
        <input type="hidden" id="userId" name="userId" value="${userID}">
        <input type="submit" value="Edit Info">
    </form>
    <c:if test="${fn:contains('OL', userId)}">
        <form method="post" action="${pageContext.request.contextPath}/ManageWarehouse">
            <input type="hidden" id="managerId", name="userId" value="${userID}">
            <input type="submit" value="Manage Warehouse">
        </form>
    </c:if>
</c:if>
</body>
</html>
