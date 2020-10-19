package pl.mgrzech.alcohols_collection.newsletter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.email.SendEmailAfterSubscribedNewsletter;
import pl.mgrzech.alcohols_collection.email.SendEmailToAdminWithNewNewsletter;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;
import pl.mgrzech.alcohols_collection.property.FindProperty;

@Component
@RequiredArgsConstructor
public class AddNewNewsletter {

    private final NewsletterRepository newsletterRepository;
    private final FindProperty findProperty;
    private final SendEmailToAdminWithNewNewsletter sendEmailToAdminWithNewNewsletter;
    private final SendEmailAfterSubscribedNewsletter sendEmailAfterSubscribedNewsletter;
    private final GenerateUniqueUnsubscribeCode generateUniqueUnsubscribeCode;

    @Value("${message.correct.newsletter.add}")
    private String messageCorrectAddNewsletter;

    @Value("${message.fail.newsletter.add}")
    private String messageFailAddNewsletter;

    /**
     * Method saves new newsletter.
     * Method adds to newsletter unique code for find newsletter during delete.
     * (only person from newsletter have this code in link to unsubscribe);
     * @param newsletter new newsletter
     * @param redirectAttributes redirectAttributes
     */
    public void save(Newsletter newsletter, RedirectAttributes redirectAttributes) {
        try{
            newsletter.setCodeToVerifyDelete(generateUniqueUnsubscribeCode.generate());
            newsletterRepository.save(newsletter);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddNewsletter);
            sendEmailToAdminWithNewNewsletter.sendEmailToAdminWithNewNewsletter(newsletter);
            sendEmailAfterSubscribedNewsletter.sendEmailAfterSubscribedNewsletter(newsletter);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailAddNewsletter);
            e.printStackTrace();
        }
    }
}
