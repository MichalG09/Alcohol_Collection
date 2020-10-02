package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import pl.mgrzech.alcohols_collection.email.model.EmailMessage;

@Component
@RequiredArgsConstructor
public class SendEmailToAdminFromContactPage {

    private final SendEmail sendEmail;

    @Value("${message.correct.email.toAdmin}")
    private String messageCorrectSentEmailToAdmin;

    @Value("${message.error.email.toAdmin}")
    private String messageErrorSentEmailToAdmin;

    @Value("${admin.mail}")
    private String adminMail;

    /**
     * Method sends email to admin from contact page.
     * Method send email to addressee too if user marked this functionality in form
     * @param email email addressee
     * @param redirectAttributes redirectAttributes
     */
    public void send(EmailMessage email, RedirectAttributes redirectAttributes) {
        Context context = new Context();
        context.setVariable("text", email.getText());
        context.setVariable("author", email.getName());
        sendEmail.send(
                adminMail,
                "wiadomość z formularza: " + email.getSubject(),
                adminMail,
                context,
                "emailTemplate/fromFormToAdmin",
                redirectAttributes,
                messageCorrectSentEmailToAdmin,
                messageErrorSentEmailToAdmin);
        if(email.isCopy()){
            sendEmail.send(
                    email.getEmail(),
                    "kopia wiadomości z formularza: " + email.getSubject(),
                    adminMail,
                    context,
                    "emailTemplate/fromFormToAdmin");
        }
    }
}
