
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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

<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="container">
  <!-- Thông tin sản phẩm -->
  <div class="product-container">
    <div class="row">
      <div class="col-md-5 product-image">
        <div class="product-item position-relative bg-white d-flex flex-column text-center ${product.productAmount == 0 ? 'out-of-stock' : ''}">
          <!-- Hình ảnh chính -->
          <img id="mainImage" class="img-fluid"  src="${pageContext.request.contextPath}/assets/img/${product.getImagePaths().isEmpty() ? '' : product.getImagePaths().get(0)}" alt="">

          <div class="out-of-stock" style="display: ${product.productAmount == 0 ? 'flex' : 'none'};">
            Hết hàng
          </div>
        </div>

        <!-- Danh sách hình ảnh nhỏ -->
        <div class="thumbnails mt-2 d-flex justify-content-center">
          <c:forEach var="imagePath" items="${product.getImagePaths()}">
            <img class="thumbnail" src="${pageContext.request.contextPath}/assets/img/${imagePath}" alt="Thumbnail"
                 style="width: 80px; height: auto; cursor: pointer; margin: 5px;"
                 onmouseover="changeImage('${pageContext.request.contextPath}/assets/img/${imagePath}')">
          </c:forEach>
        </div>
      </div>

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
                   onclick="var qty_el = document.getElementById('qty');
                                               var qty = qty_el.value;
                                               if (!isNaN(qty) && qty > 1)
                                                   qty_el.value--;
                                               return false;" value="-">
            <input aria-label="Number" name="amount" id="qty" type="text" size="1"
                   class="soluong_12 shopee-button-outline shopee-button-outline-mid" value="1"
                   readonly>
            <input type="button" class="sub-sl shopee-button-outline" title="Thêm"
                   onclick="var qty_el = document.getElementById('qty');
                                               var qty = qty_el.value;
                                               if (!isNaN(qty))
                                                   qty_el.value++;
                                               return false;" value="+">
            <p style="font-size: 16px;margin-bottom: 0px; margin-left: 12px ">${product.productAmount} sản phẩm có sẵn</p>
          </div>

          <div class="buttons">
            <div>
              <input name="id" type="text" hidden="" value="${product.productId}">
              <c:choose>
                <c:when test="${product.productAmount gt 0}">
                  <c:if test="${sessionScope.user.userId ne product.CTVID and sessionScope.user.role ne 2}">
                    <input class="btn btn-primary" style="margin-top: 20px;" type="submit" value="Thêm Vào Giỏ Hàng">
                  </c:if>
                  <c:if test="${sessionScope.user.userId eq product.CTVID or sessionScope.user.role eq 2}">
                    <!-- Nút bị vô hiệu hóa trong trường hợp này -->
                    <input class="btn btn-primary" style="margin-top: 20px;" type="submit" value="Thêm Vào Giỏ Hàng">
                  </c:if>
                </c:when>
                <c:otherwise>
                  <!-- Nút bị làm mờ và vô hiệu hóa khi hết hàng -->
                  <input class="btn btn-primary disabled-button" style="margin-top: 20px;" type="submit" value="Thêm Vào Giỏ Hàng" disabled>
                </c:otherwise>
              </c:choose>
            </div>
            <h3>${message}</h3>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- Thông tin gian hàng -->
  <div class="store-info">
    <div class="row">
      <div class="col-md-1">
        <img src="${pageContext.request.contextPath}/Image/${brand.brandLogo}" alt="Store Logo">
      </div>
      <div class="col-md-9">
        <h1>${brand.brandName}</h1>
        <p class="stats">Đánh Giá: 125</p>
        <p class="stats">Sản Phẩm: 87</p>
        <p class="stats">Tỉ Lệ Phản Hồi: 100%</p>
        <p class="stats">Thời Gian Phản Hồi: trong vài giờ</p>
        <p class="stats">Tham Gia: 48 ngày trước</p>
        <p class="stats">Người Theo Dõi: 688</p>
      </div>
      <div class="col-md-2 text-center">
        <form action="viewshop">
          <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">
          <button class="button">Xem Shop</button>
        </form>
        <form action="chat">
          <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">
          <button class="button">Nhắn tin</button>
        </form>
        <input type="hidden" name="brandID" value="${brand.brandID}">


        <c:choose>
          <c:when test="${sessionScope.user != null && product != null}">
            <c:if test="${sessionScope.user.userId ne product.CTVID}">
              <c:choose>
                <c:when test="${!isFollowing}">
                  <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">
                  <a href="followBrand?brandId=${brand.brandID}&id=${product.productId}" class="btn btn-danger">Theo Dõi</a>
                </c:when>
                <c:when test="${isFollowing}">
                  <input name="id" type="text" hidden="" value="${product.productId}">
                  <input name="CTVID" type="text" hidden="" value="${brand.CTVID}">
                  <a href="unfollowBrand?brandId=${brand.brandID}&id=${product.productId}" class="btn btn-danger">Bỏ Theo Dõi</a>
                </c:when>
              </c:choose>
            </c:if>


          </c:when>
        </c:choose>


      </div>
    </div>
  </div>
  <!-- Chi tiết sản phẩm -->
  <div class="product-details">
    <h3 >CHI TIẾT SẢN PHẨM</h3>
    <p>Xuất xứ: ${product.origin}</p>
    <p>Kho hàng: ${product.productAmount}</p>
<%--    <p>Gửi từ: ${brand.brandAddess}</p>--%>
  </div>
  <!-- Mô tả sản phẩm -->
  <div class="product-details">
    <h3 >MÔ TẢ SẢN PHẨM</h3>
    <p class="auto-line-break">${product.productDescription}</p>
  </div>
  <!-- Thêm bình luận sản phẩm -->

  <!-- Hiển thị bình luận sản phẩm -->
  <div class="comment-list product-details" id="comment-list">
    <h3>ĐÁNH GIÁ SẢN PHẨM</h3>
    <c:forEach var="comment" items="${listComments}">
      <div class="comment-item">
        <div class="" style="display: flex;">
          <img src="${pageContext.request.contextPath}/assets/img/${comment.user.avatar}" alt="Avatar" class="avatar">
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
          <img style="border-radius: 0%; cursor: pointer;" src="${pageContext.request.contextPath}/Image/${comment.commentImg}" alt="Comment Image" width="200" data-toggle="modal" data-target="#imageModal">
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
