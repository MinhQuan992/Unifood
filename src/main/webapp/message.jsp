<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đặt lại mật khẩu | Unifood</title>
</head>
<body>
<div align="center">
    <c:if test="${not empty message}">
        <h1 style="text-align: center"><c:out value="${message}"/></h1>
    </c:if>

    <a href="${pageContext.request.contextPath}/signin.jsp">ĐĂNG NHẬP</a>
</div>
</body>
</html>
