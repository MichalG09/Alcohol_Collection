package pl.mgrzech.alcohols_collection.propertyTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.property.FindProperty;
import pl.mgrzech.alcohols_collection.repositories.PropertyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FindPropertyTest {

    @InjectMocks
    private FindProperty findProperty;

    @Mock
    private PropertyRepository propertyRepository;

    private Optional<Property> propertyToTest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPropertyByName(){
        propertyToTest = Optional.of(new Property(1, "test", "test1"));

        Mockito.when(propertyRepository.findByNameProperty("test")).thenReturn(propertyToTest);

        Assert.assertEquals("test1", findProperty.findByName("test").getValueProperty());
    }

    @Test
    public void shouldReturnListValueFoundAndSplattedFromProperty(){
        propertyToTest = Optional.of( new Property(2, "test", "value1,value2,value3"));
        List<String> expectedResult = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));
        ReflectionTestUtils.setField(findProperty, "separatorForProperties", ",");

        Mockito.when(propertyRepository.findByNameProperty("test")).thenReturn(propertyToTest);

        assertEquals(expectedResult, findProperty.findByNameAndGetValuesInList("test"));
    }

    @Test
    public void shouldReturnWelcomeTextFromPropertyByName(){
        propertyToTest = Optional.of( new Property(3, "welcomeText", "Welcome Text"));

        Mockito.when(propertyRepository.findByNameProperty("welcomeText")).thenReturn(propertyToTest);

        Assert.assertEquals("Welcome Text", findProperty.findWelcomeTextFromProperty().getValueProperty());
    }

    @Test
    public void shouldReturnDefaultNumberAlcoholsInOnePageFromPropertyByName(){
        propertyToTest = Optional.of( new Property(4, "startNumberAlcoholsInOnePage", "10,15,20,25"));
        ReflectionTestUtils.setField(findProperty, "separatorForProperties", ",");

        Mockito.when(propertyRepository.findByNameProperty("startNumberAlcoholsInOnePage")).thenReturn(propertyToTest);

        Assert.assertEquals(new Integer(10), findProperty.findBasicNumberAlcoholsInOnePage());
    }
}
