package pl.mgrzech.alcohols_collection.property;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindProperty {

    private final PropertyRepository propertyRepository;

    @Value("${separator.property}")
    private String separatorForProperties;


    /**
     * Method returns all properties.
     * @return all properties
     */
    public Iterable<Property> findAll() {
        return propertyRepository.findAll();
    }

    /**
     * Method returns property by Id.
     * @param id id property to find
     * @return found property
     */
    public Property findById(int id) {
        return propertyRepository.findById(id)
                .orElseGet(Property::new);
    }

    /**
     * Method returns welcome text from property.
     * @return property with welcome text
     */
    public Property findWelcomeTextFromProperty() {
        return findByName("welcomeText");
    }

    /**
     * Method returns property by name property.
     * @param propertyName name property to find
     * @return found property
     */
    public Property findByName(String propertyName) {
        return propertyRepository.findByNameProperty(propertyName)
                .orElseGet(Property::new);
    }

    /**
     * Method convert value property found by id to list.
     * Method splits value property by dedicated separator (',').
     * @param propertyName name property to find
     * @return value property convert to list
     */
    public List<String> findByNameAndGetValuesInList(String propertyName) {
        return Arrays.stream(findByName(propertyName)
                .getValueProperty().split(separatorForProperties))
                .collect(Collectors.toList());
    }

    /**
     * Method return basic (default) number alcohols in one page.
     * @return number alcohols in one page.
     */
    public Integer findBasicNumberAlcoholsInOnePage() {
        return Integer.parseInt(findByNameAndGetValuesInList("startNumberAlcoholsInOnePage").get(0));
    }
}
