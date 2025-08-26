
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!--<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">-->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href="${pageContext.request.contextPath}/assets/css/product_detail.css" rel="stylesheet">
</head>


<style>
  h6, .h6, h5, .h5, h4, .h4, h3, .h3, h2, .h2, h1, .h1 {
    font-family: sans-serif;
  }
</style>
<div class="container" style="max-width: 1697px;margin: 0;">
  <!-- Thông tin sản phẩm -->
  <div class="product-container" style="border-radius: 0px">
    <div class="row">
      <div class="col-md-5 product-image">
        <div class="product-item position-relative bg-white d-flex flex-column text-center ${product.productAmount == 0 ? 'out-of-stock' : ''}">
          <!-- Hình ảnh chính -->
          <img id="mainImage" class="img-fluid" src="${pageContext.request.contextPath}/image/${product.getImagePaths().isEmpty() ? '' : product.getImagePaths().get(0)}" alt="">

          <div class="out-of-stock" style="display: ${product.productAmount == 0 ? 'flex' : 'none'};">
            Hết hàng
          </div>
        </div>

        <!-- Danh sách hình ảnh nhỏ -->
        <div class="thumbnails mt-2 d-flex justify-content-center">
          <c:forEach var="imagePath" items="${product.getImagePaths()}">
            <img class="thumbnail" src="${pageContext.request.contextPath}/image/${imagePath}" alt="Thumbnail"
                 style="width: 80px; height: auto; cursor: pointer; margin: 5px;"
                 onmouseover="changeImage('${pageContext.request.contextPath}/image/${imagePath}')">
          </c:forEach>
        </div>


      </div>

      <!-- JavaScript để thay đổi hình ảnh chính -->
      <script>
        function changeImage(src) {
          document.getElementById('mainImage').src = src;
        }
      </script>


      <div class="col-md-6">
        <h2 style="font-family: sans-serif; color: black">${product.productName}</h2>
        <div style="display: flex; align-items: center;">
          <div class="comment-rating">
            <p style="color: red; text-decoration : underline">${TotalRating}</p>
            <c:forEach var="i" begin="1" end="5">
              <i class="fa fa-star" style="position: relative; color: ${i <= TotalRating ? '#f5b301' : (i == Math.ceil(TotalRating) && TotalRating % 1 != 0 ? 'transparent' : '#ccc')};">
                <c:if test="${i == Math.ceil(TotalRating) && TotalRating % 1 != 0}">
                  <i class="fa fa-star half"></i>
                </c:if>
              </i>
            </c:forEach>

          </div>
          <div style="border-left: 1px solid rgba(0, 0, 0, .14); margin-left: 20px;">
            <p style="margin-left: 20px;">
              <span style="color: red;">${TotalComment}</span>
              <span style="color: black;">đánh giá</span>
            </p>


          </div>
          <div style="border-left: 1px solid rgba(0, 0, 0, .14); margin-left: 20px;">

            <p style="margin-left: 20px;">
              <span style="color: red;">${product.sold}</span>
              <span style="color: black;">Đã bán</span>
            </p>
          </div>


        </div>

        <p class="price" style="color: red; font-size: 24px;">${product.getPriceString()}</p>
        <form action="additem">
          <div class="shopee-input-quantity">
            <input type="button" class="add-sl shopee-button-outline" title="Bớt"
                   onclick="var qty_el = document.getElementById('qty'); var qty = qty_el.value; if (!isNaN(qty) && qty > 1) qty_el.value--; return false;" value="-">
            <input aria-label="Number" name="amount" id="qty" type="text" size="1"
                   class="soluong_12 shopee-button-outline shopee-button-outline-mid" value="1" readonly>
            <input type="button" class="sub-sl shopee-button-outline" title="Thêm"
                   onclick="var qty_el = document.getElementById('qty'); var qty = qty_el.value; if (!isNaN(qty)) qty_el.value++; return false;" value="+">
            <p style="font-size: 16px;margin-bottom: 0px; margin-left: 12px ">${product.productAmount} sản phẩm có sẵn</p>
          </div>

          <div class="buttons">
            <div>
              <input name="id" type="text" hidden="" value="${product.productId}">
              <c:choose>
                <c:when test="${product.productAmount > 0}">
                  <c:if test="${sessionScope.account.userID != product.CTVID and sessionScope.account.role != 2}">
                    <input class="btn btn-primary" style="margin-top: 20px;" type="submit" value="Thêm Vào Giỏ Hàng">
                  </c:if>
                  <c:if test="${sessionScope.account.userID == product.CTVID or sessionScope.account.role == 2}">
                    <input class="btn btn-primary disabled-button" style="margin-top: 20px;" type="submit" value="Thêm Vào Giỏ Hàng" disabled>
                  </c:if>
                </c:when>
                <c:otherwise>
                  <input class="btn btn-primary disabled-button" style="margin-top: 20px;" type="submit" value="Thêm Vào Giỏ Hàng">
                </c:otherwise>
              </c:choose>
              <h3>${message}</h3>
            </div>
          </div>
        </form>

      </div>


    </div>
  </div>






        <div class="shop-container">
          <div class="shop-header">
            <img src="${pageContext.request.contextPath}/image/${brand.brandLogo}" alt="TrendyMan Logo" class="shop-logo">
            <div class="shop-info">
              <h1 class="shop-name">${brand.brandName}</h1>
