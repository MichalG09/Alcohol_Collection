package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import pl.mgrzech.alcohols_collection.email.model.EmailMessage;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class SendEmailToAdminFromContactPage {

    private final SendEmail sendEmail;

    @Value("${admin.mail}")
    private String adminMail;

    /**
     * Method sends email to admin from contact page.
     * Method send email to addressee too if user marked this functionality in form
     * @param email email addressee
     */
    public void send(EmailMessage email) throws MessagingException {
        Context context = new Context();
        context.setVariable("text", email.getText());
        context.setVariable("author", email.getName());
        sendEmail.send(
                adminMail,
                "wiadomość z formularza: " + email.getSubject(),
                adminMail,
                context,
                "emailTemplate/fromFormToAdmin");
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
