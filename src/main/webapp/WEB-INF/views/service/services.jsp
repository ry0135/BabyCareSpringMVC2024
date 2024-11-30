<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    /* Basic Reset */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    /* Category Container */
    .category {
        padding: 20px;
        max-width: 800px;
        margin: 0 auto;
        display: flex;
        align-items: center;
        gap: 20px;
    }
    .category-container {
        display: flex;
        flex-wrap: wrap;
        gap: 0px;
        justify-content: center;
        padding: 20px 0;
    }
    /* Service List */
    .service-list {
        flex: 1;
        display: flex;
    }

    .service-list a {

        font-size: 20px;
        text-decoration: none;
        color: #007bff;
        font-weight: bold;
    }

    .service-list ul {
        display: flex;
        list-style-type: none;
        margin-top: 10px;
        padding-left: 0;
    }

    .service-list li {
        margin: 10px;
    }

    .service-list li a {

        font-size: 16px;
        text-decoration: none;
        color: #555;
    }

    .service-list li a:hover {
        text-decoration: underline;
        color: #007bff;
    }
    .search{

        margin-right: 20px;
    }
    /* Search Form */
    .search-form {
        width: 400px;
        display: flex;
        align-items: center;

    }

    .search-form input[type="search"] {

        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;

        flex: 1;
    }

    .search-form button {
        padding: 15px 10px;
        border: none;
        background-color: #007bff;
        color: white;
        border-radius: 4px;
        margin-left: 10px;
        cursor: pointer;
        font-size: 12px;
        width: 100px;

    }

    .search-form button:hover {
        background-color: #0056b3;

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
    .product-name{

    }
    .product-price{
        position: absolute;
        top: 95px;
        font-size: 12px;
    }
    .category-item {
        flex: 0 0 calc(12.5% - 10px); /* 100% / 8 - 10px (khoảng cách giữa các ô) */
        max-width: calc(12.5% - 10px); /* Đảm bảo kích thước tối đa của mỗi ô */
        text-align: center;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        height: 120px; /* Đặt chiều cao cố định cho mỗi ô */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .category-item img {
        width: 80px;
        height: 80px;
        border-radius: 50%;
    }
    .category-item a {
        text-decoration: none;
        color: #000;
        display: block;
        width: 90%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 10px 0;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #fff;
        transition: background-color 0.3s ease;
    }
    .category-item a:hover {
        background-color: #f8f9fa;
        color: #ff4880;
        transform: scale(1.05);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .category-item p {
        margin:  0 0;
        font-size: 14px;
        text-align: center;
    }

</style>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!-- Services Start -->
<div class="header-carousel-item">
    <img src="assets/images/h/h3.jpg" class="img-fluid w-100" style="height: 600px" alt="Image">

    <div class="carousel-caption">
        <div class="carousel-caption-content p-3">
            <h5 class="text-white text-uppercase fw-bold mb-4" style="letter-spacing: 3px;">Trung Tâm Dịch Vụ</h5>
            <h1 class="display-1 text-capitalize text-white mb-4">Giải Pháp Tốt Nhất Cho Bạn</h1>
            <p class="mb-5 fs-5">Chúng tôi luôn sẵn sàng mang lại những dịch vụ tốt nhất cho bạn.</p>
        </div>
    </div>
</div>

<div class="container-fluid py-5">
    <div class="container">
        <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
            <h6 class="text-primary text-uppercase">DỊCH VỤ</h6>
            <h1 class="display-5  mb-0" >ĐẶT DỊCH VỤ TRỰC TUYẾN CHO BÉ CỦA BẠN</h1>
        </div>
        <div class="category-container">
            <c:forEach var="t" items="${listC}">
                <div class="category-item">
                    <a href="serviceType?tid=${t.typeID}">  <!-- Thay từ type thành typeID -->
                        <img src="${t.typeImg}" alt="${t.typeName}">  <!-- Bỏ phần comment để sử dụng hình ảnh -->
                        <p>${t.typeName}</p>
                    </a>
                </div>
            </c:forEach>
        </div>




        <div class="search">
            <form class="search-form" onsubmit="return false;">
                <input type="search" id="searchInput" placeholder="Tìm kiếm...">
                <button type="button" onclick="searchServices()">Tìm Kiếm</button> <!-- Sử dụng type="button" để ngăn form gửi -->
            </form>
        </div>



    </div>
    <div class="row g-5" style="margin: 10px" id="serviceList">
        <c:forEach items="${ListS}" var="S">
            <div class="col-lg-3 col-md-4 col-sm-6 service-item" data-name="${S.serviceName.toLowerCase()}" style="margin-top: 12px">
                <a href="getservicedetail?serviceID=${S.serviceID}" class="product-item-link">
                    <div class="product-item position-relative bg-white d-flex flex-column text-center">
                        <div class="product-image">
                            <img class="img-fluid" src="${S.listImg}" alt="">
                        </div>
                        <div class="product-details">
                            <h4 class="product-name" style="font-size: 14px; text-align: left;">${S.serviceName}</h4>
                            <h5 class="text-primary mb-0 product-price">${S.getServicePrice()}₫ </h5>
                        </div>
                        <h6 class="text-primary text-uppercase" style="font-size: 14px">xem thêm >></h6>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>


</div>
</div>
<!-- Services End -->


<div class="row mt-5">
    <div class="col-12">
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:forEach var="i" begin="1" end="${numberPage}">
                    <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                        <a class="page-link" href="service?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>

<div class="container-fluid about bg-light py-5">
    <div class="container py-5">
        <div class="row g-5 align-items-center">
            <div class="col-lg-5 wow fadeInLeft" data-wow-delay="0.2s">
                <div class="about-img pb-5 ps-5">
                    <img src="images/y/y5.jpg" class="img-fluid rounded w-100" style="object-fit: cover;" alt="Image">
                </div>
            </div>
            <div class="col-lg-7 wow fadeInRight" data-wow-delay="0.4s">
                <div class="section-title text-start mb-5">

                    <h1 class=" mb-4" style="font-weight: 600">Chúng tôi luôn sẵn sàng mang lại những dịch vụ tốt nhất cho bạn.</h1>
                    <p class="mb-4">Babycare là trang thông tin dành cho Bố và Bé, chuyên cung cấp các dịch vụ tiện ích cho em bé. Nhằm giúp cho độc giả có những quyết định tốt trong việc chăm sóc sức khỏe, xây dựng đời sống gia đình và nuôi dạy trẻ tốt hơn</p>
                    <div class="mb-4">
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Dịch vụ chuyên nghiệp và nhanh chóng</p>
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Cung cấp đồ trang trí, nội thất và phụ kiện theo yêu cầu</p>
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Chi Phí hợp lý</p>
                    </div>
                    <a href="#" class="btn btn-primary rounded-pill text-white py-3 px-5">Hot</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function searchServices() {
        // Lấy giá trị từ input tìm kiếm
        let input = document.getElementById('searchInput');
        let filter = input.value.toLowerCase().trim(); // Chuyển giá trị thành chữ thường và loại bỏ khoảng trắng
        let serviceList = document.getElementById('serviceList'); // Danh sách dịch vụ
        let services = serviceList.getElementsByClassName('service-item'); // Lấy tất cả dịch vụ

        // Lặp qua từng dịch vụ và kiểm tra tên có khớp không
        for (let i = 0; i < services.length; i++) {
            let serviceName = services[i].getAttribute('data-name'); // Lấy tên dịch vụ

            if (serviceName) {
                // Kiểm tra xem tên dịch vụ có chứa giá trị nhập vào không
                if (serviceName.indexOf(filter) > -1) {
                    services[i].style.display = ""; // Hiện dịch vụ nếu có khớp
                } else {
                    services[i].style.display = "none"; // Ẩn dịch vụ nếu không khớp
                }
            }
        }
    }
</script>

