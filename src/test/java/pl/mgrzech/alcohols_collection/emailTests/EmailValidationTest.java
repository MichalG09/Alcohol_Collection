package pl.mgrzech.alcohols_collection.emailTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.mgrzech.alcohols_collection.email.model.EmailMessage;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmailValidationTest {

    private Validator validator;
    private EmailMessage emptyEmailMessage;
    private EmailMessage emailWithoutSubject;
    private EmailMessage emailWithoutAddressEmail;
    private EmailMessage emailWithoutCorrectEmail;
    private EmailMessage emailWithoutText;
    private EmailMessage correctEmailMessage;

    @BeforeAll
    public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        emptyEmailMessage = new EmailMessage();
        emailWithoutSubject = new EmailMessage("nadawca", null, "test@test.com", "text for test",false);
        emailWithoutAddressEmail = new EmailMessage("nadawca", null, "testtest.com", "text for test",false);
        emailWithoutText = new EmailMessage("nadawca", "subject", "test@test.com", null,false);
        emailWithoutCorrectEmail = new EmailMessage("nadawca", null, "test@test.com", "text for test",false);
        correctEmailMessage =  new EmailMessage("nadawca", "subject", "test@test.com", "text for test",false);
    }

    @Test
    public void shouldReturnCorrectValidationEmailMessage() {
        Set<ConstraintViolation<EmailMessage>> violations = validator.validate(correctEmailMessage);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmptyEmailMessage() {
        Set<ConstraintViolation<EmailMessage>> violations = validator.validate(emptyEmailMessage);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmailMessageWithoutAddressEmail() {
        Set<ConstraintViolation<EmailMessage>> violations = validator.validate(emailWithoutAddressEmail);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmailMessageWithIncorrectAddressEmail() {
        Set<ConstraintViolation<EmailMessage>> violations = validator.validate(emailWithoutCorrectEmail);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmailMessageWithoutSubject() {
        Set<ConstraintViolation<EmailMessage>> violations = validator.validate(emailWithoutSubject);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldReturnIncorrectValidationEmailMessageWithoutText() {
        Set<ConstraintViolation<EmailMessage>> violations = validator.validate(emailWithoutText);
        assertFalse(violations.isEmpty());
    }
}
