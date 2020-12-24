<%@ page import="java.util.List" %>
<%@ page import="com.mvc.entities.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/19/2020
  Time: 10:24 PM
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
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <%
        List<DonvigiaohangEntity> listDV = (List<DonvigiaohangEntity>) request.getAttribute("listDV");
        List<SanphamEntity> listSP = (List<SanphamEntity>) request.getAttribute("listSP");
        NguoidungEntity user = (NguoidungEntity) request.getAttribute("user");
    %>
    <div class="jumbotron">
        <div class="page-header">
            <p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
            <h1>Trang thanh toán</h1>
        </div>

        <div class="container">
            <h3>Địa chỉ nhận hàng: </h3>
            <p><strong>Họ tên: </strong><%= user.getHoVaTen() %></p>
            <p><strong>Điện thoại: </strong><%= user.getDienThoai() %></p>
            <p><strong>Địa chỉ: </strong><%= user.getDiaChi() %></p>
            <h3>Sản phẩm: </h3>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th class="text-center">Hình ảnh</th>
                    <th class="text-center">Tên sản phẩm</th>
                    <th class="text-center">Số luọng</th>
                    <th class="text-center">Đơn giá</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (SanphamEntity sp: listSP) {
                %>
                <tr>
                    <td class="text-center"><img src="<%= sp.getAnhMinhHoa()%>" width="200px"
                                                 onerror="this.onerror=null; this.src='311151.jpg'"></td>
                    <td><%= sp.getTenSanPham() %>
                    </td>
                    <td><%= sp.getSoLuong() %>
                    </td>
                    <td><%= sp.getDonGia() %>
                    </td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <th colspan="3">Tổng tiền: </th>
                    <td><c:out value="${cost}"></c:out></td>
                </tr>
                <!--tr>
                    <th colspan="3">Giảm giá: <c:out value="${TenMaGiamGia}"></c:out></th>
                    <td>${discount}</td>
                </tr>
                <tr>
                    <th colspan="3">Số tiền phải thanh toán: </th>
                    <td><c:out value="${cost - discount}"></c:out></td>
                </tr-->
                </tbody>
            </table>
            <h3>Chọn đơn vị giao hàng </h3>
            <form method="get" action="${pageContext.request.contextPath}/Payment">
                <input type="hidden" name="MaGio" value="${MaGio}">
                <input type="hidden" name="TongGiaTri" value="${cost}">
                <%
                    String checked = "checked";
                    for (DonvigiaohangEntity dv: listDV) {
                %>
                <div class="form-group custom-radio">
                    <label>
                        <input type="radio" name="MaDonViGiaoHang" value="<%= dv.getMaDonVi() %>" <%=checked%> > <%= dv.getTenDonVi() %>
                    </label>
                </div>
                <%
                        checked = "";
                    }
                %>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Xác nhận đơn hàng</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
