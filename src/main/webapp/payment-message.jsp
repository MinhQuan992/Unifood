<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/23/2020
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanh toán</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='bootstrap.min.css'>
    <style>
        .jumbotron {
            background-color: #fafafa;
        }
    </style>
</head>
<body>
<div class="jumbotron">
    <c:if test="${not empty authorize}">
        <c:if test="${empty status}">
            <h2>Đơn hàng đã được ghi nhận</h2>
            <p class="text-info">Cập nhật vào: <%= Calendar.getInstance().getTime().toString() %></p>
        </c:if>
        <c:if test="${not empty status}">
            <h2>Có lỗi khi xác nhận đơn hàng</h2>
            <p class="text-danger"><c:out value="${status}"></c:out></p>
        </c:if>
    </c:if>
    <p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
</div>
</body>
</html>