<%--              <p class="shop-status">Online 19 Phút Trước</p>--%>

              <a href="chatMessage?toId=${brand.CTVID}" class="btn chat-btn">Nhắn Tin</a>
              <a href="chatMessage?toId=${brand.CTVID}" class="btn" style="color: black;
    border: solid 1px;">Xem Shop</a>

            </div>
          </div>
          <div class="shop-details">
            <div class="detail-item">
              <span class="detail-label">Đánh Giá</span>
              <span class="detail-value rating_shop ">${TotalComment}</span>
            </div>
            <div class="detail-item">
              <span class="detail-label">Sản Phẩm</span>
              <span class="detail-value product-count">${productCount}</span>
            </div>
            <div class="detail-item">
              <span class="detail-label">Tỉ Lệ Phản Hồi</span>
              <span class="detail-value response-rate">100%</span>
            </div>
            <div class="detail-item">
              <span class="detail-label">Thời Gian Phản Hồi</span>
              <span class="detail-value">Trong vài phút</span>
            </div>
            <div class="detail-item">
              <span class="detail-label">Tham Gia</span>
              <span class="detail-value">6 năm trước</span>
            </div>
            <div class="detail-item">
              <span class="detail-label">Người Theo Dõi</span>
              <span class="detail-value followers">87,7k</span>
            </div>
          </div>
        </div>

        <div style="display: flex;">
          <div class="col-md-9">
            <!-- Chi tiết sản phẩm -->
            <div class="product-details" style="border-radius: 0px">
              <h3 >CHI TIẾT SẢN PHẨM</h3>
              <p>Xuất xứ: ${product.origin}</p>
              <p>Kho hàng: ${product.productAmount}</p>
              <%--    <p>Gửi từ: ${brand.brandAddess}</p>--%>
            </div>

            <div class="product-details" style="border-radius: 0px">
              <h3 >MÔ TẢ SẢN PHẨM</h3>
              <p class="auto-line-break">${product.productDescription}</p>
            </div>
            <!-- Thêm bình luận sản phẩm -->

            <!-- Hiển thị bình luận sản phẩm -->
            <div class="comment-list product-details" id="comment-list" style="border-radius: 0px">
              <h3>ĐÁNH GIÁ SẢN PHẨM</h3>
              <c:forEach var="comment" items="${listComments}">
                <div class="comment-item">
                  <div class="" style="display: flex;">
                    <img src="${pageContext.request.contextPath}/image/${comment.user.avatar}" alt="Avatar" class="avatar">
                    <div>
                      <p>${comment.user.firstname} ${comment.user.lastname}</p>
                      <div class="comment-rating">
                        <c:forEach var="i" begin="1" end="5">
                          <i class="fa fa-star" style="color: <c:choose>
                          <c:when test="${i <= comment.rating}">#f5b301</c:when>
                          <c:otherwise>#ccc</c:otherwise>
                          </c:choose>;"></i>
                        </c:forEach>
                      </div>
                      <p class="stats">${comment.createdAt}</p>
                    </div>
                  </div>
                  <p>${comment.comment}</p>
                  <c:if test="${not empty comment.commentImg}">
                    <img style="border-radius: 0%; cursor: pointer;border: solid 2px;" src="${pageContext.request.contextPath}/image/${comment.commentImg}" alt="Comment Image" width="200" data-toggle="modal" data-target="#imageModal">
                  </c:if>
                  <hr>
                </div>
              </c:forEach>
              <div class="pagination" id="pagination"></div>
            </div>


            <!-- Modal -->
            <div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="true">
              <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                  <div class="modal-header">

                    <button style="margin-left: 445px;" type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body text-center">
                    <img id="modalImage" src="" alt="Comment Image" class="img-fluid">
                  </div>
                </div>
              </div>
            </div>



           <style>
             .product-carousel {
               display: flex;
               align-items: center;
               position: relative;
             }

             .arrow {
               font-size: 30px;
               cursor: pointer;
               position: absolute;
               top: 50%;
               transform: translateY(-50%);
               background-color: white; /* Bạn có thể điều chỉnh màu nền, viền... */
               border-radius: 50%;
               padding: 10px;
               z-index: 2;
             }

             .left-arrow {
               left: 0;
             }

             .right-arrow {
               right: 0;
             }

             .product-items {
               display: flex;
               transition: transform 0.3s ease; /* Thêm hiệu ứng trượt */
             }
           </style>
            <script>
              let scrollPosition = 0;

              function scrollLeft() {
                const productItems = document.getElementById('productItems');
                scrollPosition -= 200; // Số pixel di chuyển sang trái mỗi lần nhấn
                productItems.style.transform = `translateX(${scrollPosition}px)`;
              }

              function scrollRight() {
                const productItems = document.getElementById('productItems');
                scrollPosition += 200; // Số pixel di chuyển sang phải mỗi lần nhấn
                productItems.style.transform = `translateX(${scrollPosition}px)`;
              }

            </script>
            <div style="margin-top: 16px;">
              <div style="height: 40px"> CÁC SẢN PHẨM KHÁC CỦA SHOP</div>
              <div class="arrow left-arrow" onclick="scrollLeft()">&#10094;</div>
              <div class="row g-5 product-items" id="productItems" style="overflow: hidden; white-space: nowrap;justify-content: normal;">
                <c:forEach var="product" items="${allProduct}">
                  <div class="col-lg-2 col-md-4 col-sm-6" style=" position: relative;">
                    <a href="getProductDetail?id=${product.productId}" class="product-item-link">
                      <div style="height: 250px; border: 1px solid #ddd; position: relative;" class="product-item bg-white d-flex flex-column text-center ${product.productAmount == 0 ? 'out-of-stock' : ''}">
                        <div class="product-image" style="position: relative;">
                          <img class="img-fluid" style="height: 120px; object-fit: cover;" src="${pageContext.request.contextPath}/image/${product.getImagePaths().get(0)}" alt="Product Image">
                          <div class="out-of-stock" style="display: ${product.productAmount == 0 ? 'flex' : 'none'}; align-items: center; justify-content: center; background: rgba(255, 255, 255, 0.7); position: absolute; top: 0; left: 0; right: 0; bottom: 0; color: red; font-size: 18px; font-weight: bold;">HẾT HÀNG</div>
                        </div>
                        <div class="product-details" style="position: absolute; bottom: 10px; left: 10px; right: 10px; padding: 5px; background: rgba(255, 255, 255, 0.8);border: none;border-radius: 0px;box-shadow: none;">
                          <h6 class="product-name" style=" font-size: 14px; margin: 0; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; max-width: 100%;">${product.productName}</h6>
                          <h5 class="text-primary mb-0 product-price" style="font-size: 16px; margin: 0;" >₫ ${product.getPriceString()}</h5>
                        </div>
                      </div>
                    </a>
                  </div>
                </c:forEach>
              </div>
              <div class="arrow right-arrow" onclick="scrollRight()">&#10095;</div>
            </div>


          </div>

          <div class="col-md-3" style="    margin-left: 35px;">
            <h2 style="margin-top: 18px;">Top Sản Phẩm Nổi Bật</h2>


            <div style="width: 270px;height: 310px;">
              <c:forEach var="product" items="${topProduct}">
                <div style="margin-bottom: 16px">
                  <a href="getProductDetail?id=${product.productId}" class="product-item-link">
                    <div class="product-item position-relative bg-white d-flex flex-column text-center ${product.productAmount == 0 ? 'out-of-stock' : ''}">
                      <div class="product-image" style="height: 190px">
                        <img class="img-fluid" style="height: 100%" src="${pageContext.request.contextPath}/image/${product.getImagePaths().get(0)}" alt="Product Image">
                        <div class="out-of-stock" style="display: ${product.productAmount == 0 ? 'flex' : 'none'};">Hết hàng</div>
                      </div>
                      <div class="product-details" style="border-radius: 0px;border: none;">
                        <h6 class="product-name">${product.productName}</h6>
                        <h5 class="text-primary mb-0 product-price">₫ ${product.getPriceString()}</h5>
                      </div>
                    </div>
                  </a>
                </div>
              </c:forEach>
            </div>
          </div>

      </div>

