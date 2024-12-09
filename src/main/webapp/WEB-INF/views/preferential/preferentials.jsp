<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Preferentials</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
<h1>Preferential List</h1>
<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Description</th>
    <th>Image</th>
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
<%--        <img src="${preferential.preferentialImg}" alt="Image" width="100">--%>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
