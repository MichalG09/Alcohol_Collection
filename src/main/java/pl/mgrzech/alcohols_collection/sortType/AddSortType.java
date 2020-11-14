package pl.mgrzech.alcohols_collection.sortType;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;

@Component
@AllArgsConstructor
public class AddSortType {

    private final SortTypeRepository sortTypeRepository;

    /**
     * Method saves sort type.
     * @param sortType sort type to save
     */
    public void save(SortType sortType) {
            sortTypeRepository.save(sortType);
    }
}
