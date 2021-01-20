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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
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
<%
    String userType = (String) request.getSession().getAttribute("userType");
    if (userType == null)
    {
        request.getSession().setAttribute("userType", "Customer");
    }
%>
<div align="center">
    <h1 id="form-title">ĐĂNG KÝ TÀI KHOẢN</h1>
    <form id="frmSignup" method="post" action="${pageContext.request.contextPath}/signup">
        <table>
            <tr>
                <td class="row-name">Họ và tên:</td>
                <td>
                    <input class="text textbox" type="text" name="userFullname"
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
                    <br>
                    <p class="error-message"><c:out value="${fullnameError}"/></p>
                </td>
            </tr>

            <tr>
                <td class="row-name">Giới tính:</td>
                <td>
                    <label class="rdbGender" style="float: left">Nam
                        <input type="radio" name="userGender" value="Nam" <c:if test="${empty userGender || userGender == 'Nam'}">checked</c:if>>
                        <span class="checkmark"></span>
                    </label>
                    <label class="rdbGender" style="float: right; margin-right: 68%;">Nữ
                        <input type="radio" name="userGender" value="Nữ" <c:if test="${userGender == 'Nữ'}">checked</c:if>>
                        <span class="checkmark"></span>
                    </label>
                </td>
            </tr>

            <tr>
                <td class="row-name">Ngày sinh:</td>
                <td>
                    <input class="text datetime" type="date" name="userBirthdate"
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
                    <br>
                    <p class="error-message"><c:out value="${birthdateError}"/></p>
                </td>
            </tr>

            <tr>
                <td class="row-name">Địa chỉ:</td>
                <td class="info">
                    <textarea class="text" name="userAddress" rows="3" cols="50"
                            <c:if test="${empty userAddress}">
                                placeholder="Nhập địa chỉ"
                            </c:if>

                        <c:if test="${not empty addressError}">
                            style="border-color: red"
                        </c:if>
                              required><c:out value="${userAddress}"/></textarea>
                    <br>
                    <p class="error-message"><c:out value="${addressError}"/></p>
                </td>
            </tr>

            <tr>
                <td class="row-name">Điện thoại:</td>
                <td class="info">
                    <input class="text textbox" type="text" name="userPhone"
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
                    <br>
                    <p class="error-message"><c:out value="${phoneError}"/></p>
                </td>
            </tr>

            <tr>
                <td class="row-name">Email:</td>
                <td class="info">
                    <input class="text textbox" type="email" name="userEmail"
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
                    <br>
                    <p class="error-message"><c:out value="${emailError}"/></p>
                </td>
            </tr>
        </table>

        <input id="btnSignup" type="submit" value="ĐĂNG KÝ">
        <br>

        <c:choose>
            <c:when test="${userType == 'Manager'}">
                <a style="color: #007bff;" href="qlhome.jsp">Quay về trang chủ</a>
            </c:when>

            <c:otherwise>
                <a style="color: #007bff;" href="index.jsp">Quay về trang chủ</a>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<div id="footer">
    <p style="text-align: center">
        <b> NhomHQNT 2020 - Quan Com Online Unifood </b>
    </p>
</div>
</body>
</html>