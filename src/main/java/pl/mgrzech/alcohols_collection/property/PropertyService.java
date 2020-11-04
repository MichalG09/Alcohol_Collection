package pl.mgrzech.alcohols_collection.property;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Property;

/**
 * User have access to WelcomeText property (can edit and save).
 * For other mapping only ADMIN role have access.
 */
@Service
@RequiredArgsConstructor
public class PropertyService {

    private final AddProperty addProperty;
    private final DeleteProperty deleteProperty;
    private final FindProperty findProperty;

    @Value("${message.correct.property.add}")
    private String messageCorrectAddProperty;

    @Value("${message.fail.property.add}")
    private String messageFailAddProperty;

    @Value("${message.correct.property.delete}")
    private String messageCorrectDeleteProperty;

    @Value("${message.fail.property.delete}")
    private String messageFailDeleteProperty;

    /**
     * Method saves property.
     * @param property property to save
     * @param redirectAttributes redirectAttributes
     */
    public void saveProperty(Property property, RedirectAttributes redirectAttributes) {
        try {
            addProperty.save(property);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddProperty);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailAddProperty);
        }
    }

    /**
     * Method returns all properties.
     * @return all properties
     */
    public Iterable<Property> findAllProperties() {
        return findProperty.findAll();
    }

    /**
     * Method returns property by Id.
     * @param id id property to find
     * @return found property by id
     */
    public Property findPropertyById(int id) {
        return findProperty.findById(id);
    }

    /**
     * Method deletes property by Id.
     * @param id id property to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deletePropertyById(int id, RedirectAttributes redirectAttributes) {
        try{
            deleteProperty.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeleteProperty);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteProperty);
            e.printStackTrace();
        }
    }

    /**
     * Method returns welcome text from property.
     */
    public void getPropertyWithWelcomeText(Model model) {
        model.addAttribute("welcomeText", findProperty.findWelcomeTextFromProperty());
    }
}
