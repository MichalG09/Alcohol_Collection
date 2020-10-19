package pl.mgrzech.alcohols_collection.sortType;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;

@Component
@RequiredArgsConstructor
public class DeleteSortType {

    private final SortTypeRepository sortTypeRepository;

    @Value("${message.correct.sortType.delete}")
    private String messageCorrectDeleteSortType;

    @Value("${message.fail.sortType.delete}")
    private String messageFailDeleteSortType;

    /**
     * Method deletes sort type by Id
     * @param id id sort type to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteById(int id, RedirectAttributes redirectAttributes) {
        try {
            sortTypeRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeleteSortType);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteSortType);
            e.printStackTrace();
        }
    }
}
