<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
</head>
<body>
<form method="post" action="/edit">
    <div style="color: teal; font-size: 20px">Edit user</div>
    <a href="/reg">New User</a>
    <table>
        <tr>
            <td>ID :</td>
            <td>${user.id}
                <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
            </td>
        </tr>
        <tr>
            <td>Login :</td>
            <td><input type="text" name="login" value="<c:out value="${user.login}"/>"/>
            </td>
        </tr>

        <tr>
            <td>Password :</td>
            <td><input type="password" name="password" value="<c:out value="${user.password}"/>"/>

            </td>
        </tr>
        <tr>
            <td>Email :</td>
            <td><input type="email" name="email" value="<c:out value="${user.email}"/>"/>

            </td>
        </tr>
        <tr>
            <td>Role :</td>
            <td><input type="text" name="role" value="<c:out value="${user.role}"/>"/>
            </td>
        </tr>
        <tr>
            <td>Rating :</td>
            <td><input type="number" name="rating" value="<c:out value="${user.rating}"/>"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>

                <input type="submit" value="Save">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/admin" type="submit">cancel</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
