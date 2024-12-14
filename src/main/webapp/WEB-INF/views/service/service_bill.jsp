<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>


<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="container-fluid">
  <div class="container">
    <div class="donhang">
      <div class="border-start border-5 border-primary ps-5 mb-5 mt-5">
        <h6 class="text-primary text-uppercase">Đơn dịch vụ</h6>
        <h1 class="display-5 text-uppercase mb-0">Kiểm tra dịch vụ của bạn</h1>
      </div>
      <div class="d-flex justify-content-center">
        <h2 class="text-primary m-1 p-0">Tình trạng lịch dịch vụ của bạn: </h2>
        <h2 class="text-danger m-1 p-0">Đang xử lý....</h2>
      </div>
      <div class="mt-5 text-center">
        <p class="">Cảm ơn quý khách đã đặt dịch vụ của BabyCare.</p>

      </div>
      <div class="border-bottom">
        <h3>Thông tin khách hàng</h3>
        <div>
          <p>${sessionScope.account.firstname} ${sessionScope.account.lastname}</p>
          <p>Số điện thoại: ${sessionScope.account.phone}</p>
          <c:choose>
            <c:when test="${not empty address }">
              <p>Địa chỉ: ${address}</p>
            </c:when>
            <c:otherwise>
              <p>Địa chỉ: ${address1}</p>
            </c:otherwise>
          </c:choose>
        </div>
      </div>

        <div class="border-bottom">
          <h3>Thông tin dịch vụ: ${serviceName}</h3>
          <div class="d-flex justify-content-around">
            <p>Ngày: ${bookingDate}</p>
            <p>Slot: ${slot}</p>
            <p>Ghi chú: ${note}</p>
          </div>
        </div>



      </div>
    </div>
  </div>
</div>
