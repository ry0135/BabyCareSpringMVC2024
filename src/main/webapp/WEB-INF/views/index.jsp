<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BabyCare</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>

<body>

<jsp:include page="include/header.jsp" />

<!-- Modal Search Start -->
<div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-fullscreen">
        <div class="modal-content rounded-0">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex align-items-center">
                <div class="input-group w-75 mx-auto d-flex">
                    <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                    <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Search End -->



<!-- Hero Start -->
<div class="container-fluid py-5 hero-header wow fadeIn" data-wow-delay="0.1s">
    <div class="container py-5">
        <div class="row g-5">
            <div class="col-lg-7 col-md-12">
                <h1 class="mb-3 text-primary">We Care Your Baby</h1>
                <h1 class="mb-5 display-1 text-white">The Best Play Area For Your Kids</h1>
                <a href="" class="btn btn-primary px-4 py-3 px-md-5  me-4 btn-border-radius">Get Started</a>
                <a href="" class="btn btn-primary px-4 py-3 px-md-5 btn-border-radius">Learn More</a>
            </div>
        </div>
    </div>
</div>
<!-- Hero End -->
<!-- Buy Now Section -->
<div class="container py-5">
    <div class="row">
        <!-- Buy Now Section -->
        <div class="col-lg-4 mb-4">
            <div class="row">
                <div class="col-12 mb-3">
                    <img src="https://i.imgur.com/xsQKAtN.png" alt="Buy Now" class="img-fluid">
                </div>
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Mua Hàng</h5>
                            <p class="card-text">Mua các sản phẩm chăm sóc trẻ em tốt nhất từ cửa hàng của chúng tôi.</p>
                            <a href="product" class="btn btn-primary">Xem Sản Phẩm</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Đăng ký cộng tác viên Section -->
        <div class="col-lg-4 mb-4">
            <div class="row">
                <div class="col-12 mb-3">
                    <img src="https://i.imgur.com/P0D4wcI.png" alt="Register" class="img-fluid">
                </div>
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Đăng ký Cộng Tác Viên</h5>
                            <p class="card-text">Trở thành một cộng tác viên và kiếm thêm thu nhập bằng cách bán các sản phẩm chăm sóc trẻ em.</p>
                            <a href="addBrand" class="btn btn-primary">Đăng Ký</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Săn Sale Section -->
        <div class="col-lg-4 mb-4">
            <div class="row">
                <div class="col-12 mb-3">
                    <img src="https://i.imgur.com/4kbIJrp.png" alt="Sale" class="img-fluid">
                </div>
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Săn Sale</h5>
                            <p class="card-text">Khám phá các ưu đãi và mã giảm giá hấp dẫn từ cửa hàng của chúng tôi.</p>
                            <a href="preferential" class="btn btn-primary">Xem Ưu Đãi</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 mb-4">
            <div class="row">
                <div class="col-12 mb-3">
                    <img src="https://i.imgur.com/4kbIJrp.png" alt="Sale" class="img-fluid">
                </div>
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Dịch Vụ</h5>
                            <p class="card-text">Khám phá các dịch vụ hấp dẫn từ cửa hàng của chúng tôi.</p>
                            <a href="preferential" class="btn btn-primary">Xem Ưu Đãi</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>





<!-- Services End -->
<div class="row mt-5">
    <div class="col-12">
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:forEach var="i" begin="1" end="${numberPage}">
                    <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                        <a class="page-link" href="listService?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>

