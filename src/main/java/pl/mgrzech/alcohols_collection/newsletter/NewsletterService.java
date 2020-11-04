package pl.mgrzech.alcohols_collection.newsletter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.email.SendEmailToAllNewsletters;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.property.FindProperty;

@Service
@RequiredArgsConstructor
public class NewsletterService {

    private final FindProperty findProperty;
    private final AddNewNewsletter addNewNewsletter;
    private final DeleteNewsletter deleteNewsletter;
    private final FindNewsletters findNewsletters;
    private final SendEmailToAllNewsletters sendEmailToAllNewsletters;

    @Value("${message.correct.newsletter.add}")
    private String messageCorrectAddNewsletter;

    @Value("${message.fail.newsletter.add}")
    private String messageFailAddNewsletter;

    @Value("${message.correct.newsletter.delete}")
    private String messageCorrectDeleteNewsletter;

    @Value("${message.fail.newsletter.delete}")
    private String messageFailDeleteNewsletter;

    /**
     * Method returns welcome text from property.
     * @return property with welcome text
     */
    public Property findWelcomeTextFromProperty() {
        return findProperty.findWelcomeTextFromProperty();
    }

    /**
     * Method saves new newsletter.
     * Method adds to newsletter unique code for find newsletter during delete.
     * (only person from newsletter have this code in link to unsubscribe);
     * @param newsletter new newsletter
     * @param redirectAttributes redirectAttributes
     */
    public void saveNewNewsletter(Newsletter newsletter, RedirectAttributes redirectAttributes) {
        try{
            addNewNewsletter.save(newsletter);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddNewsletter);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailAddNewsletter);
            e.printStackTrace();
        }
    }

    /**
     * Method returns all newsletters.
     * @return all newsletters
     */
    public Iterable<Newsletter> findAllNewsletters() {
        return findNewsletters.findAll();
    }

    /**
     * Method sends e-mail to all user saved to list newsletter.
     * @param subject subject email
     * @param textMessage text email
     * @param redirectAttributes redirectAttributes
     */
    public void sendEmailToAllNewsletters(String subject, String textMessage, RedirectAttributes redirectAttributes) {
        sendEmailToAllNewsletters.sendMessageToAllPersonInNewsletter(subject, textMessage, redirectAttributes);
    }

    /**
     * Method return newsletter by unique code to unsubscribed.
     * @param uniqueCode unique code to find newsletter to delete
     * @return found newsletter
     */
    public Newsletter findNewsletterToDeleteByUniqueCode(int uniqueCode) {
        return findNewsletters.findNewsletterToDeleteByUniqueCode(uniqueCode);
    }

    /**
     * Method deleted newsletter by id.
     * @param id id newsletter to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteNewsletterById(int id, RedirectAttributes redirectAttributes) {
        try {
            deleteNewsletter.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeleteNewsletter);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteNewsletter);
            e.printStackTrace();
        }
    }
}
