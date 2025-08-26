<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<style>
  .product-item-link {
    text-decoration: none;
  }

  .product-item-link .product-item {
    border: 1px solid #ddd;
    transition: transform 0.3s ease, border-color 0.3s ease;
    height: 250px;
    display: flex;
    flex-direction: column;
  }

  .product-item-link:hover .product-item,
  .product-item-link.active .product-item {
    transform: scale(1.05);
    border-color: #ff4880;
  }

  .product-image, .product-details {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .product-image {
    position: relative;
    border-bottom: 1px solid #ddd;
  }

  .product-image img {
    width: 100%;
    height: 120px;
    object-fit: cover;
  }

  .out-of-stock {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    z-index: 1;
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
    background-color: #f8f9fa;
    color: #007bff;
    padding: 10px 15px;
    margin: 0 5px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease, color 0.3s ease;
  }

  .pagination button:hover {
    background-color: #ff4880;
    color: #fff;
  }

  .pagination button.active {
    background-color: #ff4880;
    color: #fff;
  }
  h6, .h6, h5, .h5, h4, .h4, h3, .h3, h2, .h2, h1, .h1 {
    font-family: sans-serif;
  }
  .product-search {
    max-width: 1300px;
    padding: 20px;
    border-left: 5px solid #e91e63; /* Đường viền màu hồng */
  }

  /* Tiêu đề */
  .text-primary {
    color: #e91e63; /* Màu hồng */
    font-weight: bold; /* Đậm chữ */
  }

  /* Form tìm kiếm */
  .search-form {
    display: flex; /* Sắp xếp các phần tử */
    align-items: center; /* Căn giữa dọc */
    gap: 10px; /* Khoảng cách giữa các phần tử */
    margin-top: 10px; /* Khoảng cách trên */
  }

  /* Ô tìm kiếm */
  #searchInput {
    flex: 1; /* Chiếm toàn bộ không gian còn lại */
    padding: 10px 15px; /* Padding trong ô */
    border: 1px solid #ccc; /* Đường viền */
    border-radius: 5px; /* Bo góc */
    font-size: 16px; /* Kích thước chữ */
    outline: none; /* Bỏ viền khi focus */
    transition: border-color 0.3s; /* Hiệu ứng khi đổi màu viền */
  }

  /* Đổi màu viền khi focus */
  #searchInput:focus {
    border-color: #e91e63; /* Màu viền khi focus */
  }

  /* Nút */
  button {
    padding: 10px 15px; /* Padding trong nút */
    border: 1px solid #e91e63; /* Đường viền màu hồng */
    border-radius: 5px; /* Bo góc */
    background-color: white; /* Màu nền trắng */
    color: #e91e63; /* Màu chữ hồng */
    cursor: pointer; /* Chuột khi hover */
    transition: background-color 0.3s, color 0.3s; /* Hiệu ứng khi hover */
  }

  /* Hiệu ứng khi hover trên nút */
  button:hover {
    background-color: #e91e63; /* Màu nền khi hover */
    color: white; /* Đổi màu chữ khi hover */
  }

  /* Đặt lại kiểu cho nút "Xem tất cả sản phẩm" */
  button:nth-child(2) {
    background-color: white; /* Màu nền trắng */
    color: #333; /* Màu chữ chính */
  }

  button:nth-child(2):hover {
    background-color: #f0f0f0; /* Màu nền khi hover */
  }
  .no-result-message {
    margin-top: 10px; /* Khoảng cách trên */
    color: #f44336; /* Màu chữ đỏ */
    font-weight: bold; /* Đậm chữ */
  }
</style>



