<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>BabyCare - Daycare Website Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@600;700&family=Montserrat:wght@200;400;600&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/lib/animate/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>

<body>
<header>
    <%@include file="include/header.jsp" %>
</header>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/wow/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/easing/easing.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/waypoints/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/lightbox/js/lightbox.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
