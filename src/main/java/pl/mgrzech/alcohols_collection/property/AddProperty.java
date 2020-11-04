package pl.mgrzech.alcohols_collection.property;

import lombok.AllArgsConstructor;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

@AllArgsConstructor
public class AddProperty {

    private final PropertyRepository propertyRepository;

    /**
     * Method saves property.
     * @param property property to save
     */
    public void save(Property property) {
        propertyRepository.save(property);
    }
}
