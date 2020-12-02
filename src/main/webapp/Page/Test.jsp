<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 11/27/2020
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mvc.bean.ItemBean"%>
<%@ page import="com.mvc.bean.ListItemBean"%>
<%
    ListItemBean list1 = (ListItemBean) request.getAttribute("ListDependenceItems1");
    ListItemBean list2 = (ListItemBean) request.getAttribute("ListDependenceItems2");
    ListItemBean list3 = (ListItemBean) request.getAttribute("ListDependenceItems3");
%>
<html>
<head>
    <title>Product detail</title>
    <link href="${pageContext.request.contextPath}/Style/PDstyle.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Oswald:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
    <div class="PDmain">
        <div class="PDBox" id="PDFoodImageBox">
            <img class="PDFoodImgae" src="${pageContext.request.contextPath}${requestScope.Item.itemImage}">
        </div>
        <div class="PDBox" id="PDFoodMainMenu">
            <div class="PD-box-title"><strong>${requestScope.Item.itemName}</strong></div>
            <div class="PD-main-menu-option-title"><strong>Phần ăn bạn đặt sẽ bao gồm:</strong></div>
            <div class="PD-main-menu-option-text">
                <ul>
                    <c:forEach items="${requestScope.Item.itemDescription.split(\",\")}" var="subItem" >
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
                <button class="PD-main-menu-option-quantity-dec-button">-</button>
                <div class="PD-main-menu-option-quantity-number">1</div>
                <button class="PD-main-menu-option-quantity-inc-button">+</button>
            </div>
            <div class="PD-main-menu-form">
                <form  name="PDMainMenuForm" action="#">
                    <input class="btn btn-danger PD-main-menu-submit-button" type="submit" value="ĐẶT HÀNG" name="DatHang" id="Submit-button-1">
                </form>
                <div class="PD-main-menu-total-price">99,000</div>
            </div>
        </div>
        <div class="PDBox" id="PDFoodOption">
            <div class="PD-box-title"><strong>Càng ăn càng ngon</strong></div>
            <div class="PD-extra-option">
                <div class="PD-extra-option-title"><strong>${requestScope.ListDependenceItems1.listItemName}</strong></div>
                <c:forEach var="subitem" items="${requestScope.ListDependenceItems1.itemList}">
                <div class="PD-extra-option-AnOption">
                    <input type="checkbox" name="MonAnKem02"
                           class="PD-extra-option-check" id="${subitem.itemName}">
                    <label for="${subitem.itemName}">${subitem.itemName}</label>
                    <div class="PD-extra-option-quantity">
                        <button class="PD-extra-option-quantity-dec-button">-</button>
                        <span class="PD-extra-option-quantity">1</span>
                        <button class="PD-extra-option-quantity-inc-button">+</button>
                    </div>
                    <span class="PD-extra-option-price">${subitem.itemPrice}</span>
                </div>
                </c:forEach>
            </div>
            <div class="PD-extra-option">
                <div class="PD-extra-option-title"><strong>${requestScope.ListDependenceItems2.listItemName}</strong></div>
                <c:forEach var="subitem" items="${requestScope.ListDependenceItems2.itemList}">
                    <div class="PD-extra-option-AnOption">
                        <input type="checkbox" name="MonAnKem02"
                               class="PD-extra-option-check" id="${subitem.itemName}">
                        <label for="${subitem.itemName}">${subitem.itemName}</label>
                        <div class="PD-extra-option-quantity">
                            <button class="PD-extra-option-quantity-dec-button">-</button>
                            <span class="PD-extra-option-quantity">1</span>
                            <button class="PD-extra-option-quantity-inc-button">+</button>
                        </div>
                        <span class="PD-extra-option-price">${subitem.itemPrice}</span>
                    </div>
                </c:forEach>
            </div>
            <div class="PD-extra-option">
                <div class="PD-extra-option-title"><strong>${requestScope.ListDependenceItems3.listItemName}</strong></div>
                <c:forEach var="subitem" items="${requestScope.ListDependenceItems3.itemList}">
                    <div class="PD-extra-option-AnOption">
                        <input type="checkbox" name="MonAnKem02"
                               class="PD-extra-option-check" id="${subitem.itemName}">
                        <label for="${subitem.itemName}">${subitem.itemName}</label>
                        <div class="PD-extra-option-quantity">
                            <button class="PD-extra-option-quantity-dec-button">-</button>
                            <span class="PD-extra-option-quantity">1</span>
                            <button class="PD-extra-option-quantity-inc-button">+</button>
                        </div>
                        <span class="PD-extra-option-price">${subitem.itemPrice}</span>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
