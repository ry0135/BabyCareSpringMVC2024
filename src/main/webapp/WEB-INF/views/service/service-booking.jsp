
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!-- Book Appointment Start -->
<div class="container-fluid appointment py-5">
  <div class="container py-5">
    <div class="row g-5 align-items-center">
      <div class="col-lg-6 wow fadeInLeft" data-wow-delay="0.2">
        <div class="section-title text-start">
          <h4 class="sub-title pe-3 mb-0">Giải pháp cho bạn</h4>
          <h1 class="display-4 mb-4">Chất lượng tốt nhất và dịch vụ chuyên nghiệp</h1>
          <p class="mb-4">Babycare là trang thông tin dành cho Bố và Bé, chuyên cung cấp các dịch vụ tiện ích cho em bé. Nhằm giúp cho độc giả có những quyết định tốt trong việc chăm sóc sức khỏe, xây dựng đời sống gia đình và nuôi dạy trẻ tốt hơn</p>
          <div class="row g-4">
            <div class="col-sm-6">
              <div class="d-flex flex-column h-100">
                <div class="mb-4">
                  <h5 class="mb-3"><i class="fa fa-check text-primary me-2"></i>Chuyên nghiệp và sáng tạo</h5>
                  <p class="mb-0">Dịch vụ trang trí sinh nhật của chúng tôi tận tâm mang đến cho bạn những bữa tiệc sinh nhật tuyệt vời nhất với những cách trang trí độc đáo, sáng tạo.</p>
                </div>
                <div class="mb-4">
                  <h5 class="mb-3"><i class="fa fa-check text-primary me-2"></i>Đa dạng và linh hoạt</h5>
                  <p class="mb-0">Chúng tôi cung cấp nhiều gói trang trí sinh nhật với giá cả phải chăng, từ đơn giản đến cao cấp, phục vụ mọi nhu cầu và túi tiền. </p>
                </div>

              </div>
            </div>
            <a href="create-payment">Thanh toán với VNPay</a>
            <div class="col-sm-6">
              <div>
                <img src="${serviceIMG}" class="img-fluid rounded w-100 h-100" style="object-fit: cover;" alt="">

              </div>

            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-6 wow fadeInRight" data-wow-delay="0.4s">
        <div class="appointment-form rounded p-5">
          <p class="fs-4 text-uppercase text-primary">Liên lạc</p>

          <h1 class="display-5 mb-4">Đặt Lịch</h1>
          <h5 class="mb-3"><i class="fa me-2" style=" font-size: 24px">Tên Dịch Vụ: ${serviceName}</i></h5>

          <% if (request.getAttribute("errorMessage") != null) {%>
          <div class="alert alert-danger" role="alert">
            <%= request.getAttribute("errorMessage")%>
          </div>
          <% }%>


          <form action="bookingService" method="post" onsubmit="return validateDate();">
            <div class="row gy-3 gx-4">
              <div class="col-md-6 offset-3 mb-3">

                <div class="input-group">
                  <input type="hidden" class="form-control" id="serviceIMG" name="serviceIMG" value="${serviceIMG}" aria-describedby="inputGroupPrepend" required>
                  <input type="hidden" class="form-control" id="serviceID" name="serviceID" value="${serviceID}" aria-describedby="inputGroupPrepend" required>
                  <input type="hidden" class="form-control" id="servicePrice" name="price" value="${servicePrice}" aria-describedby="inputGroupPrepend" required>
                  <input type="hidden" class="form-control" id="serviceName" name="serviceName" value="${serviceName}" aria-describedby="inputGroupPrepend" required>
                  <div class="invalid-feedback"></div>

                </div>
              </div>
              <div class="col-xl-6">
                <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Tên" name="name" value="${name}" required>
              </div>

              <div class="col-xl-6">
                <input type="text" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Điện Thoại" name="phone" pattern="\d{10}" maxlength="10" value="${phone}" required>
                <div class="invalid-feedback">Please enter a valid 10-digit phone number.</div>
              </div>
              <div class="col-xl-6">
                <select class="form-select py-3 border-primary bg-transparent text-dark" name="slot" required>
                  <option selected disabled>Slot</option>
                  <option value="Slot 1 (7:00 - 9:00)" <%= "Slot 1 (7:00 - 9:00)".equals(request.getParameter("slot")) ? "selected" : ""%>>Slot 1 (7:00 - 9:00)</option>
                  <option value="Slot 2 (9:00 - 11:00)" <%= "Slot 2 (9:00 - 11:00)".equals(request.getParameter("slot")) ? "selected" : ""%>>Slot 2 (9:00 - 11:00)</option>
                  <option value="Slot 3 (13:00 - 15:00)" <%= "Slot 3 (13:00 - 15:00)".equals(request.getParameter("slot")) ? "selected" : ""%>>Slot 3 (13:00 - 15:00)</option>
                  <option value="Slot 4 (15:00 - 18:00)" <%= "Slot 4 (15:00 - 18:00)".equals(request.getParameter("slot")) ? "selected" : ""%>>Slot 4 (15:00 - 18:00)</option>
                  <option value="Slot 5 (18:00 - 22:00)" <%= "Slot 5 (18:00 - 22:00)".equals(request.getParameter("slot")) ? "selected" : ""%>>Slot 5 (18:00 - 22:00)</option>
                </select>
              </div>
              <div class="col-xl-6">
                <input type="date" class="form-control py-3 border-primary bg-transparent text-dark" id="bookingDate" name="bookingDate" value="${param.bookingDate}" required>
              </div>
              <div class="col-xl-6">
                <select class="form-select py-3 border-primary bg-transparent text-dark" name="sex" required>
                  <option selected disabled>Giới tính em bé</option>
                  <option value="Nam" <%= "Nam".equals(request.getParameter("sex")) ? "selected" : ""%>>Nam</option>
                  <option value="Nữ" <%= "Nữ".equals(request.getParameter("sex")) ? "selected" : ""%>>Nữ</option>
                </select>
              </div>

              <div class="col-xl-6">
                <input type="email" class="form-control py-3 border-primary bg-transparent text-dark" placeholder="Email" name="email" value="${email}" required>
              </div>


                <div class="col-xl-12 input-group">
                  <input type="text" class="form-control" id="address" placeholder="Address" name="address"
                         value="${address}" readonly required>
                  <button id="editButton" type="button" class="btn btn-edit">
                    <i class="glyphicon glyphicon-pencil"></i> Chỉnh sửa
                  </button>
                </div>

              <div class="col-xl-12" id="editAddressSection" style="display: none;">
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
              <div class="col-12">
                <textarea class="form-control py-3 border-primary bg-transparent text-dark" name="note" placeholder="Ghi chú" rows="4">${note}</textarea>
              </div>
              <div class="col-12">
                <button type="submit" class="btn btn-primary text-white w-100 py-3 px-5">Đặt Ngay</button>
              </div>
            </div>
          </form>




        </div>
      </div>
    </div>
  </div>
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
