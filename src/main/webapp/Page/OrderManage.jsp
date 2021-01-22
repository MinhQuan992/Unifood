<%@ page import="java.util.List" %>
<%@ page import="com.mvc.entities.DonhangEntity" %>
<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.entities.ViewAllOrderEntity" %><%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 12/23/2020
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ViewAllOrderEntity> listPayments = (List<ViewAllOrderEntity>) request.getAttribute("ListPayments");
    NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
    if (!user.getMaNguoiDung().startsWith("QL"))
    {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
%>
<html>
<head>
    <title>Order Manage</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Oswald:wght@500&display=swap" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/JS/CartScript.js"></script>
</head>
<body>
    <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <div class="Main-Form">
    <h1>Trang quản lí đơn hàng</h1>
    <h4>Chào mừng quản lí (<%=user.getMaNguoiDung()%>): <%=user.getHoVaTen()%></h4>
        <br/>
        <p>Trở về <a href="${pageContext.request.contextPath}/qlhome.jsp">trang chủ</a></p>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">OrderCode</th>
            <th scope="col">UserName</th>
            <th scope="col">User Phone</th>
            <th scope="col">Address</th>
            <th scope="col">Quantity</th>
            <th scope="col">Total</th>
            <th scope="col">Payment</th>
            <th scope="col">Shipping Unit</th>
            <th scope="col">PlaceDate</th>
            <th scope="col">TransDate</th>
            <th scope="col">CompleteDate</th>
            <th scope="col">Status</th>
            <th scope="col">Manage</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.ListPayments}" var="payment">
            <tr>
                <th scope="row">${payment.maDon}</th>
                <td>${payment.tenNguoiDung}</td>
                <td>${payment.phoneNumber}</td>
                <td>${payment.diaChi}</td>
                <td>${payment.quantity}</td>
                <td>${payment.tongGiaTri}</td>
                <td>${payment.trangThaiThanhToan}</td>
                <td>${payment.tenDonViGiaoHang}</td>
                <td>${payment.ngayDat}</td>
                <td>${payment.ngayThanhToan}</td>
                <td>${payment.ngayGiaoHang}</td>
                <td>${payment.trangThaiDonHang}</td>
                <td><button type="submit" onclick="ButtonClick('EditPayment','${payment.maDon}')">Edit</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="post" name="SendToPostRequest" action="${pageContext.request.contextPath}/OrderManage">
        <input type="hidden" name="ParaName">
        <input type="hidden" name="KeyValue">
    </form>
    </div>
    </div>
</body>
</html>
