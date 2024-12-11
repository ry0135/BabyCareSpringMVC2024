<%--
    Document   : updateBooking-form
    Created on : May 29, 2024, 4:36:44 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
  }


  .form-group {
    margin-bottom: 15px;
  }
  .form-group label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
  }
  .form-group input, .form-group select, .form-group textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
  }
  .form-group textarea {
    resize: none;
    height: 100px;
  }
  .btn-primary {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
  }
  .btn-primary:hover {
    background-color: #0056b3;
  }
</style>


<script>
  function validateDate() {
    const bookingDate = document.getElementById('bookingDate').value;
    const today = new Date();
    const selectedDate = new Date(bookingDate);
    const twoDaysFromNow = new Date();
    twoDaysFromNow.setDate(today.getDate() + 2);

    if (selectedDate < twoDaysFromNow) {
      alert('Ngày đặt lịch phải trước ít nhất hai ngày.');
      return false;
    }
    return true;
  }
</script>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
</head>
<body>
<div class="container mt-5" style=" border-radius: 10px;
            box-shadow: 10px; margin-bottom: 20px;border: 2px solid #ccc;" >
  <h2 style="text-align: center; font-weight: 600; margin-top: 10px">Chỉnh sửa lịch dịch vụ</h2>

  <% if (request.getAttribute("errorMessage") != null) {%>
  <div class="alert alert-danger" role="alert">
    <%= request.getAttribute("errorMessage")%>
  </div>
  <% }%>

  <% if (request.getAttribute("bookedCount") != null) {
    int bookedCount = (int) request.getAttribute("bookedCount");
    if (bookedCount >= 5) { %>
  <div class="alert alert-danger" role="alert">
    <%= request.getParameter("slot") %> của <%= request.getParameter("bookingDate") %> đã bị đầy . Xin vui lòng chọn Slot khác.
  </div>
  <%    }
  } %>
<%--  <form action="UpdateBookingServlet" method="post" onsubmit="return validateDate();">--%>
<%--    <input type="hidden" name="bookingID" value="${booking.bookingID}">--%>
<%--    <input type="hidden" name="serviceID" value="${booking.serviceID}">--%>
<%--    <input type="hidden" id="price" name="price" value="${booking.price}" required>--%>
<%--    <input type="hidden" id="serviceName" name="serviceName" value="${booking.serviceName}" required>--%>

<%--    <div class="form-group">--%>
<%--      <label for="name">Tên</label>--%>
<%--      <input type="text" class="form-control" name="name" value="${booking.name}" required>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--      <label for="sex">Giới tính</label>--%>
<%--      <select class="form-control" name="sex" required>--%>
<%--        <option value="Nam" ${booking.sex == 'Nam' ? 'selected' : ''}>Nam</option>--%>
<%--        <option value="Nữ" ${booking.sex == 'Nữ' ? 'selected' : ''}>Nữ</option>--%>
<%--      </select>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--      <label for="bookingDate">Ngày</label>--%>
<%--      <input type="date" class="form-control" id="bookingDate" name="bookingDate" value="${booking.bookingDate}" required>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--      <label for="phone">Điện Thoại</label>--%>
<%--      <input type="text" class="form-control" name="phone" pattern="\d{10}" maxlength="10" value="${booking.phoneNumber}" required>--%>
<%--      <div class="invalid-feedback">Please enter a valid 10-digit phone number.</div>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--      <label for="email">Email</label>--%>
<%--      <input type="email" class="form-control" name="email" value="${booking.email}" required>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--      <label for="slot">Slot</label>--%>
<%--      <select class="form-control" name="slot" required>--%>
<%--        <option value="Slot 1 (7:00 - 9:00)" ${booking.slot == 'Slot 1 (7:00 - 9:00)' ? 'selected' : ''}>Slot 1 (7:00 - 9:00)</option>--%>
<%--        <option value="Slot 2 (9:00 - 11:00)" ${booking.slot == 'Slot 2 (9:00 - 11:00)' ? 'selected' : ''}>Slot 2 (9:00 - 11:00)</option>--%>
<%--        <option value="Slot 3 (13:00 - 15:00)" ${booking.slot == 'Slot 3 (13:00 - 15:00)' ? 'selected' : ''}>Slot 3 (13:00 - 15:00)</option>--%>
<%--        <option value="Slot 4 (15:00 - 18:00)" ${booking.slot == 'Slot 4 (15:00 - 18:00)' ? 'selected' : ''}>Slot 4 (15:00 - 18:00)</option>--%>
<%--      </select>--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--      <label for="address">Địa chỉ<span class="text-danger">*</span></label>--%>
<%--      <input type="text" class="form-control" id="address" placeholder="Address" name="address" value="${booking.address}" readonly required>--%>
<%--      <button id="editButton" type="button" class="btn btn-edit" onclick="editAddress();">--%>
<%--        <i class="glyphicon glyphicon-pencil"></i> Chỉnh sửa--%>
<%--      </button>--%>
<%--    </div>--%>

