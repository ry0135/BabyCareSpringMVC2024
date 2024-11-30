
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="container mt-3">
  <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/updateprofile" method="post" enctype="multipart/form-data">
    <c:if test="${not empty error}">
      <div class="alert alert-danger">${error}</div>
    </c:if>

  <div class="row">
      <div class="col-md-3" style="background-color: hwb(48 90% 2%); border-radius: 10px; width: 200px; height: 250px; margin-right: 20px;">
        <div>
          <div class="col-12">

            <div style=" margin: 15px;">
              <img style="width: 50px; height: 83%; border-radius: 50%; margin-top: 5px;"src="${pageContext.request.contextPath}/image/${avatar}" class="img-fluid" alt="Ảnh cá nhân">
            </div>
            <h2 style=" font-size:15px; font-weight: 550px;">${firstname} ${lastname}</h2>
          </div>
        </div>

        <section>

          <a href="changepass" class="icon-link">
            <i class="glyphicon glyphicon-cloud icon"></i>
            <span class="text">Change password</span>
          </a>
        </section>
      </div>
      <div class="col-md-9" style="background-color: hwb(319 83% 3%);
                 border-radius: 10px;
                 padding: 1rem;
                 margin-bottom: 2rem;">
        <div class="row">
          <div class="col-12" style="border-radius: 10px; text-align: center;">
            <h2  style="font-weight: 600;color: white;font-size: 22px ;font-family: 'Roboto', sans-serif;" >Thông Tin Cá Nhân</h2>
          </div>

          <%--input fistname--%>
          <div class="col-md-8 mb-3" >
            <div >

              <label for="firstname">Họ:</label>
                          <div class="input-group">
                <input  type="text" class="form-control" id="firstname" name="firstname" placeholder="Họ"
                       value="${firstname}"
                       aria-describedby="inputGroupPrepend"
                       pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$"
                       required>
                <div class="invalid-feedback">
                  Họ chứa từ 1 đến 50 kí tự, không chứa ký tự đặc biệt và chữ số
                </div>
              </div>
            </div>
            <%-- input lastname--%>
            <div >
              <label for="lastname">Last Name</label>
              <div class="input-group">
                <input type="text" class="form-control" id="lastname" placeholder="Tên" name="lastname"
                       value="${lastname}"
                       aria-describedby="inputGroupPrepend"
                       pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$"
                       required>
                <div class="invalid-feedback">
                  Tên chứa từ 1 đến 50 kí tự, không chứa ký tự đặc biệt và chữ số
                </div>
              </div>
            </div>

            <%--        input address--%>

            <div id="address-section">
              <!-- Trường chỉ đọc ban đầu -->
              <label for="address">Địa chỉ</label>
              <div class="input-group">
                <input type="text" class="form-control" id="address" placeholder="Address" name="address"
                       value="${address}" readonly required>
              </div>

              <!-- Nút Chỉnh sửa -->
              <button type="button" id="edit-address-btn" class="btn btn-primary" style="margin-top: 10px;">Chỉnh sửa</button>

              <!-- Phần mã chỉnh sửa địa chỉ -->
              <div id="edit-address-section" style="display:none; margin-top: 20px;">
                <label for="address">Địa chỉ<span class="text-danger">*</span></label>
                <div class="Address" style="display: flex; flex-direction: row; justify-content: space-between; align-items: center;">
                  <select name="province" id="province">
                    <option value="">Chọn tỉnh</option>
                    <!-- Thêm các tỉnh ở đây -->
                  </select>
                  <select name="district" id="district">
                    <option value="">Chọn quận</option>
                    <!-- Thêm các quận ở đây -->
                  </select>
                  <select name="ward" id="ward">
                    <option value="">Chọn phường</option>
                    <!-- Thêm các phường ở đây -->
                  </select>
                </div>

                <div style="margin-top: 10px">
                  <input type="text" style="width: 400px; height: 40px; border: 1px solid #ced4da; border-radius: 4px;"
                         id="addressDetail" placeholder="Nhập số nhà hoặc địa chỉ chi tiết">
                </div>

                <div style="margin-top: 10px">
                  <input type="hidden" id="result" name="address" style="width: 400px; height: 40px; border: 1px solid #ced4da; border-radius: 4px;">
                </div>

                <button type="button" id="save-address-btn" class="btn btn-success" style="margin-top: 10px;">Lưu địa chỉ</button>
              </div>
            </div>
          <%--        input phone--%>
            <div >
              <label for="phone">Phone</label>
              <div class="input-group">
                <input type="text" class="form-control" id="phone" placeholder="Phone" name="phone" value="${phone}"
                       aria-describedby="inputGroupPrepend" pattern="^[0-9]{10}$" required>
                <div class="invalid-feedback">
                  Số điện thoại có dộ dài 10 kí tự
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4 mb-3">
            <div>
              <div style=" margin: 50px;">
                <img style="width: 180px; height: 83%; border-radius: 50%; margin-top: 5px;"src="image/${avatar}" class="img-fluid" alt="Ảnh cá nhân">
                <input  hidden name="avatarName" value="${avatar}">
              </div>
            </div>

            <div >
              <label for="avatar"></label>
              <div class="input-group">
                <br>
                <input id="avatar" type="file"  class="form-control" name="avatar" value="${avatar}">
                <br>
              </div>
            </div>

          </div>


          <div class="row">
            <div class="col-3 offset-3">
              <button class="btn btn-primary" type="submit">Edit</button>

            </div>

          </div>

        </div>
      </div>
    </div>
  </form>

</div>
<script>
  // Lấy các phần tử cần thiết
  const editBtn = document.getElementById('edit-address-btn');
  const addressInput = document.getElementById('address');
  const addressSection = document.getElementById('address-section');
  const editAddressSection = document.getElementById('edit-address-section');

  const provinceSelect = document.getElementById('province');
  const districtSelect = document.getElementById('district');
  const wardSelect = document.getElementById('ward');
  const addressDetailInput = document.getElementById('addressDetail');

  // Thêm sự kiện khi nhấn nút "Chỉnh sửa"
  editBtn.addEventListener('click', () => {
    // Chuyển trạng thái của các phần tử khi chỉnh sửa
    addressInput.disabled = true; // Vô hiệu hóa trường địa chỉ ban đầu
    addressSection.style.display = 'none'; // Ẩn phần địa chỉ chỉ đọc
    editAddressSection.style.display = 'block'; // Hiển thị phần chỉnh sửa

    // Kích hoạt các trường select và input trong phần chỉnh sửa
    provinceSelect.disabled = false;
    districtSelect.disabled = false;
    wardSelect.disabled = false;
    addressDetailInput.disabled = false;
  });

  // Nếu không nhấn chỉnh sửa, dữ liệu dưới phần chỉnh sửa không được gửi
  // Đảm bảo khi trang được submit, dữ liệu chỉnh sửa chỉ được gửi khi phần chỉnh sửa được hiển thị.
  const form = document.querySelector('form');
  form.addEventListener('submit', function (event) {
    if (editAddressSection.style.display === 'none') {
      // Nếu không có chỉnh sửa, không gửi giá trị mới
      document.querySelector('input[name="addressDetail"]').disabled = true;
      document.querySelector('select[name="province"]').disabled = true;
      document.querySelector('select[name="district"]').disabled = true;
      document.querySelector('select[name="ward"]').disabled = true;
    }
  });
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
