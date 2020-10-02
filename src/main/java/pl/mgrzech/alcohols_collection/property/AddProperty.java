package pl.mgrzech.alcohols_collection.property;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

@Component
@RequiredArgsConstructor
public class AddProperty {

    private final PropertyRepository propertyRepository;

    @Value("${message.correct.property.add}")
    private String messageCorrectAddProperty;

    @Value("${message.fail.property.add}")
    private String messageFailAddProperty;

    /**
     * Method saves property.
     * @param property property to save
     * @param redirectAttributes redirectAttributes
     */
    public void save(Property property, RedirectAttributes redirectAttributes) {
        try{
            propertyRepository.save(property);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddProperty);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailAddProperty);
        }
    }
}
