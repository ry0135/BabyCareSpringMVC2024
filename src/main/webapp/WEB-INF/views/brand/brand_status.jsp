<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Brand Management</title>
  <style>
    /* CSS trực tiếp */
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      margin: 0;
      padding: 0;
      line-height: 1.6;
      color: #333;
    }

    h1 {
      text-align: center;
      margin: 20px 0;
      color: #007bff;
      font-size: 2em;
    }

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

    a.btn-unlock, a.btn-lock {
      display: inline-block;
      padding: 8px 12px;
      color: #fff;
      border-radius: 5px;
      font-size: 0.9em;
      text-decoration: none;
    }

    a.btn-unlock {
      background-color: #28a745;
    }

    a.btn-unlock:hover {
      background-color: #218838;
    }

    a.btn-lock {
      background-color: #dc3545;
    }

    a.btn-lock:hover {
      background-color: #c82333;
    }

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
<h1>Brand Management</h1>
<table>
  <thead>
  <tr>
    <th>Brand ID</th>
    <th>Brand Name</th>
    <th>Brand Description</th>
    <th>Status</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="brand" items="${brandstatus}">
    <tr>
      <td>${brand.brandID}</td>
      <td>${brand.brandName}</td>
      <td>${brand.brandDescription}</td>
      <td>${brand.status == 1 ? 'Active' : 'Locked'}</td>
      <td>
        <!-- Nếu trạng thái là "khóa", hiển thị nút mở khóa -->
        <c:if test="${brand.status == 0}">
          <a href="${pageContext.request.contextPath}/brand/unlock/${brand.brandID}" class="btn-unlock"
             onclick="return confirm('Are you sure you want to unlock this brand?');">Unlock Brand</a>
        </c:if>
        <!-- Nếu trạng thái là "mở khóa", hiển thị nút khóa -->
        <c:if test="${brand.status == 1}">
          <a href="${pageContext.request.contextPath}/brand/lock/${brand.brandID}" class="btn-lock"
             onclick="return confirm('Are you sure you want to lock this brand?');">Lock Brand</a>
        </c:if>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
