<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 10/11/2021
  Time: 8:32 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Customer</title>
</head>
<body>
<h1>Customer details</h1>
<p>
    <a href="/customers">Back to customer list</a>
</p>
<table border="1">
    <tr>
        <td>Name:</td>
        <td>"${customer.getName()}"</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>"${customer.getEmail()}"</td>
    </tr>
    <tr>
        <td>Address:</td>
        <td>"${customer.getAddress()}"</td>
    </tr>

</table>
</body>
</html>
