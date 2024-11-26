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
    <a href="index.jsp" class="navbar-brand" style="margin-left: 90px;"><h1 style="color: #ff4880;">Baby<span style="color: #4d65f9;">Care</span></h1></a>
    <nav>
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">Dịch Vụ</a></li>
            <li><a href="#">Sản Phẩm</a></li>
            <li><a href="#">Mã Giảm Giá</a></li>
            <li><a href="#">Đăng kí CTV</a></li>
            <li><a href="#">Giỏ Hàng</a></li>
        </ul>
    </nav>
    <c:if test="${sessionScope.account==null}">
        <a href="login" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Đăng nhập<i
                class="bi bi-arrow-right"></i></a>
    </c:if>
</header>
</body>
</html>
