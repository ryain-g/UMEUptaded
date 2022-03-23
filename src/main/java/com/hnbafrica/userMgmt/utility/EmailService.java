package com.hnbafrica.userMgmt.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    @Autowired
    JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void send(Email email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.addAttachment("email-background.png", new ClassPathResource("images"));
        Context context = new Context();
        context.setVariables(email.getProps());

        String html = templateEngine.process("email-template", context);

        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(html,true);
        emailSender.send(message);
    }
}
