<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/vapeShop.jpg">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
<title>Đăng Ký</title>
<style>
    body {
        font-family: 'Poppins', sans-serif;
        background: #ffecf2;
    }

    .box-area {
        width: 1930px;
        margin: auto;
    }

    .right-box {
        padding: 40px 30px 40px 40px;
    }

    .left-box {
        background: #FF4880;
        color: white;
    }

    .rounded-4 {
        border-radius: 20px;
    }

    .rounded-5 {
        border-radius: 30px;
    }
    .input-group{
        width: 100%;

    }
    @media only screen and (max-width: 768px) {
        .box-area {
            margin: 0 10px;
        }
        .left-box {
            height: 100px;
            overflow: hidden;
        }
        .right-box {
            padding: 20px;
        }
    }
</style>
<div class="container d-flex justify-content-center align-items-center min-vh-100">

    <div class="row border rounded-5 p-3 bg-white shadow box-area">

        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <div class="featured-image mb-3">
                <img src="img/logobaby.png" class="img-fluid rounded-circle" style="width: 250px;">

            </div>
            <p class="fs-2" style="font-family: 'Courier New', Courier, monospace; font-weight: 600;">BabyCare Register</p>
            <small class="text-wrap text-center" style="width: 17rem; font-family: 'Courier New', Courier, monospace;">Tạo TK NGAY.</small>
        </div>

        <div class="col-md-6 right-box">
            <div class="row align-items-center">
                <div class="header-text mb-4">
                    <h2> Xin chào <i class="fa fa-heart fa-beat-fade" style="color: #ff0000;"></i> </h2>
                    <p>Chúng tôi rất vui khi có bạn tham gia..${message}</p>
                </div>

                <form class="needs-validation" novalidate action="register" method="post">
                    <div class="row">
                        <div class="col-12 mb-3">
                            <h2 style="text-align: center; background-color:#ff4880; padding: 10px; border-radius: 10px;">Đăng kí thành viên mới</h2>
                        </div>
                        <h3 class="text-danger">${thongbao}</h3>

                        <div class="col-md-12 mb-3">
                            <label for="username">Tên tài khoản<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="username" placeholder="Username" name="username" aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z][a-zA-Z0-9]{0,20}$" required>
                                <div class="invalid-feedback">
                                    Username bắt đầu bằng chữ cái và từ 0 đến 20 kí tự không chứa kí tự đặc biệt
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 mb-3">
                            <label for="password">Mật khẩu<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="password" placeholder="Mật khẩu" name="password" aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>
                                <div class="invalid-feedback">
                                    Mật khẩu chứa số hoặc chữ cái độ dài từ 6 đến 32 kí tự
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 mb-3">
                            <label for="re-password">Nhập mật khẩu<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="re-password" placeholder="Nhập lại mật khẩu" name="re-password" aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>
                                <div class="invalid-feedback">
                                    Mật khẩu không trùng nhau
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 mb-3">
                            <label for="firstname">Họ<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="firstname" placeholder="Họ" name="firstname" aria-describedby="inputGroupPrepend" pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$" required>
                                <div class="invalid-feedback">
                                    Họ chứa từ 1 đến 50 kí tự
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 mb-3">
                            <label for="lastname">Tên<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="lastname" placeholder="Tên" name="lastname" aria-describedby="inputGroupPrepend" pattern="^[\p{Lu}\p{Ll}\sA-Za-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]{1,50}$" required>
                                <div class="invalid-feedback">
                                    Tên chứa từ 1 đến 50 kí tự
                                </div>
                            </div>
                        </div>


                        <label for="address">Địa chỉ<span class="text-danger">*</span></label>
                        <div class="Address" style="display: flex; flex-direction: row; justify-content: space-between; align-items: center;">
                            <select name="" id="province">
                                <option value="">chọn tỉnh</option>
                            </select>
                            <select name="" id="district">
                                <option value="">chọn quận</option>
                            </select>
                            <select name="" id="ward">
                                <option value="">chọn phường</option>
                            </select>
                        </div>

                        <div style="margin-top: 10px">
                            <input type="text" style="width: 400px; height: 40px; border: 1px solid #ced4da; border-radius: 4px;"
                                   id="addressDetail" placeholder="Nhập số nhà hoặc địa chỉ chi tiết">
                        </div>

                        <div style="margin-top: 10px">
                            <input type="hidden" id="result" name="address" style="width: 400px; height: 40px; border: 1px solid #ced4da; border-radius: 4px;" >
                        </div>


                        <div class="col-md-12 mb-3">
                            <label for="phone">Điện thoại<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="phone" placeholder="Điện thoại" name="phone" aria-describedby="inputGroupPrepend" pattern="^[0-9]{10}$" required>
                                <div class="invalid-feedback">
                                    Số điện thoại có độ dài 10 kí tự
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 mb-3">
                            <label for="email">Email<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="email" class="form-control" id="email" placeholder="Email" name="email" aria-describedby="inputGroupPrepend" required>
                                <div class="invalid-feedback">
                                    Địa chỉ email không hợp lệ
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <button id="registerBtn" class="btn btn-primary w-100" type="submit">Đăng kí</button>
                        </div>
                    </div>
                </form>

                <p class="login-link">Bạn đã có tài khoản? <a href="login">Đăng nhập</a></p>

            </div>
        </div>
    </div>

</div>
<style>
    .login-link {
        color: grey;
    }
    .login-link a {
        color: red;
        text-decoration: none;
    }
    .login-link a:hover {
        text-decoration: underline;
    }
    .Address select{
        height: 40px;
        border: 1px solid #ced4da;
        width: 200px;
        border-radius: 4px;
    }
</style>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<script>
    // Lấy đối tượng của 2 trường password
    const passwordField = document.getElementById("password");
    const rePasswordField = document.getElementById("re-password");

    // Thêm sự kiện khi thay đổi giá trị của trường re-password
    rePasswordField.addEventListener("input", function () {
        // Kiểm tra xem giá trị của 2 trường có giống nhau hay không
        if (passwordField.value !== rePasswordField.value) {
            // Nếu không giống nhau, hiển thị thông báo lỗi
            rePasswordField.setCustomValidity("Mật khẩu không khớp");
        } else {
            // Nếu giống nhau, xóa thông báo lỗi đi
            rePasswordField.setCustomValidity("");
        }
    });

    // Thêm sự kiện submit cho form
    document.querySelector("form").addEventListener("submit", function (event) {
        // Kiểm tra xem giá trị của 2 trường có giống nhau hay không
        if (passwordField.value !== rePasswordField.value) {
            // Nếu không giống nhau, ngăn chặn form được submit
            event.preventDefault();
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
<script src="/js/adreess.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
