
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<style>
    h6, .h6, h5, .h5, h4, .h4, h3, .h3, h2, .h2, h1, .h1 {
        font-family: sans-serif;
    }
</style>
<div class="container mt-5">
    <form class="needs-validation" novalidate action="savechangepass" method="post" enctype="multipart/form-data">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="row">
            <div class="col-md-4 ">
                <div class="card">
                    <div class="card-body text-center">
                        <img src="${pageContext.request.contextPath}/image/${avatar}" class="avatar" alt="Ảnh cá nhân">
                        <h5 class="card-title">${firstname} ${lastname}</h5>
                        <a href="profile" class="btn btn-outline-primary" style="color: black;border-color: #ff4880#4d65f9;">Thay Đổi Thông Tin</a>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <div class="card personal-info-card">
                    <div class="card-header text-center" style="background-color: #f8d9e1">
                        <h3>Thay Đổi Mật Khẩu</h3>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <label for="old-password">Mật khẩu cũ</label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="old-password" placeholder="Mật khẩu cũ"
                                       name="oldPassword"
                                       aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="new-password">Mật khẩu mới</label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="new-password" placeholder="Mật khẩu mới" name="newPassword"
                                       aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>
                                <div class="invalid-feedback">
                                    Mật khẩu chứa số hoặc chữ cái độ dài từ 6 đến 32 kí tự
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="re-password">Nhập lại mật khẩu</label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="re-password" placeholder="Nhập lại mật khẩu"
                                       name="rePassword"
                                       aria-describedby="inputGroupPrepend" pattern="^[a-zA-Z0-9]{6,32}$" required>
                                <div class="invalid-feedback">
                                    Mật khẩu không trùng nhau
                                </div>
                            </div>

                        </div>


                        <button class="btn btn-primary" type="submit" style="border-radius: 0px;">Cập nhật</button>
                        <p style="color: green">${thongbao2}</p>
                        <p>${errorMessage}</p>
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
    // Password validation
    const passwordField = document.getElementById("new-password");
    const rePasswordField = document.getElementById("re-password");

    rePasswordField.addEventListener("input", function () {
        if (passwordField.value !== rePasswordField.value) {
            rePasswordField.setCustomValidity("Mật khẩu không khớp");
        } else {
            rePasswordField.setCustomValidity("");
        }
    });

    document.querySelector("form").addEventListener("submit", function (event) {
        if (passwordField.value !== rePasswordField.value) {
            event.preventDefault();
        }
    });

    // Bootstrap validation
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/fc6bd51969.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js" integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="${pageContext.request.contextPath}/assets/js/adreess.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
