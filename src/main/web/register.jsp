<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<div style="color: teal; font-size: 20px">Registration Form</div>
<form method="post" action="reg">
    <table>
        <tr>
            <td>Login :</td>
            <td><input type="text" name="login"/>
            </td>
        </tr>

        <tr>
            <td>Password :</td>
            <td><input type="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td>Email :</td>
            <td><input type="email" name="email"/>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Save">
            </td>
        </tr>
    </table>
    <a href="/admin" class=""> list </a>
    <a href="/index" class=""> login</a>
</form>
</body>
</html>