<%--    <div class="col-xl-12" id="editAddressSection" style="display: none;">--%>
<%--      <label for="address">Địa chỉ chi tiết<span class="text-danger">*</span></label>--%>
<%--      <div class="Address d-flex justify-content-between align-items-center mb-3">--%>
<%--        <select id="province" name="province" class="form-control" required>--%>
<%--          <option value="">Chọn tỉnh</option>--%>
<%--          <!-- Add province options here -->--%>
<%--        </select>--%>
<%--        <select id="district" name="district" class="form-control" required>--%>
<%--          <option value="">Chọn quận</option>--%>
<%--          <!-- Add district options here -->--%>
<%--        </select>--%>
<%--        <select id="ward" name="ward" class="form-control" required>--%>
<%--          <option value="">Chọn phường</option>--%>
<%--          <!-- Add ward options here -->--%>
<%--        </select>--%>
<%--      </div>--%>
<%--      <input type="text" class="form-control mb-3" id="addressDetail" name="addressDetail" placeholder="Nhập số nhà hoặc địa chỉ chi tiết" required>--%>
<%--      <input type="hidden" id="result" name="address1">--%>
<%--    </div>--%>

<%--    <div class="form-group">--%>
<%--      <label for="note">Ghi chú</label>--%>
<%--      <textarea class="form-control" name="note">${booking.note}</textarea>--%>
<%--    </div>--%>

<%--    <button style="margin-top: 10px; margin-bottom: 15px" type="submit" class="btn btn-primary">Cập nhật</button>--%>
<%--  </form>--%>
  <form action="UpdateBookingServlet" method="post" onsubmit="return validateDate();">
    <input type="hidden" name="bookingID" value="${booking.bookingID}">
    <input type="hidden" name="serviceID" value="${booking.serviceID}">
    <input type="hidden" id="price" name="price" value="${booking.price}" required>
    <input type="hidden" id="serviceName" name="serviceName" value="${booking.serviceName}" required>

    <div class="form-group">
      <label for="name">Tên</label>
      <input type="text" class="form-control" name="name" value="${booking.name}" required>
    </div>

    <div class="form-group">
      <label for="sex">Giới tính</label>
      <select class="form-control" name="sex" required>
        <option value="Nam" ${booking.sex == 'Nam' ? 'selected' : ''}>Nam</option>
        <option value="Nữ" ${booking.sex == 'Nữ' ? 'selected' : ''}>Nữ</option>
      </select>
    </div>

    <div class="form-group">
      <label for="bookingDate">Ngày</label>
      <input type="date" class="form-control" id="bookingDate" name="bookingDate" value="${booking.bookingDate}" required>
    </div>

    <div class="form-group">
      <label for="phone">Điện Thoại</label>
      <input type="text" class="form-control" name="phone" pattern="\d{10}" maxlength="10" value="${booking.phoneNumber}" required>
      <div class="invalid-feedback">Please enter a valid 10-digit phone number.</div>
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" name="email" value="${booking.email}" required>
    </div>

    <div class="form-group">
      <label for="slot">Slot</label>
      <select class="form-control" name="slot" required>
        <option value="Slot 1 (7:00 - 9:00)" ${booking.slot == 'Slot 1 (7:00 - 9:00)' ? 'selected' : ''}>Slot 1 (7:00 - 9:00)</option>
        <option value="Slot 2 (9:00 - 11:00)" ${booking.slot == 'Slot 2 (9:00 - 11:00)' ? 'selected' : ''}>Slot 2 (9:00 - 11:00)</option>
        <option value="Slot 3 (13:00 - 15:00)" ${booking.slot == 'Slot 3 (13:00 - 15:00)' ? 'selected' : ''}>Slot 3 (13:00 - 15:00)</option>
        <option value="Slot 4 (15:00 - 18:00)" ${booking.slot == 'Slot 4 (15:00 - 18:00)' ? 'selected' : ''}>Slot 4 (15:00 - 18:00)</option>
      </select>
    </div>

    <div class="form-group input-group">
      <label for="Diachi">Đại chỉ: </label>

      <input type="text" class="form-control" id="address" placeholder="Address" name="address"
             value="${booking.address}" readonly required>
      <button id="editButton" type="button" class="btn btn-edit">
        <i class="glyphicon glyphicon-pencil"></i> Chỉnh sửa
      </button>
    </div>

    <div class="form-group" id="editAddressSection" style="display: none;">
      <label for="address">Địa chỉ<span class="text-danger">*</span></label>
      <div class="Address d-flex justify-content-between align-items-center mb-3">
        <select name="" id="province" class="form-control">
          <option value="">Chọn tỉnh</option>
          <!-- Add province options here -->
        </select>
        <select name="" id="district" class="form-control">
          <option value="">Chọn quận</option>
          <!-- Add district options here -->
        </select>
        <select name="" id="ward" class="form-control">
          <option value="">Chọn phường</option>
          <!-- Add ward options here -->
        </select>
      </div>
      <input type="text" class="form-control mb-3" id="addressDetail" placeholder="Nhập số nhà hoặc địa chỉ chi tiết">
      <input type="hidden" id="result" name="address1">
    </div>

    <div class="form-group">
      <label for="note">Ghi chú</label>
      <textarea class="form-control" name="note">${booking.note}</textarea>
    </div>

    <button style="margin-top: 10px; margin-bottom: 15px" type="submit" class="btn btn-primary">Cập nhật</button>
  </form>

  <script>
    function validateDate() {
      const bookingDate = document.getElementById("bookingDate").value;
      const currentDate = new Date().toISOString().split("T")[0];

      if (bookingDate < currentDate) {
        alert("Ngày đặt không thể là trước ngày hiện tại.");
        return false; // Ngăn không cho gửi form
      }
      return true; // Cho phép gửi form
    }
  </script>

