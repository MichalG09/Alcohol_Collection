package pl.mgrzech.alcohols_collection.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.picture.FindPicture;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final FindPicture findPicture;
    private final AddNewPictureToGallery addNewPictureToGallery;
    private final DeletePictureFromGallery deletePictureFromGallery;

    @Value("${message.correct.gallery.picture.add}")
    private String messageCorrectAddPicture;

    @Value("${message.fail.gallery.picture.add}")
    private String messageFailAddPicture;

    @Value("${message.correct.gallery.picture.delete}")
    private String messageCorrectDeletePicture;

    @Value("${message.fail.gallery.picture.delete}")
    private String messageFailDeletePicture;

    /**
     * Method returns all pictures for gallery.
     * Picture to gallery has specially parameter (isGallery set true).
     * @return pictures to gallery
     */
    public List<Picture> findALlPicturesToGallery() {
        return findPicture.findALlPicturesToGallery();
    }

    /**
     * Methods saves gallery picture in database.
     * @param filesValidated picture from form
     * @param redirectAttributes redirectAttributes
     */
    public void savePicturesToGallery(FilesValidated filesValidated, RedirectAttributes redirectAttributes) {
        try{
            addNewPictureToGallery.savePicturesToGallery(filesValidated);
            redirectAttributes.addFlashAttribute("messageError", messageCorrectAddPicture);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("messageError", messageFailAddPicture);
        }
    }

    /**
     * Method deletes picture by Id
     * @param id picture id to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deletePictureFromGallery(int id, RedirectAttributes redirectAttributes) {
        try {
            deletePictureFromGallery.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeletePicture);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageError", messageFailDeletePicture);
            e.printStackTrace();
        }
    }
}
