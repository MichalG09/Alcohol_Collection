package pl.mgrzech.alcohols_collection.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.picture.FindPicture;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final FindPicture findPicture;
    private final AddNewPictureToGallery addNewPictureToGallery;
    private final DeletePictureFromGallery deletePictureFromGallery;

    /**
     * Method returns all pictures for gallery.
     * Picture to gallery has specially parameter (isGallery set true).
     * @return pictures to gallery
     */
    public void findALlPicturesToGallery(Model model) {
        model.addAttribute("picturesGallery", findPicture.findALlPicturesToGallery());
    }

    /**
     * Methods saves gallery picture in database.
     * @param filesValidated picture from form
     * @param redirectAttributes redirectAttributes
     */
    public void savePicturesToGallery(FilesValidated filesValidated, RedirectAttributes redirectAttributes) {
        addNewPictureToGallery.savePicturesToGallery(filesValidated, redirectAttributes);
    }

    /**
     * Method deletes picture by Id
     * @param id picture id to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deletePictureFromGallery(int id, RedirectAttributes redirectAttributes) {
        deletePictureFromGallery.deleteById(id, redirectAttributes);
    }
}
