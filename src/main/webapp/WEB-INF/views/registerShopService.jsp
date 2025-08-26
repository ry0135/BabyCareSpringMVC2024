<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>


    <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@600;700&family=Montserrat:wght@200;400;600&display=swap" rel="stylesheet">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
    }
    .info-definition {
        position: absolute;
        background-color: white;
        width: 70%;
        margin-top: 100px;
        margin-left: 15%;  /* Giảm khoảng cách bên trái để cân bằng */
        margin-right:  25%;  /* Giảm khoảng cách bên trái để cân bằng */
        margin-bottom: 50px; /* Tạo thêm khoảng cách phía dưới nếu cần */
        padding: 20px; /* Tạo thêm không gian bên trong phần tử */
        height: 1300px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Thêm đổ bóng để tạo độ nổi */

    }
    .shop-form .form-group {
        margin-bottom: 20px;
    }

    .shop-form label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }

    .shop-form .required {
        color: #ff4d4f;
    }

    .shop-form input[type="text"],
    .shop-form input[type="email"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .shop-form input[disabled] {
        background-color: #f5f5f5;
    }

    .error {
        color: #ff4d4f;
        font-size: 12px;
    }

    .edit-link {
        color: #1890ff;
        text-decoration: none;
        font-size: 14px;
    }

    .edit-link:hover {
        text-decoration: underline;
    }

    .form-actions {
        display: flex;
        justify-content: flex-end;
    }

    .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
    }

    .save-btn {
        background-color: #f5f5f5;
        margin-right: 10px;
    }

    .next-btn {
        background-color: #ff4880;
        color: white;
    }

    .next-btn:hover {
        background-color: #d9363e;
    }

    .timeline {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 20px 0;
        padding: 0;
        list-style-type: none;
    }

    .timeline li {
        flex: 1;
        position: relative;
        text-align: center;
    }

    .timeline li:not(:last-child)::after {
        content: '';
        position: absolute;
        top: 50%;
        left: 49%;
        height: 4px;
        width: 100%;
        background-color: #ccc;
        z-index: 1;
        transform: translateY(-50%);
    }
    .timeline li.active:not(:last-child)::after {
        background-color: #4CAF50;
    }

    .timeline .icon {
        background-color: #4CAF50;
        color: white;
        border-radius: 50%;
        padding: 10px;
        display: inline-block;
        margin-bottom: 30px;
    }

    .timeline .icon.active {
        background-color: #ff4880;
    }

    .timeline .icon.inactive {
        background-color: #ccc;
    }

    .timeline .step-title {
        font-weight: bold;
    }

    .timeline .step-date {
        color: #666;
    }

    .timeline .step {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    #newAddressInput {
        display: none;
    }
    /* Định dạng thông báo thành công */
    #success-message {
        position: absolute;
        top: 100px;
        right: 600px;
        background-color: #28a745;
        color: white;
        padding: 15px;
        border-radius: 5px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);

    }
</style>
<html>
<body>

<c:if test="${hasPending}">
    <div class="info-definition">
        <h3 class="text-danger">Chúng tôi đã tiếp nhận thông tin của bạn. Chúng tôi sẽ thông báo qua email của bạn trong vòng 7 ngày.</h3>
    </div>
