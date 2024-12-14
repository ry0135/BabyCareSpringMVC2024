<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Product Management</title>
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
<h1>Product Management</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Description</th>
    <th>View More</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="product" items="${productstatus}">
    <tr>
      <td>${product.productId}</td>
      <td>${product.productName}</td>
      <td>${product.productDescription}</td>
      <td><a href="${pageContext.request.contextPath}/getProductDetail?id=${product.productId}" class="product-item-link">View</a></td>

      <td>
        <!-- Nếu trạng thái là "khóa", hiển thị nút mở khóa -->
        <c:if test="${product.status == 0}">
          <a href="${pageContext.request.contextPath}/product/unlock/${product.productId}" class="btn-unlock"
             onclick="return confirm('Are you sure you want to unlock this product?');">Unlock Status</a>
        </c:if>
        <!-- Nếu trạng thái khác, hiển thị nút khóa -->
        <c:if test="${product.status != 0 and not empty product.productId}">
          <a href="${pageContext.request.contextPath}/product/lock/${product.productId}" class="btn-lock"
             onclick="return confirm('Are you sure you want to lock this product?');">Lock Status</a>
        </c:if>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