<div class="container-fluid about bg-light py-5">
    <div class="container py-5">
        <div class="row g-5 align-items-center">
            <div class="col-lg-5 wow fadeInLeft" data-wow-delay="0.2s">
                <div class="about-img pb-5 ps-5">
                    <img src="images/y/y5.jpg" class="img-fluid rounded w-100" style="object-fit: cover;" alt="Image">


                </div>
            </div>
            <div class="col-lg-7 wow fadeInRight" data-wow-delay="0.4s">
                <div class="section-title text-start mb-5">
                    <h4 class="sub-title pe-3 mb-0">About Us</h4>
                    <h1 class="display-3 mb-4">Chúng tôi luôn sẵn sàng mang lại những dịch vụ tốt nhất cho bạn.</h1>
                    <p class="mb-4">Babycare là trang thông tin dành cho Bố và Bé, chuyên cung cấp các dịch vụ tiện ích cho em bé. Nhằm giúp cho độc giả có những quyết định tốt trong việc chăm sóc sức khỏe, xây dựng đời sống gia đình và nuôi dạy trẻ tốt hơn</p>
                    <div class="mb-4">
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Dịch vụ chuyên nghiệp và nhanh chóng</p>
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Cung cấp đồ trang trí, nội thất và phụ kiện theo yêu cầu</p>
                        <p class="text-secondary"><i class="fa fa-check text-primary me-2"></i> Chi Phí hợp lý </p>
                    </div>
                    <a href="#" class="btn btn-primary rounded-pill text-white py-3 px-5">Discover More</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer Start -->
<div class="container-fluid footer py-5 wow fadeIn" data-wow-delay="0.1s">
    <div class="container py-5">
        <div class="row g-5">
            <div class="col-md-6 col-lg-4 col-xl-3">
                <div class="footer-item">
                    <h2 class="fw-bold mb-3"><span class="text-primary mb-0">Baby</span><span class="text-secondary">Care</span></h2>
                    <p class="mb-4">There cursus massa at urnaaculis estieSed aliquamellus vitae ultrs condmentum leo massamollis its estiegittis miristum.</p>
                    <div class="border border-primary p-3 rounded bg-light">
                        <h5 class="mb-3">Newsletter</h5>
                        <div class="position-relative mx-auto border border-primary rounded" style="max-width: 400px;">
                            <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                            <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2 text-white">SignUp</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 col-xl-3">
                <div class="footer-item">
                    <div class="d-flex flex-column p-4 ps-5 text-dark border border-primary"
                         style="border-radius: 50% 20% / 10% 40%;">
                        <p>Monday: 8am to 5pm</p>
                        <p>Tuesday: 8am to 5pm</p>
                        <p>Wednes: 8am to 5pm</p>
                        <p>Thursday: 8am to 5pm</p>
                        <p>Friday: 8am to 5pm</p>
                        <p>Saturday: 8am to 5pm</p>
                        <p class="mb-0">Sunday: Closed</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 col-xl-3">
                <div class="footer-item">
                    <h4 class="text-primary mb-4 border-bottom border-primary border-2 d-inline-block p-2 title-border-radius">LOCATION</h4>
                    <div class="d-flex flex-column align-items-start">
                        <a href="" class="text-body mb-4"><i class="fa fa-map-marker-alt text-primary me-2"></i> 104 North tower New York, USA</a>
                        <a href="" class="text-start rounded-0 text-body mb-4"><i class="fa fa-phone-alt text-primary me-2"></i> (+012) 3456 7890 123</a>
                        <a href="" class="text-start rounded-0 text-body mb-4"><i class="fas fa-envelope text-primary me-2"></i> exampleemail@gmail.com</a>
                        <a href="" class="text-start rounded-0 text-body mb-4"><i class="fa fa-clock text-primary me-2"></i> 26/7 Hours Service</a>
                        <div class="footer-icon d-flex">
                            <a class="btn btn-primary btn-sm-square me-3 rounded-circle text-white" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-primary btn-sm-square me-3 rounded-circle text-white" href=""><i class="fab fa-twitter"></i></a>
                            <a href="#" class="btn btn-primary btn-sm-square me-3 rounded-circle text-white"><i class="fab fa-instagram"></i></a>
                            <a href="#" class="btn btn-primary btn-sm-square rounded-circle text-white"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 col-xl-3">
                <div class="footer-item">
                    <h4 class="text-primary mb-4 border-bottom border-primary border-2 d-inline-block p-2 title-border-radius">OUR GALLARY</h4>
                    <div class="row g-3">
                        <div class="col-4">
                            <div class="footer-galary-img rounded-circle border border-primary">
                                <img src="img/galary-1.jpg" class="img-fluid rounded-circle p-2" alt="">
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="footer-galary-img rounded-circle border border-primary">
                                <img src="img/galary-2.jpg" class="img-fluid rounded-circle p-2" alt="">
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="footer-galary-img rounded-circle border border-primary">
                                <img src="img/galary-3.jpg" class="img-fluid rounded-circle p-2" alt="">
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="footer-galary-img rounded-circle border border-primary">
                                <img src="img/galary-4.jpg" class="img-fluid rounded-circle p-2" alt="">
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="footer-galary-img rounded-circle border border-primary">
                                <img src="img/galary-5.jpg" class="img-fluid rounded-circle p-2" alt="">
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="footer-galary-img rounded-circle border border-primary">
                                <img src="img/galary-6.jpg" class="img-fluid rounded-circle p-2" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer End -->


