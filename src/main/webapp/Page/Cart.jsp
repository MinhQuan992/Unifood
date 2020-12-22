<%@ page import="java.util.List" %>
<%@ page import="com.mvc.entities.DathangEntity" %>
<%@ page import="com.mvc.entities.SanphamEntity" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 12/18/2020
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession session1 = request.getSession();
    List<String> listCheckedItem = (List<String>) session1.getAttribute("CheckedItemList");
    List<DathangEntity> listOrder = (List<DathangEntity>) request.getAttribute("OrderList");
    Map<String, SanphamEntity> listItem = (Map<String, SanphamEntity>) request.getAttribute("ItemMap");
    Map<String, Boolean> checkList = (Map<String, Boolean>) request.getAttribute("CheckMap");
    int totalCost = (Integer) request.getAttribute("TotalCost");
    int quantityNumber = (Integer) request.getAttribute("QuantityNumber");
    int shippingFee = (Integer) request.getAttribute("ShippingFee");
%>
<html>
<head>
    <title>Shopping Cart</title>
    <link href="${pageContext.request.contextPath}/Style/CartStyle.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Oswald:wght@500&display=swap" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/JS/CartScript.js"></script>
</head>
<body>
<div class="Main-Cart">
    <div class="Cart-Header-Bar">
        <div class="select-all">
            <input type="checkbox" style="width: 20px; height: 20px" name="Select-All-Check_Button" id="Select-All-Check_Button"
                    <c:if test="${requestScope.SelectAllItem!=null && requestScope.SelectAllItem=='On'}">checked</c:if>
                   onclick="OnSelectAll('${pageContext.request.contextPath}/Cart?SelectAll=On')"/>
            <label for="Select-All-Check_Button">Select all</label>
        </div>
    </div>
    <div class="Cart-Items">
        <c:forEach items="${requestScope.OrderList}" var="order">
            <div class="An-Item">
                <div class="Item-Check">
                    <input type="checkbox" class="Check-Button" style="width: 20px; height: 20px;" <%%> <c:if test="${requestScope.CheckedItemList.contains(order.maSanPham)}">checked</c:if>
                           name="Check${order.maSanPham}" id="Check${order.maSanPham}" onclick="OnCheck('${order.maSanPham}','${pageContext.request.contextPath}/Cart?CheckedItem=${order.maSanPham}')">
                </div>
                <div class="Item-Image">
                    <img class="Image" src="${pageContext.request.contextPath}${requestScope.ItemMap.get(order.maSanPham).anhMinhHoa}">
                </div>
                <div class="Item-Name">${requestScope.ItemMap.get(order.maSanPham).tenSanPham}</div>
                <div class="Item-Quantity">
                    <input class="inc-button" type="button" value="-" onclick="DecButton('${order.maSanPham}','${pageContext.request.contextPath}/Cart?SubItem=${order.maSanPham}')">
                    <input type="text" disabled class="quanity" name="${order.maSanPham}" id="${order.maSanPham}" value="${order.soLuong}">
                    <input class="dec-button" type="button" value="+" onclick="IncButton('${order.maSanPham}','${pageContext.request.contextPath}/Cart?AddItem=${order.maSanPham}')">
                </div>
                <input type="text" disabled class="Item-Price" name="SL${order.maSanPham}" id="SL${order.maSanPham}" value="${order.donGia}">
                <div class="Items-Option">
                    <input type="button" class="Button-Note" name="Take-Items-Note" value="Note">
                    <%--<a style="text-decoration: none" href="${pageContext.request.contextPath}/Cart?DeleteItem=${order.maSanPham}">--%>
                    <input type="button" class="Button-Delete" name="Remove-Items-Button" value="Delete" onclick="OnDelete('${pageContext.request.contextPath}/Cart?DeleteItem=${order.maSanPham}')">
                </div>
            </div>
        </c:forEach>
    </div>
    <form name="Confirm-cart" action="#" method="get">
        <div class="Cart-Confirm">
            Order summary
            <div>   Total number of Items: <input type="text" name="Total-Number-Of-Item" class="Input-text" disabled id="Total-Number-Of-Item" value="<%=quantityNumber%>"></div>
            <div>   Shipping fee: đ <input type="text" name="Shipping-fee" class="Input-text" disabled id="Shipping-fee" value="<%=shippingFee%>"></div>
            <div>   Total Cost: đ <input type="text" name="Total-Cost-Of-Cart" disabled class="Input-text" id="Total-Cost-Of-Cart" value="<%=totalCost%>"></div>
            <input type="submit" class="Cart-Confirm-Button" name="Cart-Confirm-Button" value="CONFIRM CART">
        </div>
    </form>
</div>
</body>
</html>
