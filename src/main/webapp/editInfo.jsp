<%@ page import="com.mvc.entities.NguoidungEntity" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/29/2020
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Info</title>
    <link rel="stylesheet" href="bootstrap.min.css">
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
    <link type="text/css" rel="stylesheet" href="css/home.css" />
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        .clear {
            clear: both;
            display: block;
            height: 0;
            width: auto;
        }

        .faq-row {
            background-color: #fff;
            border: #ccc 1px solid;
            margin-bottom: 12px;
            position: relative;
            overflow: auto;
        }

        .faq-row-handle {
            border: #ccc 1px solid;
            border-bottom: none;
        }

        .faq-row-handle>a {
            cursor: pointer;
            display: block;
            color: #191919;
            font-weight: normal;
            font-size: 13px;
            padding: 7px 30px 7px 10px;
            line-height: 20px;
            text-decoration: none;
            outline: 0;
            float: left;
            border-right: #ccc 1px solid;
        }

        .faq-row-handle a.open {
            color: green;
        }

        .faq-row-content {
            color: inherit;
            font-weight: inherit;
            line-height: inherit;
            font-size: inherit;
            display: none;
            position: inherit;
            top: 0;
            left: 0;
        }

        .faq-row-content.open {
            display: block;
        }

        .faq-row-content p {
            padding: 0 14px 10px 14px;
        }
    </style>
</head>
<body>
<%
    NguoidungEntity user = (NguoidungEntity) request.getSession().getAttribute("User");
    int index = 0;
    Object objIdx = request.getAttribute("index");
    if (objIdx != null) { index = (int)objIdx; }
