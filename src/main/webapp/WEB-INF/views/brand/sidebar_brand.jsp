<%-- 
    Document   : sidebar_brand
    Created on : Oct 29, 2024, 3:53:03 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!--<link rel="stylesheet" href="styles.css">-->
    </head>
    <body style="margin-top: 120px">

    <%@include file="header_sellChanel.jsp" %>
            <div class="sidebar col-lg-3">
                <ul>
                    <li class="sidebar-item">
                        <span>Quản Lý Đơn Hàng</span>
                        <ul class="submenu">
                            <li><a href="order-list-manager">Tất Cả </a></li>
<%--                            <li>Giao Hàng Loạt</li>--%>
<%--                            <li>Đơn Hủy</li>--%>
<%--                            <li>Trả Hàng/Hoàn Tiền</li>--%>
<%--                            <li>Cài Đặt Vận Chuyển</li>--%>
<%--                            <li>Bàn Giao Đơn Hàng</li>--%>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <span>Quản Lý Sản Phẩm</span>
                        <ul class="submenu">
                            <li><a href="manager_product">Tất Cả Sản Phẩm</a></li>
                            <li><a href="addproduct">Thêm Sản Phẩm</a></li>
                        </ul>
                    </li>

                    <li class="sidebar-item">
                        <span>Tài Chính</span>
                        <ul class="submenu">
                            <li>Doanh Thu</li>
                            <li>Số dư TK Shopee</li>
                            <li>Tài Khoản Ngân Hàng</li>
                        </ul>
                    </li>

                </ul>
            </div>

          
 


        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

          

            .sidebar {
                width: 250px;
                background-color: #f7f7f7;
                padding: 20px;
                border-right: 1px solid #ddd;
            }

            .sidebar ul {
                list-style-type: none;
                padding: 0;
            }

            .sidebar-item > span {
                font-weight: bold;
                color: #333;
                cursor: pointer;
                display: block;
                margin: 10px 0;
            }

            .submenu {
                margin-top: 5px;
                margin-left: 15px;
                display: none; /* Ẩn submenu theo mặc định */
            }

            .submenu li {
                font-size: 14px;
                color: #555;
                margin: 5px 0;
                cursor: pointer;
            }

            .main-content {
                flex: 1;
                padding: 20px;
            }

            .sidebar-item.active .submenu {
                display: block; /* Hiện submenu khi item có class active */
            }
            
        .container {
            display: flex;
            justify-content: flex-start;
            margin: 0px;
            padding: 0px;
        }
      
        a {
            text-decoration: none;
  
            transition: color 0.3s;
        }

        a:hover {
            color: #0056b3;
            text-decoration: underline;
        }


    </style>

        </style>
        <script >
            // JavaScript để mở/đóng các submenu
            document.querySelectorAll('.sidebar-item > span').forEach(item => {
                item.addEventListener('click', () => {
                    const parent = item.parentElement;
                    parent.classList.toggle('active');
                });
            });

        </script>
    </body>
</html>
