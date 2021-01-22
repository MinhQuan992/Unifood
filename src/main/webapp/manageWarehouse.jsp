<%@ page import="com.mvc.entities.KhohangEntity" %>
<%@ page import="com.mvc.entities.SanphamEntity" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/9/2020
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.mvc.dao.CartDao" %>
<%@ page import="com.mvc.entities.GiohangEntity" %>
<%@ page import="com.mvc.entities.NguoidungEntity" %>
<%@ page import="com.mvc.dao.UserDao" %>
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
        .collapsible {
            background-color: #f5f5f5;
            color: black;
            cursor: pointer;
            padding: 18px;
            width: 100%;
            border: none;
            text-align: left;
            outline: none;
            font-size: 15px;
            font-weight: bold;
        }
        .active, .collapsible:hover {
            background-color: #ddd;
        }
        .collapsible:after {
            content: '\002B'; /* Unicode character for "plus" sign (+) */
            color: black;
            font-weight: bold;
            float: right;
            margin-left: 5px;
        }
        .active:after {
            content: "\2212"; /* Unicode character for "minus" sign (-) */
        }
        .content {
            padding: 0 18px;
            max-height: 0;
            overflow: auto;
            transition: max-height 0.2s ease-out;
            background-color: #fafafa;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <c:if test="${not empty authorize}">
        <c:if test="${not empty DeleteStatus}">
            <script type="text/javascript">
                alert("Lỗi: Không thể thực hiện việc xóa. \n${DeleteStatus}")
            </script>
        </c:if>
        <div class="container">
            <div class="page-header">
                <p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
                <h1>Trang quản lý kho hàng</h1>
                <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                    <input type="hidden" name="Type" value="AddWarehouse">
                    <input type="submit" class="btn btn-success" value="Thêm kho hàng">
                </form>
            </div>
        </div>

        <div class="container">
            <%
                Map<KhohangEntity, List<SanphamEntity>> map = (Map<KhohangEntity, List<SanphamEntity>>) request.getAttribute("map");
                List<KhohangEntity> listKho = new ArrayList<>(map.keySet());
                Collections.sort(listKho, Comparator.comparing(KhohangEntity::getMaKho));
                for (KhohangEntity k : listKho) {
                    List<SanphamEntity> listSP = map.get(k);
            %>
            <button class="collapsible"><%=k.getTenKho()%></button>
            <div class="content">
                <div class="jumbotron">

                    <div class="container">
                        <table class="table table-bordered table-striped table-hover">
                            <thead>
                            <tr>
                                <td class="text-center"><strong>Mã kho:</strong></td>
                                <td><%= k.getMaKho() %>
                                </td>
                                <th rowspan="2">
                                    <!--form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                        <input type="hidden" name="Type" value="AddItem">
                                        <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                        <input type="submit" class="btn btn-success" value="Thêm sản phẩm">
                                    </form-->
                                    <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                        <input type="hidden" name="Type" value="EditWarehouse">
                                        <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                        <input type="submit" class="btn btn-info"
                                               value="Chỉnh sửa thông tin kho hàng">
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
                                <td class="text-center"><img src=".<%= sp.getAnhMinhHoa()%>" width="200px"
                                                             onerror="this.onerror=null; this.src='./Images/311151.jpg'">
                                </td>
                                <td><%= sp.getMaNhom() %>
                                </td>
                                <td><%= sp.getMoTa() %>
                                </td>
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                        <input type="hidden" name="Type" value="EditItem">
                                        <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                        <input type="hidden" name="MaSanPham" value="<%= sp.getMaSanPham() %>">
                                        <input type="submit" class="btn btn-info" value="Chỉnh sửa">
                                    </form>
                                    <!--form method="get" action="${pageContext.request.contextPath}/ManageWarehouse">
                                        <input type="hidden" name="Type" value="DeleteItem">
                                        <input type="hidden" name="MaKho" value="<%= k.getMaKho() %>">
                                        <input type="hidden" name="MaSanPham" value="<%= sp.getMaSanPham() %>">
                                        <input type="submit" class="btn btn-danger" value="Xóa"
                                               onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm <%= sp.getMaSanPham() %> khỏi kho hàng <%= k.getMaKho() %>?');">
                                    </form-->
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
        </div>
    </c:if>

    <c:if test="${empty authorize}">
        <div class="page-header">
            <h1>Không có quyền truy cập</h1>
        </div>
    </c:if>

    <script>
        var coll = document.getElementsByClassName("collapsible");
        var i;
        for (i = 0; i < coll.length; i++) {
            coll[i].addEventListener("click", function() {
                this.classList.toggle("active");
                var content = this.nextElementSibling;
                if (content.style.maxHeight){
                    content.style.maxHeight = null;
                } else {
                    content.style.maxHeight = content.scrollHeight + "px";
                }
            });
        }
    </script>
</div>
</body>
</html>