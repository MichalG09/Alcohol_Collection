package pl.mgrzech.alcohols_collection.sortType;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;

@Component
@RequiredArgsConstructor
public class AddSortType {

    private final SortTypeRepository sortTypeRepository;

    @Value("${message.correct.sortType.add}")
    private String messageCorrectAddSortType;

    @Value("${message.fail.sortType.add}")
    private String messageFailAddSortType;

    /**
     * Method saves sort type.
     * @param sortType sort type to save
     * @param redirectAttributes redirectAttributes
     */
    public void save(SortType sortType, RedirectAttributes redirectAttributes) {
        try{
            sortTypeRepository.save(sortType);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddSortType);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailAddSortType);
        }
    }
}
