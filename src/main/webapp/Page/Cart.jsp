<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 12/18/2020
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
    <link href="${pageContext.request.contextPath}/Style/CartStyle.css" rel="stylesheet">
</head>
<body>
<div class="Main-Cart">

    <div class="Cart-Header-Bar">
        <div class="select-all">
            <input type="checkbox" id="Select-All-Check_Button"/>
            <label for="Select-All-Check_Button">Select all</label>
        </div>
    </div>

    <div class="Cart-Items">
        <div class="An-Item">
            <div class="Item-Image">
                <img class="Image" src="${pageContext.request.contextPath}/Images/COM-SUON-NUONG.jpeg">
            </div>
            <div class="Item-Name">
                Com Suon Nuong
            </div>
            <div class="Item-Quantity">
                <input class="inc-button" type="button" value="-">
                <div class="quanity">1</div>
                <input class="dec-button" type="button" value="+">
            </div>
            <div class="Item-Price">
                65,000
            </div>
            <div class="Items-Option">
                <div>note</div>
                <div>delete</div>
            </div>
        </div>
    </div>

    <div class="Cart-Confirm">
        Order summary
        <div>Total number of Item: 99</div>
        <div>Shipping fee: 19.999</div>
        <div>
            <input type="text" placeholder="Voucher" value="" maxlength="50">
            <input type="button" value="APPLY" name="Voucher-Applyp=-Button">
        </div>
        <div>Total Cost for your Shopping Cart: 19,999,999</div>
        <input type="button" name="Cart-Confirm-Button" value="CONFIRM CART">
    </div>
</div>
</body>
</html>
