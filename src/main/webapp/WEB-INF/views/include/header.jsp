
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
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
</head>

<%--<body>--%>
<nav class="navbar navbar-light navbar-expand-xl py-3" style="width: 100%;background-color: #f8d9e1">
  <a href="index.jsp" class="navbar-brand" style="margin-left: 90px;"><h1 style="color: #ff4880;">Baby<span style="color: #4d65f9;">Care</span></h1></a>
  <div class="collapse navbar-collapse" id="navbarCollapse">
    <div class="navbar-nav mx-auto" style="font-family: Arial; ">
      <nav>
        <ul>
          <li><a href="#">Home</a></li>
          <li><a href="service">Dịch Vụ</a></li>
          <li><a href="products">Sản Phẩm</a></li>
          <li><a href="#">Mã Giảm Giá</a></li>
          <c:if test="${sessionScope.account.role == 4 }">
            <li><a href="sellChanel">Kênh Người Bán</a></li>
          </c:if>
          <li><a href="registerctv">Đăng kí CTV</a></li>
          <li><a href="cart">Giỏ Hàng</a></li>
        </ul>
      </nav>

      <div class="d-flex me-4">
        <c:if test="${sessionScope.account == null}">
          <a href="login" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Đăng nhập<i
                  class="bi bi-arrow-right"></i></a>
        </c:if>
        <c:if test="${sessionScope.account!=null}">



          <c:if test="${sessionScope.account.role == 4 }">
            <div class="wallet-section ms-4" style="margin-top: 15px">
              <i class="bi bi-wallet wallet-icon"></i>
              <div class="dropdown d-inline">
                <c:if test="${not empty sessionScope.revenue}">
                  <a href="#" class="wallet-label dropdown-toggle" id="walletDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                    Ví tiền ${revenue} vnđ
                  </a>
                </c:if>


                <ul class="dropdown-menu " style="background-color: #ffecf2" aria-labelledby="walletDropdown">
                  <li><a class="dropdown-item" href="transaction">Lịch sử giao dịch</a></li>
                  <li><a class="dropdown-item" href="withdraw">Yêu Cầu Rút Tiền</a></li>
                </ul>
              </div>
            </div>

          </c:if>
          <div class="nav-item dropdown" >
            <div class=" d-flex align-items-center" data-bs-toggle="dropdown">
              <img class="img-avatar text-white ms-lg-5" style="width: 50px; height: 83%; border-radius: 50%; margin-top: 5px;" src="${pageContext.request.contextPath}/image/${sessionScope.account.avatar}" alt="">
              <a href="#" class="nav-link bg-primary text-white px-5 ms-lg-3">
                  ${sessionScope.account.firstname} ${sessionScope.account.lastname}
              </a>
            </div>
            <div class="dropdown-menu m-0" style="left: 131px;">
              <a href="profile" class="dropdown-item">Quản lí thông tin cá nhân</a>



              <c:if test="${sessionScope.account.role  == 5}">

                <a href="preferential-list-manager"  class="dropdown-item">Quản lí mã giảm giá</a>
                <a href="getAllBooking" class="dropdown-item">Quản lí đơn dịch vụ</a>
                <a href="service-add.jsp" class="dropdown-item">Quản lí dịch vụ</a>
                <a href="category-add.jsp" class="dropdown-item">Quản lí danh mục</a>

              </c:if>
              <c:if test="${sessionScope.account.role  == 4}">
                <a href="product-list-manager" class="dropdown-item">Quản lí sản phẩm</a>
                <a href="order-list-manager" class="dropdown-item">Quản lí đơn hàng</a>
                <a href="ListBookingCustomerIDServlet" class="dropdown-item">Quản lí dich vụ</a>
                <a href="StatisticCTV" class="dropdown-item">Quản lí doanh thu</a>
                <a href="getorderhistoryservlet" class="dropdown-item">Lịch sử đặt hàng</a>



              </c:if>

              <c:if test="${sessionScope.account.role == 1}">
                <a href="manage-emp-account" class="dropdown-item">Quản lí tài khoản nhân viên</a>
                <a href="manage-cus-account" class="dropdown-item">Quản lí tài khoản khách hàng</a>
                <a href="statistics"  class="dropdown-item">Thống kê bán hàng</a>
              </c:if>

              <c:if test="${sessionScope.account.role  == 3}">
                <a href="byCustomerID" class="dropdown-item">Lịch sử đặt hàng dịch vụ</a>
                <a href="getorderhistoryservlet" class="dropdown-item">Lịch sử đặt hàng</a>
              </c:if>

              <a href="logout" class="dropdown-item text-danger">Đăng xuất</a>
            </div>
          </div>
        </c:if>
      </div>
    </div>
  </div>
</nav>


<%--</body>--%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/wow/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/easing/easing.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/waypoints/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/lightbox/js/lightbox.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/owlcarousel/owl.carousel.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/js/bootstrap.min.js"></script>
<!-- Template Javascript -->
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</html>
