package pl.mgrzech.alcohols_collection.newsletter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

@Component
@RequiredArgsConstructor
public class DeleteNewsletter {

    private final NewsletterRepository newsletterRepository;

    @Value("${message.correct.newsletter.delete}")
    private String messageCorrectDeleteNewsletter;

    @Value("${message.fail.newsletter.delete}")
    private String messageFailDeleteNewsletter;

    /**
     * Method deleted newsletter by id.
     * @param id id newsletter to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteById(int id, RedirectAttributes redirectAttributes) {
        try {
            newsletterRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeleteNewsletter);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteNewsletter);
            e.printStackTrace();
        }
    }
}
