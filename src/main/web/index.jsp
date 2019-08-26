<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="color: teal; font-size: 20px">Login Form</div>
<form method="post" action="index">
    <table>
        <tr>
            <td>Login :</td>
            <td><input type="text" name="login" />
            </td>
        </tr>

        <tr>
            <td>Password :</td>
            <td><input type="password" name="password" />
            </td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Login">
            </td>
        </tr>
    </table>
    <c:if test="<c:out value='${check}'/>" >
        <p style="color: crimson">Неправильный логин или пароль</p>
    </c:if>
    <a href="/reg" class=""> Registration </a>
    <a href="/admin" class=""> list </a>

</form>
</body>
</html>
