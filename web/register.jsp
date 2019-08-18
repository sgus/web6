<%--
  Created by IntelliJ IDEA.
  User: Так
  Date: 16.08.2019
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
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
              <td><input type="text" name="login" />
              </td>
          </tr>

        <tr>
          <td>Password :</td>
          <td><input type="password" name="password" />
          </td>
        </tr>
          <tr>
              <td>Email :</td>
              <td><input type="email" name="email" />
              </td>
          </tr>

          <tr>
              <td> </td>
              <td><input type="submit" value="Save">
              </td>
          </tr>
      </table>
      <a href="/list" class=""> list </a>
  </form>
  </body>
</html>
