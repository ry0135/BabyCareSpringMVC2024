<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Sản Phẩm Mới</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;               /* Loại bỏ margin */
                padding: 0;              /* Loại bỏ padding */
                background-color: #f4f4f4;
            }

            .container {
                width: 100%;             /* Đặt width là 100% */
                margin: 0;               /* Loại bỏ margin */
                padding: 20px;           /* Thêm padding nếu cần thiết */
                background: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }


            .sidebar {
                width: 200px;
                background-color: #f7f7f7;
                padding: 20px;
            }

            .sidebar ul {
                list-style-type: none;
                padding: 0;
            }

            .sidebar ul li {
                margin: 10px 0;
                color: #333;
                cursor: pointer;
            }

            .main-content {
                border: 1px solid #ddd;
                background-color: #fff;
                padding: 20px;
                margin-top: 20px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                flex: 1;
                padding: 20px;
                margin-left: 20px;
            }

            .tabs {
                display: flex;
                margin-bottom: 20px;
            }

            .tab-button {
                padding: 10px 20px;
                border: none;
                background-color: #f7f7f7;
                color: #333;
                cursor: pointer;
                border-right: 1px solid #ddd;
            }

            .tab-button.active {
                background-color: #e0e0e0;
            }


            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                transition: 0.4s;
                border-radius: 20px;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 12px;
                width: 12px;
                left: 4px;
                bottom: 4px;
                background-color: white;
                transition: 0.4s;
                border-radius: 50%;
            }

            input:checked + .slider {
                background-color: #4caf50;
            }

            input:checked + .slider:before {
                transform: translateX(14px);
            }


            .sidebar {
                width: 200px;
                background-color: #f7f7f7;
                padding: 20px;
            }

            .main-content {
                flex: 1;
                padding: 20px;
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
            .invalid-feedback {
                display: none;
            }
            .input-group {
                margin-bottom: 20px;
            }
            .form-control {
                height: 30px;
            }

            .upload-area {
                border: 2px dashed #FF5722;
                border-radius: 8px;
                width: 110px;
                height: 80px;
                display: flex;
                justify-content: center;
                align-items: center;
                position: relative;
            }

            .file-input {
                display: none;
            }

            .upload-preview {
                width: 100%;
                height: 100%;
                display: flex;
                flex-wrap: nowrapwrap;
                gap: 10px;

            }

            .img-preview {
                width: 100%;
                height: 100%;
                border: 1px solid #ddd;
                border-radius: 5px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <div class="container ">
            <%@include file="sidebar_brand.jsp" %>


            <div class="  col-lg-9" id="basic-info">
                <div class="tabs">
                    <button class="tab-button" onclick="scrollToContent('basic-info')">Thông tin cơ bản</button>
                    <button class="tab-button" onclick="scrollToContent('detailed-info')">Thông tin chi tiết</button>
                    <button class="tab-button" onclick="scrollToContent('shipping-info')">Vận chuyển</button>
                    <button class="tab-button" onclick="scrollToContent('other-info')">Thông tin khác</button>
                </div>
                <form class="needs-validation" novalidate action="addproduct" method="post" enctype="multipart/form-data">
                    <div class="main-content">

                        <div style=";height: 400px; " >
                            <div class="row">
                                <label class="col-lg-2"><span class="text-danger">*</span>Tên sản phẩm</label>
                                <div class="input-field col-lg-10">
                                    <div class="input-group">
                                        <input pattern="^.{1,150}$" type="text" class="form-control" id="foodName" placeholder="Tên sản phẩm" name="productName" required>
                                        <div class="invalid-feedback">Tối đa từ 1-150 ký tự</div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-lg-2"><span class="text-danger">*</span>Ngành Hàng</label>
                                <div class="input-field col-lg-10">
                                    <div class="input-group">
                                        <input type="button" id="productType" value="Chọn ngành hàng..." onclick="showModal()" style="width: 100%;"/>
                                    </div>
                                    <input type="hidden" name="productType" id="hiddenProductType" value="" />
                                </div>
                            </div>

                            <div class="row">

                                <label class="col-lg-2"><span class="text-danger">*</span>Xuất xứ sản phẩm</label>
                                <div class="input-field col-lg-10">
                                    <div class="input-group">
                                        <input type="text" pattern="^.{1,30}$" class="form-control" id="productOrigin" placeholder="Xuất xứ sản phẩm" name="productOrigin" required>
                                        <div class="invalid-feedback">Trường này là bắt buộc, độ dài tối đa 30 ký tự</div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <label class="col-lg-2"><span class="text-danger">*</span>Mô tả sản phẩm</label>

                                <div class="input-field col-lg-10">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="productDescription" placeholder="Mô tả sản phẩm" name="productDescription">
                                    </div>
                                </div>
                            </div>

                            <div class="upload-container row">
                                <label for="fileUpload" class="upload-label col-lg-2"> <span class="text-danger">*</span> Hình ảnh sản phẩm</label>
                                <div class="upload-area col-lg-1" onclick="triggerFileUpload()">
                                    <input type="file" id="fileUpload" name="productImages" class="file-input" accept="image/*" multiple onchange="previewMultipleImages(this)">
                                    <div id="upload-preview-container" class="upload-preview"></div>
                                </div>
                            </div>

                        </div>
                    </div>


                    <style>


                        .modal {
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            position: fixed;
                            top: 0;
                            left: 0;
                            right: 0;
                            bottom: 0;
                            background-color: rgba(0, 0, 0, 0.5);
                        }

                        .modal-content {
                            background-color: #fff;
                            padding: 20px;
                            border-radius: 5px;
                            width: 700px;
                            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                        }

                        .selections {
                            display: flex;
                            justify-content: space-between;
                            margin: 20px 0;
                        }

                        ul {
                            list-style: none;
                            padding: 0;
                        }

                        li {
                            padding: 5px;
                            cursor: pointer;
                        }

                        li:hover {
                            background-color: #f0f0f0;
                        }

                        .selected {
                            background-color: #e0e0e0;
                        }

                        .actions {
                            display: flex;
                            justify-content: space-between;
                        }

                        button {
                            padding: 10px 15px;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                        }

                        .cancel-btn {
                            background-color: #ccc;
                        }

                        .confirm-btn {
                            background-color: #ff5722;
                            color: white;
                        }

                    </style>

                    <div class="modal" id="categoryModal" style="display: none;">
                        <div class="modal-content">
                            <h2>Chỉnh sửa ngành hàng</h2>
                            <input type="text" placeholder="Vui lòng nhập tối thiểu 1 ký tự." class="search-input">

                            <div class="selections row">
                                <div class="col-lg-6" style="width: 200px">
                                    <h4>Ngành hàng</h4>
                                    <ul id="parentCategories">
                                        <c:forEach var="category" items="${categories}">
                                            <div class="row">
                                                <li class="col-lg-9" onclick="fetchSubcategories(${category.categoryID})">${category.categoryName}</li>
                                                <span class="col-lg-2">></span>
                                            </div>
                                        </c:forEach>
                                    </ul>
                                </div>


                                <div class="col-lg-6">
                                    <h4>Danh mục con</h4>
                                    <ul id="subcategories">
                                        <!-- Danh mục con sẽ hiển thị ở đây -->
                                    </ul>
                                </div>


                            </div>
                            <input type="text" id="productType" placeholder="Chọn danh mục" readonly />
                            <input type="hidden" id="selectedCategoryID" name="selectedCategoryID" />
                            <div class="actions">
                                <div class="cancel-btn" onclick="closeModal()" style="display: inline-block; padding: 10px; background-color: #ccc; border-radius: 5px; cursor: pointer; margin-right: 10px;">
                                    Hủy
                                </div>
                                <div class="confirm-btn" onclick="confirmSelection()" style="display: inline-block; padding: 10px; background-color: #ff5722; color: white; border-radius: 5px; cursor: pointer;">
                                    Xác nhận
                                </div>
                            </div>
                        </div>
                    </div>





                    <div class="main-content" id="detailed-info">
                        <h4>Thông tin bán hàng</h4>



                        <div class="row">

                            <label for="" class="col-lg-2"><span class="text-danger">*</span>Giá sản phẩm</label>
                            <div class="input-field col-lg-10">
                                <input type="text" pattern="^\d{1,8}(.\d{1,2})?$" class="form-control" id="foodPrice" placeholder="Giá sản phẩm" name="productPrice" required>
                                <div class="invalid-feedback">Giá tiền chỉ bao gồm chữ số</div>
                            </div>

                        </div>

                        <div class="input-field  mt-5 row">
                            <label class="col-lg-2" for="stock"><span class="text-danger">*</span>Kho hàng:</label>
                            <div class="input-field  col-lg-10">
                                <input class="form-control col-lg-10" type="number" name="productAmount" id="stock" value="0" required>
                            </div>

                        </div>


                    </div>

                    <div class="main-content " id="shipping-info">

                        <header>
                            <h3>Vận chuyển</h3>
                        </header>
                        <div class="row">

                            <label for="" class="col-lg-2"><span class="text-danger">*</span>Cân nặng (Sau khi đóng gói):</label>
                            <div class="input-field col-lg-10">
                                <input type="text" pattern="^\d{1,8}(.\d{1,2})?$" class="form-control" id="weight" placeholder="Giá sản phẩm" name="weight" required>
                                <div class="invalid-feedback">Giá tiền chỉ bao gồm chữ số</div>
                            </div>

                        </div>

                        <div class="form-group">
                            <label>Kích thước đóng gói thực tế (Phi vận chuyển thực tế sẽ thay đổi nếu bạn nhập sai kích thước)</label>
                            <div class="dimensions">
                                <input type="text" name="R" placeholder="R" required>
                                <span>cm</span>
                                <input type="text" name="D" placeholder="D" required>
                                <span>cm</span>
                                <input type="text"  name="C" placeholder="C" required>
                                <span>cm</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="shippingCost">Chi phí vận chuyển:</label>
                            <input type="text" id="shippingCost" name="shippingCost" class="form-control" readonly>
                        </div>

                        <script>
                            function calculateShippingCost() {
                                // Lấy giá trị từ các trường nhập liệu
                                const weight = parseFloat(document.getElementById('weight').value) || 0;
                                const length = parseFloat(document.querySelector('input[placeholder="R"]').value) || 0;
                                const width = parseFloat(document.querySelector('input[placeholder="D"]').value) || 0;
                                const height = parseFloat(document.querySelector('input[placeholder="C"]').value) || 0;

                                // Tính toán thể tích
                                const volume = length * width * height; // cm³

                                // Định nghĩa phí
                                let totalShippingCost;

                                // Tính tổng chi phí vận chuyển dựa trên cân nặng và thể tích
                                if (weight < 2 && volume < 1000) {
                                    totalShippingCost = 31000; // VNĐ
                                } else {
                                    totalShippingCost = 41000; // VNĐ
                                }

                                // Hiển thị kết quả
                                document.getElementById('shippingCost').value = totalShippingCost.toLocaleString();
                            }

                            // Gọi hàm tính phí khi có thay đổi trong các trường nhập liệu
                            document.querySelectorAll('input[type="text"]').forEach(input => {
                                input.addEventListener('input', calculateShippingCost);
                            });
                        </script>


                        <div class="form-group">
                            <span>Cài đặt đơn vị vận chuyển ở đây chỉ áp dụng cho sản phẩm này.</span>
                        </div>

                    </div>
                    <div class="row mt-3">
                        <div class="col-3">
                            <button class="btn btn-primary" type="submit">Thêm</button>
                        </div>
                        <div class="col-6">${thongbao}</div>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
        <script>
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

                        function triggerFileUpload() {
                            document.getElementById('fileUpload').click();
                        }

                        function previewMultipleImages(input) {
                            const container = document.getElementById('upload-preview-container');


                            Array.from(input.files).forEach(file => {
                                const reader = new FileReader();
                                reader.onload = function (e) {
                                    const img = document.createElement('img');
                                    img.src = e.target.result;
                                    img.classList.add('img-preview'); // Thêm class để áp dụng CSS
                                    container.appendChild(img);
                                };
                                reader.readAsDataURL(file);
                            });

                            //
                        }



        </script>
        <script>
            function showModal() {
                document.getElementById('categoryModal').style.display = 'flex';
            }

            function closeModal() {
                document.getElementById('categoryModal').style.display = 'none';
            }

            function selectCategory(categoryID, categoryName) {
                // Cập nhật giá trị cho productType
                document.getElementById('productType').value = categoryName; // Cập nhật tên danh mục
                document.getElementById('hiddenProductType').value = categoryName; // Cập nhật tên danh mục
                document.getElementById('selectedCategoryID').value = categoryID; // Cập nhật ID nếu cần
                closeModal(); // Đóng modal
            }


            function confirmSelection() {
                // Xử lý xác nhận nếu cần
                alert('Đã chọn: ' + document.getElementById('productType').value);
                closeModal();
            }

            function showModal() {

                // Mở modal hoặc logic hiển thị ngành hàng (không được cung cấp ở đây)
               document.getElementById('categoryModal').style.display = 'flex';
                // Sau khi người dùng chọn ngành hàng:
                const selectedType = "Ngành hàng đã chọn"; // Thay thế với ngành hàng thực tế, có thể lấy từ modal
                document.getElementById('hiddenProductType').value = selectedType;
                document.getElementById('productType').value = selectedType; // Cập nhật giá trị vào input ẩn
            }



        </script>


        <script>
            function fetchSubcategories(parentId) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "fetchSubcategories?parent_id=" + parentId, true);
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        var subcategories = JSON.parse(xhr.responseText);
                        console.log(subcategories); // Kiểm tra dữ liệu
                        displaySubcategories(subcategories);
                    }
                };
                xhr.send();
            }

            function displaySubcategories(subcategories) {
                var subcategoryList = document.getElementById("subcategories");
                subcategoryList.innerHTML = ""; // Reset danh sách trước

                subcategories.forEach(function (sub) {
                    var li = document.createElement("li");
                    li.onclick = function () {
                        selectCategory(sub.categoryID, sub.categoryName); // Gọi hàm và truyền cả ID và tên
                    };
                    li.innerText = sub.categoryName; // Hiển thị tên danh mục
                    subcategoryList.appendChild(li);
                });
            }

            function scrollToContent(contentId) {
                // Lấy phần nội dung cụ thể
                const contentElement = document.getElementById(contentId);

                if (contentElement) {
                    // Cuộn tới phần nội dung
                    contentElement.scrollIntoView({behavior: 'smooth', block: 'start'});
                }
            }
        </script>

    </body>
</html>
