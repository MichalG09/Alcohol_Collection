package pl.mgrzech.alcohols_collection.newsletterTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class NewsletterRepositoryTest {

    @Autowired
    private NewsletterRepository newsletterRepository;

    private final Newsletter newsletterToTest = new Newsletter(null, "testName", "email@test.com", 1111);

    @Test
    public void shouldCorrectSaveNewsletter(){
        newsletterRepository.save(newsletterToTest);
        assertNotNull(newsletterToTest.getId());
    }

    @Test
    public void shouldReturnNewsletterByUniqueName(){
        newsletterRepository.save(newsletterToTest);
        Optional<Newsletter> newsletter = newsletterRepository.findByCodeToVerifyDelete(newsletterToTest.getCodeToVerifyDelete());
        assertTrue(newsletter.isPresent());
        assertEquals(newsletterToTest.getEmail(), newsletter.get().getEmail());
    }

    @Test
    public void shouldReturnNullNewsletterByName(){
        Optional<Newsletter> newsletter = newsletterRepository.findByCodeToVerifyDelete(newsletterToTest.getCodeToVerifyDelete());
        assertFalse(newsletter.isPresent());
    }
}
