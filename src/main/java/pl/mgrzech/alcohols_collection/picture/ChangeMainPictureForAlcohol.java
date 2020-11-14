package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ChangeMainPictureForAlcohol {

    private final PictureRepository pictureRepository;

    /**
     * Method changes a main picture.
     * Old main picture after change is additional picture for alcohol.
     * @param alcoholId alcohol id for which picture is change
     * @param oldPictureId id old picture
     */
    public void change(int alcoholId, int oldPictureId) {
        Optional<Picture> oldMainPicture = pictureRepository.findMainPictureForAlcohol(alcoholId);
        Optional<Picture> newMainPicture = pictureRepository.findById(oldPictureId);
        if(oldMainPicture.isPresent() && newMainPicture.isPresent()){
            oldMainPicture.get().setMainPicture(false);
            pictureRepository.save(oldMainPicture.get());
            newMainPicture.get().setMainPicture(true);
            pictureRepository.save(newMainPicture.get());
        }
    }
}
