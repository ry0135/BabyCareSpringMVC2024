<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Account</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Edit Account</h1>
    <form action="/manage-emp-account/editPost" method="post"> <!-- Đường dẫn đến phương thức edit -->
        <input type="hidden" name="userID" value="${account.userID}"> <!-- Giữ lại userID -->

        <div class="form-group">
            <label>Username:</label>
            <input type="text" name="username" value="${account.username}" class="form-control" required>
        </div>

        <div class="form-group">
            <label>First Name:</label>
            <input type="text" name="firstname" value="${account.firstname}" class="form-control" required>
        </div>

        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="lastname" value="${account.lastname}" class="form-control" required>
        </div>

        <div class="form-group">
            <label>Phone:</label>
            <input type="text" name="phone" value="${account.phone}" class="form-control">
        </div>

        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" value="${account.email}" class="form-control">
        </div>

        <button type="submit" class="btn btn-success">Update Account</button>
    </form>
    <a href="/manage-emp-account" class="btn btn-secondary">Back to Account List</a>
</div>
</body>
</html>
