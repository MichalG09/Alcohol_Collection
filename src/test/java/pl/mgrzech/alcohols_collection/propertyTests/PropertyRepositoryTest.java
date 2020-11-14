package pl.mgrzech.alcohols_collection.propertyTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class PropertyRepositoryTest {

    @Autowired
    private PropertyRepository propertyRepository;

    private final Property propertyToTest = new Property(null, "testName", "testValue");

    @Test
    public void shouldCorrectSaveProperty(){
        propertyRepository.save(propertyToTest);
        assertNotNull(propertyToTest.getId());
    }

    @Test
    public void shouldReturnPropertyByName(){
        propertyRepository.save(propertyToTest);
        Optional<Property> property = propertyRepository.findByNameProperty(propertyToTest.getNameProperty());
        assertTrue(property.isPresent());
        assertEquals(propertyToTest.getValueProperty(), property.get().getValueProperty());
    }

    @Test
    public void shouldReturnNullPropertyByName(){
        Optional<Property> property = propertyRepository.findByNameProperty(propertyToTest.getNameProperty());
        assertFalse(property.isPresent());
    }
}
