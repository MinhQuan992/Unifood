<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hương Gấu
  Date: 12/24/2020
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
</head>
<body>
<c:if test="${not empty Trangthaithem}">
    <c:choose>
        <c:when test="${Trangthaithem==true}">
            <script type="text/javascript">
                alert("Thêm thành công!");
            </script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript">
                alert("Thêm không thành công!");
            </script>
        </c:otherwise>
    </c:choose>
</c:if>
<div align="center">
    <form id="addcontainer" method="post" action="${pageContext.request.contextPath}/addPro">
        <table>
            <tr>
                <td> Mã Sản Phẩm: </td>
                <td>
                <input type="text" name="maSanPham"
                <c:choose>
                    <c:when test="${empty maSanPham}">
                        placeholder="Nhập mã Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                        value="<c:out value="${maSanPham}"/> "
                    </c:otherwise>
                </c:choose>
                    <c:if test="${not empty maSanPhamError}">
                        style="border-color: #60150c"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${maSanPhamError}"/></td>
            </tr>

            <tr>
                <td> Tên Sản Phẩm: </td>
                <td>
                    <input type="text" name="tenSanPham"
                    <c:choose>
                        <c:when test="${empty tenSanPham}">
                            placeholder="Nhập tên Sản Phẩm"
                        </c:when>
                        <c:otherwise>
                            value="<c:out value="${tenSanPham}"/> "
                        </c:otherwise>
                    </c:choose>
                        <c:if test="${not empty tenSanPhamError}">
                            style="border-color: #60150c"
                        </c:if>
                required>
                </td>
                <td><c:out value="${tenSanPhamError}"/></td>
            </tr>

            <tr>
                <td> Đơn giá: </td>
                <td>
                <input type="text" name="donGia"
                <c:choose>
                    <c:when test="${empty donGia}">
                        placeholder="Nhập giá Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                        value="<c:out value="${donGia}"/> "
                    </c:otherwise>
                </c:choose>
                    <c:if test="${not empty donGiaError}">
                        style="border-color: #60150c"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${donGiaError}"/></td>
            </tr>

            <tr>
                <td> Đơn vị tính của Sản Phẩm: </td>
                <td>
                <input type="text" name="donViTinh"
                <c:choose>
                    <c:when test="${empty donViTinh}">
                        placeholder="Nhập đơn vị tính của Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                        value="<c:out value="${donViTinh}"/> "
                    </c:otherwise>
                </c:choose>
                    <c:if test="${not empty donViTinhError}">
                        style="border-color: #60150c"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${donViTinhError}"/></td>
            </tr>

            <tr>
                <td> Số lượng của Sản Phẩm: </td>
                <td>
                    <input type="text" name="soLuong"
                    <c:choose>
                    <c:when test="${empty soLuong}">
                           placeholder="Nhập số lượng của Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                           value="<c:out value="${soLuong}"/> "
                    </c:otherwise>
                    </c:choose>
                    <c:if test="${not empty soLuongError}">
                           style="border-color: #60150c"
                    </c:if>
                           required>
                </td>
                <td><c:out value="${soLuongError}"/></td>
            </tr>


            <tr>
                <td> Hình ảnh Sản Phẩm: </td>
                <td>
                <input type="text" name="anhMinhHoa"
                <c:choose>
                    <c:when test="${empty anhMinhHoa}">
                        placeholder="Nhập url hình ảnh Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                        value="<c:out value="${anhMinhHoa}"/> "
                    </c:otherwise>
                </c:choose>
                    <c:if test="${not empty anhMinhHoaError}">
                        style="border-color: #60150c"
                    </c:if>
                    required>
                    </td>
                    <td><c:out value="${anhMinhHoaError}"/></td>
            </tr>

            <tr>
                <td> Mã nhóm của Sản Phẩm: </td>
                <td>
                <input type="text" name="maNhom"
                <c:choose>
                    <c:when test="${empty maNhom}">
                        placeholder="Nhập mã nhóm của Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                        value="<c:out value="${maNhom}"/> "
                    </c:otherwise>
                </c:choose>
                    <c:if test="${not empty maNhomError}">
                        style="border-color: #60150c"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${maNhomError}"/></td>
            </tr>

            <tr>
                <td> Mã kho của Sản Phẩm: </td>
                <td>
                <input type="text" name="maKho"
                <c:choose>
                    <c:when test="${empty maKho}">
                        placeholder="Nhập mã kho của Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                        value="<c:out value="${maKho}"/> "
                    </c:otherwise>
                </c:choose>
                    <c:if test="${not empty maKhoError}">
                        style="border-color: #60150c"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${maKhoError}"/></td>
            </tr>

            <tr>
                <td> Mô tả Sản Phẩm: </td>
                <td>
                <input type="text" name="moTa"
                <c:choose>
                    <c:when test="${empty moTa}">
                        placeholder="Nhập mô tả Sản Phẩm"
                    </c:when>
                    <c:otherwise>
                        value="<c:out value="${moTa}"/> "
                    </c:otherwise>
                </c:choose>
                    <c:if test="${not empty moTaError}">
                        style="border-color: #60150c"
                    </c:if>
                    required>
                    </td>
                    <td><c:out value="${moTaError}"/></td>
                </tr>
        </table>

        <input type="submit" value="Xác nhận">
    </form>
</div>
</body>
</html>