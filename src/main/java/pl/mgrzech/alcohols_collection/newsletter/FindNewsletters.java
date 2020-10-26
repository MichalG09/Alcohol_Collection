package pl.mgrzech.alcohols_collection.newsletter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

@Component
@AllArgsConstructor
public class FindNewsletters {

    private final NewsletterRepository newsletterRepository;

    /**
     * Method returns all newsletters.
     * @return list all newsletter
     */
    public Iterable<Newsletter> findAll() {
        return newsletterRepository.findAll();
    }

    /**
     * Method return newsletter by unique code to unsubscribed.
     * @param uniqueCode unique code to find newsletter to delete
     * @return found newsletter
     */
    public Newsletter findNewsletterToDeleteByUniqueCode(int uniqueCode) {
        return newsletterRepository.findByCodeToVerifyDelete(uniqueCode).orElseGet(Newsletter::new);
    }
}
