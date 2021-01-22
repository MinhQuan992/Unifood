<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.entities.ViewAllOrderEntity" %>
<%@ page import="com.mvc.entities.DonhangEntity" %><%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 12/23/2020
  Time: 11:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
    ViewAllOrderEntity payment =  (ViewAllOrderEntity) request.getAttribute("Payment");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.mvc.dao.CartDao" %>
<%@ page import="com.mvc.entities.GiohangEntity" %>
<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (user==null)
    {
        UserDao userDao = new UserDao();
        user = userDao.getUserByID("KH0000000");
        CartDao cartDao = new CartDao();
        GiohangEntity cart = cartDao.GetNewCart(user);
        session.setAttribute("User",user);
        session.setAttribute("ShoppingCart",cart);
        pageContext.setAttribute("User", user);
    }
    if (!user.getMaNguoiDung().startsWith("QL"))
        request.getRequestDispatcher("index.jsp").forward(request,response);
%>
<html>
<head>
    <title>Manage Order</title>
    <link rel="stylesheet" href="bootstrap.min.css">
</head>
<body>
    <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
        <div class="jumbotron">
            <div class="page-header">
                <h1>Cập nhật thông tin đơn hàng</h1>
                <h4>Chào mừng quản lí (<%=user.getMaNguoiDung()%>): <%=user.getHoVaTen()%></h4>
                <br/>
                <p>Trở về <a href="${pageContext.request.contextPath}/qlhome.jsp">trang chủ</a></p>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/OrderManage">
                <table>
                    <tr>
                        <th>
                            <input name="OrderCode" value="${requestScope.Payment.maDon}" hidden>
                            <div class="form-group">
                                <label for="OrderCode">OrderCode:</label>
                                <input type="text" disabled
                                       style="font-size: 24px"
                                       class="form-control" id="OrderCode"
                                       value="${requestScope.Payment.maDon}">
                            </div>

                            <div class="form-group">
                                <label for="UserName">UserName:</label>
                                <input type="text" disabled
                                       style="font-size: 24px"
                                       class="form-control" id="UserName"
                                       name="UserName" value="${requestScope.Payment.tenNguoiDung}" required>
                            </div>

                            <div class="form-group">
                                <label for="PhoneNum">UserName:</label>
                                <input type="text" disabled
                                       style="font-size: 24px"
                                       class="form-control" id="PhoneNum"
                                       name="UserName" value="${requestScope.Payment.phoneNumber}" required>
                            </div>

                            <div class="form-group">
                                <label for="Address">Address:</label>
                                <input type="text" disabled
                                       style="font-size: 24px"
                                       class="form-control" id="Address"
                                       name="Address" value="${requestScope.Payment.diaChi}" required>
                            </div>

                            <div class="form-group">
                                <label for="Quantity">Quantity:</label>
                                <input type="text" disabled
                                       style="font-size: 24px"
                                       class="form-control" id="Quantity"
                                       name="Quantity" value="${requestScope.Payment.quantity}">
                            </div>

                            <div class="form-group">
                                <label for="Total">Total:</label>
                                <input type="text" disabled
                                       style="font-size: 24px"
                                       class="form-control" id="Total"
                                       name="Total" value="${requestScope.Payment.tongGiaTri}">
                            </div>

                        </th>
                        <th width="100px">
                        </th>
                        <th>

                            <div class="form-group">
                                <label for="Payment">Payment:</label>
                                <select id="Payment" name="Payment" class="form-control" style="font-size: 24px">
                                    <option value="true" <c:if test="${requestScope.Payment.trangThaiThanhToan.equals('Đã thanh toán')}">selected</c:if>>Đã thanh toán</option>
                                    <option value="false" <c:if test="${requestScope.Payment.trangThaiThanhToan.equals('Chưa thanh toán')}">selected</c:if>>Chưa thanh toán</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="ShippingUnit">Shipping Unit:</label>
                                <%--<input type="text"
                                       class="form-control" id="ShippingUnit"
                                       name="ShippingUnit" value="${requestScope.Payment.tenDonViGiaoHang}">--%>
                                <select id="ShippingUnit" name="ShippingUnit" class="form-control" onselect="${requestScope.Payment.trangThaiDonHang}"
                                        style="font-size: 24px">
                                    <c:forEach items="${requestScope.ShipperList}" var="shiper">
                                        <option value="${shiper.maDonVi}" <c:if test="${requestScope.Payment.tenDonViGiaoHang.equals(shiper.tenDonVi)}">selected</c:if>>${shiper.tenDonVi}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="PlaceDate">PlaceDate:</label>
                                <input type="date" disabled
                                       style="font-size: 24px"
                                       class="form-control" id="PlaceDate"
                                       name="PlaceDate" value="${requestScope.Payment.ngayDat}">
                            </div>

                            <div class="form-group">
                                <label for="TransDate">TransDate:</label>
                                <input type="date"
                                       style="font-size: 24px"
                                       class="form-control" id="TransDate"
                                       name="TransDate" value="${requestScope.Payment.ngayGiaoHang}"
                                       onchange="checkRghtTransTime()">
                            </div>

                            <div class="form-group">
                                <label for="CompleteDate">CompleteDate:</label>
                                <input type="date"
                                       style="font-size: 24px"
                                       class="form-control" id="CompleteDate"
                                       name="CompleteDate" value="${requestScope.Payment.ngayGiaoHang}"
                                       onchange="checkRightCompleteTime()">
                            </div>

                            <div class="form-group">
                                <label for="Status">Status:</label>
                                <%--<input type="text"
                                       class="form-control" id="Status"
                                       name="Status" value="${requestScope.Payment.trangThaiDonHang}">--%>
                                <select id="Status" name="Status" class="form-control" onselect="${requestScope.Payment.trangThaiDonHang}" style="font-size: 24px">
                                    <option value="Đang xử lý" <c:if test="${requestScope.Payment.trangThaiDonHang.equals('Đang xử lý')}">selected</c:if>>Đang xử lý</option>
                                    <option value="Đang giao hàng" <c:if test="${requestScope.Payment.trangThaiDonHang.equals('Đang giao hàng')}">selected</c:if>>Đang giao hàng</option>
                                    <option value="Giao hàng thành công" <c:if test="${requestScope.Payment.trangThaiDonHang.equals('Giao hàng thành công')}">selected</c:if>>Giao hàng thành công</option>
                                    <option value="Đã bị hủy" <c:if test="${requestScope.Payment.trangThaiDonHang.equals('Đã bị hủy')}">selected</c:if>>Đã bị hủy</option>
                                </select>
                            </div>

                        </th>
                    </tr>
                </table>
                <div class="form-group">
                    <button type="submit" class="btn btn-success" name="Save" value="On">Confirm changes</button>
                    <button type="submit" class="btn btn-danger" name="Cancel" value="On">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
<script>

    function checkRightCompleteTime()
    {
        var dateA = document.getElementById('TransDate').value;
        var dateB = document.getElementById('CompleteDate').value;
        console.log(dateA);
        if (dateA == undefined || dateA == "" || dateA == NaN || dateA == null)
        {
            window.alert("You can't set the complete day before delivery day");
            document.getElementById('CompleteDate').value = NaN;
        }
        else if (dateA>dateB)
        {
            window.alert("You can't set the complete day smaller than delivery day");
            document.getElementById('CompleteDate').value = NaN;
        }
    }

    function checkRghtTransTime()
    {
        var dateA = document.getElementById('PlaceDate').value;
        var dateB = document.getElementById('TransDate').value;

        if (dateA>dateB)
        {
            window.alert("You can't set the delivery day before oder placed day");
            document.getElementById('TransDate').value = NaN;
        }
    }
</script>
<style>
    .form-group
    {
        font-size: 24px;
    }
</style>