</c:if>
<c:if test="${!hasPending}">
    <div class="info-definition">
        <form action="register-shop-service" method="POST" enctype="multipart/form-data">

            <div class="form-group">
                <%--@declare id="shop-name"--%><label for="shop-name">Tên Shop <span class="required">*</span></label>
                <input type="text" class="form-control" id="brandName" placeholder="Tên cửa hàng"
                       name="brandName" aria-describedby="inputGroupPrepend" pattern="^.{1,100}$" required>
                <div class="invalid-feedback">
                    Tên cửa hàng chứa từ 1 đến 100 kí tự
                </div>
                <p class="text-danger">${thongbao1}</p>
            </div>


    <div class="form-group">
        <%--@declare id="brandDescription"--%><label for="brandDescription">Mô tả cửa hàng <span class="required">*</span></label>
        <input type="text" class="form-control" id="brandName" placeholder="Mô tả cửa hàng"
               name="brandDescription" aria-describedby="inputGroupPrepend" pattern="^.{1,200}$"   required>
        <div class="invalid-feedback">
            Mô tả cửa hàng không quá 200 kí tự
        </div>
        <p class="text-danger">${thongbao1}</p>
    </div>

    <div class="form-group">
        <%--@declare id="brandDescription"--%><label for="brandDescription">Địa chỉ cửa hàng <span class="required">*</span></label>
        <input type="text" class="form-control" id="brandAddress" placeholder="Địa chỉ cửa hàng"
               name="brandAddress" aria-describedby="inputGroupPrepend" pattern="^.{1,100}$"   required>
        <div class="invalid-feedback">
            Địa chỉ cửa hàng không quá 100 từ
        </div>
        <p class="text-danger">${thongbao1}</p>
    </div>

    <div class="form-group">
        <%--@declare id="brandDescription"--%><label for="brandDescription">Số Điện Thoại Cửa Hàng <span class="required">*</span></label>
        <input type="text" class="form-control" id="brandAddress" placeholder="Số Điện Thoại Cửa Hàng"
               name="brandPhone" aria-describedby="inputGroupPrepend" pattern="^.{1,10}$"   required>
        <div class="invalid-feedback">
            Địa chỉ cửa hàng không quá 10 từ
        </div>
        <p class="text-danger">${thongbao1}</p>
    </div>

    <label for="brandLogo">Brand Logo:</label>
    <input type="file" id="brandLogo" name="brandLogo" accept="image/*">



    <div class="form-group row">
        <label class="col-lg-3" for="IdentifiNumber">* Số CCCD/CMND <span class="text-danger">*</span></label>
        <input class="form-control col-lg-3" type="text" id="IdentifiNumber" name="IdentifiNumber" placeholder="Nhập vào" maxlength="12" pattern="\d{12}" value="${shopService.identifiNumber}" required style="width: 320px;">
        <div class="invalid-feedback">
            Vui lòng nhập đúng 12 số cho Số CCCD/CMND.
        </div>
    </div>

    <!-- Họ và Tên -->
    <div class="form-group row">
        <label class="col-lg-3" for="name">* Họ và Tên</label>
        <input class="col-lg-3" type="text" id="name" name="name" placeholder="Nhập vào" maxlength="100"  required style="width: 320px;">
        <div class="invalid-feedback">
            Vui lòng nhập Họ và Tên.
        </div>
    </div>

    <!-- Hình chụp của thẻ CMND/CCCD -->
    <div class="upload-container row">
        <label for="fileUpload" class="upload-label col-lg-3">* Hình chụp của thẻ CMND/CCCD <span class="text-danger">*</span></label>
        <div class="upload-area col-lg-3" onclick="triggerFileUpload()">
            <!-- Input file -->
            <input type="file" id="fileUpload" name="IdentifiImg" class="file-input" accept="image/*" required onchange="previewImage(this, document.getElementById('imgPreview1'))">
            <!-- Icon dấu cộng -->
            <div class="upload-preview">
                <img id="imgPreview1" class="img-preview" style="width: 100%; height: auto; display: none;" alt="">
                <span class="add-icon" id="addIcon1">+</span>
            </div>
        </div>

        <div class="col-lg-2">
            <img id="imgPreview1" class="img-avatar text-white" style="width: 100px; height: auto;" src="${pageContext.request.contextPath}/image/mattruoc.png" alt="">
        </div>
        <div class="invalid-feedback">
            Vui lòng tải lên hình chụp của thẻ CMND/CCCD.
        </div>
    </div>

    <!-- Ảnh đang cầm CMND/CCCD của bạn -->
    <div class="upload-container row">
        <label for="fileUpload2" class="upload-label col-lg-3">* Ảnh đang cầm CMND/CCCD của bạn<span class="text-danger">*</span></label>
        <div class="upload-area col-lg-3" onclick="triggerFileUpload2()">
            <!-- Input file -->
            <input type="file" id="fileUpload2" name="IdentifiImgFace" class="file-input" accept="image/*" required onchange="previewImage(this, document.getElementById('imgPreview2'))">
            <!-- Icon dấu cộng -->
            <div class="upload-preview">
                <img id="imgPreview2" class="img-preview" style="width: 100%; height: auto; display: none;" alt="">
                <span class="add-icon" id="addIcon2">+</span>
            </div>
        </div>
        <div class="col-lg-2">
            <img id="imgPreview2" class="text-white" style="width: 100px; height: auto;" src="${pageContext.request.contextPath}/image/voimat.png" alt="">
        </div>
        <div class="invalid-feedback">
            Vui lòng tải lên ảnh đang cầm CMND/CCCD của bạn.
        </div>
    </div>


        <div class="form-group">
            <%--@declare id="bankname"--%><label for="bankName">Tên Ngân Hàng <span class="text-danger">*</span></label>
            <div class="input-group">
                <select class="form-control" name="bankName" required>
                    <option value="">Chọn ngân hàng</option>
                    <c:choose>
                        <c:when test="${not empty shopService}">
                            <option value="Vietcombank" ${shopService.bankName == 'Vietcombank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Ngoại thương Việt Nam (Vietcombank)</option>
                            <option value="Vietinbank" ${shopService.bankName == 'Vietinbank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Công Thương Việt Nam (Vietinbank)</option>
                            <option value="BIDV" ${shopService.bankName == 'BIDV' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Đầu tư và Phát triển Việt Nam (BIDV)</option>
                            <option value="ACB" ${shopService.bankName == 'ACB' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Á Châu (ACB)</option>
                            <option value="Techcombank" ${shopService.bankName == 'Techcombank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Kỹ Thương Việt Nam (Techcombank)</option>
                            <option value="MB" ${shopService.bankName == 'MB' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Quân đội (MB)</option>
                            <option value="SHB" ${shopService.bankName == 'SHB' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Sài Gòn - Hà Nội (SHB)</option>
                            <option value="DongABank" ${shopService.bankName == 'DongABank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Đông Á (DongABank)</option>
                            <option value="TPBank" ${shopService.bankName == 'TPBank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Tiên Phong (TPBank)</option>
                            <option value="VietABank" ${shopService.bankName == 'VietABank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Bản Việt (VietABank)</option>
                            <option value="SCB" ${shopService.bankName == 'SCB' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Sài Gòn (SCB)</option>
                            <option value="HDBank" ${shopService.bankName == 'HDBank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Phát triển Thành phố Hồ Chí Minh (HDBank)</option>
                            <option value="BacABank" ${shopService.bankName == 'BacABank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Bắc Á (BacABank)</option>
                            <option value="OCB" ${shopService.bankName == 'OCB' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Phương Đông (OCB)</option>
                            <option value="Sacombank" ${shopService.bankName == 'Sacombank' ? 'selected' : ''}>Ngân hàng Thương mại Cổ phần Sài Gòn Thương Tín (Sacombank)</option>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </select>
                <div class="invalid-feedback">
                    Vui lòng chọn ngân hàng
                </div>
            </div>
        </div>


        <!-- Account Number -->
        <div class="form-group">
            <label for="accountNumber">Số tài khoản <span class="text-danger">*</span></label>
            <div class="input-group">
                <input type="text" class="form-control" id="accountNumber" placeholder="Số tài khoản"
                       name="AccountNumber" aria-describedby="inputGroupPrepend" pattern="^.{1,20}$"
                       value="${not empty shopService ? shopService.acountNumber : ''}" required>
                <div class="invalid-feedback">
                    Số tài khoản có độ dài tối đa 20 ký tự
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="terms">Điều khoản đăng ký bán hàng<span class="text-danger">*</span></label>
            <div class="terms" id="terms" style="max-height: 150px; overflow-y: auto; border: 1px solid #ccc; padding: 10px;">
                <p><strong>Điều khoản 1:</strong> Người bán hàng phải tuân thủ tất cả các quy định pháp luật liên quan đến hoạt động kinh doanh và bán hàng trực tuyến.</p>
                <p><strong>Điều khoản 2:</strong> Người bán cam kết các sản phẩm được rao bán là hàng chính hãng, đảm bảo chất lượng và có nguồn gốc xuất xứ rõ ràng.</p>
                <p><strong>Điều khoản 3:</strong> Người bán phải cung cấp thông tin chi tiết về chính sách đổi trả và bảo hành cho khách hàng, đảm bảo quyền lợi của người tiêu dùng.</p>
                <p><strong>Điều khoản 4:</strong> Người bán cam kết giá cả các sản phẩm niêm yết trên website là chính xác và không thay đổi trong thời gian diễn ra khuyến mãi (nếu có).</p>
                <p><strong>Điều khoản 5:</strong> Người bán phải đảm bảo việc xử lý đơn hàng và giao hàng đúng thời gian cam kết, đồng thời thông báo kịp thời cho khách hàng trong trường hợp có sự cố.</p>
                <p><strong>Điều khoản 6:</strong> Người bán cam kết bảo mật thông tin cá nhân của khách hàng và không tiết lộ cho bên thứ ba mà không có sự đồng ý của khách hàng.</p>
                <p><strong>Điều khoản 7:</strong> Người bán chịu trách nhiệm bồi thường cho khách hàng trong trường hợp cung cấp sản phẩm kém chất lượng hoặc không đúng mô tả.</p>
                <p><strong>Điều khoản 8:</strong> Người bán có quyền chấm dứt hợp đồng bán hàng trong trường hợp khách hàng vi phạm các điều khoản hoặc không tuân thủ quy định của website.</p>
                <p><strong>Điều khoản 9:</strong> Người bán cam kết không vi phạm quyền sở hữu trí tuệ của bên thứ ba, bao gồm nhưng không giới hạn ở việc sử dụng hình ảnh, thương hiệu, và mô tả sản phẩm.</p>
                <p><strong>Điều khoản 10:</strong> Mọi tranh chấp phát sinh từ hợp đồng bán hàng sẽ được giải quyết thông qua thương lượng hòa giải trước khi đưa ra cơ quan pháp luật có thẩm quyền.</p>
                <p><strong>Điều khoản 11:</strong> Mỗi đơn hàng sẽ được trả cho hệ thống 10% giá trị.</p>
            </div>
            <div class="form-check mt-2">
                <input type="checkbox" class="form-check-input" id="agreeTerms" required>
                <label class="form-check-label" for="agreeTerms">Tôi đã đọc và đồng ý với các điều khoản đăng ký bán hàng</label>
                <div class="invalid-feedback">
                    Bạn phải đồng ý với các điều khoản trước khi đăng ký
                </div>
            </div>
        </div>

    <button type="submit">Đăng Ký</button>

        </form>
    </div>
</c:if>
    </body>
</html>

    <script>
    function triggerFileUpload() {
    document.getElementById('fileUpload').click();
    }

    function triggerFileUpload2() {
    document.getElementById('fileUpload2').click();
    }



    function previewImage(input, previewElement) {
    const file = input.files[0];
    const reader = new FileReader();

    reader.onload = function (e) {
    previewElement.src = e.target.result; // Đặt src của img là dữ liệu hình ảnh đã tải lên
    previewElement.style.display = 'block'; // Hiển thị hình ảnh
    const addIcon = previewElement.parentElement.querySelector('.add-icon');
    if (addIcon) {
    addIcon.style.display = 'none'; // Ẩn biểu tượng cộng
    }
    };

    if (file) {
    reader.readAsDataURL(file); // Đọc file và gọi hàm onload
    }
    }
    </script>
<style>
    html, body {
        padding: 0;
        margin: 0;
        height: 100%;
        width: 100%;
    }
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
    }

    .shop-form .form-group {
        margin-bottom: 20px;
    }

    .shop-form label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }

    .shop-form .required {
        color: #ff4d4f;
    }

    .shop-form input[type="text"],
    .shop-form input[type="email"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .shop-form input[disabled] {
        background-color: #f5f5f5;
    }

    .error {
        color: #ff4d4f;
        font-size: 12px;
    }

    .edit-link {
        color: #1890ff;
        text-decoration: none;
        font-size: 14px;
    }

    .edit-link:hover {
        text-decoration: underline;
    }

    .form-actions {
        display: flex;
        justify-content: flex-end;
    }

    .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
    }

    .save-btn {
        background-color: #f5f5f5;
        margin-right: 10px;
    }

    .complete-btn {
        background-color: #ff4880;
        color: white;
    }


    .complete-btn:hover {
        background-color: #d9363e;
    }
    .back-btn{
        background-color: #ccc;
        color: white;
    }
    .back-btn:hover {
        background-color: #ccc;
    }
    .timeline {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 20px 0;
        padding: 0;
        list-style-type: none;
    }

    .timeline li {
        flex: 1;
        position: relative;
        text-align: center;
    }

    .timeline li:not(:last-child)::after {
        content: '';
        position: absolute;
        top: 50%;
        left: 49%;
        height: 4px;
        width: 100%;
        background-color: #ff4880;
        z-index: 1;
        transform: translateY(-50%);
    }
    .timeline li.active:not(:last-child)::after {
        background-color: #4CAF50;
    }

    .timeline .icon {
        background-color: #4CAF50;
        color: white;
        border-radius: 50%;
        padding: 10px;
        display: inline-block;
        margin-bottom: 30px;
    }

    .timeline .icon.active {
        background-color: #ff4880;
    }

    .timeline .icon.inactive {
        background-color: #ff4880;
    }

    .timeline .step-title {
        font-weight: bold;
    }

    .timeline .step-date {
        color: #666;
    }

    .timeline .step {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .container-header {
        display: flex;
        justify-content: space-around;
        background-color: white;
        position: fixed;
        top: 0;
        width: 100%;
        z-index: 1000; /* Đảm bảo header luôn hiển thị trên cùng */
        padding: 10px 20px;
    }
    .container {
        background-color: #f6f6f6;
        min-height: 100vh;
        margin-top: 100px;
        padding: 0; /* Xóa padding */
        width: 100vw; /* Sử dụng toàn bộ chiều rộng màn hình */
        position: relative;
    }

    .info-shop{
        display: flex;
        align-items: center;
    }

    .info-definition {
        position: absolute;
        background-color: white;
        width: 70%;
        margin-top: 100px;
        margin-left: 15%;  /* Giảm khoảng cách bên trái để cân bằng */
        margin-right:  25%;  /* Giảm khoảng cách bên trái để cân bằng */
        margin-bottom: 50px; /* Tạo thêm khoảng cách phía dưới nếu cần */
        padding: 20px; /* Tạo thêm không gian bên trong phần tử */
        height: 1000px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Thêm đổ bóng để tạo độ nổi */

    }
    .alert {
        display: flex;
        align-items: center;
        padding: 15px;
        background-color: #e7f3fe;
        border: 1px solid #b3d7ff;
        border-radius: 5px;
        color: #31708f;
        font-size: 16px;
    }

    .alert-info {
        background-color: #e7f3fe;
        border-color: #b3d7ff;
    }

    .alert i {
        margin-right: 10px;
        color: #31708f; /* Màu cho biểu tượng thông tin */
    }
    .upload-container {
        display: flex;
        margin-bottom: 20px;
    }

    .upload-label {
        margin-bottom: 5px;
        font-size: 16px;
    }

    .text-danger {
        color: red;
    }

    .upload-area {
        border: 2px dashed #FF5722; /* Màu viền của ô upload */
        border-radius: 8px;
        width: 200px; /* Chiều rộng của ô upload */
        height: 150px; /* Chiều cao của ô upload */
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }

    .file-input {
        display: none; /* Ẩn input file */
    }

    .upload-preview {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .add-icon {
        font-size: 40px; /* Kích thước biểu tượng */
        color: #FF5722; /* Màu sắc của biểu tượng */
    }



    .upload-preview {
        width: 100%; /* Sử dụng toàn bộ chiều rộng của ô upload */
        height: 100%; /* Sử dụng toàn bộ chiều cao của ô upload */
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative; /* Để có thể căn giữa biểu tượng + */
        overflow: hidden; /* Ẩn phần hình ảnh tràn ra ngoài */
    }

    .img-preview {
        width: 100%;  /* Đặt chiều rộng của ảnh là 100% */
        height: auto; /* Chiều cao tự động để duy trì tỉ lệ khung hình */
        max-height: 100%; /* Giới hạn chiều cao của ảnh để nằm trong khung */
        border-radius: 4px; /* Để tạo các góc tròn cho ảnh */
        position: absolute; /* Để có thể căn giữa ảnh */
        top: 50%; /* Căn giữa theo chiều dọc */
        left: 50%; /* Căn giữa theo chiều ngang */
        transform: translate(-50%, -50%); /* Điều chỉnh để căn giữa chính xác */
    }
</style>
