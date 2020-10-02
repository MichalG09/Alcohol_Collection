package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

@Component
@RequiredArgsConstructor
public class SendEmailToAllNewsletters {

    @Value("${message.correct.email.toAllNewsletters}")
    private String messageCorrectSentEmailToAllNewsletter;

    @Value("${message.fail.email.toAllNewsletters}")
    private String messageFailSentEmailToAllNewsletter;

    @Value("${admin.mail}")
    private String adminMail;

    private final NewsletterRepository newsletterRepository;
    private final SendEmail sendEmail;

    /**
     * Method sends e-mail to all user saved to list newsletter.
     * @param subject subject email
     * @param textMessage text email
     * @param redirectAttributes redirectAttributes
     */
    public void sendMessageToAllPersonInNewsletter(String subject, String textMessage, RedirectAttributes redirectAttributes) {
        newsletterRepository.findAll().forEach(newsletter -> {
            Context context = new Context();
            context.setVariable("linkToUnsubscribe", "http://kolekcjaalkoholi.pl/delete/newsletter/" + newsletter.getCodeToVerifyDelete());
            context.setVariable("welcome", "Witaj " + newsletter.getName());
            context.setVariable("text", textMessage);
            sendEmail.send(newsletter.getEmail(),
                    subject,
                    adminMail,
                    context,
                    "emailTemplate/toAllNewsletters",
                    redirectAttributes,
                    messageCorrectSentEmailToAllNewsletter,
                    messageFailSentEmailToAllNewsletter);
        });
    }
}
