package pl.mgrzech.alcohols_collection.pictureTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.entities.Picture;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PictureValidationTest {

    private Validator validator;
    private Picture emptyPicture;
    private Picture pictureWithoutName;
    private Picture pictureWithoutFile;
    private Picture pictureWithoutDate;
    private Picture correctPicture;

    @BeforeAll
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Date now = new Date();
        byte[] file = new byte[10];

        emptyPicture = new Picture();
        pictureWithoutName = new Picture(null, null, file, now, false, false);
        pictureWithoutFile = new Picture(null, "testPicture", null, now, false, false);
        pictureWithoutDate = new Picture(null, "testPicture", file, null, false, false);
        correctPicture = new Picture(null, "testPicture", file, now, false, false);
    }

    @Test
    public void shouldReturnCorrectValidationPicture() {
        Set<ConstraintViolation<Picture>> violations = validator.validate(correctPicture);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptyPicture() {
        Set<ConstraintViolation<Picture>> violations = validator.validate(emptyPicture);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationPictureWithoutFile() {
        Set<ConstraintViolation<Picture>> violations = validator.validate(pictureWithoutFile);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationPictureWithoutName() {
        Set<ConstraintViolation<Picture>> violations = validator.validate(pictureWithoutName);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationPictureWithoutDate() {
        Set<ConstraintViolation<Picture>> violations = validator.validate(pictureWithoutDate);
        assertFalse(violations.isEmpty());
    }
}
