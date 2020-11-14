package pl.mgrzech.alcohols_collection.sortTypeTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.entities.SortType;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SortTypeValidationTest {

    private Validator validator;
    private SortType emptySortType;
    private SortType sortTypeWithValue;
    private SortType sortTypeWithTranslateValue;
    private SortType correctSortType;

    @BeforeAll
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        emptySortType = new SortType();
        sortTypeWithValue = new SortType(null, "test", null);
        sortTypeWithTranslateValue = new SortType(null, null, "testValue");
        correctSortType = new SortType(null, "test", "testValue");
    }

    @Test
    public void shouldReturnCorrectValidationSortType() {
        Set<ConstraintViolation<SortType>> violations = validator.validate(correctSortType);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptySortType() {
        Set<ConstraintViolation<SortType>> violations = validator.validate(emptySortType);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationSortTypeWithoutValue() {
        Set<ConstraintViolation<SortType>> violations = validator.validate(sortTypeWithValue);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationSortTypeWithoutValueTranslate() {
        Set<ConstraintViolation<SortType>> violations = validator.validate(sortTypeWithTranslateValue);
        assertFalse(violations.isEmpty());
    }
}
