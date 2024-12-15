
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<style>
  h6, .h6, h5, .h5, h4, .h4, h3, .h3, h2, .h2, h1, .h1 {
    font-family: sans-serif;
  }
</style>
<div class="container mt-5">
  <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/updateprofile" method="post" enctype="multipart/form-data">
    <c:if test="${not empty error}">
      <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="row">
      <div class="col-md-4 ">
        <div class="card">
          <div class="card-body text-center">
            <img src="${pageContext.request.contextPath}/image/${avatar}" class="avatar" alt="Ảnh cá nhân">
            <h5 class="card-title">${firstname} ${lastname}</h5>
            <a href="changepass" class="btn btn-outline-primary" style="color: black;border-color: #ff4880#4d65f9;">Thay Đổi Mật Khẩu</a>
          </div>
        </div>
      </div>

      <div class="col-md-8">
        <div class="card personal-info-card">
          <div class="card-header text-center" style="background-color: #f8d9e1">
            <h3>Thông Tin Cá Nhân</h3>
          </div>
          <div class="card-body">
            <div class="mb-3">
              <label for="firstname">Họ:</label>
              <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Họ"
                     value="${firstname}" pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$"
                     required>
              <div class="invalid-feedback">Họ chứa từ 1 đến 50 kí tự, không chứa ký tự đặc biệt và chữ số</div>
            </div>

            <div class="mb-3">
              <label for="lastname">Tên:</label>
              <input type="text" class="form-control" id="lastname" placeholder="Tên" name="lastname"
                     value="${lastname}" pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$"
                     required>
              <div class="invalid-feedback">Tên chứa từ 1 đến 50 kí tự, không chứa ký tự đặc biệt và chữ số</div>
            </div>

            <div class="mb-3">
              <label for="address">Địa chỉ:</label>
              <div class="input-group">
                <input type="text" class="form-control" id="address" placeholder="Address" name="address"
                       value="${address}" readonly required>
                <button id="editButton" type="button" class="btn btn-edit" style="margin-top: 0px; background-color: #ff4880;color: black">
                  <i class="glyphicon glyphicon-pencil"></i> Chỉnh sửa
                </button>
              </div>


              <div id="editAddressSection" style="display: none;">
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

            </div>

            <div class="mb-3">
              <label for="phone">Số Điện Thoại:</label>
              <input type="text" class="form-control" id="phone" placeholder="Phone" name="phone" value="${phone}"
                     pattern="^[0-9]{10}$" required>
              <div class="invalid-feedback">Số điện thoại có độ dài 10 kí tự</div>
            </div>

            <div class="mb-4">
              <label for="avatar"></label>
              <div class="input-group">
                <input id="avatar" type="file" class="form-control" name="avatar">
              </div>
            </div>

            <button class="btn btn-primary" type="submit" style="border-radius: 0px;">Cập nhật</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</div>

<style>
  .btn-edit {
    background-color: #007bff; /* Blue background */
    color: white; /* White text */
    border: none; /* No border */
    padding: 10px 15px; /* Padding for better size */
    border-radius: 5px; /* Rounded corners */
    font-size: 16px; /* Larger font size */
    transition: background-color 0.3s, transform 0.2s; /* Animation effects */
  }

  .btn-edit:hover {
    background-color: #0056b3; /* Darker blue on hover */
    transform: translateY(-2px); /* Slight lift effect */
  }

  .profile-card {
    background-color: #f3f4f6;
    border-radius: 15px;
    margin-bottom: 20px;
  }

  .card {
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }

  .avatar {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    margin-top: 15px;
    border: 3px solid #007bff;
  }

  .personal-info-card {
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }

  .card-header {
    background-color: rgba(255, 2, 129, 0.48);
    color: white;
    border-radius: 10px 10px 0 0;
  }

  .input-group {
    margin-bottom: 15px;
  }

  button {
    margin-top: 10px;
  }

  .form-control {
    border-radius: 5px;
  }

  .text-danger {
    margin-left: 5px;
  }
</style>


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
