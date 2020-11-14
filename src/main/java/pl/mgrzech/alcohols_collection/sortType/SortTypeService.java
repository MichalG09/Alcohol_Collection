package pl.mgrzech.alcohols_collection.sortType;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.SortType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SortTypeService {

    private final AddSortType addSortType;
    private final FindSortType findSortType;
    private final DeleteSortType deleteSortType;

    @Value("${message.correct.sortType.add}")
    private String messageCorrectAddSortType;

    @Value("${message.fail.sortType.add}")
    private String messageFailAddSortType;

    @Value("${message.correct.sortType.delete}")
    private String messageCorrectDeleteSortType;

    @Value("${message.fail.sortType.delete}")
    private String messageFailDeleteSortType;

    /**
     * Method saves sort type.
     * @param sortType sort type to save
     * @param redirectAttributes redirectAttributes
     */
    public void saveSortType(SortType sortType, RedirectAttributes redirectAttributes) {
        try {
            addSortType.save(sortType);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddSortType);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailAddSortType);
        }
    }

    /**
     * Method returns all sort type.
     * @return list all sort types
     */
    public List<SortType> findAllSortType() {
        return findSortType.allSortedByValueTypesSort();
    }

    /**
     * Method returns sort type by Id.
     * @param id sort type id to find
     * @return sort type found by id
     */
    public SortType findSortTypeById(int id) {
        return findSortType.findById(id);
    }

    /**
     * Method deletes sort type by Id
     * @param id id sort type to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteSortTypeById(int id, RedirectAttributes redirectAttributes) {
        try {
            deleteSortType.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeleteSortType);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteSortType);
            e.printStackTrace();
        }
    }
}
