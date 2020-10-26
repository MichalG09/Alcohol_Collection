package pl.mgrzech.alcohols_collection.sortType;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.SortType;

import java.util.List;

@Service
@AllArgsConstructor
public class SortTypeService {

    private final AddSortType addSortType;
    private final FindSortType findSortType;
    private final DeleteSortType deleteSortType;

    /**
     * Method saves sort type.
     * @param sortType sort type to save
     * @param redirectAttributes redirectAttributes
     */
    public void saveSortType(SortType sortType, RedirectAttributes redirectAttributes) {
        addSortType.save(sortType, redirectAttributes);
    }

    /**
     * Method returns all sort type.
     */
    public void findAllSortType(Model model) {
        model.addAttribute("sortTypes", findSortType.allTypesSort());
    }

    /**
     * Method returns sort type by Id.
     * @param id sort type id to find
     */
    public void findSortTypeById(Model model, int id) {
        model.addAttribute("sortType", findSortType.findById(id));
    }

    /**
     * Method deletes sort type by Id
     * @param id id sort type to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteSortTypeById(int id, RedirectAttributes redirectAttributes) {
        deleteSortType.deleteById(id, redirectAttributes);
    }
}
