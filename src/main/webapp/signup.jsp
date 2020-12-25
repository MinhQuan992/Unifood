<%--
  Created by IntelliJ IDEA.
  User: Vo Tran Minh Quan
  Date: 11/28/2020
  Time: 8:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký | Unifood</title>
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
          crossorigin="anonymous">
</head>
<body>
<div id="container">
<nav style="background-color: #60150c;" class="navbar navbar-expand-sm">
    <a href="#"><img class="logo" src="Images/LOGO.png" style="width: auto; height: 50px;">}</a>
    <a class="homelogo" href="index.jsp"><img src="Images/homepage_icon.png" style="width: auto; height: 50px;"></a>
    <ul class="navbar-nav">
        <li class="nav-item active"><a class="nav-link" href="index.jsp">HOME</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MainPage?">PRODUCTS</a></li>
        <li class="nav-item"><a class="nav-link" href="contact.jsp">CONTACTS</a></li>
    </ul>
</nav>
<%
    String userType = (String) request.getSession().getAttribute("userType");
    if (userType == null)
    {
        request.getSession().setAttribute("userType", "Customer");
    }
%>
<c:if test="${not empty signupSuccess}">
    <c:choose>
        <c:when test="${signupSuccess == true}">
            <script type="text/javascript">
                alert("Thêm quản lí thành công!")
            </script>
        </c:when>

        <c:otherwise>
            <script type="text/javascript">
                alert("Không thể tạo tài khoản, mời bạn thử lại!")
            </script>
        </c:otherwise>
    </c:choose>
</c:if>
<div id="procontainer" style="text-align: center;">
    <h1>ĐĂNG KÝ TÀI KHOẢN</h1>
    <form id="" method="post" action="${pageContext.request.contextPath}/signup">
        <table>
            <tr>
                <td>Họ và tên:</td>
                <td>
                    <input type="text" name="userFullname"
                    <c:choose>
                        <c:when test="${empty userFullname}">
                            placeholder="Nhập họ và tên"
                        </c:when>

                        <c:otherwise>
                            value="<c:out value="${userFullname}"/>"
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty fullnameError}">
                           style="border-color: red"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${fullnameError}"/></td>
            </tr>

            <tr>
                <td>Giới tính:</td>
                <td>
                    <label>
                        <input type="radio" name="userGender" value="Nam" <c:if test="${empty userGender || userGender == 'Nam'}">checked</c:if>>
                    </label>Nam
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <label>
                        <input type="radio" name="userGender" value="Nữ" <c:if test="${userGender == 'Nữ'}">checked</c:if>>
                    </label>Nữ
                </td>
            </tr>

            <tr>
                <td>Ngày sinh:</td>
                <td>
                    <input type="date" name="userBirthdate"
                    <c:choose>
                        <c:when test="${not empty userBirthdate}">
                            value="<c:out value="${userBirthdate}"/>"
                        </c:when>

                        <c:otherwise>
                            value="2000-01-01"
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty birthdateError}">
                            style="border-color: red"
                    </c:if>
                    >
                </td>
                <td><c:out value="${birthdateError}"/></td>
            </tr>

            <tr>
                <td>Địa chỉ:</td>
                <td>
                    <input type="text" name="userAddress"
                    <c:choose>
                        <c:when test="${empty userAddress}">
                            placeholder="Nhập địa chỉ"
                        </c:when>

                        <c:otherwise>
                            value="<c:out value="${userAddress}"/>"
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty addressError}">
                           style="border-color: red"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${addressError}"/></td>
            </tr>

            <tr>
                <td>Điện thoại:</td>
                <td>
                    <input type="text" name="userPhone"
                    <c:choose>
                        <c:when test="${empty userPhone}">
                           placeholder="Nhập số điện thoại"
                        </c:when>

                        <c:otherwise>
                           value="<c:out value="${userPhone}"/>"
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty phoneError}">
                        style="border-color: red"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${phoneError}"/></td>
            </tr>

            <tr>
                <td>Email:</td>
                <td>
                    <input type="email" name="userEmail"
                    <c:choose>
                        <c:when test="${empty userEmail}">
                           placeholder="Nhập địa chỉ email"
                        </c:when>

                        <c:otherwise>
                           value="<c:out value="${userEmail}"/>"
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty emailError}">
                        style="border-color: red"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${emailError}"/></td>
            </tr>

            <tr>
                <td>Mật khẩu:</td>
                <td>
                    <input type="password" name="password" placeholder="Nhập mật khẩu độ dài từ 10 đến 50 kí tự, bao gồm chữ hoa, chữ thường và chữ số"
                    <c:if test="${not empty passwordError}">
                        style="border-color: red"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${passwordError}"/></td>
            </tr>

            <tr>
                <td>Nhập lại mật khẩu:</td>
                <td>
                    <input type="password" name="retypePassword" placeholder="Nhập lại mật khẩu"
                    <c:if test="${not empty retypePasswordError}">
                        style="border-color: red"
                    </c:if>
                    required>
                </td>
                <td><c:out value="${retypePasswordError}"/></td>
            </tr>
        </table>

        <input type="submit" style="text-align: center; background-color: #60150c; text-decoration-color: white" value="ĐĂNG KÝ">
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
