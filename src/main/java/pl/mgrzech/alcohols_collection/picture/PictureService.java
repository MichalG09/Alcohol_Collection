package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@AllArgsConstructor
public class PictureService {

    private final DeletePictureForAlcohol deletePictureForAlcohol;
    private final ChangeMainPictureForAlcohol changeMainPictureForAlcohol;

    /**
     * Method deletes pictures alcohol.
     * @param alcoholId Id edited alcohol
     * @param pictureName name deleted picture
     * @param redirectAttributes redirectAttributes
     */
    public void deletePictureForAlcohol(int alcoholId, String pictureName, RedirectAttributes redirectAttributes) {
        deletePictureForAlcohol.deletePictureForAlcohol(alcoholId, pictureName, redirectAttributes);
    }
    /**
     * Method changes a main picture.
     * @param alcoholId alcohol id for which picture is change
     * @param oldPictureId id old picture
     * @param redirectAttributes redirectAttributes
     */
    public void changeMainPictureForAlcohol(int alcoholId, int oldPictureId, RedirectAttributes redirectAttributes) {
        changeMainPictureForAlcohol.change(alcoholId, oldPictureId, redirectAttributes);
    }
}
