package pl.mgrzech.alcohols_collection.propertyTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.entities.Property;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PropertyValidationTest {

    private Validator validator;
    private Property emptyProperty;
    private Property propertyWithName;
    private Property propertyWithValue;
    private Property correctProperty;

    @BeforeAll
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        emptyProperty = new Property();
        propertyWithName = new Property(null, "test", null);
        propertyWithValue = new Property(null, null, "testValue");
        correctProperty = new Property(null, "test", "testValue");
    }

    @Test
    public void shouldReturnCorrectValidationProperty() {
        Set<ConstraintViolation<Property>> violations = validator.validate(correctProperty);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptyProperty() {
        Set<ConstraintViolation<Property>> violations = validator.validate(emptyProperty);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationPropertyWithoutValue() {
        Set<ConstraintViolation<Property>> violations = validator.validate(propertyWithName);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationPropertyWithoutName() {
        Set<ConstraintViolation<Property>> violations = validator.validate(propertyWithValue);
        assertFalse(violations.isEmpty());
    }
}
