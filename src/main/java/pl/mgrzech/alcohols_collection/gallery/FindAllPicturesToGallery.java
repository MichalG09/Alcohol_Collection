package pl.mgrzech.alcohols_collection.gallery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.PicturesRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class FindAllPicturesToGallery {

    private final PicturesRepository picturesRepository;

    /**
     * Method returns all pictures for gallery.
     * Picture to gallery has specially parameter (isGallery set true).
     * @return pictures to gallery
     */
    public List<Picture> findALlPicturesToGallery() {
        return picturesRepository.findByGallery(true);
    }
}
