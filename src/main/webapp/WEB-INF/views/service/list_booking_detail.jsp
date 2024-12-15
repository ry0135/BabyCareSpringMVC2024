<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<div class="container ">
  <%@include file="../servicebrand/serband_service.jsp" %>
  <div class="  col-lg-11" id="basic-info" style="margin-left: 25px; margin-top: 25px;">
    <nav class="nav nav-pills nav-justified">
      <a class="nav-item nav-link " href="getAllBooking">Đơn hàng đang chờ</a>
      <a class="nav-item nav-link " href="listbookingAccept">Đơn hàng đã xác nhận</a>

      <a class="nav-item nav-link " href="list-service_succsess">Đơn hàng đã hoàn thành</a>
      <a class="nav-item nav-link " href="list-service-comment-success">Đơn hàng đã đánh giá</a>
      <a class="nav-item nav-link  " href="list-service-cancel">Đơn hàng đã hủy</a>

    </nav>


    <h2>Chi Tiết Dịch Vụ</h2>
    <p>Nhân viên có thể xem được chi tiếc đặt dịch vụ ở đây</p>
    <input class="form-control" id="myInput" type="text" placeholder="Tìm kiếm">
    <br>
    <h3 class="text-center text-danger" >${thongbao}</h3>
    <form action="bookingHandler" method="post" class="form-horizontal">
      <div class="form-group">
        <label for="serviceName" class="col-sm-2 control-label">Dịch Vụ</label>
        <div class="col-sm-10">
          <input type="text" id="serviceName" name="serviceName" class="form-control" value="${order.serviceName}" readonly>
        </div>
      </div>
      <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Tên khách hàng</label>
        <div class="col-sm-10">
          <input type="text" id="name" name="name" class="form-control" value="${order.name}" readonly>
        </div>
      </div>
      <div class="form-group">
        <label for="address" class="col-sm-2 control-label">Địa chỉ</label>
        <div class="col-sm-10">
          <input type="text" id="address" name="address" class="form-control" value="${order.address}" readonly>
        </div>
      </div>
      <div class="form-group">
        <label for="bookingDate" class="col-sm-2 control-label">Ngày đặt</label>
        <div class="col-sm-10">
          <input type="text" id="bookingDate" name="bookingDate" class="form-control" value="${order.bookingDate}" readonly>
        </div>
      </div>
      <div class="form-group">
        <label for="phoneNumber" class="col-sm-2 control-label">ĐT</label>
        <div class="col-sm-10">
          <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${order.phoneNumber}" readonly>
        </div>
      </div>
      <div class="form-group">
        <label for="sex" class="col-sm-2 control-label">Bé</label>
        <div class="col-sm-10">
          <input type="text" id="sex" name="sex" class="form-control" value="${order.sex}" readonly>
        </div>
      </div>
      <div class="form-group">
        <label for="slot" class="col-sm-2 control-label">Slot</label>
        <div class="col-sm-10">
          <input type="text" id="slot" name="slot" class="form-control" value="${order.slot}" readonly>
        </div>
      </div>

      <div class="form-group">
        <label for="note" class="col-sm-2 control-label">Note</label>
        <div class="col-sm-10">
          <input type="text" id="note" name="note" class="form-control" value="${order.note}" readonly>
        </div>
      </div>
      <div class="form-group" style="margin: 20px">
        <div class="col-sm-offset-2 col-sm-10">
          <a href="acceptbooking?id=${order.bookingID}" class="btn btn-success">Xác nhận</a>
          <a href="cancelbooking?id=${order.bookingID}" class="btn btn-danger">Hủy</a>
        </div>
      </div>
    </form>



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