<div class="container-fluid d-none d-lg-block pt-5">
  <div class="container">
    <div class="border-start border-5 border-primary ps-5 mb-5 product-search" style="max-width: 1300px;">
      <form class="search-form" onsubmit="return false;">
        <input type="search"  onkeydown="checkEnter(event);"  id="searchInput" placeholder="Tìm kiếm...">
        <button type="button" onclick="searchProduct()">Tìm Kiếm</button>
        <button type="button" onclick="resetSearch()">Xem tất cả sản phẩm</button>
      </form>

    </div>
    <div id="noResultMessage" class="no-result-message" style="display: none;">Không tìm thấy sản phẩm.</div> <!-- Thông báo -->

    <!-- Danh sách sản phẩm chính -->
    <div class="row g-5" id="productContainer">
      <c:forEach var="product" items="${listProducts}">
        <div class="col-lg-2 col-md-4 col-sm-6 comment-item" style="margin-top: 12px;">
          <a href="getProductDetail?id=${product.productId}" class="product-item-link">
            <div class="product-item position-relative bg-white d-flex flex-column text-center ${product.productAmount == 0 ? 'out-of-stock' : ''}">
              <div class="product-image">
                <img class="img-fluid" src="${pageContext.request.contextPath}/image/${product.getImagePaths().get(0)}" alt="Product Image">
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

    <!-- Thẻ hiển thị kết quả tìm kiếm -->
    <div id="searchResultContainer" class="row g-5" style="display: none;">
      <!-- Kết quả tìm kiếm sẽ được thêm vào đây -->
    </div>

    <div id="pagination" class="pagination d-flex justify-content-center mt-4"></div>
  </div>
</div>

<script>
  function checkEnter(event) {
    if (event.key === "Enter") { // Kiểm tra xem phím nhấn có phải là Enter không
      event.preventDefault(); // Ngăn chặn hành động mặc định của phím Enter
      searchProduct(); // Gọi hàm gửi tin nhắn
    }
  }


  // Hàm xáo trộn mảng
  function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
  }

  // Hàm hiển thị ngẫu nhiên sản phẩm
  function randomizeProductDisplay() {
    let productContainer = document.getElementById('productContainer');
    let products = Array.from(productContainer.getElementsByClassName('comment-item'));

    // Xáo trộn sản phẩm
    shuffleArray(products);

    // Xóa danh sách sản phẩm hiện tại
    productContainer.innerHTML = "";

    // Thêm các sản phẩm đã xáo trộn vào container
    products.forEach(product => productContainer.appendChild(product));
  }

  // Gọi hàm hiển thị ngẫu nhiên khi trang được load
  window.onload = function () {
    randomizeProductDisplay();
  };

  // Hàm tìm kiếm sản phẩm
  function searchProduct() {
    let input = document.getElementById('searchInput');
    let filter = input.value.toLowerCase().trim();
    let productContainer = document.getElementById('productContainer');
    let searchResultContainer = document.getElementById('searchResultContainer');
    let products = productContainer.getElementsByClassName('comment-item');

    // Xóa các sản phẩm cũ trong thẻ tìm kiếm
    searchResultContainer.innerHTML = "";

    let found = false;

    for (let i = 0; i < products.length; i++) {
      let productName = products[i].querySelector('.product-name').textContent.toLowerCase();
      if (productName.indexOf(filter) > -1) {
        found = true;
        let clonedProduct = products[i].cloneNode(true); // Sao chép sản phẩm khớp
        searchResultContainer.appendChild(clonedProduct);
      }
    }

    if (found) {
      searchResultContainer.style.display = "flex"; // Hiển thị thẻ tìm kiếm
      noResultMessage.style.display = 'none'; // Hiện thông báo nếu không tìm thấy sản phẩm
      productContainer.style.display = "none"; // Ẩn thẻ danh sách sản phẩm gốc
    } else {
      searchResultContainer.style.display = "none"; // Ẩn nếu không tìm thấy
      productContainer.style.display = "none"; // Hiển thị lại danh sách sản phẩm gốc
      noResultMessage.style.display = 'block'; // Hiện thông báo nếu không tìm thấy sản phẩm

    }
  }

  // Hàm đặt lại tìm kiếm
  function resetSearch() {
    let productContainer = document.getElementById('productContainer');
    let searchResultContainer = document.getElementById('searchResultContainer');

    searchResultContainer.style.display = "none"; // Ẩn kết quả tìm kiếm
    noResultMessage.style.display = 'none'; // Hiện thông báo nếu không tìm thấy sản phẩm
    productContainer.style.display = "flex"; // Hiển thị lại danh sách sản phẩm gốc
  }
</script>
