<%--
  Created by IntelliJ IDEA.
  User: kimph
  Date: 10/12/2024
  Time: 12:05 SA
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Refund List</title>
</head>
<body>
<h2>Refund List</h2>
<a href="refunds/new">Create New Refund</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Service Name</th>
        <th>Refund Amount</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${refunds}" var="refund">
        <tr>
            <td>${refund.RefundID}</td>
            <td>${refund.Name}</td>
            <td>${refund.ServiceName}</td>
            <td>${refund.RefundAmount}</td>
            <td>
                <a href="refunds/edit/${refund.RefundID}">Edit</a> |
                <a href="refunds/delete/${refund.RefundID}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>