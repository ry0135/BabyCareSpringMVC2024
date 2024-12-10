<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<link href="${pageContext.request.contextPath}/assets/css/cart_form.css" rel="stylesheet">

<jsp:include page="/WEB-INF/views/include/header.jsp" />
<div class="cart-page">

    <c:if test="${sessionScope.account == null}">
        <% response.sendRedirect("login");%>
    </c:if>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-8">
                <div class="cart-page-inner">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead class="thead-dark">
                            <tr>
                                <!--                                    <th>Chọn</th>-->
                                <th>Shop</th>
                                <th>Sản phẩm</th>
                                <th>Giá</th>
                                <th>Số lượng</th>
                                <th>Tạm tính</th>
                                <th>Xóa</th>
                            </tr>
                            </thead>
                            <tbody class="align-middle">
                            <c:forEach var="item" items="${itemsList}">
                                <tr>
                                    <!--                                        <td>
                                                                                    <input type="checkbox" name="selectedItems" value="${item.product.productId}" class="checkbox">
                                                                                </td>-->
                                    <td>
                                        <p>${item.brandName}</p>
                                    </td>
                                    <td>
                                        <div class="img">
                                            <img src="${pageContext.request.contextPath}/image/${item.product.getImagePaths().get(0)}" alt="Image">
                                            <p>${item.product.productName}</p>
                                        </div>
                                    </td>
                                    <td>${item.product.getPriceString()}</td>
                                    <td>
                                        <div class="qty d-flex">


                                            <form action="changeamount">
                                                <input name="decrease" value="${item.product.productId}" type="hidden">
                                                <button type="submit" class="btn-minus"><i class="fa fa-minus"></i></button>
                                            </form>

                                            <input type="text" value="${item.amount}">
                                            <form action="changeamount">
                                                <input name="increase" value="${item.product.productId}" type="hidden">
                                                <button class="btn-plus" type="submit"><i class="fa fa-plus"></i></button>
                                            </form>
                                        </div>
                                    </td>
                                    <td>${item.getPriceString()}</td>
                                    <td>
                                        <form action="changeamount">
                                            <input name="delete" value="${item.product.productId}" type="hidden">
                                            <button type="submit"><i class="fa fa-trash"></i></button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="cart-page-inner">
                    <div class="row">
                        <form action="applydiscount">
                            <div class="col-md-12">
                                <div class="coupon">
                                    <input type="text" placeholder="Coupon Code" name="discountCode">
                                    <input class="button" type="submit" value="Apply Code">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <p>${message}</p>
                            </div>
                        </form>
                        <div class="col-md-12">
                            <div class="cart-summary">
                                <div class="cart-content">
                                    <h1>Thanh toán đơn hàng</h1>
                                    <form id="myForm" class="form-inline" action="makeorder" method="get">
                                        <div class="form-group mr-2">
                                            <label for="paymentMethod">Chọn hình thức thanh toán</label>
                                        </div>
                                        <div class="form-group">
                                            <select class="form-control" name="paymentMethod" id="paymentMethod" onchange="changeFormAction(this)">
                                                <option value="0">Thanh toán khi nhận hàng</option>
                                                <option value="1">Thanh toán VNPAY</option>
                                            </select>
                                        </div>
                                        <div class="flex-container">
                                            <p>Tạm tính:</p>
                                            <span class="text-right">${totalAmount}</span>
                                        </div>
                                        <div class="flex-container">
                                            <p>Tiền ship:</p>
                                            <span class="text-right">${totalShippingFee}</span>
                                        </div>
                                        <c:if test="${discountCode == null}">
                                            <div class="flex-container">
                                                <h2>Tổng cộng:</h2>
                                                <span class="text-right">${calculateWithShip}</span>
                                            </div>
                                        </c:if>
                                        <input type="hidden" name="discountCode" value="${discountCode}" />

                                        <c:if test="${discountCode != null}">
                                            <div class="flex-container">
                                                <p>Discount :</p>
                                                <span class="text-right">-${discountPercent * 100}%</span>
                                            </div>
                                            <div class="flex-container">
                                                <p>Tổng vourcher giảm giá :</p>
                                                <span class="text-right">-${calculatePriceDiscout}</span>
                                            </div>
                                            <div class="flex-container">
                                                <h2 style="padding-top: 0;">Tổng Thanh Toán:</h2>
                                                <span class="text-right" style="color: red;">${totalPriceAllWithDiscount}</span>
                                            </div>
                                        </c:if>
                                        <div class="Address">
                                            <label for="addressType">Chọn địa chỉ:</label>
                                            <select name="addressType" id="addressType" onchange="toggleNewAddressInput()">
                                                <option value="default">Chọn địa chỉ mặc định</option>
                                                <option value="new">Nhập địa chỉ mới</option>
                                            </select>

                                            <div id="newAddressInput">
                                                <div class="Address" style="display: flex; flex-direction: row; justify-content: space-between; align-items: center;">
                                                    <select name="province" id="province">
                                                        <option value="">chọn tỉnh</option>
                                                    </select>
                                                    <select name="district" id="district">
                                                        <option value="">chọn quận</option>
                                                    </select>
                                                    <select name="ward" id="ward">
                                                        <option value="">chọn phường</option>
                                                    </select>

                                                </div>

                                                <div style="margin-top: 10px">
                                                    <input type="text" style="width: 400px; height: 40px; border: 1px solid #ced4da; border-radius: 4px;"
                                                           id="addressDetail" placeholder="Nhập số nhà hoặc địa chỉ chi tiết">
                                                </div>

                                                <div style="margin-top: 10px">
                                                    <input type="hidden" id="result" name="newAddress" style="width: 400px; height: 40px; border: 1px solid #ced4da; border-radius: 4px;" >
                                                </div>
                                            </div>
                                        </div>
                                        <div class="cart-btn">
                                            <button type="submit">Đặt hàng</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("addressType").addEventListener("change", function () {
        var newAddressInput = document.getElementById("newAddressInput");
        if (this.value === "new") {
            newAddressInput.style.display = "block";
        } else {
            newAddressInput.style.display = "none";
        }
    });
