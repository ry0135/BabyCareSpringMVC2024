<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">


<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Preferential</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f9f9f9;
      color: #333;
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      margin: 0;
    }

    h1 {
      color: #4CAF50;
      text-align: center;
    }

    form {
      background-color: #fff;
      padding: 20px 30px;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      max-width: 500px;
      width: 100%;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 15px;
    }

    th, td {
      text-align: left;
      padding: 8px;
      border-bottom: 1px solid #ddd;
    }

    .preferentialImg {
      border-radius: 4px;
      border: 1px solid #ddd;
      padding: 2px;
      width: 100px;
    }

    label {
      display: block;
      margin: 10px 0 5px;
      font-weight: bold;
    }

    input[type="number"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    button {
      background-color: #4CAF50;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
      margin-top: 15px;
    }

    button:hover {
      background-color: #45a049;
    }
  </style>
</head>

<body style="    display: block;">
<div style="display: block">

  <jsp:include page="/WEB-INF/views/include/header.jsp" />
<form action="${pageContext.request.contextPath}/preferential/update" method="post">
  <h1>Update Quantity</h1>
  <table>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Description</th>
      <th>Image</th>
    </tr>
    <tr>
      <td>${preferential.preferentialCode}</td>
      <td>${preferential.preferentialName}</td>
      <td>${preferential.preferentialDescription}</td>
      <td>
        <img src="${pageContext.request.contextPath}/image/${preferential.preferentialImg}"
             class="preferentialImg"
             alt="Preferential Image">
      </td>
    </tr>
  </table>

  <input type="hidden" name="preferentialCode" value="${preferential.preferentialCode}" />

  <label for="quantity">Quantity:</label>
  <input type="number" id="quantity" name="quantity" value="${preferential.quantity}" required />

  <button type="submit">Update</button>
</form>
</div>
</body>
</html>
