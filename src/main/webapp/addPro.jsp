<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.mvc.dao.CartDao" %>
<%@ page import="com.mvc.entities.GiohangEntity" %>
<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.dao.UserDao" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
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
    <title>Thêm sản phẩm</title>
    <meta name="description" content="Quan Com Online Unifood" />
    <meta name="author" content="NhomHQNT">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="css/qlpage.css" />
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <style>
        table{border: 0px solid darkred}
        td{border: 20px solid white}
    </style>
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
<div id="container">
    <nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
        <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
        <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav">
            <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/qlhome.jsp">HOME</a></li>
            <li class="nav-item"><a class="nav-link" href="staffs.jsp">STAFFS</a></li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">${User.hoVaTen}</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/ManageWarehouse">My Profile</a>
                    <a class="dropdown-item dropdown-item-custom" href="${pageContext.request.contextPath}/signout">Sign Out</a>
                </div></li>
        </ul>
    </nav>
    <div id="procontainer" align="center">
        <form id="addcontainer" method="post" action="${pageContext.request.contextPath}/addPro">
            <h1 align="center"><b>THÊM SẢN PHẨM</b></h1>
            <table border="1" cellpadding="10" style="width:500px;height:100px">
                <br><tr>
                <td><b> Mã Sản Phẩm: </b></td>
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
                    <td><b>Tên Sản Phẩm: </b></td>
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
                    <td><b> Đơn giá: </b></td>
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
                    <td><b> Đơn vị tính của Sản Phẩm: </b></td>
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
                    <td><b> Số lượng của Sản Phẩm: </b></td>
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
                    <td><b> Hình ảnh Sản Phẩm: </b></td>
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
                    <td><b> Mã nhóm của Sản Phẩm: </b></td>
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
                    <td><b> Mã kho của Sản Phẩm: </b></td>
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
                    <td><b> Mô tả Sản Phẩm: </b></td>
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

            <br><input type="submit" style="text-align: center; background-color: #9fcdff; text-decoration-color: white; width: 100px; height: 30px" value="Xác nhận">
        </form>
    </div>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>
</body>
</html>