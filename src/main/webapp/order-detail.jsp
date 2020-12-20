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
<h1>Đơn hàng <span><c:out value="${orderID}"></c:out></span></h1>
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
            <td><p><c:out value="${detail.donGia}"/><span> VND</span></p></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>Trạng thái đơn hàng: <span><c:out value="${orderStatus}"/></span></p>
<p>Trạng thái thanh toán: <span><c:out value="${paymentStatus}"/></span></p>
<p>Ngày đặt: <span><c:out value="${placeOrderDate}"/></span></p>
<c:if test="${not empty shipDate}">
    <p>Ngày giao hàng: <span><c:out value="${shipDate}"/></span></p>
</c:if>
<c:if test="${not empty payDate}">
    <p>Ngày thanh toán: <span><c:out value="${payDate}"/></span></p>
</c:if>
<h2>Tổng giá trị: <span style="font-weight: bold; color: red"><c:out value="${totalCost}"/></span></h2>
<a href="${pageContext.request.contextPath}/orders.jsp">ĐƠN HÀNG CỦA TÔI</a>
</body>
</html>
