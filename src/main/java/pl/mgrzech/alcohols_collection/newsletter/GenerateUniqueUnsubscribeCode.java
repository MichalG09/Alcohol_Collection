package pl.mgrzech.alcohols_collection.newsletter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.NewsletterRepository;

import java.util.Random;

@Component
@AllArgsConstructor
public class GenerateUniqueUnsubscribeCode {

    private final NewsletterRepository newsletterRepository;

    public int generate() {
        Random rand = new Random();
        int uniqueCode;
        do {
            uniqueCode = rand.nextInt(1000000000);
        } while(isUniqueUnsubscribeCode(uniqueCode));
        return uniqueCode;
    }

    private boolean isUniqueUnsubscribeCode(int uniqueCode) {
        return newsletterRepository.findByCodeToVerifyDelete(uniqueCode).isPresent();
    }
}
