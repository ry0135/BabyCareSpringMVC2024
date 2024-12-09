<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh Sách Yêu Thích</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center mb-4">Danh Sách Yêu Thích</h2>
  <table class="table table-bordered table-striped">
    <thead class="thead-dark">
    <tr>
      <th>#</th>
      <th>Tên Dịch Vụ</th>
      <th>Giá</th>
      <th>Hành Động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${favorites}" var="favorite">
      <tr>
        <td>${favorite.favoriteID}</td>
        <td>${favorite.service.serviceName}</td>
        <td>${favorite.service.servicePrice}₫</td>
        <td>
          <a href="getservicedetail?serviceID=${favorite.serviceID}" class="btn btn-info">Xem thêm</a>
          <a href="removefavorite?favoriteID=${favorite.favoriteID}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa mục này không?');">Xóa</a>

        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="text-center mt-4">
    <a href="service" class="btn btn-secondary">Quay lại</a>
  </div>
</div>
</body>
</html>
