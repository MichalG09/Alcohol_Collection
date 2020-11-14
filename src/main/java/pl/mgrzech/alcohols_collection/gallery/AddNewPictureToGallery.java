package pl.mgrzech.alcohols_collection.gallery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.picture.GetUniqueNameForPicture;
import pl.mgrzech.alcohols_collection.picture.SavePicture;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import java.io.IOException;

@Component
@AllArgsConstructor
public class AddNewPictureToGallery {

    private final SavePicture savePicture;
    private final GetUniqueNameForPicture getUniqueNameForPicture;

    /**
     * Methods saves gallery picture in database.
     * Method gets each picture added in form and saves picture as byte code
     * and gives picture random and unique name.
     * Picture to gallery have to set parameter isGallery to true.
     * This parameter lets recognize picture to gallery from other
     * pictures in database (pictures for alcohols).
     * @param filesValidated picture from form
     */
    public void savePicturesToGallery(FilesValidated filesValidated) {
        filesValidated.getMultipartFiles().forEach(
                validatedPicture -> {
                    try {
                        savePicture.save(
                                validatedPicture.getBytes(),
                                getUniqueNameForPicture.get("gallery"),
                                false,
                                true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
