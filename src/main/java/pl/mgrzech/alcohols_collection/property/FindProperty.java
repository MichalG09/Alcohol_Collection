package pl.mgrzech.alcohols_collection.property;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

import java.util.ArrayList;
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
        return propertyRepository.findById(id).orElseGet(Property::new);
    }

    /**
     * Method returns welcome text from property.
     * @return property with welcome text
     */
    public Property findWelcomeTextFromProperty() {
        return propertyRepository.findByNameProperties("welcomeText").orElseGet(Property::new);
    }

    /**
     * Method returns property by name property.
     * @param propertyName name property to find
     * @return found property
     */
    public List<String> findByName(String propertyName) {
        return propertyRepository
                .findByNameProperties(propertyName)
                    .map(property -> Arrays.stream(property
                            .getValueProperties()
                            .split(separatorForProperties))
                    .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }

    /**
     * Method return basic (default) number alcohols in one page.
     * @return number alcohols in one page.
     */
    public Integer findBasicNumberAlcoholsInOnePage() {
        return Integer.parseInt(findByName("startNumberAlcoholsInOnePage").get(0));
    }
}
