
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<style>
  .stars-outer {
    display: inline-block;
    position: relative;
    font-family: 'Font Awesome 5 Free';
    font-weight: 900;
  }
  .stars-outer::before {
    content: '\f005 \f005 \f005 \f005 \f005'; /* Unicode character for stars */
    font-size: 1rem;
    color: #ccc; /* Color of empty stars */
  }
  .stars-inner {
    position: absolute;
    top: 0;
    left: 0;
    white-space: nowrap;
    overflow: hidden;
    width: 0;
    color: #f8ce0b; /* Color of filled stars */
  }
  .stars-inner::before {
    content: '\f005 \f005 \f005 \f005 \f005'; /* Unicode character for stars */
    font-size: 1rem;
  }
  .fa-star {
    color: #f5b301;
  }

  .fa-star.half::before {
    content: '\f089'; /*    Unicode for half star in Font Awesome */
    color: #f5b301;
    position: absolute;
    margin-left: -1em;
    top: 1px;
  }
  .nen{
    background-color: white;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    border-radius: 8px;
    padding: 20px;

  }
</style>

<div class="container-fluid about bg-light py-5">
  <div class="container py-5">
    <div class="row g-5 align-items-center">
      <div class="col-lg-5 wow fadeInLeft" data-wow-delay="0.2s">
        <div class="about-img pb-5 ps-5">
          <img src="${service.listImg}${cd.listImg}" class="img-fluid rounded" style=" width:470px; height: 370px;  margin-right: 10px" alt="Image">



        </div>
      </div>
      <div class="col-lg-7 nen wow fadeInRight" data-wow-delay="0.4s">
        <div class="section-title  text-start mb-5">

          <h1 class="display-3 mb-4" style=" font-size: 40px; font-family: Times New Roman">${service.serviceName}${cd.serviceName} ${cd.comboName}</h1>
          <div class="comment-rating">
            <!--                            <p style="color: red; text-decoration: underline">${getAverageRating}</p>-->
            <c:forEach var="i" begin="1" end="5">
              <i class="fa fa-star" style="position: relative; color: ${i <= getAverageRating? '#f5b301' : (i == Math.ceil(getAverageRating) && getAverageRating % 1 != 0 ? 'transparent' : '#ccc')};">
                <c:if test="${i == Math.ceil(getAverageRating) && getAverageRating % 1 != 0}">
                  <i class="fa fa-star half"></i>
                </c:if>
              </i>
            </c:forEach>
          </div>

          <div style="border-left: 1px solid rgba(0, 0, 0, .14); margin-left: 20px;">
            <p style="margin-left: 20px;">
              <span style="color: red;">${EvaluateRating}</span>
              <span style="color: black;">đánh giá</span>
            </p>
          </div>

          <div class="mb-4">

            <h4 class="sub-title pe-3 mb-0">${service.servicePrice}${cd.comboPrice}đ</h4>

          </div>
          <div class="combo-card mb-4">
            <c:forEach var="c" items="${combo}">


              <a style=" background-color: white;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        border-radius: 8px;
        padding: 20px;" href="ServiceComboServlet?tid=${c.comboID}&serviceID=${service.serviceID}">
                  ${c.comboName}
              </a>


            </c:forEach>

          </div>


        </div>

        <form action="bookingservice" method="get">
          <c:if test="${sessionScope.account != null && sessionScope.account.role  == 3 || sessionScope.account.role  == 4}">
            <div class="buttons">
              <div>
                <input type="hidden" name="serviceIMG" value="${service.listImg}${cd.listImg}">
                <input type="hidden" name="serviceID" value="${service.serviceID}${cd.serviceID}">
                <input type="hidden" name="servicePrice" value="${service.servicePrice}${cd.comboPrice}">
                <input type="hidden" name="serviceName" value="${service.serviceName}${cd.serviceName}${cd.comboName}">
                <input class="btn btn-primary" type="submit" value="Đặt Ngay">
              </div>
              <h3>${message}</h3>
            </div>
          </c:if>

          <c:if test="${sessionScope.account == null}">
            <a href="login.jsp" class="btn btn-primary">Đặt lịch ngay</a>
          </c:if>
        </form>
      </div>
    </div>
  </div>
  <div class="nen" >
    <div style=" margin: 10px" >
      <h4 style="font-family: Times New Roman">MÔ TẢ DỊCH VỤ</h4>
      <p class="mb-4 text-primary auto-line-break">${service.description}${cd.description}</p>
    </div>

  </div>
  <div class="store-info nen"style=" margin: 10px">
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
        <form action="ViewShopServlet">
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
  <div class="mt-5 nen">
    <h4 style="font-family: Times New Roman">ĐÁNH GIÁ </h4>
    <div >
      <c:forEach items="${feedbackList}" var="feedback">
        <div class=" mb-4">
          <div class="service-item bg-light d-flex p-4">
            <div >
              <h5 class="text-uppercase mb-3">Khách hàng: ${feedback.name}</h5>
              <small class="text-secondary">Ngày: ${feedback.experienceDate}</small><br>
              <div class="comment-rating">
                <c:forEach var="i" begin="1" end="5">
                  <i class="fa fa-star" style="color: <c:choose>
                  <c:when test="${i <= feedback.satisfactionLevel}">#f5b301</c:when>
                  <c:otherwise>#ccc</c:otherwise>
                  </c:choose>;"></i>
                </c:forEach>
              </div>
              <p>${feedback.testimonial}</p>

            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const paragraphs = document.querySelectorAll('.auto-line-break');
    paragraphs.forEach(p => {
      p.innerHTML = p.innerHTML.replace(/\./g, '.<br>');
    });
  });
</script>


