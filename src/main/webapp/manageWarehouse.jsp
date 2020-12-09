<%@ page import="com.mvc.entities.KhohangEntity" %>
<%@ page import="com.mvc.entities.SanphamEntity" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/9/2020
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý kho hàng</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='bootstrap.min.css'>
</head>
<body>
<c:if test="${not empty authorize}">
    <div class="container">
        <div class="page-header">
            <h1>Trang quản lý kho hàng</h1>
        </div>
        <div class="row">

            <%
                Map<KhohangEntity, List<SanphamEntity>> map = (Map<KhohangEntity, List<SanphamEntity>>) request.getAttribute("map");
                List<KhohangEntity> listKho = new ArrayList<KhohangEntity>(map.keySet());
                Collections.sort(listKho, Comparator.comparing(KhohangEntity::getMaKho));
                for (KhohangEntity k : listKho) {
            %>
            <div class="col-md-12 col-md-offset-12">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr style="background-color: #c0ffee;">
                        <td colspan="1"><strong><%= k.getMaKho() %>
                        </strong></td>
                        <td colspan="3"><strong><%= k.getTenKho() %>
                        </strong></td>
                        <td colspan="8"><em><%= k.getDiaChi() %>
                        </em></td>
                    </tr>
                    </thead>
                    <tr>
                        <th class="text-center">Mã sản phẩm</th>
                        <th class="text-center">Tên sản phẩm</th>
                        <th class="text-center">Đơn vị tính</th>
                        <th class="text-center">Đơn giá</th>
                        <th class="text-center">Số lượng</th>
                        <th class="text-center">Ảnh minh họa</th>
                        <th class="text-center">Mã nhóm</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <%
                        for (SanphamEntity sp : map.get(k)) {
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
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                <input type="hidden" name="Type" value="Edit">
                                <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                <input type="hidden" name="MaSanPham" value="<%= sp.getMaSanPham() %>">
                                <input type="submit" class="btn btn-info" value="Sửa">
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                <input type="hidden" name="Type" value="Delete">
                                <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                <input type="hidden" name="MaSanPham" value="<%= sp.getMaSanPham() %>">
                                <input type="submit" class="btn btn-danger" value="Xóa"
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm <%= sp.getMaSanPham() %> khỏi kho hàng <%= k.getMaKho() %>');">
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
            <%
                }
            %>

        </div>
    </div>
</c:if>
<c:if test="${empty authorize}">
    <div class="page-header">
        <h1>Không có quyền truy cập</h1>
    </div>
</c:if>
</body>
</html>
