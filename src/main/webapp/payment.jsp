<%@ page import="java.util.List" %>
<%@ page import="com.mvc.entities.*" %>
<%@ page import="com.mvc.dao.UserDao" %>
<%@ page import="com.mvc.dao.PaymentDao" %>
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

        /* The container */
        .cont {
            display: block;
            position: relative;
            padding-left: 35px;
            margin-bottom: 12px;
            cursor: pointer;
            font-size: inherit;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        /* Hide the browser's default radio button */
        .cont input {
            position: absolute;
            opacity: 0;
            cursor: pointer;
        }

        /* Create a custom radio button */
        .checkmark {
            position: absolute;
            top: 0;
            left: 0;
            height: 25px;
            width: 25px;
            background-color: #eee;
            border-radius: 50%;
        }

        /* On mouse-over, add a grey background color */
        .cont:hover input ~ .checkmark {
            background-color: #ccc;
        }

        /* When the radio button is checked, add a blue background */
        .cont input:checked ~ .checkmark {
            background-color: #2196F3;
        }

        /* Create the indicator (the dot/circle - hidden when not checked) */
        .checkmark:after {
            content: "";
            position: absolute;
            display: none;
        }

        /* Show the indicator (dot/circle) when checked */
        .cont input:checked ~ .checkmark:after {
            display: block;
        }

        /* Style the indicator (dot/circle) */
        .cont .checkmark:after {
            top: 9px;
            left: 9px;
            width: 8px;
            height: 8px;
            border-radius: 50%;
            background: white;
        }
    </style>
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <%
        List<DonvigiaohangEntity> listDV = (List<DonvigiaohangEntity>) request.getAttribute("listDV");
        List<SanphamEntity> listSP = (List<SanphamEntity>) request.getAttribute("listSP");
        // NguoidungEntity user = (NguoidungEntity) request.getSession().getAttribute("User");
        int Madon = (int) request.getAttribute("MaDon");
        DonhangEntity don = PaymentDao.GetDonHang(Madon);
    %>
    <div class="jumbotron">
        <div class="page-header">
            <p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
            <h1>Trang thanh toán</h1>
            <p><c:out value="${status}"></c:out></p>
        </div>

        <div class="container">
            <form method="get" action="${pageContext.request.contextPath}/Payment">
                <h3>Thông tin người nhận hàng: </h3>

                <p><strong>Họ tên: </strong><%= don.getHoVaTen() %>
                </p>

                <p><strong>Điện thoại: </strong><%= don.getDienThoai() %>
                </p>

                <p><strong>Địa chỉ nhận hàng: </strong></p>
                <label class="cont"><%= don.getDiaChi() %>
                    <input type="radio" id="defaultAddress" name="DiaChi" value="default" checked
                           onclick="EnableDisableTB()">
                    <span class="checkmark"></span>
                </label>
                <label class="cont">Địa chỉ khác
                    <input type="radio" id="otherAddress" name="DiaChi" value="other" onclick="EnableDisableTB()">
                    <span class="checkmark"></span>
                </label>
                <div class="form-group">
                    <label for="otherAddr">Nhập địa chỉ:</label>
                    <input class="form-control" type="text" id="otherAddr" name="DiaChiKhac" disabled="disabled"
                           placeholder="Địa chỉ khác">
                </div>

                <h3>Chọn đơn vị giao hàng </h3>

                <input type="hidden" name="MaDon" value="${MaDon}">
                <%
                    String checked = "checked";
                    for (DonvigiaohangEntity dv : listDV) {
                %>
                <div class="form-group custom-radio">
                    <label class="cont"><%= dv.getTenDonVi() %>
                        <input type="radio" name="MaDonViGiaoHang" value="<%= dv.getMaDonVi() %>" <%=checked%> >
                        <span class="checkmark"></span>
                    </label>
                </div>
                <%
                        checked = "";
                    }
                %>

                <h3>Sản phẩm: </h3>

                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th class="text-center">Hình ảnh</th>
                        <th class="text-center">Tên sản phẩm</th>
                        <th class="text-center">Số lượng</th>
                        <th class="text-center">Đơn giá</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (SanphamEntity sp : listSP) {
                    %>
                    <tr>
                        <td class="text-center"><img src="${pageContext.request.contextPath}<%= sp.getAnhMinhHoa()%>"
                                                     width="200px"
                                                     onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/Images/311151.jpg'">
                        </td>
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
                        <th colspan="3">Tổng tiền:</th>
                        <th><c:out value="${cost}"></c:out></th>
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
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Xác nhận đơn hàng</button>
                </div>
            </form>


        </div>
    </div>
</div>
<script type="text/javascript">
    function EnableDisableTB() {
        var others = document.getElementById("otherAddress");
        var otherlan = document.getElementById("otherAddr");
        otherlan.disabled = others.checked ? false : true;
        otherlan.value = "";
        if (!otherlan.disabled) {
            otherlan.focus();
        }
    }
</script>
</body>
</html>