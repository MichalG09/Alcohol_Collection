package pl.mgrzech.alcohols_collection.picture;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.PicturesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeletePictureForAlcohol {

    private final AlcoholRepository alcoholRepository;
    private final PicturesRepository picturesRepository;

    @Value("${message.correct.picture.delete}")
    private String messageCorrectDeletedPicture;

    @Value("${message.fail.picture.delete}")
    private String messageFailDeletedPicture;

    /**
     * Method deletes pictures alcohol.
     * First method deletes picture from list pictures connected with edited alcohol.
     * Second method deletes picture from database.
     * @param alcoholId Id edited alcohol
     * @param pictureName name deleted picture
     * @param redirectAttributes redirectAttributes
     */
    public void deletePictureForAlcohol(int alcoholId, String pictureName, RedirectAttributes redirectAttributes) {
        try{
            Optional<Picture> pictureToDelete = picturesRepository.findByName(pictureName);
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
                picturesRepository.delete(pictureToDelete.get());
            }
            redirectAttributes.addFlashAttribute("message", messageCorrectDeletedPicture);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeletedPicture);
            e.printStackTrace();
        }
    }
}
