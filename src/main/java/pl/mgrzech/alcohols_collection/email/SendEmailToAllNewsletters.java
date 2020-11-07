package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class SendEmailToAllNewsletters {

    @Value("${admin.mail}")
    private String adminMail;

    private final NewsletterRepository newsletterRepository;
    private final SendEmail sendEmail;

    /**
     * Method sends e-mail to all user saved to list newsletter.
     * @param subject            subject email
     * @param textMessage        text email
     */
    public void sendMessageToAllPersonInNewsletter(String subject, String textMessage) throws MessagingException {
        Context context;
        for(Newsletter newsletter : newsletterRepository.findAll()){
            context = new Context();
            context.setVariable("linkToUnsubscribe", "http://kolekcjaalkoholi.pl/delete/newsletter/" + newsletter.getCodeToVerifyDelete());
            context.setVariable("welcome", "Witaj " + newsletter.getName());
            context.setVariable("text", textMessage);
            sendEmail.send(newsletter.getEmail(),
                    subject,
                    adminMail,
                    context,
                    "emailTemplate/toAllNewsletters");
        }
    }
}
