package pl.mgrzech.alcohols_collection.manufacturerTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManufacturerValidationTest {

    private Validator validator;
    private Manufacturer emptyManufacturer;
    private Manufacturer manufacturerWithoutName;
    private Manufacturer correctManufacturer;

    @BeforeAll
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Date nowDate = new Date();

        emptyManufacturer = new Manufacturer();
        manufacturerWithoutName = new Manufacturer(null, null, "", "", null, "", nowDate, nowDate);
        correctManufacturer = new Manufacturer(null, "test manufacturer", "", "", null, "", nowDate, nowDate);
    }

    @Test
    public void shouldReturnCorrectValidationManufacturer() {
        Set<ConstraintViolation<Manufacturer>> violations = validator.validate(correctManufacturer);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptyManufacturer() {
        Set<ConstraintViolation<Manufacturer>> violations = validator.validate(emptyManufacturer);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationManufacturerWithoutName() {
        Set<ConstraintViolation<Manufacturer>> violations = validator.validate(manufacturerWithoutName);
        assertFalse(violations.isEmpty());
    }
}
