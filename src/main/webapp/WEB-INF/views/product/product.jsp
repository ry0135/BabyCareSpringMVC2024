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
</style>



<div class="container-fluid d-none d-lg-block pt-5">
  <div class="container">
    <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
      <h6 class="text-primary text-uppercase">Sản Phẩm</h6>
      <form class="search-form" onsubmit="return false;">
        <input type="search" id="searchInput" placeholder="Tìm kiếm...">
        <button type="button" onclick="searchProduct()">Tìm Kiếm</button>
        <button type="button" onclick="resetSearch()">Xem tất cả sản phẩm</button>
      </form>
    </div>

    <!-- Danh sách sản phẩm chính -->
    <div class="row g-5" id="productContainer">
      <c:forEach var="product" items="${listProducts}">
        <div class="col-lg-2 col-md-4 col-sm-6 comment-item" style="margin-top: 12px;">
          <a href="getProductDetail?id=${product.productId}" class="product-item-link">
            <div class="product-item position-relative bg-white d-flex flex-column text-center ${product.productAmount == 0 ? 'out-of-stock' : ''}">
              <div class="product-image">
                <img class="img-fluid" src="${pageContext.request.contextPath}/assets/img/${product.getImagePaths().get(0)}" alt="Product Image">
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
      productContainer.style.display = "none"; // Ẩn thẻ danh sách sản phẩm gốc
    } else {
      searchResultContainer.style.display = "none"; // Ẩn nếu không tìm thấy
      productContainer.style.display = "flex"; // Hiển thị lại danh sách sản phẩm gốc
      alert("Không tìm thấy sản phẩm nào khớp!");
    }
  }

  // Hàm đặt lại tìm kiếm
  function resetSearch() {
    let productContainer = document.getElementById('productContainer');
    let searchResultContainer = document.getElementById('searchResultContainer');

    searchResultContainer.style.display = "none"; // Ẩn kết quả tìm kiếm
    productContainer.style.display = "flex"; // Hiển thị lại danh sách sản phẩm gốc
  }
</script>
