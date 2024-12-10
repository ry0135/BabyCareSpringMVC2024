


<%@page contentType="text/html" pageEncoding="UTF-8" %>


<div class="container-fluid pt-5 ">
    <div id="page-wrapper">
        <div class="container">
            <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                <h6 class="text-primary text-uppercase">Thống kê</h6>
                <h1 class="display-5 text-uppercase mb-0">Thống kê bán hàng của hệ thống</h1>
            </div>
            <div class="col-md-12 statistical d-flex justify-content-around mb-5">
                <div class="d-flex register m-1">
                    <div style="background-color: orange;" class="icon"><i class="fa-solid fa-person-circle-check"></i>
                    </div>
                    <div class="detail">
                        <div>${totalCollaborators}</div>
                        <div>Tố cộng tác viên</div>
                    </div>
                </div>


                <div class="d-flex user m-1">
                    <div style="background-color: rgba(0, 0, 255, 0.726);" class="icon"><i
                            class=" fa-solid fa-user"></i></div>
                    <div class="detail">
                        <div>${totalUsers}</div>
                        <div>Tổng số người dùng của s</div>      <div class="subtext">Toàn thời gian</div>

                    </div>
                </div>
                <div class="d-flex user m-1">
                    <div style="background-color: rgba(0, 0, 255, 0.726);" class="icon"><i
                            class=" fa-solid fa-user"></i></div>
                    <div class="detail">
                        <div>${totalPrduct}</div>
                        <div>Tổng số người dùng</div>      <div class="subtext">Toàn thời gian</div>
                    </div>
                </div>
                <%--        <div class="d-flex user m-1">--%>
                <%--          <div style="background-color: rgba(0, 0, 255, 0.726);" class="icon"><i--%>
                <%--                  class=" fa-solid fa-user"></i></div>--%>
                <%--          <div class="detail">--%>
                <%--            <div>${numberOfCTV}</div>--%>
                <%--            <div>Số tài khoản cộng tác viên đã đăng kí</div>--%>
                <%--          </div>--%>
                <%--        </div>--%>
                <%--        <div class="d-flex pet-sold m-1">--%>
                <%--          <div style="background-color: #7AB730;" class="icon"><i class="fa-solid fa-paw"></i></div>--%>
                <%--          <div class="detail">--%>
                <%--            <div>${numberOfProductLeft}</div>--%>
                <%--            <div>Tổng số sản phẩm đang bán</div>--%>
                <%--          </div>--%>
                <%--        </div>--%>

                <%--        <div class="d-flex money m-1">--%>
                <%--          <div style="background-color: rgb(204, 0, 204);" class="icon"><i class=" fa-solid fa-coins"></i>--%>
                <%--          </div>--%>
                <%--          <div class="detail">--%>
                <%--            <div>${orderRevenue}</div>--%>
                <%--            <div>Doanh thu bán hàng trong năm nay</div>--%>
                <%--          </div>--%>
                <%--        </div>--%>
                <%--      </div>--%>
                <%--    </div>--%>
                <%--    <div class="container mt-3" style="background-color: #f3f3f3">--%>
                <%--      <h2>Danh sách sản phẩm  bán chạy nhất</h2>--%>
                <%--      <p>Đây là danh sách các mặt hàng sản phẩm bán chạy trong nhất</p>--%>

                <%--      <br>--%>
                <%--      <table class="table table-bordered">--%>
                <%--        <thead>--%>
                <%--        <tr>--%>
                <%--          <th>Thứ hạng</th>--%>
                <%--          <th>ID sản phẩm </th>--%>
                <%--          <th>Tên sản phẩm</th>--%>
                <%--          <th>Số lượng đã bán</th>--%>

                <%--        </tr>--%>
                <%--        </thead>--%>
                <%--        <tbody id="myTable">--%>

                <%--        <c:forEach var="product" items="${listProduct}">--%>
                <%--          <tr>--%>
                <%--            <td>${listProduct.indexOf(product)+1}</td>--%>
                <%--            <td>${product.productId}</td>--%>
                <%--            <td>${product.productName}</td>--%>
                <%--            <td>${product.productAmount}</td>--%>
                <%--          </tr>--%>

                <%--        </c:forEach>--%>

                <%--        </tbody>--%>
                <%--      </table>--%>


                <%--    </div>--%>
                <%--  </div>--%>

            </div>




            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


            <style>
                .statistical .icon {

                    color: white;
                    align-self: center;
                    padding: 30px 20px;
                    font-size: xx-large;
                    border-radius: 10% 0 0 10%;
                }

                .statistical .detail {
                    background-color: rgba(128, 128, 128, 0.116);
                    border-radius: 0 10% 10% 0;
                    width: 150px;
                    font-size: smaller;
                    text-align: center;
                }

                .statistical .detail :first-child {
                    font-size: 40px;
                    width: 100%;

                    padding-top: 5%;
                }

                .statistical .money .detail {
                    width: 250px !important;
                }
            </style>