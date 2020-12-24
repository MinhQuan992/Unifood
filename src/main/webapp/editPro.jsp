<%--
  Created by IntelliJ IDEA.
  User: Hương Gấu
  Date: 12/24/2020
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sửa sản phẩm</title>
</head>
<body>
<table>
    <thead>
        <tr>
            <td>Mã Sản Phẩm</td>
            <td>Tên Sản Phẩm</td>
            <td>Đơn Vị Tính</td>
            <td>Đơn Giá</td>
            <td>Số Lượng</td>
            <td>Ảnh Minh Họa</td>
            <td>Mã Nhóm</td>
            <td>Mã Kho</td>
            <td>Mô tả</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td><c:out value="${product.maSanPham}"></c:out></td>
                <td><c:out value="${product.tenSanPham}"></c:out></td>
                <td><c:out value="${product.donViTinh}"></c:out></td>
                <td><c:out value="${product.donGia}"></c:out></td>
                <td><c:out value="${product.soLuong}"></c:out></td>
                <td><c:out value="${product.anhMinhHoa}"></c:out></td>
                <td><c:out value="${product.maNhom}"></c:out></td>
                <td><c:out value="${product.maKho}"></c:out></td>
                <td><c:out value="${product.moTa}"></c:out></td>

                <td>
                    <form method="post" action="${pageContext.request.contextPath}/getprodetail">
                        <input type="hidden" name="maSanPham" value="${product.maSanPham}">
                        <input type="hidden" name="tenSanPham" value="${product.tenSanPham}">
                        <input type="hidden" name="donViTinh" value="${product.donViTinh}">
                        <input type="hidden" name="donGia" value="${product.donGia}">
                        <input type="hidden" name="soLuong" value="${product.soLuong}">
                        <input type="hidden" name="anhMinhHoa" value="${product.anhMinhHoa}">
                        <input type="hidden" name="maNhom" value="${product.maNhom}">
                        <input type="hidden" name="maKho" value="${product.maKho}">
                        <input type="hidden" name="moTa" value="${product.moTa}">
                        <input type="submit" value="Chỉnh sửa">
                    </form>
                </td>
            </tr>
        </c:forEach>

    </tbody>
</table>
</body>
</html>
