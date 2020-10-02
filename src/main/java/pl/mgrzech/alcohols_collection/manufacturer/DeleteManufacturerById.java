package pl.mgrzech.alcohols_collection.manufacturer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;

@Component
@RequiredArgsConstructor
public class DeleteManufacturerById {

    private final ManufacturerRepository manufacturerRepository;

    @Value("${message.correct.manufacturer.delete}")
    private String messageCorrectDeletedManufacturer;

    @Value("${message.fail.manufacturer.delete}")
    private String messageFailDeletedManufacturer;

    /**
     * Method deletes manufacturer by Id.
     * @param id id manufacturer to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteManufacturerById(int id, RedirectAttributes redirectAttributes) {
        try{
            manufacturerRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeletedManufacturer);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeletedManufacturer);
        }
    }
}
