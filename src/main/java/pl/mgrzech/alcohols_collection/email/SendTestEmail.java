package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class SendTestEmail {

    private final SendEmail sendEmail;

    @Value("${admin.mail}")
    private String adminMail;

    public void sendTestEmailWithHTML(String to, String title, String content) {
        Context context = new Context();
        context.setVariable("header", "Nowy artykuł na CodeCouple");
        context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");
        context.setVariable("description", "Tutaj jakis opis...");
        sendEmail.send(adminMail, "Nowy subskrybent", adminMail, context, "emailTemplate/template");
    }
}
