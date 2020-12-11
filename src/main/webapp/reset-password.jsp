<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 11/24/2020
  Time: 6:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đặt lại mật khẩu | Unifood</title>
</head>
<body>
<c:if test="${not empty wrongEmail}">
    <c:if test="${wrongEmail == true}">
        <script type="text/javascript">
            alert("Email không hợp lệ!")
        </script>
    </c:if>
</c:if>
<div align="center">
    <h1>ĐẶT LẠI MẬT KHẨU</h1>
    <p>Nhập vào địa chỉ email của bạn, chúng tôi sẽ đặt một mật khẩu mới ngẫu nhiên cho tài khoản của bạn và gửi vào hộp thư!</p>

    <form id="frmReset" method="post" action="${pageContext.request.contextPath}/resetPassword">
        <label for="reset-email">Email:</label>&nbsp;
        <input id="reset-email" type="email" name="reset-email" required>
        <br>
        <br>
        <input id="btnSubmit" type="submit" value="Gửi tôi mật khẩu mới">
    </form>
</div>
</body>
</html>
