<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>


    <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@600;700&family=Montserrat:wght@200;400;600&display=swap" rel="stylesheet">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
    }
    .info-definition {
        position: absolute;
        background-color: white;
        width: 70%;
        margin-top: 100px;
        margin-left: 15%;  /* Giảm khoảng cách bên trái để cân bằng */
        margin-right:  25%;  /* Giảm khoảng cách bên trái để cân bằng */
        margin-bottom: 50px; /* Tạo thêm khoảng cách phía dưới nếu cần */
        padding: 20px; /* Tạo thêm không gian bên trong phần tử */
        height: 1200px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Thêm đổ bóng để tạo độ nổi */

    }
    .shop-form .form-group {
        margin-bottom: 20px;
    }

    .shop-form label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }

    .shop-form .required {
        color: #ff4d4f;
    }

    .shop-form input[type="text"],
    .shop-form input[type="email"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .shop-form input[disabled] {
        background-color: #f5f5f5;
    }

    .error {
        color: #ff4d4f;
        font-size: 12px;
    }

    .edit-link {
        color: #1890ff;
        text-decoration: none;
        font-size: 14px;
    }

    .edit-link:hover {
        text-decoration: underline;
    }

    .form-actions {
        display: flex;
        justify-content: flex-end;
    }

    .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
    }

    .save-btn {
        background-color: #f5f5f5;
        margin-right: 10px;
    }

    .next-btn {
        background-color: #ff4880;
        color: white;
    }

    .next-btn:hover {
        background-color: #d9363e;
    }

    .timeline {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 20px 0;
        padding: 0;
        list-style-type: none;
    }

    .timeline li {
        flex: 1;
        position: relative;
        text-align: center;
    }

    .timeline li:not(:last-child)::after {
        content: '';
        position: absolute;
        top: 50%;
        left: 49%;
        height: 4px;
        width: 100%;
        background-color: #ccc;
        z-index: 1;
        transform: translateY(-50%);
    }
    .timeline li.active:not(:last-child)::after {
        background-color: #4CAF50;
    }

    .timeline .icon {
        background-color: #4CAF50;
        color: white;
        border-radius: 50%;
        padding: 10px;
        display: inline-block;
        margin-bottom: 30px;
    }

    .timeline .icon.active {
        background-color: #ff4880;
    }

    .timeline .icon.inactive {
        background-color: #ccc;
    }

    .timeline .step-title {
        font-weight: bold;
    }

    .timeline .step-date {
        color: #666;
    }

    .timeline .step {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    #newAddressInput {
        display: none;
    }
    /* Định dạng thông báo thành công */
    #success-message {
        position: absolute;
        top: 100px;
        right: 600px;
        background-color: #28a745;
        color: white;
        padding: 15px;
        border-radius: 5px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);

    }
</style>
<html>
<head>
<title>Register Shop Service</title>

    </head>
    <body>
    <h2>Information Shop </h2>

    <!-- Form đăng ký -->
    <form action="/BabyCareSpringMVC2024_war/register-shop-service" method="POST">
        <label for="brandName">Brand Name:</label>
    <input type="text" id="brandName" name="brandName"  required><br><br>

    <label for="brandDescription">Brand Description:</label>
    <textarea id="brandDescription" name="brandDescription" required></textarea><br><br>

    <<!-- Brand Logo Upload -->
        <!-- Brand Logo Upload -->
        <label for="brandLogo">Brand Logo:</label>
        <input type="file" id="brandLogo" name="brandLogo" accept="image/*" /><br>
        <!-- Show preview if available -->
        <c:if test="${not empty shopService.brandLogo}">
            <h3>Current Brand Logo:</h3>
            <img src="<c:url value='/images/${shopService.brandLogo}' />" alt="Brand Logo" style="max-width: 200px; max-height: 200px;" />
        </c:if>
        <br><br>

    <label for="brandAddress">Brand Address:</label>
    <input type="text" id="brandAddress" name="brandAddress"  required><br><br>


    <label for="bandName">Band Name:</label>
    <input type="text" id="bandName" name="bandName"  required><br><br>

    <label for="eventType">Event Type:</label>
    <input type="text" id="eventType" name="eventType"  required><br><br>

    <label for="brandPhone">Brand Phone:</label>
    <input type="text" id="brandPhone" name="brandPhone"  required><br><br>

    <button type="submit">Register</button>
    </form>
    </body>
    </html>