</script>
<script>
    function changeFormAction(select) {
        var form = document.getElementById("myForm");
        if (select.value == "1") {
            form.action = "makeorderbank";
        } else {
            form.action = "makeorder";
        }
    }

    window.onload = function () {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '${pageContext.request.contextPath}/removediscountcode', true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send();

        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('Session attributes removed successfully');
            } else {
                console.error('Failed to remove session attributes');
            }
        };
    };
</script>


<script>
    function updateFullAddress() {
        // Lấy giá trị từ các dropdown và trường nhập
        const province = document.getElementById('province').options[document.getElementById('province').selectedIndex].text; // Lấy text của tỉnh
        const district = document.getElementById('district').options[document.getElementById('district').selectedIndex].text; // Lấy text của quận
        const ward = document.getElementById('ward').options[document.getElementById('ward').selectedIndex].text; // Lấy text của phường
        const addressDetail = document.getElementById('addressDetail').value;

        // Kiểm tra và in ra console các giá trị
        console.log("Địa chỉ chi tiết:", addressDetail);
        console.log("Phường:", ward);
        console.log("Quận:", district);
        console.log("Tỉnh:", province);

        // Gộp các giá trị thành một chuỗi
        let fullAddress = '';
        if (addressDetail) {
            fullAddress += addressDetail + ', ';
        }
        if (ward) {
            fullAddress += ward + ', ';
        }
        if (district) {
            fullAddress += district + ', ';
        }
        if (province) {
            fullAddress += province;
        }

        // Xóa dấu phẩy cuối nếu có
        fullAddress = fullAddress.replace(/, $/, '');

        // Hiển thị kết quả
        document.getElementById('result').innerText = fullAddress;

        // Kiểm tra nếu có địa chỉ hoàn chỉnh và in ra console
        if (fullAddress) {
            console.log("Địa chỉ hoàn chỉnh:", fullAddress);
        } else {
            console.log("Không có dữ liệu địa chỉ nào.");
        }
        document.getElementById('result').value = fullAddress; // Gán giá trị cho input ẩn
    }

    // Thêm sự kiện để tự động gộp địa chỉ khi có thay đổi
    document.getElementById('addressDetail').addEventListener('input', updateFullAddress);
    document.getElementById('province').addEventListener('change', updateFullAddress);
    document.getElementById('district').addEventListener('change', updateFullAddress);
    document.getElementById('ward').addEventListener('change', updateFullAddress);
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js" integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="${pageContext.request.contextPath}/assets/js/adreess.js"></script>