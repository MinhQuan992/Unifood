<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 1/20/2021
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Ajax with Jquery Simple Example</h1>
<h3>madushankaperera.wordpress.com</h3>
<br>
<form name="form1" method="GET" action="${pageContext.request.contextPath}/AjaxAPI" id="form1">
    <table>
        <tr>
            <td>Number 1</td><td><input type="text" name="n1"/></td>
        </tr>
        <tr>
            <td>Number 2</td><td><input type="text" name="n2"/></td>
        </tr>
        <tr>
            <td></td><td><input type="submit" value="Calculate"/></td>
        </tr>
        <tr>
            <td>Result</td><td><input type="text" value="" id="result"/></td>
        </tr>
    </table>
</form>
</body>
</html>

<script src="https://code.jquery.com/jquery-1.10.2.js"
        type="text/javascript"></script>

<script type="text/javascript">

    var form = $('#form1');
    form.submit(function () {

        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            success: function (data) {
                var result=data;
                $('#result').attr("value",result);

            }
        });

        return false;
    });
</script>
