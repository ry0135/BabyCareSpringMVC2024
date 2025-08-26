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
                /*flex-wrap: nowrapwrap;*/
                gap: 10px;

            }

            .img-preview {
                width: 100%;
                height: 100%;
                border: 1px solid #ddd;
                border-radius: 5px;
                object-fit: cover;
            }
            .table-cell-ellipsis {
                width: 150px; /* Adjust the width based on your layout */
                overflow: hidden; /* Hide the overflow */
                text-overflow: ellipsis; /* For showing ellipsis */
                white-space: nowrap; /* Prevent wrapping */
                display: block; /* Display as block */
                position: relative; /* For using absolute positioning */
            }

            .table-cell-ellipsis:after {
                content: '...'; /* Add ellipsis */
                position: absolute; /* Position absolutely within the cell */
                right: 0; /* Align it to the right */
                background: white; /* Adjust based on the background color of your table */
                padding-left: 5px; /* Space between the last character and ellipsis */
            }

            .status-pending {
                color: red;
                font-weight: bold;
            }

            .status-approved {
                color: green;
                font-weight: bold;
            }
        </style>
    </head>
    <jsp:include page="/WEB-INF/views/addBrand/header_brand.jsp" />

    <body>
        <div class="container ">
            <%@include file="sidebar_brand.jsp" %>
<%--                <h2>Quản lí danh sách đơn đã hoàn thành</h2>--%>
<%--                <p>Nhân viên có thể xem được danh sách đơn đã hoàn thành ở đây</p>--%>

                <table  class="table table-bordered" id="data-table">
                    <thead>
                    <tr>
<%--                        <th>Product ID</th>--%>
                        <th>Product Name</th>
                        <th>Product Type</th>
                        <th>Product Price</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>CTV ID</th>
                        <th>Product Origin</th>
                        <th class="table-cell-ellipsis">Description</th>
                        <th>Weight</th>
                        <th>Dimensions</th>
                        <th>Shipping Cost</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody id="myTable">
                    <c:forEach var="product" items="${listProducts}">
                    <tr>
<%--                        <td>${product.productId}</td>--%>
                        <td>${product.productName}</td>
                        <td>${product.productType}</td>
                        <td>${product.productPrice}</td>
                        <td>${product.productAmount}</td>
                        <td>
                                <span class="${product.status == 1 ? 'status-approved' : 'status-pending'}">
                                        ${product.status == 1 ? 'Đã duyệt' : 'Đợi duyệt'}
                                </span>
                        </td>
                        <td>${product.CTVID}</td>
                        <td>${product.origin}</td>
                        <td class="table-cell-ellipsis">${product.productDescription}</td> <!-- Truncated Description -->
                        <td>${product.weight}</td>
                        <td>${product.dimensions}</td>
                        <td>${product.shippingCost}</td>
                        <td>
                            <a href="editProduct.jsp?productId=${product.productId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="deleteProduct?id=${product.productId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this product?');">Delete</a>
                        </td>
                    </tr>
                    </c:forEach>

    </body>
</html>
