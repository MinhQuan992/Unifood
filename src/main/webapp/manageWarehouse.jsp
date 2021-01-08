<%@ page import="com.mvc.entities.KhohangEntity" %>
<%@ page import="com.mvc.entities.SanphamEntity" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý kho hàng</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='bootstrap.min.css'>
    <style>
        .jumbotron {
            background-color: #fafafa;
        }
    </style><meta name="description" content="Quan Com Online Unifood" />
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
</head>
<body>
<div id="container">
    <nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
        <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
        <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
        <ul class="navbar-nav">
            <li class="nav-item active"><a class="nav-link" href="qlhome.jsp">HOME</a></li>
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
<c:if test="${not empty authorize}">
    <div class="container">
        <div class="page-header">
            <h1>Trang quản lý kho hàng</h1>
            <p>
            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                <input type="hidden" name="Type" value="AddWarehouse">
                <input type="submit" class="btn btn-success" value="Thêm kho hàng">
            </form>
            </p>
        </div>
    </div>
    <%
        Map<KhohangEntity, List<SanphamEntity>> map = (Map<KhohangEntity, List<SanphamEntity>>) request.getAttribute("map");
        List<KhohangEntity> listKho = new ArrayList<>(map.keySet());
        Collections.sort(listKho, Comparator.comparing(KhohangEntity::getMaKho));
        for (KhohangEntity k : listKho) {
            List<SanphamEntity> listSP = map.get(k);
    %>
    <div class="container">
        <div class="jumbotron">
            <h3><%= k.getTenKho() %>
            </h3>
            <div class="container">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <td class="text-center"><strong>Mã kho:</strong></td>
                        <td><%= k.getMaKho() %>
                        </td>
                        <th rowspan="2">
                            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                <input type="hidden" name="Type" value="AddItem">
                                <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                <input type="submit" class="btn btn-success" value="Thêm sản phẩm">
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                <input type="hidden" name="Type" value="EditWarehouse">
                                <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                <input type="submit" class="btn btn-info" value="Chỉnh sửa thông tin kho hàng">
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                <input type="hidden" name="Type" value="DeleteWarehouse">
                                <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                <input type="submit" class="btn btn-danger" value="Xóa kho hàng" onclick="
                                        if (0 < <%= listSP.size() %>) {
                                        alert('Không thể xóa kho hàng <%= k.getMaKho() %> khi vẫn còn sản phẩm trong kho.');
                                        return false;
                                        } else {
                                        return confirm('Bạn có chắc chắn muốn xóa kho hàng <%= k.getMaKho() %>?');
                                        }">
                            </form>
                        </th>
                    </tr>
                    <tr>
                        <td class="text-center"><strong>Địa chỉ:</strong></td>
                        <td><%= k.getDiaChi() %>
                        </td>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="container">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th class="text-center">Mã sản phẩm</th>
                        <th class="text-center">Tên sản phẩm</th>
                        <th class="text-center">Đơn vị tính</th>
                        <th class="text-center">Đơn giá</th>
                        <th class="text-center">Số lượng</th>
                        <th class="text-center">Ảnh minh họa</th>
                        <th class="text-center">Mã nhóm</th>
                        <th class="text-center">Mô tả</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (SanphamEntity sp : listSP) {
                    %>
                    <tr>
                        <td><%= sp.getMaSanPham() %>
                        </td>
                        <td><%= sp.getTenSanPham() %>
                        </td>
                        <td><%= sp.getDonViTinh() %>
                        </td>
                        <td><%= sp.getDonGia() %>
                        </td>
                        <td><%= sp.getSoLuong() %>
                        </td>
                        <td class="text-center"><img src="<%= sp.getAnhMinhHoa()%>" width="200px"
                                                     onerror="this.onerror=null; this.src='311151.jpg'"></td>
                        <td><%= sp.getMaNhom() %>
                        </td>
                        <td><%= sp.getMoTa() %>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                <input type="hidden" name="Type" value="EditItem">
                                <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                <input type="hidden" name="MaSanPham" value="<%= sp.getMaSanPham() %>">
                                <input type="submit" class="btn btn-info" value="Sửa">
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                <input type="hidden" name="Type" value="DeleteItem">
                                <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                <input type="hidden" name="MaSanPham" value="<%= sp.getMaSanPham() %>">
                                <input type="submit" class="btn btn-danger" value="Xóa"
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm <%= sp.getMaSanPham() %> khỏi kho hàng <%= k.getMaKho() %>?');">
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <%
        }
    %>
</c:if>
<c:if test="${empty authorize}">
    <div class="page-header">
        <h1>Không có quyền truy cập</h1>
    </div>
</c:if>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>
</body>
</html>
