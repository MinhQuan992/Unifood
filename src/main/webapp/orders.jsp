<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 12/17/2020
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đơn hàng của tôi | Unifood</title>
</head>
<body>
<c:choose>
    <c:when test="${empty orders}">
        <h1>Bạn chưa có đơn hàng nào</h1>
    </c:when>

    <c:otherwise>
        <h1>Đơn hàng của tôi</h1>
        <table>
            <thead>
            <tr>
                <td>Mã đơn hàng</td>
                <td>Trạng thái đơn hàng</td>
                <td>Trạng thái thanh toán</td>
                <td>Tổng giá trị</td>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td><c:out value="${order.maDon}"/></td>
                    <td><c:out value="${order.ttDonHang}"/></td>
                    <c:choose>
                        <c:when test="${order.ttThanhToan == true}">
                            <td>Đã thanh toán</td>
                        </c:when>

                        <c:otherwise>
                            <td>Chưa thanh toán</td>
                        </c:otherwise>
                    </c:choose>
                    <td><c:out value="${order.tongGiaTri}"/></td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/orderDetail">
                            <input type="hidden" name="maDon" value="${order.maDon}">
                            <input type="hidden" name="ttDonHang" value="${order.ttDonHang}">
                            <input type="hidden" name="ttThanhToan" value="${order.ttThanhToan}">
                            <input type="hidden" name="ngayDat" value="${order.ngayDat}">
                            <input type="hidden" name="ngayGiaoHang" value="${order.ngayGiaoHang}">
                            <input type="hidden" name="ngayThanhToan" value="${order.ngayThanhToan}">
                            <input type="hidden" name="tongGiaTri" value="${order.tongGiaTri}">
                            <input type="submit" value="Chi tiết">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/index.jsp">Về trang chủ</a>
</body>
</html>