<%--  <c:choose>--%>
<%--    <c:when test="${sessionScope.user != null && product != null}">--%>
<%--      <c:if test="${sessionScope.user.userId ne product.CTVID}">--%>
<%--        <c:choose>--%>
<%--          <c:when test="${!isFollowing}">--%>
<%--            <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">--%>
<%--            <a href="followBrand?brandId=${brand.brandID}&id=${product.productId}" class="btn btn-danger">Theo Dõi</a>--%>
<%--          </c:when>--%>
<%--          <c:when test="${isFollowing}">--%>
<%--            <input name="id" type="text" hidden="" value="${product.productId}">--%>
<%--            <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">--%>
<%--            <a href="unfollowBrand?brandId=${brand.brandID}&id=${product.productId}" class="btn btn-danger">Bỏ Theo Dõi</a>--%>
<%--          </c:when>--%>
<%--        </c:choose>--%>
<%--      </c:if>--%>


<%--    </c:when>--%>
<%--  </c:choose>--%>


  <style>
    .shop-container {
      height: 190px;
      background-color: #fff;

      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      padding: 20px;
      display: flex;
      flex-direction: row;
      justify-content: space-around;
      margin: 26px 0px;
    }

    .shop-header {
      display: flex;
      align-items: center;
    }

    .shop-logo {
      width: 20%;
      height: 65%;
      border-radius: 50%;
      margin-right: 15px;
    }

    .shop-info {
      flex-grow: 1;
    }

    .shop-name {
      margin: 0;
      font-size: 24px;
      font-weight: bold;
    }

    .shop-status {
      margin: 5px 0;
      color: gray;
    }

    .btn {
      margin-right: 10px;
      padding: 8px 12px;
      border: none;
      border-radius: 4px;
      color: #fff;
      cursor: pointer;
    }

    .chat-btn {
      background-color: #ff4880;
    }

    .view-shop-btn {
      background-color: white;
    }

    .favorite-btn {
      background-color: #ff6b6b;
    }

    .shop-details {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      margin-top: 15px;
    }

    .detail-item {
      text-align: center;
      padding: 10px;
    }

    .detail-label {
      font-size: 14px;
      color: gray;
    }

    .detail-value {
      font-size: 16px;
      font-weight: bold;
      color: #ff4880;
    }
  </style>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const comments = Array.from(document.querySelectorAll(".comment-item"));
    const commentsPerPage = 5;
    const pagination = document.getElementById("pagination");
    let currentPage = 1;
    const totalPages = Math.ceil(comments.length / commentsPerPage);

    function showPage(page) {
      comments.forEach((comment, index) => {
        comment.style.display = (index >= (page - 1) * commentsPerPage && index < page * commentsPerPage) ? "block" : "none";
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
  document.addEventListener("DOMContentLoaded", function () {
    const paragraphs = document.querySelectorAll('.auto-line-break');
    paragraphs.forEach(p => {
      p.innerHTML = p.innerHTML.replace(/\./g, '.<br>');
    });
  });
</script>
<script>
  $('#imageModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var imageSrc = button.attr('src'); // Extract info from data-* attributes
    var modal = $(this);
    modal.find('.modal-body #modalImage').attr('src', imageSrc);
  });
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>
