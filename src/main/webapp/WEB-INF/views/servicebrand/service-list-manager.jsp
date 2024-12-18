<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<style>

  #pagination {
    margin-top: 10px;
  }

  .paging-button {
    display: inline-block;
    padding: 5px 10px;
    border: 1px solid #ccc;
    background-color: #f7f7f7;
    cursor: pointer;
    text-decoration: none;
  }

  .paging-button.active {
    background-color: #ccc;
  }

</style>
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link active " href="#">Danh sách dịch vụ</a>
  <a class="nav-item nav-link " href="service-add">Thêm dịch vụ mới</a>
</nav>
<div class="container mt-3">
  <h2>Quản lí danh sách dịch vụ </h2>
  <p>Cộng tác viên có thể xem được danh sách dịch vụ  ở đây</p>
  <input class="form-control" id="myInput" type="text" placeholder="Tìm kiếm">
  <br>
  <table class="table table-bordered" id="data-table">
    <thead>
    <tr>
      <th>Service ID</th>
      <th>Name</th>
      <th>Price</th>
      <th>Description</th>
      <th>Type ID</th>
      <th>Ảnh</th>
    </tr>
    </thead>
    <tbody id="myTable">

    <c:forEach var="service" items="${serviceList}">
      <tr>
        <td>${service.serviceID}</td>
        <td>${service.serviceName}</td>
        <td>${service.servicePrice}</td>
        <td>${service.description}</td>
        <td>${service.typeId}</td>

<%--        <td>${(service.status==1)?"Đang bán":"Ngừng bán"}</td>--%>
        <td><img style="width: 50%" src="pageContext.request.contextPath}/assets/img/${service.listImg}" alt="${service.serviceID}"></td>
        <td>
          <a class="btn btn-success" href="${pageContext.request.contextPath}/updateService?serviceID=${service.serviceID}">Cập nhật</a>
        </td>
        <td><a class="btn btn-success" href="deleteService?serviceID=${service.serviceID}">xóa</a></td>

      </tr>
    </c:forEach>

    </tbody>
  </table>


</div>
<div id="pagination" class="text-center"></div>


<script>
  $(document).ready(function () {
    $("#myInput").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#myTable tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>
<script>
  var currentPage = 1;
  var recordsPerPage = 10;
  var table = document.getElementById("data-table");
  var rows = table.getElementsByTagName("tr");
  var totalPage = Math.ceil(rows.length / recordsPerPage);

  function showTable(page) {
    var start = (page - 1) * recordsPerPage;
    var end = start + recordsPerPage;

    for (var i = 0; i < rows.length; i++) {
      if (i >= start && i < end) {
        rows[i].style.display = "table-row";
      } else {
        rows[i].style.display = "none";
      }
    }
  }

  function createPagination() {
    var pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    for (var i = 1; i <= totalPage; i++) {
      var button = document.createElement("a");
      button.href = "#";
      button.innerHTML = i;

      if (i === currentPage) {
        button.classList.add("paging-button", "active");
      } else {
        button.classList.add("paging-button");
      }

      button.addEventListener("click", function () {
        currentPage = parseInt(this.innerHTML);
        showTable(currentPage);

        var currentButton = document.querySelector(".paging-button.active");
        currentButton.classList.remove("active");

        this.classList.add("active");
      });

      pagination.appendChild(button);
    }
  }

  showTable(currentPage);
  createPagination();

</script>


