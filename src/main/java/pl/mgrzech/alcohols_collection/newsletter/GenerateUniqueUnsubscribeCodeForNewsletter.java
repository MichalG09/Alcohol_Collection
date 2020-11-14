package pl.mgrzech.alcohols_collection.newsletter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class GenerateUniqueUnsubscribeCodeForNewsletter {

    private final NewsletterRepository newsletterRepository;

    @Value("${newsletter.random.scope}")
    private int scopeToRandomNumber;

    @Value("${newsletter.random.minValue}")
    private int minRandValue;

    /**
     * Method generate unique code for newsletter.
     * Method checks rand number exists in database
     * if yes rand new number if not returns this number.
     * @return unique number to unsubscribed
     */
    public int generate() {
        Random rand = new Random();
        int uniqueCode;
        do {
            uniqueCode = rand.nextInt(scopeToRandomNumber) + minRandValue;
        } while(newsletterRepository.findByCodeToVerifyDelete(uniqueCode).isPresent());
        return uniqueCode;
    }
}