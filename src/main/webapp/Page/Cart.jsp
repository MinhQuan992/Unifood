<%@ page import="java.util.List" %>
<%@ page import="com.mvc.entities.DathangEntity" %>
<%@ page import="com.mvc.entities.SanphamEntity" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.mvc.entities.GiohangEntity" %>
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
    GiohangEntity cart = (GiohangEntity) session1.getAttribute("ShoppingCart");
%>
<html>
<head>
    <title>Shopping Cart</title>
    <link href="${pageContext.request.contextPath}/Style/CartStyle.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Oswald:wght@500&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" type="text/javascript"></script>
    <%--<script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>--%>

    <link rel="stylesheet" href="bootstrap.min.css">
    <meta name="description" content="Quan Com Online Unifood" />
    <meta name="author" content="NhomHQNT">
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="css/home.css" />
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
    <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
    <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
    <ul class="navbar-nav">
        <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
        <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
    </ul>
</nav>
<div class="cart-box">
    <div class="Main-Cart">
        <div class="Cart-Header-Bar">
            <div class="select-all">
                <input type="checkbox" style="width: 20px; height: 20px" name="Select-All-Check_Button" id="Select-All-Check_Button"
                       <c:if test="${requestScope.SelectAllItem!=null && requestScope.SelectAllItem=='On'}">checked</c:if>
                       onclick="ButtonClick('SelectAll','On')"/>
                <label for="Select-All-Check_Button">Select all</label>
            </div>
        </div>
        <div class="Cart-Items">
            <c:forEach items="${requestScope.OrderList}" var="order">
                <div class="An-Item">
                    <div class="Item-Check">
                        <input type="checkbox" class="Check-Button" style="width: 20px; height: 20px;" <%%> <c:if test="${requestScope.CheckedItemList.contains(order.maSanPham)}">checked</c:if>
                               name="Check${order.maSanPham}" id="Check${order.maSanPham}" onclick="Checked('CheckedItem','${order.maSanPham}')">
                    </div>
                    <div class="Item-Image">
                        <img class="Image" src="${pageContext.request.contextPath}${requestScope.ItemMap.get(order.maSanPham).anhMinhHoa}">
                    </div>
                    <div class="Item-Name">${requestScope.ItemMap.get(order.maSanPham).tenSanPham}</div>
                    <div class="Item-Quantity">
                        <input class="inc-button" style="font-size: 24px; width: 30px;" type="button" value="-" onclick="DecButton('SubItem','${order.maSanPham}')">
                        <input type="text" disabled class="quanity" name="${order.maSanPham}" id="${order.maSanPham}" value="${order.soLuong}">
                            <%--<input class="dec-button" type="button" value="+" onclick="IncButton('${order.maSanPham}','${pageContext.request.contextPath}/Cart?AddItem=${order.maSanPham}')">--%>
                        <input class="dec-button" style="font-size: 24px; width: 30px;" type="button" value="+" onclick="IncButton('AddItem','${order.maSanPham}')">
                    </div>
                    <input type="text" disabled class="Item-Price" name="SL${order.maSanPham}" id="SL${order.maSanPham}" value="${order.donGia}">
                    <div class="Items-Option">
                        <input type="text" disabled="true" name="Note${order.maSanPham}" id="Note${order.maSanPham}" class="Input-text" style="width: 200px; font-size: 14px;" maxlength="300" value="${order.ghiChu}">
                        <input type="button" style="font-size: 14px;" class="Button-Note" name="Take-Items-Note${order.maSanPham}"  id="Take-Items-Note${order.maSanPham}" value="Note" onclick="NoteButtonClick('${order.maSanPham}')">
                            <%--<a style="text-decoration: none" href="${pageContext.request.contextPath}/Cart?DeleteItem=${order.maSanPham}">--%>
                        <input type="button" style="font-size: 14px;" class="Button-Delete" name="Remove-Items-Button" value="Delete" onclick="Deleted('DeleteItem','${order.maSanPham}')">
                    </div>
                </div>
            </c:forEach>
        </div>
        <form name="Confirm-cart" action="${pageContext.request.contextPath}/Cart" method="post">
            <div class="Cart-Confirm">
                Order summary
                <div>   Total number of Items: <input type="text" name="Total-Number-Of-Item" class="Input-text" disabled id="Total-Number-Of-Item" value="<%=quantityNumber%>"></div>
                <div>   Shipping fee: đ <input type="text" name="Shipping-fee" class="Input-text" disabled id="Shipping-fee" value="<%=shippingFee%>"></div>
                <div>   Total Cost: đ <input type="text" name="Total-Cost-Of-Cart" disabled class="Input-text" id="Total-Cost-Of-Cart" value="<%=totalCost%>"></div>
                <input name="MaGio" value="<%=cart.getMaGio()%>" hidden>
                <input type="submit" class="Cart-Confirm-Button" name="Cart-Confirm" value="CONFIRM CART">
            </div>
        </form>
        <form name="SendToPostRequest" method="Post" style="visibility: hidden;" action="${pageContext.request.contextPath}/Cart">
            <input type="hidden" name="ParaName">
            <input type="hidden" name="KeyValue">
            <input type="hidden" name="Extend">
        </form>
    </div>
</div>
<form name="AjaxSendingForm" id="AjaxSendingForm" action="#" method="GET">
    <input type="hidden" name="ParaName" id="AjaxSendingFormParaName">
    <input type="hidden" name="KeyValue" id="AjaxSendingFormKeyValue">
    <input type="hidden" name="Extend" id="AjaxSendingFormExtend">
</form>
</body>
</html>
<script src="https://code.jquery.com/jquery-1.10.2.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/JS/NewJS.js"
        type="text/javascript"></script>