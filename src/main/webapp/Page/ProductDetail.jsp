<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 12/16/2020
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mvc.entities.*"%>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
    HttpSession session1 = request.getSession();
    SanphamEntity item = (SanphamEntity) request.getAttribute("Item");
    ListItemEntity listItem1 = (ListItemEntity) request.getAttribute("ListDependenceItems1");
    ListItemEntity listItem2 = (ListItemEntity) request.getAttribute("ListDependenceItems2");
    ListItemEntity listItem3 = (ListItemEntity) request.getAttribute("ListDependenceItems3");
    HashMap <String,Integer> map = (HashMap<String, Integer>) request.getAttribute("ProductQuantity");
    int MainItemQuantity = map.get(item.getMaSanPham());
    System.out.println(MainItemQuantity);
    GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");
%>
<html>
<head>
    <title>Product detail</title>
    <link href="${pageContext.request.contextPath}/Style/PDstyle.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Oswald:wght@500&display=swap" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/JS/ProductScript.js"></script>
</head>
<body>
<div class="main">
    <form class="PDmain" method="post" action="${pageContext.request.contextPath}/Order">
        <input hidden name="MaGio" value="<%=cart.getMaGio()%>">
        <div class="PDBox" id="PDFoodImageBox">
            <img class="PDFoodImgae" src="${pageContext.request.contextPath}${requestScope.Item.anhMinhHoa}">
        </div>
        <div class="PDBox" id="PDFoodMainMenu">
            <div class="PD-box-title"><strong>${requestScope.Item.tenSanPham}</strong></div>
            <input type="text" style="border: none;" class="PD-box-title" id="SL${requestScope.Item.maSanPham}" value="${requestScope.Item.donGia}">
            <div class="PD-main-menu-option-title"><strong>Phần ăn bạn đặt sẽ bao gồm:</strong></div>
            <div class="PD-main-menu-option-text">
                <ul>
                    <c:forEach items="${requestScope.Item.moTa.split(\",\")}" var="subItem">
                        <li><c:out value="${subItem}"/></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="PD-main-menu-option">
                <div class="PD-main-menu-option-title"><strong>Ghi chú cho nhân viên</strong></div>
                <input class="PD-main-menu-option-text" type="text" maxlength="3000"
                       name="GhiChuChoNhanVien" size="30" placeholder="Ghi chú cho nhân viên">
            </div>
            <div class="PD-main-menu-option-quantity">
                <input type="text" name="MainItemCode" style="visibility:hidden;" value="${requestScope.Item.maSanPham}">
                <input type="checkbox" style="visibility: hidden; width: 20px; height:20px" checked="checked" name="Check${requestScope.Item.maSanPham}"
                       class="PD-extra-option-check" id="Check${requestScope.Item.maSanPham}" onclick="Checked('${requestScope.Item.maSanPham}')">
                <input type="button" class="PD-main-menu-option-quantity-dec-button" value="-" onclick="DecButton('${requestScope.Item.maSanPham}')">
                <input type="text" name="${requestScope.Item.maSanPham}" style="border-style: hidden; width: 50px" maxlength="3" readonly class="PD-main-menu-option-quantity-number" id="${requestScope.Item.maSanPham}" value="<%=MainItemQuantity%>">
                <input type="button" class="PD-main-menu-option-quantity-inc-button" value="+" onclick="IncButton('${requestScope.Item.maSanPham}')">
            </div>
            <div>
                <div class="PD-main-menu-form">
                    <input class="PD-main-menu-submit-button" type="submit" value="ĐẶT HÀNG" name="PayCheck" id="Submit-button-1">
                    <input type="text" style="border: none; height: auto; padding: 0px 0px; font-size: 24px" class="PD-main-menu-total-price" id="PD-main-menu-total-price" value="${requestScope.Item.donGia}">
                </div>
                <div class="PD-main-menu-form">
                    <input class="PD-main-menu-submit-button" type="submit" value="THÊM VÀO GIỎ" name="AddToCart" id="Submit-button-2">
                    <input type="text" style="border: none; height: auto; padding: 0px 0px; font-size: 24px" class="PD-main-menu-total-price" id="" value="">
                </div>
            </div>
        </div>
        <div class="PDBox" id="PDFoodOption">
            <div class="PD-box-title"><strong>Càng ăn càng ngon</strong></div>
            <div class="PD-extra-option">
                <div class="PD-extra-option-title"><strong>${requestScope.ListDependenceItems1.listItemName}</strong></div>
                <c:forEach var="subitem" items="${requestScope.ListDependenceItems1.itemList}">
                    <div class="PD-extra-option-AnOption">
                        <input type="checkbox" style="width: 20px; height:20px" name="Check${subitem.maSanPham}"
                               class="PD-extra-option-check" id="Check${subitem.maSanPham}" onclick="Checked('${subitem.maSanPham}')">
                        <label for="${subitem.tenSanPham}">${subitem.tenSanPham}</label>
                        <div class="PD-extra-option-quantity">
                            <input type="button" class="PD-extra-option-quantity-dec-button" value="-" onclick="DecButton('${subitem.maSanPham}')">
                            <input style="border-style: hidden; width: 50px" maxlength="3" readonly class="PD-extra-option-quantity" id="${subitem.maSanPham}" name="${subitem.maSanPham}" type="number" value="${requestScope.ProductQuantity.get(subitem.maSanPham)}">
                            <input type="button" class="PD-extra-option-quantity-inc-button" value="+" onclick="IncButton('${subitem.maSanPham}')">
                        </div>
                        <input style="border-style: hidden; color: #a50909; background-color: white; font-family: Calibri, sans-serif; font-size: 28px;" disabled class="PD-extra-option-price" name="SL${subitem.maSanPham}" id="SL${subitem.maSanPham}" value="${subitem.donGia}">
                    </div>
                </c:forEach>
            </div>
            <div class="PD-extra-option">
                <div class="PD-extra-option-title"><strong>${requestScope.ListDependenceItems2.listItemName}</strong></div>
                <c:forEach var="subitem" items="${requestScope.ListDependenceItems2.itemList}">
                    <div class="PD-extra-option-AnOption">
                        <input type="checkbox" style="width: 20px; height:20px" name="Check${subitem.maSanPham}"
                               class="PD-extra-option-check" id="Check${subitem.maSanPham}" onclick="Checked('${subitem.maSanPham}')">
                        <label for="${subitem.tenSanPham}">${subitem.tenSanPham}</label>
                        <div class="PD-extra-option-quantity">
                            <input type="button" class="PD-extra-option-quantity-dec-button" value="-" onclick="DecButton('${subitem.maSanPham}')">
                            <input style="border-style: hidden; width: 50px" maxlength="3" readonly class="PD-extra-option-quantity" id="${subitem.maSanPham}" name="${subitem.maSanPham}" type="number" value="${requestScope.ProductQuantity.get(subitem.maSanPham)}">
                            <input type="button" class="PD-extra-option-quantity-inc-button" value="+" onclick="IncButton('${subitem.maSanPham}')">
                        </div>
                        <input style="border-style: hidden; color: #a50909; background-color: white; font-family: Calibri, sans-serif; font-size: 28px;" disabled class="PD-extra-option-price" name="SL${subitem.maSanPham}" id="SL${subitem.maSanPham}" value="${subitem.donGia}">
                    </div>
                </c:forEach>
            </div>
            <div class="PD-extra-option">
                <div class="PD-extra-option-title"><strong>${requestScope.ListDependenceItems3.listItemName}</strong></div>
                <c:forEach var="subitem" items="${requestScope.ListDependenceItems3.itemList}">
                    <div class="PD-extra-option-AnOption">
                        <input type="checkbox" style="width: 20px; height:20px" name="Check${subitem.maSanPham}"
                               class="PD-extra-option-check" id="Check${subitem.maSanPham}" onclick="Checked('${subitem.maSanPham}')">
                        <label for="${subitem.tenSanPham}">${subitem.tenSanPham}</label>
                        <div class="PD-extra-option-quantity">
                            <input type="button" class="PD-extra-option-quantity-dec-button" value="-" onclick="DecButton('${subitem.maSanPham}')">
                            <input style="border-style: hidden; width: 50px" maxlength="3" readonly class="PD-extra-option-quantity" id="${subitem.maSanPham}" name="${subitem.maSanPham}" type="number" value="${requestScope.ProductQuantity.get(subitem.maSanPham)}">
                            <input type="button" class="PD-extra-option-quantity-inc-button" value="+" onclick="IncButton('${subitem.maSanPham}')">
                        </div>
                        <input style="border-style: hidden; color: #a50909; background-color: white; font-family: Calibri, sans-serif; font-size: 28px;" disabled class="PD-extra-option-price" name="SL${subitem.maSanPham}" id="SL${subitem.maSanPham}" value="${subitem.donGia}">
                    </div>
                </c:forEach>
            </div>
        </div>
    </form>
</div>
</body>
</html>