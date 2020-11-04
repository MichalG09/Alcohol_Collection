package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DeletePictureForAlcohol {

    private final AlcoholRepository alcoholRepository;
    private final PictureRepository pictureRepository;

    /**
     * Method deletes pictures alcohol.
     * First method deletes picture from list pictures connected with edited alcohol.
     * Second method deletes picture from database.
     * @param alcoholId Id edited alcohol
     * @param pictureName name deleted picture
     */
    public void deletePictureForAlcohol(int alcoholId, String pictureName) {
        Optional<Picture> pictureToDelete = pictureRepository.findByName(pictureName);
        Optional<Alcohol> alcoholForDeletingPicture = alcoholRepository.findById(alcoholId);
        if(alcoholForDeletingPicture.isPresent() && pictureToDelete.isPresent()){
            List<Picture> listPicturesAlcoholToEdit = new ArrayList<>(alcoholForDeletingPicture.get().getPictures());
            alcoholForDeletingPicture.get().getPictures().forEach(picture -> {
                if(picture.getName().equals(pictureName)){
                    listPicturesAlcoholToEdit.remove(picture);
                }
            });
            alcoholForDeletingPicture.get().setPictures(listPicturesAlcoholToEdit);
            alcoholRepository.save(alcoholForDeletingPicture.get());
            pictureRepository.delete(pictureToDelete.get());
        }

    }
}
