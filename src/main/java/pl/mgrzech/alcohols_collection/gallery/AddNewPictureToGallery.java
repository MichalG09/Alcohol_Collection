package pl.mgrzech.alcohols_collection.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.picture.GetUniqueName;
import pl.mgrzech.alcohols_collection.picture.SavePicture;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AddNewPictureToGallery {

    private final SavePicture savePicture;
    private final GetUniqueName getUniqueName;

    @Value("${message.correct.gallery.picture.add}")
    private String messageCorrectAddPicture;

    @Value("${message.fail.gallery.picture.add}")
    private String messageFailAddPicture;

    /**
     * Methods saves gallery picture in database.
     * Method gets each picture added in form and saves picture as byte code
     * and gives picture random and unique name.
     * Picture to gallery have to set parameter isGallery to true.
     * This parameter lets recognize picture to gallery from other
     * pictures in database (pictures for alcohols).
     * @param filesValidated picture from form
     * @param redirectAttributes redirectAttributes
     */
    public void savePicturesToGallery(FilesValidated filesValidated, RedirectAttributes redirectAttributes) {
        filesValidated.getMultipartFiles().forEach(
                pic -> {
                    try {
                        savePicture.save(
                                pic.getBytes(),
                                getUniqueName.get("gallery"),
                                false,
                                true);
                        redirectAttributes.addFlashAttribute("message", messageCorrectAddPicture);
                    } catch (IOException e) {
                        e.printStackTrace();
                        redirectAttributes.addFlashAttribute("messageError", messageCorrectAddPicture);
                    }
                });
    }
}
