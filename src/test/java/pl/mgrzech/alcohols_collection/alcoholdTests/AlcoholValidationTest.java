package pl.mgrzech.alcohols_collection.alcoholdTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.entities.Picture;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlcoholValidationTest {

    private Validator validator;
    private Alcohol emptyAlcohol;
    private Alcohol alcoholWithoutName;
    private Alcohol alcoholWithoutType;
    private Alcohol alcoholWithIncorrectCapacityMin;
    private Alcohol alcoholWithIncorrectCapacityMax;
    private Alcohol alcoholWithIncorrectAmountOfAlcoholMin;
    private Alcohol alcoholWithIncorrectAmountOfAlcoholMax;
    private Alcohol alcoholWithoutManufacturer;
    private Alcohol alcoholWithoutPlaceInStorage;
    private Alcohol alcoholWithoutPictures;
    private Alcohol correctAlcohol;

    @BeforeAll
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Date nowDate = new Date();
        Picture picture = new Picture();
        List<Picture> listPictures = new ArrayList<>(Collections.singletonList(picture));

        emptyAlcohol = new Alcohol();
        alcoholWithoutName = new Alcohol(1, null, "type", 50, 40, nowDate, nowDate, "comment", new Manufacturer(), "", "A1", listPictures);
        alcoholWithoutType = new Alcohol(2, "name", null, 50, 40, nowDate, nowDate, "comment", new Manufacturer(), "", "A1", listPictures);
        alcoholWithIncorrectCapacityMin = new Alcohol(3, "name", "type", 0, 40, nowDate, nowDate, "comment", new Manufacturer(), "A1", "", listPictures);
        alcoholWithIncorrectCapacityMax = new Alcohol(4, "name", "type", 999, 40, nowDate, nowDate, "comment", new Manufacturer(), "A1", "", listPictures);
        alcoholWithIncorrectAmountOfAlcoholMin = new Alcohol(5, "name", "type", 110, 40, nowDate, nowDate, "comment", new Manufacturer(), "A1", "", listPictures);
        alcoholWithIncorrectAmountOfAlcoholMax = new Alcohol(6, "name", "type", 0, 40, nowDate, nowDate, "comment", new Manufacturer(), "A1", "", listPictures);
        alcoholWithoutManufacturer = new Alcohol(7, "name", "type", 50, 40, nowDate, nowDate, "comment", null, "", "A1", listPictures);
        alcoholWithoutPlaceInStorage = new Alcohol(8, "name", "type", 50, 40, nowDate, nowDate, "comment", new Manufacturer(), "", null, listPictures);
        alcoholWithoutPictures = new Alcohol(9, "name", "type", 50, 40, nowDate, nowDate, "comment", new Manufacturer(), "", "A1", null);
        correctAlcohol = new Alcohol(10, "name", "type", 50, 40, nowDate, nowDate, "comment", new Manufacturer(), "", "A1", listPictures);
    }

    @Test
    public void shouldReturnCorrectValidationAlcohol() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(correctAlcohol);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptyAlcohol() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(emptyAlcohol);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithoutName() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithoutName);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithoutType() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithoutType);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithIncorrectCapacityMin() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithIncorrectCapacityMin);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithIncorrectCapacityMax() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithIncorrectCapacityMax);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithIncorrectAmountOfAlcoholMin() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithIncorrectAmountOfAlcoholMin);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithIncorrectAmountOfAlcoholMax() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithIncorrectAmountOfAlcoholMax);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithoutManufacturer() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithoutManufacturer);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithoutPlaceInStorage() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithoutPlaceInStorage);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationAlcoholWithoutPictures() {
        Set<ConstraintViolation<Alcohol>> violations = validator.validate(alcoholWithoutPictures);
        assertFalse(violations.isEmpty());
    }
}
