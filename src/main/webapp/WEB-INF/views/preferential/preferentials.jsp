<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Preferentials</title>
  <style>
    /* CSS trực tiếp */

    /* Đặt nền và kiểu chữ mặc định */
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      margin: 0;
      padding: 0;
      line-height: 1.6;
      color: #333;
    }

    /* Tiêu đề */
    h1 {
      text-align: center;
      margin: 20px 0;
      color: #007bff;
      font-size: 2em;
    }

    /* Bảng */
    table {
      width: 90%;
      margin: 20px auto;
      border-collapse: collapse;
      background: #ffffff;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }

    th, td {
      padding: 12px 15px;
      text-align: left;
      border: 1px solid #ddd;
    }

    th {
      background-color: #007bff;
      color: #ffffff;
      text-transform: uppercase;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #d9edf7;
      cursor: pointer;
    }

    td img {
      display: block;
      margin: 0 auto;
      border-radius: 4px;
    }

    /* Nút xóa */
    a.btn-danger {
      display: inline-block;
      padding: 8px 12px;
      color: #fff;
      background-color: #dc3545;
      text-decoration: none;
      border-radius: 5px;
      font-size: 0.9em;
    }

    a.btn-danger:hover {
      background-color: #c82333;
    }

    /* Đáp ứng */
    @media (max-width: 768px) {
      table {
        width: 100%;
        font-size: 0.9em;
      }
      h1 {
        font-size: 1.5em;
      }
    }
  </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<h1>Preferential List</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Description</th>
    <th>Image</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="preferential" items="${preferentialList}">
    <tr>
      <td>${preferential.preferentialCode}</td>
      <td>${preferential.preferentialName}</td>
      <td>${preferential.preferentialDescription}</td>
      <td>
        <img src="${pageContext.request.contextPath}/image/${preferential.preferentialImg}" class="preferentialImg" alt="Ảnh MGG" width="100">
      </td>
      <td>
        <!-- Delete button -->
        <a href="${pageContext.request.contextPath}/preferential/delete/${preferential.preferentialCode}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this preferential?');">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>