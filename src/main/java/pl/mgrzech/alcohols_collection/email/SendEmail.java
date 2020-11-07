package pl.mgrzech.alcohols_collection.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@AllArgsConstructor
public class SendEmail {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    /**
     * Method sends email.
     * Method creates email and converts html template and context to message text.
     * @param to email to send message
     * @param subject subject email
     * @param from addressee
     * @param context context with variable to HTML template
     * @param template name template to send message
     */
    public void send(String to, String subject, String from, Context context, String template) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(templateEngine.process(template, context), true);
        javaMailSender.send(mail);
    }
}