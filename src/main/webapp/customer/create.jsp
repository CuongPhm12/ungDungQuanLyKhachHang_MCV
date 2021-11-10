<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 10/11/2021
  Time: 8:31 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new Customer</title>
    <style>
        message{
            color: green;
        }
    </style>
</head>
<body>
<h1>Create new Customer</h1>
<p>
    <c:if test='${message!=null}'>
        <span class = "message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/customers">Back to Customer list</a>
</p>
<form method="post">
<fieldset>
    <legend>Customer information</legend>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" id="email"></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="name" id="address"></td>
        </tr>
<tr>
            <td></td>
            <td><input type="submit" value = "Create customer"></td>
        </tr>
    </table>
</fieldset>
</form>
</body>
</html>
