package pl.mgrzech.alcohols_collection.picture;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.PicturesRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChangeMainPictureForAlcohol {

    private final PicturesRepository picturesRepository;

    @Value("${message.correct.picture.changeMain}")
    private String messageCorrectChangeMainPicture;

    @Value("${message.fail.picture.changeMain}")
    private String messageFailChangeMainPicture;

    /**
     * Method changes a main picture.
     * Old main picture after change is additional picture for alcohol.
     * @param alcoholId alcohol id for which picture is change
     * @param oldPictureId id old picture
     * @param redirectAttributes redirectAttributes
     */
    public void change(int alcoholId, int oldPictureId, RedirectAttributes redirectAttributes) {
        try{
            Optional<Picture> oldMainPicture = picturesRepository.findMainPictureForAlcohol(alcoholId);
            Optional<Picture> newMainPicture = picturesRepository.findById(oldPictureId);
            if(oldMainPicture.isPresent() && newMainPicture.isPresent()){
                oldMainPicture.get().setMainPicture(false);
                picturesRepository.save(oldMainPicture.get());
                newMainPicture.get().setMainPicture(true);
                picturesRepository.save(newMainPicture.get());
            }
            redirectAttributes.addFlashAttribute("message", messageCorrectChangeMainPicture);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailChangeMainPicture);
        }
    }
}
