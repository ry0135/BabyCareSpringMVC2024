<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
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
    position: relative; /* Đặt vị trí tương đối để chứa thông báo */
    border-bottom: 1px solid #ddd; /* Đường viền ngăn cách */
  }

  .product-image img {
    width: 100%; /* Đặt chiều rộng của hình ảnh để chiếm hết chiều rộng của khung */
    height: 120px; /* Đặt chiều cao của hình ảnh để chiếm hết chiều cao của khung */
    object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
  }

  .out-of-stock {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Màu nền bán trong suốt */
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    opacity: 1; /* Đặt độ mờ 100% để thông báo hiện rõ */
    z-index: 1; /* Đảm bảo thông báo nằm trên cùng */
  }

  .product-item.out-of-stock .out-of-stock {
    visibility: visible; /* Hiển thị thông báo khi sản phẩm hết hàng */
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

  .category-container {
    display: flex;
    flex-wrap: wrap;
    gap: 0px;
    justify-content: center;
    padding: 20px 0;
  }

  .category-item {
    flex: 0 0 calc(12.5% - 10px); /* 100% / 8 - 10px (khoảng cách giữa các ô) */
    max-width: calc(12.5% - 10px); /* Đảm bảo kích thước tối đa của mỗi ô */
    text-align: center;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    height: 150px; /* Đặt chiều cao cố định cho mỗi ô */
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
    margin: 0;
    font-size: 14px;
    text-align: center;
  }
</style>

<jsp:include page="/WEB-INF/views/include/header.jsp" />

<div class="container-fluid d-none d-lg-block pt-5">
  <div class="container">
    <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
      <h6 class="text-primary text-uppercase">Sản Phẩm</h6>
      <form class="search-form" onsubmit="return false;">
        <input type="search" id="searchInput" placeholder="Tìm kiếm...">
        <button type="button" onclick="searchProduct()">Tìm Kiếm</button>
      </form>
    </div>
    <div class="row g-5" id="productContainer">
      <c:forEach var="product" items="${listProducts}">
        <div class="col-lg-2 col-md-4 col-sm-6 comment-item" style="margin-top: 12px;">
          <a href="getProductDetail?id=${product.productId}" class="product-item-link">
            <div class="product-item position-relative bg-white d-flex flex-column text-center ${product.productAmount == 0 ? 'out-of-stock' : ''}">
              <div class="product-image">
                <img class="img-fluid" src="${pageContext.request.contextPath}/assets/img/${product.getImagePaths().get(0)}" alt="">
                <div class="out-of-stock" style="display: ${product.productAmount == 0 ? 'flex' : 'none'};">Hết hàng</div>
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
    <div id="pagination" class="pagination d-flex justify-content-center mt-4"></div>
  </div>
</div>

<script>
  // Hàm tìm kiếm sản phẩm
  function searchProduct() {
    let input = document.getElementById('searchInput');
    let filter = input.value.toLowerCase().trim();
    let productContainer = document.getElementById('productContainer');
    let products = productContainer.getElementsByClassName('comment-item');

    for (let i = 0; i < products.length; i++) {
      let productName = products[i].querySelector('.product-name').textContent.toLowerCase();
      if (productName.indexOf(filter) > -1) {
        products[i].style.display = "block";
      } else {
        products[i].style.display = "none";
      }
    }
  }

  // Phân trang sản phẩm
  $(document).ready(function () {
    const productsPerPage = 6; // Số sản phẩm hiển thị mỗi trang
    const products = $("#productContainer .comment-item");
    const totalPages = Math.ceil(products.length / productsPerPage);
    const pagination = $("#pagination");

    function showPage(page) {
      products.hide();
      let start = (page - 1) * productsPerPage;
      let end = start + productsPerPage;
      products.slice(start, end).show();
    }

    for (let i = 1; i <= totalPages; i++) {
      let button = $("<button>").text(i);
      button.on("click", function () {
        showPage(i);
        $(".pagination .active").removeClass("active");
        $(this).addClass("active");
      });
      pagination.append(button);
    }

    showPage(1);
    pagination.find("button:first").addClass("active");
  });
</script>