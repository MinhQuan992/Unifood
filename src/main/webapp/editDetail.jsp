<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hương Gấu
  Date: 12/24/2020
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa chi tiết sản phẩm</title>
</head>
<body>
<%
    String maSanPham = request.getParameter("maSanPham");
%>
<c:if test="${not empty Trangthaicapnhat}">
    <c:choose>
        <c:when test="${Trangthaicapnhat==true}">
            <script type="text/javascript">
                alert("Cập nhật thành công!");
            </script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript">
                alert("Cập nhật không thành công!");
            </script>
        </c:otherwise>
    </c:choose>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/editdetail">
    <div class="form-group">
        <label for="maSanPham">Mã Sản Phẩm: </label>
        <input type="text"
               class="form-control" id="maSanPham"
               name="maSanPham" value="${maSanPham}">
        <p style="color: darkred"><c:out value="${maSanPhamError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="tenSanPham">Tên Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="tenSanPham"
               name="tenSanPham" value="${tenSanPham}">
        <p style="color: darkred"><c:out value="${tenSanPhamError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="donGia">Giá Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="donGia"
               name="donGia" value="${donGia}">
        <p style="color: darkred"><c:out value="${donGiaError}"></c:out></p>
    </div>

    <div class="form-group">
    <label for="donViTinh">Đơn Vị Tính của Sản Phẩm:</label>
    <input type="text"
           class="form-control" id="donViTinh"
           name="donViTinh" value="${donViTinh}">
    <p style="color: darkred"><c:out value="${donViTinhError}"></c:out></p>
</div>

    <div class="form-group">
        <label for="soLuong">Số Lượng Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="soLuong"
               name="soLuong" value="${soLuong}">
        <p style="color: darkred"><c:out value="${soLuongError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="anhMinhHoa">Hình Ảnh Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="anhMinhHoa"
               name="anhMinhHoa" value="${anhMinhHoa}">
        <p style="color: darkred"><c:out value="${anhMinhHoaError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="maNhom">Mã Nhóm của Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="maNhom"
               name="maNhom" value="${maNhom}">
        <p style="color: darkred"><c:out value="${maNhomError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="maKho">Mã Kho của Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="maKho"
               name="maKho" value="${maKho}">
        <p style="color: darkred"><c:out value="${maKhoError}"></c:out></p>
    </div>

    <div class="form-group">
        <label for="moTa">Mô Tả Sản Phẩm:</label>
        <input type="text"
               class="form-control" id="moTa"
               name="moTa" value="${moTa}">
        <p style="color: darkred"><c:out value="${moTaError}"></c:out></p>
    </div>

    <input type="submit" value="Xác Nhận">

</form>
<form method="post" action="${pageContext.request.contextPath}/getPro">
    <input type="submit" value="Quay lại">
</form>
</body>
</html>
