package pl.mgrzech.alcohols_collection.newsletter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.email.SendEmailAfterSubscribedNewsletter;
import pl.mgrzech.alcohols_collection.email.SendEmailToAdminWithNewNewsletter;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

@Component
@AllArgsConstructor
public class AddNewNewsletter {

    private final NewsletterRepository newsletterRepository;
    private final GenerateUniqueUnsubscribeCodeForNewsletter generateUniqueUnsubscribeCodeForNewsletter;

    /**
     * Method saves new newsletter.
     * Method adds to newsletter unique code for find newsletter during delete.
     * (only person from newsletter have this code in link to unsubscribe);
     * @param newsletter new newsletter
     */
    public void save(Newsletter newsletter) {
            newsletter.setCodeToVerifyDelete(generateUniqueUnsubscribeCodeForNewsletter.generate());
            newsletterRepository.save(newsletter);
    }
}
