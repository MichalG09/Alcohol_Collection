package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.PicturesRepository;

import java.util.Random;

@Component
@AllArgsConstructor
public class GetUniqueName {

    private final PicturesRepository picturesRepository;
    private final int scopeToRandomNumber = 999998;

    /**
     * Method returns unique picture name.
     * For picture for alcohol name contains name alcohol and unique and random number.
     * For picture to gallery name contains "gallery" and unique and random number.
     * @param prefixName prefix name, alcohol name or "gallery".
     * @return unique name for picture
     */
    public String get(String prefixName) {
        Random rand = new Random();
        String uniqueNumberToName;
        do{
            uniqueNumberToName = String.valueOf(rand.nextInt(scopeToRandomNumber) + 1000001);
        } while (picturesRepository.findByNameIsContaining(uniqueNumberToName).isPresent());
        return prefixName + "_" + uniqueNumberToName;
    }
}
