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
<div class="container ">
    <%@include file="../servicebrand/serband_service.jsp" %>
    <div class="  col-lg-11" id="basic-info" style="margin-left: 25px; margin-top: 25px;">
        <nav class="nav nav-pills nav-justified">
            <a class="nav-item nav-link " href="getAllBooking">Đơn hàng đang chờ</a>
            <a class="nav-item nav-link  " href="listbookingAccept">Đơn hàng đã xác nhận</a>
            <a class="nav-item nav-link "href="list-service_succsess">Đơn hàng đã hoàn thành</a>

            <a class="nav-item nav-link " href="list-order-comment-success">Đơn hàng đã đánh giá</a>
            <a class="nav-item nav-link active" style=" background-color: #ff4880; "  href="list-service-cancel">Đơn hàng đã hủy</a>

        </nav>


        <h2>Quản lí danh sách đặt dịch vụ</h2>
        <p>Nhân viên có thể xem được danh sách đặt dịch vụ ở đây</p>
        <input class="form-control" id="myInput" type="text" placeholder="Tìm kiếm">
        <br>
        <h3 class="text-center text-danger" >${thongbao}</h3>
        <table class="table table-bordered" id="data-table">
            <thead>
            <tr>
                <%--        <th>Mã số đơn hàng</th>--%>
                <th>Dịch Vụ</th>
                <th>Tên khách hàng</th>
                <th>Địa chỉ</th>
                <th>Ngày đặt</th>
                <th>Slot</th>
                <th>Trạng thái</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody id="myTable">

            <c:forEach var="order" items="${list}">
                <tr>
                        <%--          <td><a href="getordereddetail?orderId=${order.idOrder}&discountCode=${order.discountId}">${order.idOrder}</a></td>--%>


                    <td>${order.serviceName}</td>
                    <td>${order.name}</td>

                    <td>${order.address}</td>
                    <td>${order.bookingDate}</td>
                    <td>${order.slot}</td>
                    <td>
                        <c:choose>
                        <c:when test="${order.bookingStatus == 0}">Dịch vụ đã bị hủy</c:when>
                        <c:when test="${order.bookingStatus == 1}">Đang xử lý...</c:when>
                        <c:when test="${order.bookingStatus == 2}">Đặt Thành Công</c:when>
                        <c:when test="${order.bookingStatus == 3}">Đã Hoàn Thành</c:when>
                        </c:choose>

                    <td><a href="acceptbooking?id=${order.bookingID}" class="btn btn-success">Xác nhận</a></td>
                    <td><a href="cancelbooking?id=${order.bookingID}" class="btn btn-danger">Hủy</a></td>

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
