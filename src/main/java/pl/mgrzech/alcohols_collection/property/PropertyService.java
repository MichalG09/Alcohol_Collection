package pl.mgrzech.alcohols_collection.property;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Property;

/**
 * User have access to WelcomeText property (can edit and save).
 * For other mapping only ADMIN role have access.
 */
@Service
@AllArgsConstructor
public class PropertyService {

    private final AddProperty addProperty;
    private final DeleteProperty deleteProperty;
    private final FindProperty findProperty;

    /**
     * Method saves property.
     * @param property property to save
     * @param redirectAttributes redirectAttributes
     */
    public void saveProperty(Property property, RedirectAttributes redirectAttributes) {
        addProperty.save(property, redirectAttributes);
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
     * @return found property
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
        deleteProperty.deleteById(id, redirectAttributes);
    }

    /**
     * Method returns welcome text from property.
     * @return property with welcome text
     */
    public Property getPropertyWithWelcomeText() {
        return findProperty.findWelcomeTextFromProperty();
    }
}
