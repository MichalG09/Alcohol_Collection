package pl.mgrzech.alcohols_collection.gallery;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final FindAllPicturesToGallery findAllPicturesToGallery;
    private final AddNewPictureToGallery addNewPictureToGallery;
    private final DeletePictureFromGallery deletePictureFromGallery;

    /**
     * Method returns all pictures for gallery.
     * Picture to gallery has specially parameter (isGallery set true).
     * @return pictures to gallery
     */
    public List<Picture> findALlPicturesToGallery() {
        return findAllPicturesToGallery.findALlPicturesToGallery();
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
