<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BabyCare</title>
    <style>
        a {
            text-decoration: none;  /* Không gạch chân */

            transition: color 0.3s; /* Hiệu ứng chuyển màu mượt mà */
        }
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: white; /* Màu nền nhẹ nhàng */
        }

        header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 20px;
            background-color: #f8d9e1; /* Màu nền cho header */

        }

        .logo {
            font-size: 2em;
            font-weight: bold;
            color: #ff4c80; /* Màu hồng cho logo */

        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 20px; /* Khoảng cách giữa các mục menu */
        }

        nav a {
            text-decoration: none;
            color: #555; /* Màu xám cho liên kết */
            font-weight: bold;
            font-size: 18px;

        }

        nav a:hover {
            color: #ff1a5e; /* Màu khi hover */
        }

        .login-btn {
            padding: 10px 20px;
            background-color: #ff4c80; /* Màu nền cho nút đăng nhập */
            color: white;
            border: none;
            cursor: pointer;
            font-weight: bold;
            width: 160px;
            height: 50px;
            margin-right: 90px;
        }

        .login-btn:hover {
            background-color: #ff1a5e; /* Màu nền khi hover */
        }
    </style>
</head>

<body>
<header>
    <jsp:include page="include/header.jsp" />
</header>
</body>
</html>
