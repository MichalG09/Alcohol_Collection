package pl.mgrzech.alcohols_collection.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

@Component
@RequiredArgsConstructor
public class DeletePictureFromGallery {

    private final PictureRepository pictureRepository;

    @Value("${message.correct.gallery.picture.delete}")
    private String messageCorrectDeletePicture;

    @Value("${message.fail.gallery.picture.delete}")
    private String messageFailDeletePicture;

    /**
     * Method deletes picture by Id
     * @param id picture id to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteById(int id, RedirectAttributes redirectAttributes) {
        try {
            pictureRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeletePicture);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageError", messageFailDeletePicture);
            e.printStackTrace();
        }
    }
}
