package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import pl.mgrzech.alcohols_collection.entities.Newsletter;

@Component
@RequiredArgsConstructor
public class SendEmailAfterSubscribedNewsletter {

    private final SendEmail sendEmail;

    @Value("${admin.mail}")
    private String adminMail;

    /**
     * Method sands email to new newsletter after added to list newsletter.
     * @param newsletter new newsletter
     */
    public void sendEmailAfterSubscribedNewsletter(Newsletter newsletter) {
        Context context = new Context();
        context.setVariable("linkToUnsubscribe", "http://kolekcjaalkoholi.pl/delete/newsletter/" + newsletter.getCodeToVerifyDelete());
        context.setVariable("welcome", "Witaj " + newsletter.getName());
        sendEmail.send(
                newsletter.getEmail(),
                "Witamy w newsletter kolekcjaalkoholi.pl",
                adminMail,
                context,
                "emailTemplate/newNewsletter");
    }
}
