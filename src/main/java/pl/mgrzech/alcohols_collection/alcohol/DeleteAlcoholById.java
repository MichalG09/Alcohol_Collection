package pl.mgrzech.alcohols_collection.alcohol;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;

@Component
@RequiredArgsConstructor
public class DeleteAlcoholById {

    private final AlcoholRepository alcoholRepository;

    @Value("${message.correct.alcohol.delete}")
    private String messageCorrectDeleteAlcohol;

    @Value("${message.fail.alcohol.delete}")
    private String messageFailDeleteAlcohol;

    /**
     * Method deletes alcohol by id.
     * @param id id alcohol to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteById(int id, RedirectAttributes redirectAttributes) {
        try{
            if(alcoholRepository.findById(id).isPresent()){
                alcoholRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("message", messageCorrectDeleteAlcohol);
            }
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteAlcohol);
        }
    }
}
