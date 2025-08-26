
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thêm dịch vụ</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">
  <style>
    body {
      background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
      height: 100vh;
      justify-content: center;
      align-items: center;
    }
    h2 {
      margin-bottom: 30px;
    }
    .invalid-feedback {
      display: none;
    }
    .input-group {
      margin-bottom: 20px;
    }
    .form-control {
      border-radius: 0.25rem;
    }
    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
      transition: background-color 0.3s ease, border-color 0.3s ease;
    }
    .btn-primary:hover {
      background-color: #0056b3;
      border-color: #0056b3;
    }
  </style>
</head>
<body>
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link" href="service-list-manager">Danh sách sản phẩm</a>
  <a class="nav-item nav-link active" href="#">Thêm sản phẩm mới</a>
</nav>


<div class="container">
  <form class="needs-validation" novalidate action="service-add" method="post" enctype="multipart/form-data">
    <div class="col-12">
      <h2>Thêm dịch vụ mới</h2>
    </div>
    <div class="row">
      <div class="input-field col s6">
        <div class="input-group">
          <input pattern="^.{1,150}$" type="text" class="form-control" id="ServiceName" placeholder="Tên dịch vụ" name="serviceName" aria-describedby="inputGroupPrepend" required>
          <div class="invalid-feedback">
            Tối đa từ 1-150 ký tự
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="input-field col s6">
        <div class="input-group">
          <input type="text" pattern="^\d{1,8}(.\d{1,2})?$" class="form-control" id="ServicePrice" placeholder="Giá dịch vụ" name="servicePrice"aria-describedby="inputGroupPrepend" required>
          <div class="invalid-feedback">
            Giá tiền chỉ bao gồm chữ số
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="input-field col s6">
        <div class="input-group">
          <input type="text" class="form-control" id="Description" placeholder="Mô tả dịch vụ" name="description" aria-describedby="inputGroupPrepend">
        </div>
      </div>
    </div>
    <div class="row">
      <div class="input-field col s6">
        < <label for="typeId">Loại Dịch Vụ:</label>
        <select class="form-select" id="TypeId" name="typeId" required>
          <option value="" disabled selected>Chọn loại dịch vụ</option>
          <c:forEach var="category" items="${serviceTypes}">
            <option value="${category.typeID }">
                ${category.typeName}
            </option>
          </c:forEach>
        </select>
      </div>

    </div>
    <div class="row">
      <div class="input-field col s6">
        <%--@declare id="listimg"--%><label for="listImg">Đường dẫn ảnh</label>
        <div class="input-group">
          <input id="avatar" type="file" class="form-control" name="listImg" value="" required>
        </div>
      </div>
    </div>
    <div class="row mt-3">
      <div class="col-3">
        <button class="btn btn-primary" type="submit">Thêm</button>
      </div>
      <div class="col-6">${thongbao}</div>
    </div>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
  (function () {
    'use strict';
    window.addEventListener('load', function () {
      var forms = document.getElementsByClassName('needs-validation');
      var validation = Array.prototype.filter.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();
</script>
</body>
</html>
