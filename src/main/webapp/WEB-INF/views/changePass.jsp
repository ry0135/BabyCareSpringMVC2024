<%--
  Created by IntelliJ IDEA.
  User: kimph
  Date: 03/12/2024
  Time: 6:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container mt-3">
    <form class="needs-validation" novalidate action="<c:url value='/changepass'/>" method="post">
        <div class="row">
            <div class="col-md-3" style="background-color: hwb(48 90% 2%); border-radius: 10px; width: 200px; height: 250px; margin-right: 20px;">
                <div>
                    <div class="col-12">
                        <div style="margin: 15px;">
                            <img style="width: 50px; height: 83%; border-radius: 50%; margin-top: 5px;" src="img/${avatar}" class="img-fluid" alt="Ảnh cá nhân">
                        </div>
                        <h2 style="font-size: 15px; font-weight: 550;">${firstname} ${lastname}</h2>
                    </div>
                </div>
                <section>
                    <a href="<c:url value='/profile'/>" class="icon-link">
                        <i class="glyphicon glyphicon-user icon"></i>
                        <span class="text">Profile</span>
                    </a>
                </section>
            </div>

            <div class="col-md-9" style="background-color: hwb(319 83% 3%); border-radius: 10px; padding: 1rem; margin-bottom: 2rem;">
                <div class="row">
                    <div class="container mt-3">
                        <div class="row">
                            <div class="col-12" style="border-radius: 10px; text-align: center;">
                                <h2 style="font-weight: 600; color: white; font-size: 22px; font-family: 'Roboto', sans-serif;">Change Password</h2>
                            </div>

                            <!-- Input Old Password -->
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="old-password">Mật khẩu cũ</label>
                                <div class="input-group">
                                    <input type="password" class="form-control" id="old-password" placeholder="Mật khẩu cũ" name="oldPassword" required>
                                    <div class="invalid-feedback">
                                        Vui lòng nhập mật khẩu cũ.
                                    </div>
                                </div>
                            </div>

                            <!-- Input New Password -->
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="password">Mật khẩu mới</label>
                                <div class="input-group">
                                    <input type="password" class="form-control" id="password" placeholder="Mật khẩu mới" name="password" required>
                                    <div class="invalid-feedback">
                                        Mật khẩu phải chứa ít nhất 6 ký tự và tối đa 32 ký tự.
                                    </div>
                                </div>
                            </div>

                            <!-- Input Re-enter New Password -->
                            <div class="col-md-6 offset-3 mb-3">
                                <label for="re-password">Nhập lại mật khẩu mới</label>
                                <div class="input-group">
                                    <input type="password" class="form-control" id="re-password" placeholder="Nhập lại mật khẩu" name="re-password" required>
                                    <div class="invalid-feedback">
                                        Mật khẩu không trùng nhau.
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-3 offset-3">
                                    <button class="btn btn-primary" type="submit">Cập nhật</button>
                                    <p style="color: green">${thongbao2}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    // Xử lý xác thực cho hai trường mật khẩu
    const passwordField = document.getElementById("password");
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
            Array.prototype.forEach.call(forms, function (form) {
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
