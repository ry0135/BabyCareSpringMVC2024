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
            </div>

    </body>
</html>
