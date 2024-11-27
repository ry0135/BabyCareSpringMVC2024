<%-- 
    Document   : sellChannel
    Created on : Oct 29, 2024, 3:53:59 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <style>
        body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

.container {
    display: flex;
}

.sidebar {
    width: 200px;
    background-color: #f7f7f7;
    padding: 20px;
}

.sidebar ul {
    list-style-type: none;
    padding: 0;
}

.sidebar ul li {
    margin: 10px 0;
    color: #333;
    cursor: pointer;
}

.main-content {
    flex: 1;
    padding: 20px;
}

.tabs {
    display: flex;
    margin-bottom: 20px;
}

.tab-button {
    padding: 10px 20px;
    border: none;
    background-color: #f7f7f7;
    color: #333;
    cursor: pointer;
    border-right: 1px solid #ddd;
}

.tab-button.active {
    background-color: #e0e0e0;
}

.shipping-options {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.shipping-option {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #eee;
    padding: 15px 0;
}

.shipping-option h3 {
    margin: 0;
    font-size: 16px;
}

.shipping-option p {
    color: #777;
    font-size: 14px;
    margin: 5px 0 0;
}

.toggle {
    display: flex;
    align-items: center;
}

.switch {
    position: relative;
    display: inline-block;
    width: 34px;
    height: 20px;
}

.switch input {
    opacity: 0;
    width: 0;
    height: 0;
}

.slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #ccc;
    transition: 0.4s;
    border-radius: 20px;
}

.slider:before {
    position: absolute;
    content: "";
    height: 12px;
    width: 12px;
    left: 4px;
    bottom: 4px;
    background-color: white;
    transition: 0.4s;
    border-radius: 50%;
}

input:checked + .slider {
    background-color: #4caf50;
}

input:checked + .slider:before {
    transform: translateX(14px);
}

    </style>
    <body>
        <div class="container">
     <%@include file="sidebar_brand.jsp" %>

    <div class="main-content">
        <h2>Thiết Lập Shop > Đơn vị vận chuyển</h2>
        <div class="tabs">
            <button class="tab-button active">Cài đặt Vận Chuyển</button>
            <button class="tab-button">Cài đặt Thanh Toán</button>
            <button class="tab-button">Cài đặt Chat</button>
            <button class="tab-button">Cài đặt Thông Báo</button>
            <button class="tab-button">Chế độ Tạm Nghỉ</button>
            <button class="tab-button">Nền Tảng Đối Tác (Kết Nối API)</button>
        </div>

        <div class="shipping-options">
            <div class="shipping-option">
                <h3>Hỏa Tốc</h3>
                <p>Phương thức vận chuyển với tốc độ giao hàng nhanh nhất</p>
                <div class="toggle">
                    <label class="switch">
                        <input type="checkbox" checked>
                        <span class="slider round"></span>
                    </label>
                </div>
            </div>

            <div class="shipping-option">
                <h3>Nhanh</h3>
                <p>Với kênh Nhanh, người mua sẽ được trải nghiệm dịch vụ vận chuyển chuyên nghiệp, nhanh và đáng tin cậy.</p>
                <div class="toggle">
                    <label class="switch">
                        <input type="checkbox" checked>
                        <span class="slider round"></span>
                    </label>
                </div>
            </div>

            <div class="shipping-option">
                <h3>Tiết Kiệm</h3>
                <p>Với kênh Tiết Kiệm, người mua sẽ được tận hưởng dịch vụ vận chuyển với giá thành hợp lý.</p>
                <div class="toggle">
                    <label class="switch">
                        <input type="checkbox" checked>
                        <span class="slider round"></span>
                    </label>
                </div>
            </div>

            <div class="shipping-option">
                <h3>Hàng Cồng Kềnh</h3>
                <p>Phương thức vận chuyển dành cho các đơn có kích thước lớn</p>
                <div class="toggle">
                    <label class="switch">
                        <input type="checkbox" checked>
                        <span class="slider round"></span>
                    </label>
                </div>
            </div>
        </div>
    </div>
</div>
    </body>
</html>
