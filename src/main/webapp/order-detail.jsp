<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 12/19/2020
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết đơn hàng | Unifood</title>
</head>
<body>
<h1><c:out value="${orderID}"></c:out></h1>
<table>
    <thead>
    <tr>
        <td>Tên sản phẩm</td>
        <td>Số lượng</td>
        <td>Đơn giá</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${orderDetail}" var="detail">
        <tr>
            <td><c:out value="${detail.tenSanPham}"/></td>
            <td><c:out value="${detail.soLuong}"/></td>
            <td><c:out value="${detail.donGia}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:out value="${oderStatus}"/>
<br>
<c:out value="${paymentStatus}"/>
<br>
<c:out value="${placeOrderDate}"/>
<br>
<c:out value="${shipDate}"/>
<br>
<c:out value="${payDate}"/>
<br>
<c:out value="${totalCost}"/>
</body>
</html>
