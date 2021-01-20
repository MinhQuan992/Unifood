<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.entities.GiohangEntity" %><%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 1/20/2021
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
    if (user.getMaNguoiDung().equals("KH0000000"))
    {
        String user_name = (String) session.getAttribute("User_Name");
        String user_phone = (String) session.getAttribute("User_Name");
        String user_address = (String) session.getAttribute("User_Name");

        if (user_name != null && user_phone != null && user_address != null)
        {
            response.sendRedirect("index.jsp");
        }
    }
    else
    {
        response.sendRedirect("index.jsp");
    }
    GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");
    int MaGio = 0;
    if (session.getAttribute("MaGio")!=null) {
        MaGio = (int) session.getAttribute("MaGio");
        session.setAttribute("MaGio",null);
    }
    else
        MaGio = cart.getMaGio();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nhập thông tin</title>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="css/home.css" />
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <div class="jumbotron">
        <div class="page-header">
            <p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
            <h1>Nhập thông tin giao hàng</h1>
            <p class="text-info"><c:out value="${status}"></c:out></p>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/InputInfo">
            <input type="hidden" id="UserId" name="UserId" value="${userID}">
            <div class="form-group">
                <label for="fullName">Họ và tên:</label>
                <input type="text"
                       class="form-control" id="fullName"
                       name="User_Name" value="${fullName}"
                       required>
            </div>

            <div class="form-group">
                <label for="address">Địa chỉ:</label>
                <input type="text"
                       class="form-control" id="address"
                       name="User_Address" value="${address}"
                       required>
            </div>

            <div class="form-group">
                <label for="phone">Số điện thoại:</label>
                <input type="text"
                       class="form-control" id="phone"
                       name="User_Phone" value="${phone}"
                       required>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Xác nhận và tiếp tục đặt hàng</button>
            </div>

            <input name="MaGio" value="<%=MaGio%>" hidden>

        </form>
    </div>
</div>
</body>
</html>
