package fa.training.phonestore.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordResetEmail(String to, String newPassword) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("noreply@yourcompany.com", "PhoneStore");
        helper.setTo(to);
        helper.setSubject("Mật khẩu mới của bạn");
        helper.setText("Mật khẩu mới của bạn là: " + newPassword);

        emailSender.send(message);
    }
}