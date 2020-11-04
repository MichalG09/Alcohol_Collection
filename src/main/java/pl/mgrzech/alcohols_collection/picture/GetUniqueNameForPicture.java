package pl.mgrzech.alcohols_collection.picture;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class GetUniqueNameForPicture {

    private final PictureRepository pictureRepository;

    @Value("${picture.random.scope}")
    private int scopeToRandomNumber;

    @Value("${picture.random.minValue}")
    private int minRandValue;

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
            uniqueNumberToName = String.valueOf(rand.nextInt(scopeToRandomNumber) + minRandValue);
        } while (!pictureRepository.findByNameIsContaining(uniqueNumberToName).isEmpty());
        return prefixName + "_" + uniqueNumberToName;
    }
}