<!-- Copyright Start -->
<div class="container-fluid copyright bg-dark py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
            </div>
            <div class="col-md-6 my-auto text-center text-md-end text-white">
                <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a>
            </div>
        </div>
    </div>
</div>
<!-- Copyright End -->


<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>
</body>

<script>
    var stompClient = null;

    function setConnected(connected) {
        var conversationDiv = document.getElementById('conversationDiv');
        var response = document.getElementById('response');

        // Kiểm tra nếu các phần tử tồn tại
        if (conversationDiv && response) {
            conversationDiv.style.visibility = connected ? 'visible' : 'hidden';
            response.innerHTML = '';
        }
    }

    window.onload = function() {
        var account = '${sessionScope.account != null ? sessionScope.account.username : ""}'; // Lấy thông tin người dùng từ session

        if (account !== "") {
            // Người dùng đã đăng nhập, tự động kết nối WebSocket
            connect();

    };


    function connect() {
        var socket = new SockJS('/Baby_war/chat');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            // Subscribe nhận tin nhắn
            var userId = document.getElementById('fromId').value; // ID người dùng hiện tại
            stompClient.subscribe('/topic/messages', function(message) {
                showMessage(JSON.parse(message.body));
            });

            // Subscribe nhận thông báo
            stompClient.subscribe('/user/' + userId + '/queue/notifications', function(notification) {
                showNotification(JSON.parse(notification.body));
            });
        });
    }

    function showNotification(notification) {
        alert(notification.message); // Hoặc hiển thị thông báo trong UI
    }


    function sendMessage() {
        var toId = document.getElementById('toId').value;
        var fromId = document.getElementById('fromId').value;
        var messageText = document.getElementById('messageText').value;

        if (toId && fromId && messageText) {
            var message = {
                toId: toId,
                fromId: fromId,
                messageText: messageText,
                timestamp: new Date().toISOString()
            };

            stompClient.send("/app/chat", {}, JSON.stringify(message));
        } else {
            alert("Please fill in all fields!");
        }
    }

    function showMessage(message) {
        var toId = document.getElementById('toId').value;
        var fromId = document.getElementById('fromId').value;

        if ((message.toId === toId && message.fromId === fromId) || (message.toId === fromId && message.fromId === toId)) {
            var response = document.getElementById('response');
            var messageElement = document.createElement('div');
            messageElement.className = 'chat-message';

            if (message.fromId === fromId) {
                messageElement.classList.add('from-current-user');
            } else {
                messageElement.classList.add('from-other-user');
            }

            var p = document.createElement('p');
            p.appendChild(document.createTextNode(message.messageText));
            messageElement.appendChild(p);

            response.appendChild(messageElement);

            response.scrollTop = response.scrollHeight;
        }

    }
</script>

</html>
