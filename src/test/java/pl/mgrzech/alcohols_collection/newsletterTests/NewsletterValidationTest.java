package pl.mgrzech.alcohols_collection.newsletterTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.entities.Newsletter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewsletterValidationTest {

    private Validator validator;
    private Newsletter emptyNewsletter;
    private Newsletter newsletterWithName;
    private Newsletter newsletterWithEmail;
    private Newsletter newsletterWithIncorrectEmail;
    private Newsletter correctNewsletter;

    @BeforeAll
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        emptyNewsletter = new Newsletter();
        newsletterWithName = new Newsletter(null, "test", null,111);
        newsletterWithEmail = new Newsletter(null, null, "testMail@test.com",111);
        newsletterWithIncorrectEmail = new Newsletter(null, "test", "testMail",111);
        correctNewsletter = new Newsletter(null, "test", "testMail@test.com",111);
    }

    @Test
    public void shouldReturnCorrectValidationNewsletter() {
        Set<ConstraintViolation<Newsletter>> violations = validator.validate(correctNewsletter);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptyNewsletter() {
        Set<ConstraintViolation<Newsletter>> violations = validator.validate(emptyNewsletter);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationNewsletterWithoutName() {
        Set<ConstraintViolation<Newsletter>> violations = validator.validate(newsletterWithEmail);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationNewsletterWithoutEmail() {
        Set<ConstraintViolation<Newsletter>> violations = validator.validate(newsletterWithName);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationNewsletterWithIncorrectEmail() {
        Set<ConstraintViolation<Newsletter>> violations = validator.validate(newsletterWithIncorrectEmail);
        assertFalse(violations.isEmpty());
    }
}
