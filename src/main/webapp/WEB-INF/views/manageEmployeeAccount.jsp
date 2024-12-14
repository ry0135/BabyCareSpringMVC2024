<%--
  Created by IntelliJ IDEA.
  User: kimph
  Date: 10/12/2024
  Time: 10:42 SA
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/include/header-admin.jsp" />
<style>
  body {
    background-color: #f8f9fa;
  }
  .container-fluid {
    padding-top: 50px;
  }
  .container {
    background: white;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    max-width: 2000px;
    margin: auto;
    margin-top: 50px;
  }
  h2 {
    margin-bottom: 20px;
  }
  p {
    margin-bottom: 20px;
  }
  .form-control {
    border-radius: 5px;
  }
  .table {
    margin-top: 20px;
    background-color: white;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  .table thead {
    background-color: #007bff;
    color: white;
  }
  .table th, .table td {
    text-align: center;
    vertical-align: middle;
  }
  #pagination {
    margin-top: 20px;
  }
  .paging-button {
    display: inline-block;
    padding: 5px 10px;
    border: 1px solid #007bff;
    background-color: white;
    color: #007bff;
    cursor: pointer;
    text-decoration: none;
    border-radius: 5px;
    margin: 0 2px;
  }
  .paging-button.active {
    background-color: #007bff;
    color: white;
  }
</style>

<div class="container-fluid pt-5">
  <div id="page-wrapper">
    <div class="container mt-3">
      <h2>Quản lí danh sách tài khoản nhân viên</h2>
      <p>Admin có thể xem được danh sách tài khoản nhân viên ở đây</p>
      <input class="form-control" id="myInput" type="text" placeholder="Tìm kiếm">
      <br>
      <table class="table table-bordered" id="data-table">
        <thead>
        <tr>
          <th>Username</th>
          <th>ID nhân viên</th>
          <th>Họ</th>
          <th>Tên</th>
          <th>Số điện thoại</th>
          <th>Email</th>
          <th></th>
        </tr>
        </thead>
        <tbody id="myTable">
        <c:forEach var="emp" items="${listEmpAcc}">
          <tr>
            <td>${emp.username}</td>
            <td>${emp.userID}</td>
            <td>${emp.firstname}</td>
            <td>${emp.lastname}</td>
            <td>${emp.phone}</td>
            <td>${emp.email}</td>
            <c:if test="${emp.status == 1}">
              <td>
                <a href="lockemployee?empID=${emp.userID}" class="btn btn-danger">
                  <i class="fa fa-lock"></i> Khóa
                </a>
              </td>
            </c:if>
            <c:if test="${emp.status == 0}">
              <td>
                <a href="unlockemployee?empID=${emp.userID}" class="btn btn-success">
                  <i class="fa fa-unlock-alt"></i> Mở
                </a>
              </td>
            </c:if>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div id="pagination" class="text-center"></div>
    </div>
  </div>
</div>

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
