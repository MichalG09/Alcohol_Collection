package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import pl.mgrzech.alcohols_collection.entities.Newsletter;

@Component
@RequiredArgsConstructor
public class SendEmailToAdminWithNewNewsletter {

    private final SendEmail sendEmail;

    @Value("${admin.mail}")
    private String adminMail;

    /**
     * Method sends email to admin with message about new newsletter.
     * @param newsletter new newsletter
     */
    public void sendEmailToAdminWithNewNewsletter(Newsletter newsletter) {
        Context context = new Context();
        context.setVariable("nameNewsletter", newsletter.getName());
        context.setVariable("emailNewsletter", newsletter.getEmail());
        sendEmail.send(adminMail, "Nowa subskrybcja newslettera", adminMail, context, "emailTemplate/newNewsletterToAdmin");
    }
}
