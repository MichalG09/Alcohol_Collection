package pl.mgrzech.alcohols_collection.newsletterTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.newsletter.GenerateUniqueUnsubscribeCodeForNewsletter;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

import static org.junit.Assert.*;

public class NewsletterUniqueNameTest {

    @InjectMocks
    private GenerateUniqueUnsubscribeCodeForNewsletter generateUniqueUnsubscribeCodeForNewsletter;

    @Mock
    private NewsletterRepository newsletterRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnUniqueNumber(){
        Newsletter newsletter = new Newsletter(1, "test", "tests@test.com", 1);
        ReflectionTestUtils.setField(generateUniqueUnsubscribeCodeForNewsletter, "scopeToRandomNumber", 2);
        ReflectionTestUtils.setField(generateUniqueUnsubscribeCodeForNewsletter, "minRandValue", 1);

        Mockito.when(newsletterRepository.findByCodeToVerifyDelete(1)).thenReturn(java.util.Optional.of((newsletter)));
        
        assertEquals(2, generateUniqueUnsubscribeCodeForNewsletter.generate());
    }

}
