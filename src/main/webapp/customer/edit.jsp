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
    <title>Edit Customer</title>
</head>
<body>
<h1>Edit Customer</h1>
<p>
    <c:if test='${message!=null}'>
    <span class="message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/customers">Back to Customer List</a>
</p>
<form method="post">
    <fieldset>
        <legend>Customer information</legend>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" id="name" value='${requestScope["customer"].getName()}'></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" id="email" value="${customer.getEmail()}"></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" id="address" value="${customer.getEmail()}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update customer"></td>
        </tr>

    </table>
</fieldset>
</form>
</body>
</html>
