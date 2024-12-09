<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Thêm Dịch Vụ</title>
</head>
<body>
<h2>Thêm Dịch Vụ</h2>
<form action="/addservice" method="post">
  <label for="serviceName">Tên dịch vụ:</label>
  <input type="text" id="serviceName" name="serviceName" required><br><br>

  <label for="servicePrice">Giá dịch vụ:</label>
  <input type="number" id="servicePrice" name="servicePrice" required><br><br>

  <label for="listImg">URL hình ảnh:</label>
  <input type="text" id="listImg" name="listImg"><br><br>

  <label for="description">Mô tả:</label>
  <textarea id="description" name="description"></textarea><br><br>

  <button type="submit">Thêm Dịch Vụ</button>
</form>

<div th:if="${message}" th:text="${message}" style="color: green;"></div>
</body>
</html>