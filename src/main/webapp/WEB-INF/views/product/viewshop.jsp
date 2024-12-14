<%--
  Created by IntelliJ IDEA.
  User: kimph
  Date: 12/12/2024
  Time: 11:35 SA
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
    .product-container, .store-info, .product-details {
        padding: 20px;
        margin-top: 20px;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        border-radius: 2px;
    }
    .container{
        margin: 0px;
    }
    .product-item-link {
        text-decoration: none;
    }
    .product-item-link .product-item {
        border: 1px solid #ddd;
        transition: transform 0.3s ease, border-color 0.3s ease;
        height: 250px; /* Cố định chiều cao của sản phẩm */
        display: flex;
        flex-direction: column;
    }
    .product-item-link:hover .product-item,
    .product-item-link.active .product-item {
        transform: scale(1.05);
        border-color: #ff4880; /* màu viền khi active */
    }
    .product-image, .product-details {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .product-image {
        border-bottom: 1px solid #ddd; /* Đường viền ngăn cách */
    }
    .product-image img {
        width: 100%; /* Đặt chiều rộng của hình ảnh để chiếm hết chiều rộng của khung */
        height: 120px; /* Đặt chiều cao của hình ảnh để chiếm hết chiều cao của khung */
        object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
    }
    .product-details {
        position: relative;
        display: block;
        text-align: center;
        padding: 10px;
    }
    .product-name {
        font-size: 12px;
        text-align: left;
    }
    .product-price {
        position: absolute;
        top: 95px;
        font-size: 12px;
    }
    .pagination button {
        border: none;
        background-color: #f8f9fa; /* Màu nền */
        color: #007bff; /* Màu chữ */
        padding: 10px 15px;
        margin: 0 5px;
        cursor: pointer;
        transition: background-color 0.3s ease, color 0.3s ease;
        border-radius: 5px;
    }
    .pagination button:hover {
        background-color: #ff4880; /* Màu nền khi hover */
        color: #fff; /* Màu chữ khi hover */
    }
    .pagination button.active {
        background-color: #ff4880; /* Màu nền khi active */
        color: #fff; /* Màu chữ khi active */
    }

</style>
<div class="container">
    <div class="store-info" style="width: 122%; margin: 10px;">
        <div class="row">
            <div class="col-md-3" style="background-image: url('https://st2.depositphotos.com/2124221/46809/i/450/depositphotos_468095768-stock-photo-abstract-multicolored-background-poly-pattern.jpg');
                 height: 120px; background-position: center; background-repeat: no-repeat; ">
                <div class="d-flex" style="align-items: center; margin-bottom: 5px;">
                    <img style="width: 22%;height: 50%;border-radius: 50%;" src="img/brand/${brand.brandLogo}" alt="Store Logo">
                    <h1>${brand.brandName}</h1>
                </div>
                <form action="chat">
                    <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">
                    <button class="button">Nhắn tin</button>
                </form>
            </div>
            <div class="col-md-9 row">
                <div class="col-md-6">
                    <p class="stats">Đánh Giá: 125</p>
                    <p class="stats">Sản Phẩm: ${productCount}</p>
                    <p class="stats">Tỉ Lệ Phản Hồi: 100%</p>
                </div>
                <div class="col-md-6">
                    <p class="stats">Thời Gian Phản Hồi: trong vài giờ</p>
                    <p class="stats">Tham Gia: 48 ngày trước</p>
                    <p class="stats">Người Theo Dõi: 688</p>
                </div>

            </div>

        </div>
    </div>
    <div class="row g-5 product-container " style=" width: 122%; margin: 20px 20px; box-shadow: none;
         border-radius: 0;;" id="productContainer">
        <c:forEach var="product" items="${listProduct}">
            <div class="col-lg-2 col-md-4 col-sm-6 comment-item" style="margin-top: 12px;">
                <a href="getProductDetail?id=${product.productId}" class="product-item-link">
                    <div class="product-item position-relative bg-white d-flex flex-column text-center">
                        <div class="product-image">
                            <img class="img-fluid" src="img/${product.img}" alt="">
                        </div>
                        <div class="product-details">
                            <h6 class="product-name">${product.productName}</h6>
                            <h5 class="text-primary mb-0 product-price">₫ ${product.getPriceString()}</h5>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    <div id="pagination" class="pagination d-flex justify-content-center mt-4">
        <!-- Pagination buttons will be added here -->
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const products = Array.from(document.querySelectorAll(".comment-item"));
        const productsPerPage = 30;
        const pagination = document.getElementById("pagination");
        let currentPage = 1;
        const totalPages = Math.ceil(products.length / productsPerPage);

        function showPage(page) {
            products.forEach((product, index) => {
                product.style.display = (index >= (page - 1) * productsPerPage && index < page * productsPerPage) ? "block" : "none";
            });
        }

        function createPagination() {
            pagination.innerHTML = "";
            for (let i = 1; i <= totalPages; i++) {
                const button = document.createElement("button");
                button.textContent = i;
                button.classList.add("page-btn");
                if (i === currentPage)
                    button.classList.add("active");
                button.addEventListener("click", function () {
                    currentPage = i;
                    showPage(currentPage);
                    document.querySelector(".pagination .active").classList.remove("active");
                    button.classList.add("active");
                });
                pagination.appendChild(button);
            }
        }

        showPage(currentPage);
        createPagination();
    });
</script>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#productContainer .col").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });

        // Thêm lớp active khi click vào sản phẩm
        $(".product-item-link").on("click", function () {
            $(".product-item-link").removeClass("active");
            $(this).addClass("active");
        });
    });
</script>
