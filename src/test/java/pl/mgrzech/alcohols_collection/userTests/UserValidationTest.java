package pl.mgrzech.alcohols_collection.userTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.entities.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserValidationTest {

    private Validator validator;
    private final User emptyUser = new User();
    private User userWithoutName;
    private User userWithoutPassword;
    private User correctUser;

    @BeforeAll
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        userWithoutName = new User(null, null, "password", true, "Admin");
        userWithoutPassword = new User(null, "name", null,  true, "Admin");
        correctUser = new User(null, "name", "password",  true, "Admin");
    }

    @Test
    public void shouldReturnCorrectValidationUser(){
        Set<ConstraintViolation<User>> violations = validator.validate(correctUser);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptyUser() {
        Set<ConstraintViolation<User>> violations = validator.validate(emptyUser);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationUserWithoutName() {
        Set<ConstraintViolation<User>> violations = validator.validate(userWithoutName);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationUserWithoutPassword() {
        Set<ConstraintViolation<User>> violations = validator.validate(userWithoutPassword);
        assertFalse(violations.isEmpty());
    }
}
