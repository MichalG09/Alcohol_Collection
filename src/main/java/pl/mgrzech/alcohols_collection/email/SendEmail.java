package pl.mgrzech.alcohols_collection.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
     * @param to email to send message
     * @param subject subject email
     * @param from addressee
     * @param context context with variable to HTML template
     * @param template name template to send message
     */
    public void send(String to, String subject, String from, Context context, String template) {
        try {
            sendEmail(to, subject, from, context, template);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method sends email and shows correct/fail message after sent message in view
     * @param to email to send message
     * @param subject addressee
     * @param from addressee
     * @param context context with variable to HTML template
     * @param template template name template to send message
     * @param redirectAttributes redirectAttributes
     * @param correctMessage message in view when send email was correct
     * @param failMessage message in view when send email fail
     */
    public void send(String to, String subject, String from, Context context, String template,
                     RedirectAttributes redirectAttributes, String correctMessage, String failMessage) {
        try {
            sendEmail(to, subject, from, context, template);
            redirectAttributes.addFlashAttribute("message", correctMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("messageError", failMessage);
        }
    }

    /**
     * Method creates email and converts html template and context to message text.
     * @param to email to send message
     * @param subject addressee
     * @param from addressee
     * @param context context with variable to HTML template
     * @param template template name template to send message
     * @throws MessagingException message exception
     */
    private void sendEmail(String to, String subject, String from, Context context, String template) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(templateEngine.process(template, context), true);
        javaMailSender.send(mail);
    }
}
