package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class FindPicture {

    private final PictureRepository pictureRepository;

    /**
     * Method returns all pictures for gallery.
     * Picture to gallery has specially parameter (isGallery set true).
     * @return pictures to gallery
     */
    public List<Picture> findALlPicturesToGallery() {
        return pictureRepository.findByGallery(true);
    }

    /**
     * Method returns picture by id.
     * @param id picture id to find
     * @return found picture
     */
    public Picture findById(int id){
        return pictureRepository.findById(id).orElseGet(Picture::new);
    }
}
