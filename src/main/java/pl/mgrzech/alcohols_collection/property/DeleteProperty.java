package pl.mgrzech.alcohols_collection.property;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

@Component
@RequiredArgsConstructor
public class DeleteProperty {

    private final PropertyRepository propertyRepository;

    @Value("${message.correct.property.delete}")
    private String messageCorrectDeleteProperty;

    @Value("${message.fail.property.delete}")
    private String messageFailDeleteProperty;

    /**
     * Method deletes property by Id.
     * @param id id property to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteById(int id, RedirectAttributes redirectAttributes) {
        try {
            propertyRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeleteProperty);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteProperty);
        }
    }
}
