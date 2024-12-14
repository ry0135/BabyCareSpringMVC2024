<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Baby Care Landing Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/landingPage.css">
</head>
<body>
<div class="landing-container">

    <section class="hero-section">
        <div class="hero-content">
            <h1>Get the right care for your child</h1>
            <p>Chúng tôi đưa ra những lời khuyên đáng tin cậy để giúp các bậc cha mẹ tự tin hơn trong việc chăm sóc con mình.</p>
            <a href="#" class="contact-btn">Contact Us</a>
        </div>
        <div class="hero-image">
            <img src="${pageContext.request.contextPath}/assets/images/p/mother-and-child.jpg" alt="Mother and Child">
        </div>
    </section>

    <section class="about-section">
        <div class="about-container">
            <div class="about-content">
                <h2>About Us</h2>
                <p>
                    Tại Baby Care, chúng tôi luôn mong muốn mang lại sự chăm sóc tốt nhất cho con bạn. Đội ngũ của chúng tôi bao gồm các chuyên gia giàu kinh nghiệm, tận tâm đảm bảo sự an toàn và sức khỏe của con bạn.
                </p>
                <p>
                    Chúng tôi cung cấp các dịch vụ được cá nhân hóa để đáp ứng nhu cầu riêng biệt của mỗi gia đình, giúp phụ huynh cảm thấy tự tin trong lựa chọn chăm sóc của mình.
                </p>
                <a href="#" class="learn-more-btn">Learn More</a>
            </div>
            <div class="about-image">
                <img src="${pageContext.request.contextPath}/assets/images/p/h4.jpg" alt="About Us">
            </div>
        </div>
    </section>

    <section class="awards-section">
        <div class="award">
            <h3>500+</h3>
            <p>Happy Parents</p>
        </div>
        <div class="award">
            <h3>6+</h3>
            <p>Awards</p>
        </div>
    </section>

    <section class="reviews-section">
        <div class="review-list">
            <img src="${pageContext.request.contextPath}/assets/images/p/review1.png" alt="Review 1">
            <img src="${pageContext.request.contextPath}/assets/images/p/review2.png" alt="Review 2">
            <img src="${pageContext.request.contextPath}/assets/images/p/review3.png" alt="Review 3">
        </div>
    </section>
</div>
</body>
</html>
