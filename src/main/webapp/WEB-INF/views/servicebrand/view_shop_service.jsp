<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<style>
  /* Tổng thể của Container */
  .container {

    margin: 0 auto;
    padding: 20px;
  }
  /* Định kiểu chung cho hàng và cột */
  .row {
    margin-bottom: 20px;
  }

  .col-md-3 {
    text-align: center;
  }

  .col-md-9 {
    padding-left: 20px;
  }

  /* Định kiểu logo thương hiệu */
  .d-flex.align-items-center img {
    width: 70px; /* Kích thước ảnh cố định */
    height: 70px;
    border-radius: 50%;
    margin-right: 15px; /* Khoảng cách giữa logo và tên */
    object-fit: cover; /* Cắt ảnh theo tỷ lệ đúng */
    border: 2px solid #007bff; /* Đường viền xanh cho nổi bật */
  }

  /* Định kiểu tên thương hiệu */
  .d-flex.align-items-center h1 {
    font-size: 24px;
    font-weight: bold;
    color: #333; /* Màu chữ nổi bật */
    margin: 0;
  }

  /* Nút nhắn tin */
  form .button {
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    font-size: 14px;
    font-weight: bold;
    transition: background-color 0.3s ease;
    cursor: pointer;
    margin-top: 10px;
  }

  form .button:hover {
    background-color: #0056b3;
  }

  /* Phần thông tin cửa hàng */
  .stats {
    font-size: 14px;
    color: #555;
    margin: 5px 0;
  }

  /* Nhấn mạnh giá trị quan trọng */
  .stats span {
    font-weight: bold;
    color: #007bff;
  }

  /* Giao diện phần đánh giá và thông tin */
  .col-md-6 {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  /* Khoảng cách giữa các đoạn văn */
  p.stats {
    line-height: 1.5;
    margin: 8px 0;
  }


  /* Định kiểu cho thông tin cửa hàng */
  .store-info {
    margin-bottom: 30px;
  }

  /* Hiệu ứng cho mục dịch vụ */
  .service-item {
    transition: transform 0.3s;
  }

  .service-item:hover {
    transform: scale(1.05);
  }

  /* Định kiểu cho sản phẩm */
  .product-item {
    border-radius: 10px;
    overflow: hidden;
    background-color: #f8f9fa;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    text-align: center; /* Định giữa văn bản */
  }

  /* Cách định dạng cho hình ảnh */
  .product-item img {
    object-fit: cover;
    height: 200px;
    width: 100%;
  }

  /* Định kiểu cho tiêu đề sản phẩm */
  .product-item h5 {
    font-size: 16px;
    margin: 10px 0;
  }

  /* Định kiểu cho giá sản phẩm */
  .text-primary {
    color: #007bff;
    font-weight: bold;
  }

  /* Nút yêu thích */


  /* Định kiểu cho phân trang */
  .pagination {
    margin-top: 20px;
  }

  .pagination button {
    margin: 0 5px;
    padding: 10px 15px;
    border: none;
    background-color: #007bff;
    color: white;
    border-radius: 5px;
    cursor: pointer;
  }

  .pagination button.active {
    background-color: #0056b3;
  }

  .pagination button:hover {
    background-color: #0056b3;
  }
  .service-item h5 {
    white-space: nowrap; /* Không xuống dòng */
    overflow: hidden; /* Ẩn phần nội dung vượt quá */
    text-overflow: ellipsis; /* Thêm dấu "..." cho phần bị ẩn */
    display: block; /* Đảm bảo nó là khối */
    max-width: 100%; /* Giới hạn chiều rộng theo khung chứa */
  }
  .service-item {
    max-width: 250px; /* Chiều rộng cố định cho từng mục */
  }
  .search-form {
    display: flex;
    margin-bottom: 20px; /* Tăng khoảng cách dưới ô tìm kiếm */
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

  h6, .h6, h5, .h5, h4, .h4, h3, .h3, h2, .h2, h1, .h1 {
    font-family: sans-serif;
  }
</style>
<div class="container">
  <div class="store-info">
    <div class="row">
      <div class="col-md-3">
        <div class="d-flex align-items-center mb-2">
          <img style="width: 60%; height: 50%; border-radius: 50%;" src="${pageContext.request.contextPath}/image/${brand.brandLogo}" alt="${brand.brandName} Logo">
          <h1>${brand.brandName}</h1>
        </div>


        <a href="chatMessage?toId=${brand.ctvID}" style="display: inline-block; width: 116px; padding: 10px; text-align: center; background-color: #007BFF; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s;">
          Nhắn tin
        </a>

      </div>
      <div class="col-md-9 row">
        <div class="col-md-6">
          <p class="stats">Đánh Giá: 125</p>
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
  <div class="row align-items-center mb-4">
    <div class="col-lg-8 col-md-7">
      <form class="search-form d-flex" onsubmit="return false;">
        <a href="service" class="btn btn-warning fw-bold me-2">ALL</a>
        <input type="search" id="searchInput" class="form-control" placeholder="Tìm kiếm dịch vụ...">
        <button type="button" class="btn btn-primary ms-2" onclick="searchServices()">Tìm Kiếm</button>
      </form>
    </div>
    <div class="col-lg-4 col-md-5 text-end">
      <a href="listfavorite" class="btn btn-warning fw-bold">DICH VỤ YÊU THÍCH</a>
    </div>
  </div>
  <div class="container mt-4">

    <c:if test="${not empty message}">
      <div class="alert alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="row g-4" id="serviceList">
      <c:forEach items="${ListS}" var="S">
        <div class="col-lg-3 col-md-4 col-sm-6 service-item" data-name="${S.serviceName.toLowerCase()}">
          <div class="product-item position-relative bg-light shadow rounded">
            <img class="img-fluid rounded-top" src="${pageContext.request.contextPath}/image/${S.listImg}" alt="">
            <div class="p-3 text-center">
              <h5 class="fw-bold">${S.serviceName}</h5>
              <span class="text-primary fw-bold">${S.getServicePrice()}₫</span>
            </div>
            <div class="d-flex justify-content-between p-3">
              <a href="getservicedetail?serviceID=${S.serviceID}&&CTVID=${S.cTVID}" class="btn btn-info">Xem thêm</a>
              <a href="addfavorite?serviceID=${S.serviceID}"
                 class="btn btn-warning"
                 onclick="markAsFavorite(this)">
                💖
              </a>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>


  </div>
  <div id="pagination" class="pagination d-flex justify-content-center mt-4"></div>
</div>


<script>
  document.addEventListener("DOMContentLoaded", function () {
    const products = Array.from(document.querySelectorAll(".service-item"));
    const productsPerPage = 8 ; // Số sản phẩm trên mỗi trang
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
        if (i === currentPage) button.classList.add("active");

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
  function searchServices() {
    // Lấy giá trị từ ô tìm kiếm
    let input = document.getElementById('searchInput');
    let filter = input.value.toLowerCase().trim(); // Chuyển giá trị thành chữ thường và loại bỏ khoảng trắng
    let serviceList = document.getElementById('serviceList'); // Danh sách dịch vụ
    let services = serviceList.getElementsByClassName('service-item'); // Lấy tất cả dịch vụ

    // Lặp qua từng dịch vụ và kiểm tra tên có khớp không
    for (let i = 0; i < services.length; i++) {
      let serviceName = services[i].getAttribute('data-name'); // Lấy tên dịch vụ
      if (serviceName) {
        // Kiểm tra xem tên dịch vụ có chứa giá trị nhập vào không
        services[i].style.display = serviceName.indexOf(filter) > -1 ? "" : "none"; // Hiện hoặc ẩn dịch vụ
      }
    }
  }
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