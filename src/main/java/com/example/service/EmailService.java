package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {
    String linkweb = "http://www.babycare.com";
    String supportEmail = "support@babycare.com";

    String hotline = "123-456-7890";
    @Autowired
    private JavaMailSender mailSender;

    public void sendCodeToEmail(String email, String code) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("🔑 Mã Xác Minh Tài Khoản BabyCare");

            // Nội dung email với định dạng HTML
            String content = "<p>Xin chào,</p>"
                    + "<p>💡 Đây là mã xác minh của bạn: <b>" + code + "</b></p>"
                    + "<p>Vui lòng nhập mã này vào trang xác minh để hoàn tất quá trình đăng ký hoặc khôi phục tài khoản. ✅</p>"
                    + "<p>Nếu bạn không yêu cầu mã này, hãy bỏ qua email này.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>🌟 Đội ngũ BabyCare</b><br>"
                    + "🌐 <a href='" + linkweb + "'>Website</a><br>"
                    + "📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>";

            helper.setText(content, true); // `true` để kích hoạt HTML trong nội dung
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email mã xác minh thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public void sendCodeToEmailResendCode(String email, String code) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("🔄 Mã Xác Minh Đã Được Gửi Lại");

            // Nội dung email với định dạng HTML
            String content = "<p>Xin chào,</p>"
                    + "<p>🔄 Chúng tôi đã gửi lại mã xác minh của bạn: <b>" + code + "</b></p>"
                    + "<p>Vui lòng nhập mã này vào trang xác minh để hoàn tất quá trình đăng ký hoặc khôi phục tài khoản. ✅</p>"
                    + "<p>Nếu bạn không yêu cầu mã này, hãy bỏ qua email này.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>🌟 Đội ngũ BabyCare</b><br>"
                    + "🌐 <a href='" + linkweb + "'>Website</a><br>"
                    + "📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>";

            helper.setText(content, true); // `true` để kích hoạt HTML trong nội dung
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi lại email mã xác minh thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public void sendVerifyCodeAndUpdatePassword(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã xác minh đặt lại mật khẩu");
        message.setText("Xin chào,\n\n"
                + "Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình. "
                + "Mã xác minh của bạn là: " + code + "\n\n"
                + "Vui lòng nhập mã này trên trang đặt lại mật khẩu để tiếp tục. "
                + "Nếu bạn không yêu cầu hành động này, hãy bỏ qua email này.\n\n"
                + "Trân trọng,\n"
                + "Đội ngũ hỗ trợ BabyCare");
        mailSender.send(message);
        System.out.println("Đã gửi email mã xác minh thành công.");
    }

    public void sendCodeToEmailSuccsessOrder(String orderCode, String email, String address, String customerName) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage thay vì SimpleMailMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("📦 Xác nhận giao hàng thành công 🎉");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã mua sắm tại BabyCare! 😊</p>"
                    + "<p>Chúng tôi xin thông báo rằng đơn hàng của bạn với mã đơn hàng: <b>" + orderCode + "</b> đã được giao thành công. ✅</p>"
                    + "<p><b>📅 Thời gian giao hàng:</b> " + formattedDate + "</p>"
                    + "<p><b>📍 Địa chỉ giao hàng:</b> " + address + "</p>"
                    + "<p>Chúng tôi hy vọng bạn hài lòng với sản phẩm và dịch vụ của chúng tôi. 🌟</p>"
                    + "<p>Nếu bạn có bất kỳ câu hỏi hoặc phản hồi nào, vui lòng liên hệ với đội ngũ hỗ trợ khách hàng qua email hoặc hotline. 📞✉️</p>"
                    + "<p>Một lần nữa, cảm ơn bạn đã tin tưởng BabyCare. 🥰 Chúng tôi rất mong được phục vụ bạn trong tương lai.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b><br>"
                    + "🌐 <a href='" + linkweb + "'>Website</a><br>"
                    + "📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email xác nhận giao hàng thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }


    public void sendCodeToEmailSuccsessBooking(String serviceName, String Slot, String email, String address, String customerName) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage thay vì SimpleMailMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("📦 Xác nhận đặt dịch vụ thành công 🎉");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã đặt dịch vụ tại BabyCare! 😊</p>"
                    + "<p>Chúng tôi xin thông báo rằng dịch: <b>" + serviceName + "</b> đã được đặt thành công. ✅</p>"
                    + "<p><b>📅 Thời gian:</b> " + formattedDate + "</p>"
                    + "<p><b>📅 Slot:</b> " + Slot + "</p>"
                    + "<p><b>📍 Địa chỉ làm dịch vụ:</b> " + address + "</p>"
                    + "<p>Chúng tôi hy vọng bạn hài lòng với sản phẩm và dịch vụ của chúng tôi. 🌟</p>"
                    + "<p>Nếu bạn có bất kỳ câu hỏi hoặc phản hồi nào, vui lòng liên hệ với đội ngũ hỗ trợ khách hàng qua email hoặc hotline. 📞✉️</p>"
                    + "<p>Một lần nữa, cảm ơn bạn đã tin tưởng BabyCare. 🥰 Chúng tôi rất mong được phục vụ bạn trong tương lai.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b><br>"
                    + "🌐 <a href='" + linkweb + "'>Website</a><br>"
                    + "📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email xác nhận giao hàng thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }
    public void sendCodeToEmailApproveCTV(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("🎉 Chúc mừng bạn đã trở thành CTV dịch vụ của BabyCare! 🥳");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã quan tâm và đăng ký trở thành Cộng Tác Viên (CTV) của BabyCare! 🌟</p>"
                    + "<p>Chúng tôi rất vui mừng thông báo rằng hồ sơ của bạn đã được duyệt thành công. 👏</p>"
                    + "<p><b>📅 Ngày duyệt:</b> " + formattedDate + "</p>"
                    + "<p>Kể từ bây giờ, bạn đã chính thức là một phần của đội ngũ CTV tại BabyCare. Chúng tôi hy vọng bạn sẽ tận dụng cơ hội này để cùng chúng tôi mang đến những dịch vụ tốt nhất cho khách hàng. 🥰</p>"
                    + "<p>👉 Nếu bạn có bất kỳ thắc mắc hoặc cần hỗ trợ, đừng ngần ngại liên hệ qua các kênh dưới đây:</p>"
                    + "<p>📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>"
                    + "<p>Chúc bạn thành công và có trải nghiệm tuyệt vời cùng BabyCare!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email xác nhận duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }
    public void sendCodeToEmailUnApproveCTV(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("❌ Đăng ký CTV dịch vụ của BabyCare không được chấp nhận");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã quan tâm và đăng ký trở thành Cộng Tác Viên (CTV) của BabyCare! 🌟</p>"
                    + "<p>Rất tiếc, sau khi xem xét hồ sơ của bạn, chúng tôi phải thông báo rằng bạn không được chấp nhận trở thành CTV dịch vụ tại BabyCare.</p>"
                    + "<p><b>📅 Ngày thông báo:</b> " + formattedDate + "</p>"
                    + "<p>Chúng tôi hiểu rằng điều này có thể làm bạn thất vọng, nhưng hy vọng bạn sẽ tiếp tục theo dõi các cơ hội khác từ BabyCare trong tương lai. Chúng tôi rất trân trọng sự quan tâm và tham gia của bạn.</p>"
                    + "<p>👉 Nếu bạn có bất kỳ câu hỏi hoặc muốn biết thêm lý do về quyết định này, đừng ngần ngại liên hệ với chúng tôi qua các kênh dưới đây:</p>"
                    + "<p>📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>"
                    + "<p>Chúc bạn mọi điều tốt đẹp và thành công trong những kế hoạch sắp tới!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email thông báo không duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public void sendCodeToEmailProductApproval(String productName, String email, String customerName) {
        // Lấy ngày hiện tại nếu approvalDate không được cung cấp
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày
        // Tạo MimeMessage thay vì SimpleMailMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("🎉 Duyệt sản phẩm thành công - Chúc mừng bạn! 🎉");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã tin tưởng và đăng sản phẩm của mình lên BabyCare! 😊</p>"
                    + "<p>Chúng tôi vui mừng thông báo rằng sản phẩm của bạn với tên: <b>" + productName + "</b> đã được duyệt thành công và sẵn sàng trên sàn. ✅</p>"
                    + "<p><b>📅 Ngày duyệt:</b> " + formattedDate + "</p>"
                    + "<p>Chúng tôi hy vọng rằng sản phẩm của bạn sẽ nhận được sự quan tâm từ nhiều khách hàng trên nền tảng của chúng tôi. 🌟</p>"
                    + "<p>Nếu bạn cần hỗ trợ thêm, vui lòng liên hệ đội ngũ hỗ trợ của chúng tôi qua email hoặc hotline. 📞✉️</p>"
                    + "<p>Chúc bạn kinh doanh thành công và nhận được nhiều đơn hàng. 🥰</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b><br>"
                    + "🌐 <a href='" + linkweb + "'>Website</a><br>"
                    + "📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email thông báo duyệt sản phẩm thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }
    public void sendCodeToEmailProductRejection(String productName, String email, String customerName, String reason) {
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày
        // Tạo MimeMessage thay vì SimpleMailMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("❌ Sản phẩm không được duyệt - Thông báo từ BabyCare");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã đăng sản phẩm của mình lên BabyCare. Tuy nhiên, chúng tôi rất tiếc thông báo rằng sản phẩm của bạn với tên: <b>" + productName + "</b> không được duyệt. ❌</p>"
                    + "<p><b>📅 Ngày thông báo:</b> " + formattedDate + "</p>"
                    + "<p><b>🔍 Lý do không được duyệt:</b> " + reason + "</p>"
                    + "<p>Vui lòng kiểm tra lại và chỉnh sửa sản phẩm của bạn theo các tiêu chuẩn của chúng tôi trước khi đăng lại. Nếu bạn cần hỗ trợ hoặc giải đáp thắc mắc, vui lòng liên hệ đội ngũ hỗ trợ của chúng tôi. 📞✉️</p>"
                    + "<p>Rất mong bạn thông cảm và tiếp tục đồng hành cùng BabyCare. 🌟</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b><br>"
                    + "🌐 <a href='" + linkweb + "'>Website</a><br>"
                    + "📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email thông báo không duyệt sản phẩm.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public void sendCodeToEmailApproveCTVOrder(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("🎉 Chúc mừng trở thành CTV của BabyCare! 🥳");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Chúc mừng bạn đã chính thức trở thành Cộng Tác Viên (CTV) của BabyCare! 🌟</p>"
                    + "<p>Ngày phê duyệt: <b>" + formattedDate + "</b></p>"
                    + "<p>Hãy bắt đầu hành trình cùng chúng tôi để mang đến dịch vụ tốt nhất cho khách hàng. 💖</p>"
                    + "<p>Nếu cần hỗ trợ, vui lòng liên hệ qua:</p>"
                    + "<ul>"
                    + "<li>Email: " + supportEmail + "</li>"
                    + "<li>Hotline: " + hotline + "</li>"
                    + "</ul>"
                    + "<p>Chúc bạn nhiều thành công!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email xác nhận duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }


    public void sendCodeToEmailUnApproveCTVOrder(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("Thông báo về hồ sơ đăng ký CTV tại BabyCare");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã đăng ký trở thành Cộng Tác Viên (CTV) của BabyCare.</p>"
                    + "<p>Rất tiếc, hồ sơ của bạn chưa phù hợp để trở thành CTV sản phẩm tại BabyCare.</p>"
                    + "<p>Ngày thông báo: <b>" + formattedDate + "</b></p>"
                    + "<p>Chúng tôi hy vọng sẽ có cơ hội hợp tác với bạn trong những lần đăng ký sau. Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ qua:</p>"
                    + "<ul>"
                    + "<li>Email hỗ trợ: " + supportEmail + "</li>"
                    + "<li>Hotline: " + hotline + "</li>"
                    + "</ul>"
                    + "<p>Chúc bạn nhiều thành công trong tương lai!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email thông báo không duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }




    public void sendCodeToEmailApproveCTV(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("🎉 Chúc mừng bạn đã trở thành CTV dịch vụ của BabyCare! 🥳");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã quan tâm và đăng ký trở thành Cộng Tác Viên (CTV) của BabyCare! 🌟</p>"
                    + "<p>Chúng tôi rất vui mừng thông báo rằng hồ sơ của bạn đã được duyệt thành công. 👏</p>"
                    + "<p><b>📅 Ngày duyệt:</b> " + formattedDate + "</p>"
                    + "<p>Kể từ bây giờ, bạn đã chính thức là một phần của đội ngũ CTV tại BabyCare. Chúng tôi hy vọng bạn sẽ tận dụng cơ hội này để cùng chúng tôi mang đến những dịch vụ tốt nhất cho khách hàng. 🥰</p>"
                    + "<p>👉 Nếu bạn có bất kỳ thắc mắc hoặc cần hỗ trợ, đừng ngần ngại liên hệ qua các kênh dưới đây:</p>"
                    + "<p>📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>"
                    + "<p>Chúc bạn thành công và có trải nghiệm tuyệt vời cùng BabyCare!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email xác nhận duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public void sendCodeToEmailUnApproveCTV(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("❌ Đăng ký CTV dịch vụ của BabyCare không được chấp nhận");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã quan tâm và đăng ký trở thành Cộng Tác Viên (CTV) của BabyCare! 🌟</p>"
                    + "<p>Rất tiếc, sau khi xem xét hồ sơ của bạn, chúng tôi phải thông báo rằng bạn không được chấp nhận trở thành CTV dịch vụ tại BabyCare.</p>"
                    + "<p><b>📅 Ngày thông báo:</b> " + formattedDate + "</p>"
                    + "<p>Chúng tôi hiểu rằng điều này có thể làm bạn thất vọng, nhưng hy vọng bạn sẽ tiếp tục theo dõi các cơ hội khác từ BabyCare trong tương lai. Chúng tôi rất trân trọng sự quan tâm và tham gia của bạn.</p>"
                    + "<p>👉 Nếu bạn có bất kỳ câu hỏi hoặc muốn biết thêm lý do về quyết định này, đừng ngần ngại liên hệ với chúng tôi qua các kênh dưới đây:</p>"
                    + "<p>📧 Email hỗ trợ: " + supportEmail + "<br>"
                    + "📞 Hotline: " + hotline + "</p>"
                    + "<p>Chúc bạn mọi điều tốt đẹp và thành công trong những kế hoạch sắp tới!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email thông báo không duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public void sendCodeToEmailApproveCTVOrder(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("🎉 Chúc mừng trở thành CTV của BabyCare! 🥳");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Chúc mừng bạn đã chính thức trở thành Cộng Tác Viên (CTV) của BabyCare! 🌟</p>"
                    + "<p>Ngày phê duyệt: <b>" + formattedDate + "</b></p>"
                    + "<p>Hãy bắt đầu hành trình cùng chúng tôi để mang đến dịch vụ tốt nhất cho khách hàng. 💖</p>"
                    + "<p>Nếu cần hỗ trợ, vui lòng liên hệ qua:</p>"
                    + "<ul>"
                    + "<li>Email: " + supportEmail + "</li>"
                    + "<li>Hotline: " + hotline + "</li>"
                    + "</ul>"
                    + "<p>Chúc bạn nhiều thành công!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email xác nhận duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public void sendCodeToEmailUnApproveCTVOrder(String customerName, String email) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Định dạng ngày

        // Tạo MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // "UTF-8" để hỗ trợ Unicode
            helper.setTo(email);
            helper.setSubject("Thông báo về hồ sơ đăng ký CTV tại BabyCare");

            // Nội dung email
            String content = "<p>Xin chào <b>" + customerName + "</b>,</p>"
                    + "<p>Cảm ơn bạn đã đăng ký trở thành Cộng Tác Viên (CTV) của BabyCare.</p>"
                    + "<p>Rất tiếc, hồ sơ của bạn chưa phù hợp để trở thành CTV sản phẩm tại BabyCare.</p>"
                    + "<p>Ngày thông báo: <b>" + formattedDate + "</b></p>"
                    + "<p>Chúng tôi hy vọng sẽ có cơ hội hợp tác với bạn trong những lần đăng ký sau. Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ qua:</p>"
                    + "<ul>"
                    + "<li>Email hỗ trợ: " + supportEmail + "</li>"
                    + "<li>Hotline: " + hotline + "</li>"
                    + "</ul>"
                    + "<p>Chúc bạn nhiều thành công trong tương lai!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><b>Đội ngũ BabyCare</b></p>";

            // Thiết lập nội dung HTML
            helper.setText(content, true); // `true` để gửi nội dung HTML
            mailSender.send(mimeMessage);
            System.out.println("✅ Đã gửi email thông báo không duyệt CTV thành công.");
        } catch (MessagingException e) {
            System.err.println("❌ Lỗi khi gửi email: " + e.getMessage());
        }
    }
}




