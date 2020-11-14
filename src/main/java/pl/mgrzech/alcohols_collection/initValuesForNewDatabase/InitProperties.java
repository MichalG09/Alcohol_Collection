package pl.mgrzech.alcohols_collection.initValuesForNewDatabase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

@Component
@AllArgsConstructor
public class InitProperties {

    private final PropertyRepository propertyRepository;

    /**
     * Method initializes all necessary properties for start project.
     */
    public void initProperties() {
        initProperty("placeInStorage", "A1,A2,A3,A4,A5,A6,B1,B2,C1,C2");
        initProperty("basicTypesAlcohol", "wódka,whisky,wiskey,rum,tequila,");
        initProperty("welcomeText", "<p>Witaj na Stronie kolekcjaAlkoholi.pl</p>");
        initProperty("valuesNumberAlcoholInOnePage", "5, 10, 15, 20");
        initProperty("startNumberAlcoholsInOnePage", "5");
    }

    /**
     * Method initializes one property.
     * If sort property exists, method don`t create it
     * @param nameProperty name property
     * @param valueProperty value property
     */
    private void initProperty(String nameProperty, String valueProperty){
        if(!propertyRepository.findByNameProperty(nameProperty).isPresent()) {
            Property property = new Property();
            property.setNameProperty(nameProperty);
            property.setValueProperty(valueProperty);
            propertyRepository.save(property);
        }
    }
}
