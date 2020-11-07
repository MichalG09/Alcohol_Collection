package pl.mgrzech.alcohols_collection.gallery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

@Component
@AllArgsConstructor
public class DeletePictureFromGallery {

    private final PictureRepository pictureRepository;

    /**
     * Method deletes picture by Id
     * @param id picture id to delete
     */
    public void deleteById(int id) {
        pictureRepository.deleteById(id);
    }
}
