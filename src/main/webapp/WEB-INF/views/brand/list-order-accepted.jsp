<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!DOCTYPE html>
<html>
<head>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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

  .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
    color: white;

    background-color: #ff4880;
  }


</style>
<div class="container ">
  <%@include file="sidebar_brand.jsp" %>
  <div class="  col-lg-11" id="basic-info" style="margin-left: 25px; margin-top: 25px;">
    <nav class="nav nav-pills nav-justified">
      <a class="nav-item nav-link " href="order-list-manager">Đơn hàng đang chờ</a>
      <a class="nav-item nav-link active" style=" background-color: #ff4880; " href="#">Đơn hàng đã xác nhận</a>
      <a class="nav-item nav-link " href="list-order-paid">Đơn hàng đã thanh toán</a>
      <a class="nav-item nav-link " href="list-order-succsess">Đơn hàng đã hoàn thành</a>
      <a class="nav-item nav-link " href="list-order-comment-success">Đơn hàng đã đánh giá</a>
      <a class="nav-item nav-link  " href="list-order-cancel">Đơn hàng đã hủy</a>

    </nav>


    <h2>Quản lí danh sách đặt hàng</h2>
    <p>Nhân viên có thể xem được danh sách đặt hàng ở đây</p>
    <input class="form-control" id="myInput" type="text" placeholder="Tìm kiếm">
    <br>
    <h3 class="text-center text-danger" >${thongbao}</h3>
    <table class="table table-bordered" id="data-table">
      <thead>
      <tr>
        <th>Mã số đơn hàng</th>

        <th>Tên khách hàng</th>
        <th>Địa chỉ</th>
        <th>Ngày đặt</th>
        <th>Mã giảm giá</th>
        <th>Trạng thái</th>
        <th></th>
        <th></th>
      </tr>
      </thead>
      <tbody id="myTable">

      <c:forEach var="order" items="${listOrder}">
        <tr>
          <td><a href="getordereddetail?orderId=${order.idOrder}&discountCode=${order.discountId}">${order.idOrder}</a></td>


          <td>${order.username}</td>
          <td>${order.address}</td>
          <td>${order.date}</td>
          <td>${order.discountId}</td>
          <td>${order.orderStatus}</td>
          <td><a href="paid?id=${order.idOrder}" class="btn btn-success">Đã Thanh Toán</a></td>
          <td><a href="cancelorder?id=${order.idOrder}" class="btn btn-danger">Hủy</a></td>

        </tr>

      </c:forEach>

      </tbody>
    </table>


  </div>


</div>
<div id="pagination" class="text-center"></div>
</div>
</body>
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

</html>