</div>
<script>
  document.getElementById('editButton').onclick = function() {
    var editSection = document.getElementById('editAddressSection');
    editSection.style.display = editSection.style.display === 'none' ? 'block' : 'none';
  };
</script>

<script>
  function updateFullAddress() {
    // Lấy giá trị từ các dropdown và trường nhập
    const province = document.getElementById('province').options[document.getElementById('province').selectedIndex].text; // Lấy text của tỉnh
    const district = document.getElementById('district').options[document.getElementById('district').selectedIndex].text; // Lấy text của quận
    const ward = document.getElementById('ward').options[document.getElementById('ward').selectedIndex].text; // Lấy text của phường
    const addressDetail = document.getElementById('addressDetail').value;

    // Kiểm tra và in ra console các giá trị
    console.log("Địa chỉ chi tiết:", addressDetail);
    console.log("Phường:", ward);
    console.log("Quận:", district);
    console.log("Tỉnh:", province);

    // Gộp các giá trị thành một chuỗi
    let fullAddress = '';
    if (addressDetail) {
      fullAddress += addressDetail + ', ';
    }
    if (ward) {
      fullAddress += ward + ', ';
    }
    if (district) {
      fullAddress += district + ', ';
    }
    if (province) {
      fullAddress += province;
    }

    // Xóa dấu phẩy cuối nếu có
    fullAddress = fullAddress.replace(/, $/, '');

    // Hiển thị kết quả
    document.getElementById('result').innerText = fullAddress;

    // Kiểm tra nếu có địa chỉ hoàn chỉnh và in ra console
    if (fullAddress) {
      console.log("Địa chỉ hoàn chỉnh:", fullAddress);
    } else {
      console.log("Không có dữ liệu địa chỉ nào.");
    }
    document.getElementById('result').value = fullAddress; // Gán giá trị cho input ẩn
  }

  // Thêm sự kiện để tự động gộp địa chỉ khi có thay đổi
  document.getElementById('addressDetail').addEventListener('input', updateFullAddress);
  document.getElementById('province').addEventListener('change', updateFullAddress);
  document.getElementById('district').addEventListener('change', updateFullAddress);
  document.getElementById('ward').addEventListener('change', updateFullAddress);
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/fc6bd51969.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js" integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="${pageContext.request.contextPath}/assets/js/adreess.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
