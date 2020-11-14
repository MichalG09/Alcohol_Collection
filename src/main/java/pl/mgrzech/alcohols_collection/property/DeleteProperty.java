package pl.mgrzech.alcohols_collection.property;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

@Component
@AllArgsConstructor
public class DeleteProperty {

    private final PropertyRepository propertyRepository;

    /**
     * Method deletes property by Id.
     * @param id id property to delete
     */
    public void deleteById(int id) {
        propertyRepository.deleteById(id);
    }
}