%>
<div id="container">
    <nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
        <c:choose>
            <c:when test="${userType == 'Customer'}">
                <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
                <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
                <ul class="navbar-nav">
                    <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
                    <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
                </ul>
            </c:when>

            <c:otherwise>
                <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;"></a>
                <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
                <ul class="navbar-nav">
                    <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/qlhome.jsp">HOME</a></li>
                    <li class="nav-item"><a class="nav-link" href="staffs.jsp">STAFFS</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </nav>

    <c:if test="${not empty authorize}">
        <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
            <div class="jumbotron">
                <div class="page-header">
                    <c:choose>
                        <c:when test="${userType == 'Customer'}">
                            <p>Trở về <a href="${pageContext.request.contextPath}/index.jsp">trang chủ</a></p>
                        </c:when>

                        <c:otherwise>
                            <p>Trở về <a href="${pageContext.request.contextPath}/qlhome.jsp">trang chủ</a></p>
                        </c:otherwise>
                    </c:choose>
                    <h1>Thay đổi thông tin người dùng</h1>
                    <p class="text-info"><c:out value="${status}"></c:out></p>
                </div>

                <div class="faq-row-handle">
                    <a class="btn-outline-info" href="javascript:;" rel="item1">Thông tin người dùng</a>
                    <a class="btn-outline-info" href="javascript:;" rel="item2">Thay đổi mật khẩu</a>
                    <a class="btn-outline-info" href="javascript:;" rel="item3">Thay đổi thông tin liên lạc</a>
                    <a class="btn-outline-info" href="javascript:;" rel="item4">Thay đổi thông tin khác</a>
                    <div class="clear"></div>
                </div>

                <div class="faq-row">

                    <div id="item1" class="faq-row-content">
                        <div class="container-fluid">
                            <div class="form-group">
                                <label for="fullName1">Họ và tên:</label>
                                <input type="text" disabled
                                       class="form-control" id="fullName1"
                                       name="fullName" value="<%=user.getHoVaTen()%>">
                            </div>
                            <div class="form-group">
                                <label for="gender1">Giới tính:</label>
                                <input type="text" disabled
                                       class="form-control" id="gender1"
                                       name="gender" value="<%=user.getGioiTinh()%>">
                            </div>
                            <div class="form-group">
                                <label for="birthDate1">Ngày sinh:</label>
                                <input type="date" disabled
                                       class="form-control" id="birthDate1"
                                       name="birthDate" value="<%=user.getNgaySinh().toString()%>">
                            </div>
                            <div class="form-group">
                                <label for="address1">Địa chỉ:</label>
                                <input type="text" disabled
                                       class="form-control" id="address1"
                                       name="address" value="<%=user.getDiaChi()%>">
                            </div>
                            <div class="form-group">
                                <label for="phone1">Số điện thoại:</label>
                                <input type="tel" disabled
                                       class="form-control" id="phone1"
                                       name="phone" value="<%=user.getDienThoai()%>">
                            </div>
                            <div class="form-group">
                                <label for="email1">Email:</label>
                                <input type="email" disabled
                                       class="form-control" id="email1"
                                       name="email" value="<%=user.getEmail()%>">
                            </div>
                        </div>
                    </div>

                    <div id="item2" class="faq-row-content">
                        <div class="container-fluid">
                            <form method="post" action="${pageContext.request.contextPath}/EditInfo">

                                <div class="form-group">
                                    <label for="newPassword">Mật khẩu mới:</label>
                                    <input type="password"
                                           class="form-control" id="newPassword"
                                           name="newPassword" value="${newPassword}" required>
                                    <p style="color: red"><c:out value="${error.password}"></c:out></p>
                                </div>

                                <div class="form-group">
                                    <label for="rePassword">Nhập lại mật khẩu mới:</label>
                                    <input type="password"
                                           class="form-control" id="rePassword"
                                           name="rePassword" value="${rePassword}">
                                    <p style="color: red"><c:out value="${error.rePassword}"></c:out></p>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Xác nhận thay đổi</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div id="item3" class="faq-row-content">
                        <div class="container-fluid">
                            <form method="post" action="${pageContext.request.contextPath}/EditInfo">

                                <div class="form-group">
                                    <label for="address">Địa chỉ:</label>
                                    <input type="text"
                                           class="form-control" id="address"
                                           name="address" value="${address}">
                                    <p style="color: red"><c:out value="${error.address}"></c:out></p>
                                </div>

                                <div class="form-group">
                                    <label for="phone">Số điện thoại:</label>
                                    <input type="tel"
                                           class="form-control" id="phone"
                                           name="phone" value="${phone}">
                                    <p style="color: red"><c:out value="${error.phone}"></c:out></p>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Xác nhận thay đổi</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div id="item4" class="faq-row-content">
                        <div class="container-fluid">
                            <form method="post" action="${pageContext.request.contextPath}/EditInfo">

                                <div class="form-group">
                                    <label for="fullName">Họ và tên:</label>
                                    <input type="text"
                                           class="form-control" id="fullName"
                                           name="fullName" value="${fullName}">
                                    <p style="color: red"><c:out value="${error.fullName}"></c:out></p>
                                </div>

                                <div class="form-group">
                                    <label for="gender">Giới tính:</label>
                                    <input type="text"
                                           class="form-control" id="gender"
                                           name="gender" value="${gender}">
                                    <p style="color: red"><c:out value="${error.gender}"></c:out></p>
                                </div>

                                <div class="form-group">
                                    <label for="birthDate">Ngày sinh:</label>
                                    <input type="date"
                                           class="form-control" id="birthDate"
                                           name="birthDate" value="${birthDate}">
                                    <p style="color: red"><c:out value="${error.birthDate}"></c:out></p>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Xác nhận thay đổi</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="clear"></div>
                </div>
                <!--
                <form method="post" action="${pageContext.request.contextPath}/EditInfo">
                    <input type="hidden" id="UserId" name="UserId" value="${userID}">
                    <div class="form-group">
                        <label for="fullName">Họ và tên:</label>
                        <input type="text"
                               class="form-control" id="fullName"
                               name="fullName" value="${fullName}">
                        <p style="color: red"><c:out value="${error.fullName}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <label for="newPassword">Mật khẩu mới:</label>
                        <input type="password"
                               class="form-control" id="newPassword"
                               name="newPassword" value="${password}" required>
                        <p style="color: red"><c:out value="${error.password}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <label for="rePassword">Nhập lại mật khẩu mới:</label>
                        <input type="password"
                               class="form-control" id="rePassword"
                               name="rePassword" value="${rePassword}">
                        <p style="color: red"><c:out value="${error.rePassword}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <label for="gender">Giới tính:</label>
                        <input type="text"
                               class="form-control" id="gender"
                               name="gender" value="${gender}">
                        <p style="color: red"><c:out value="${error.gender}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <label for="birthDate">Ngày sinh:</label>
                        <input type="date"
                               class="form-control" id="birthDate"
                               name="birthDate" value="${birthDate}">
                        <p style="color: red"><c:out value="${error.birthDate}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <label for="address">Địa chỉ:</label>
                        <input type="text"
                               class="form-control" id="address"
                               name="address" value="${address}">
                        <p style="color: red"><c:out value="${error.address}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <label for="phone">Số điện thoại:</label>
                        <input type="text"
                               class="form-control" id="phone"
                               name="phone" value="${phone}">
                        <p style="color: red"><c:out value="${error.phone}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text"
                               class="form-control" id="email"
                               name="email" value="${email}">
                        <p style="color: red"><c:out value="${error.email}"></c:out></p>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Xác nhận thay đổi</button>
                    </div>
-->
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${empty authorize}">
        <p>Not Authorize</p>
    </c:if>

    <div id="footer">
        <p style="text-align: center">
            <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
        </p>
    </div>
</div>

<script>
    $(document).ready(function () {
        var tab = $('.faq-row-handle > a'),
            tabContent = $('.faq-row-content'),
            tabParent = $('.faq-row');
        if (tab.length) {
            tab.off('click').on('click', function () {
                var ele = $(this),
                    currContent = $('#' + ele.attr('rel'));
                if (!ele.hasClass('open')) {
                    tabParent.stop(true, true).animate({
                        height: '0px'
                    }, function () {
                        tabContent.removeClass('open');
                        currContent.addClass('open');
                        tab.removeClass('open');
                        ele.addClass('open');
                        tabParent.stop(true, true).animate({
                            height: currContent.height() + 'px'
                        });
                    });
                }
            });
            $('.faq-row-handle > a:eq(<%=index%>)').click(); // a:eq(index)
        }
    });
</script>
</body>
</html>