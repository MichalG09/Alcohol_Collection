package pl.mgrzech.alcohols_collection.newsletter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

@Component
@AllArgsConstructor
public class DeleteNewsletter {

    private final NewsletterRepository newsletterRepository;

    /**
     * Method deleted newsletter by id.
     * @param id id newsletter to delete
     */
    public void deleteById(int id) {
            newsletterRepository.deleteById(id);
    }
}
