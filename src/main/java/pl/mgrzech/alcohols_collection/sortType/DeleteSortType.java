package pl.mgrzech.alcohols_collection.sortType;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;

@Component
@AllArgsConstructor
public class DeleteSortType {

    private final SortTypeRepository sortTypeRepository;

    /**
     * Method deletes sort type by Id
     * @param id id sort type to delete
     */
    public void deleteById(int id) {
        sortTypeRepository.deleteById(id);
    }
}
