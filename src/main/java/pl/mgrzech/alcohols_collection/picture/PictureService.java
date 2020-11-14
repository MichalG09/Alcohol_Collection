package pl.mgrzech.alcohols_collection.picture;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Picture;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final DeletePictureForAlcohol deletePictureForAlcohol;
    private final ChangeMainPictureForAlcohol changeMainPictureForAlcohol;
    private final FindPicture findPicture;

    @Value("${message.correct.picture.delete}")
    private String messageCorrectDeletedPicture;

    @Value("${message.fail.picture.delete}")
    private String messageFailDeletedPicture;

    @Value("${message.correct.picture.changeMain}")
    private String messageCorrectChangeMainPicture;

    @Value("${message.fail.picture.changeMain}")
    private String messageFailChangeMainPicture;

    /**
     * Method deletes pictures alcohol.
     * @param alcoholId Id edited alcohol
     * @param pictureName name deleted picture
     * @param redirectAttributes redirectAttributes
     */
    public void deletePictureForAlcohol(int alcoholId, String pictureName, RedirectAttributes redirectAttributes) {
        try {
            deletePictureForAlcohol.deletePictureForAlcohol(alcoholId, pictureName);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeletedPicture);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeletedPicture);
            e.printStackTrace();
        }
    }
    /**
     * Method changes a main picture.
     * @param alcoholId alcohol id for which picture is change
     * @param oldPictureId id old picture
     * @param redirectAttributes redirectAttributes
     */
    public void changeMainPictureForAlcohol(int alcoholId, int oldPictureId, RedirectAttributes redirectAttributes) {
        try {
            changeMainPictureForAlcohol.change(alcoholId, oldPictureId);
            redirectAttributes.addFlashAttribute("message", messageCorrectChangeMainPicture);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailChangeMainPicture);
            e.printStackTrace();
        }
    }

    /**
     * Method returns picture by id.
     * @param idPicture picture id to find
     * @return found picture
     */
    public Picture findPicture(int idPicture, int idAlcohol) {
        return findPicture.findById(idPicture);
    }
}
