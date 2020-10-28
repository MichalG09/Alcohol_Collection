package pl.mgrzech.alcohols_collection.sortType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class FindSortType {

   private final SortTypeRepository sortTypeRepository;

    /**
     * Method returns sort type by Id.
     * @param id sort type id to find
     * @return found sort type
     */
    public SortType findById(int id) {
        return sortTypeRepository.findById(id).orElseGet(SortType::new);
    }

    /**
     * Method returns all sort type sorted in list by value
     * @return all sort type in list
     */
    public List<SortType> allTypesSort() {
        return StreamSupport.stream(sortTypeRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(SortType::getValue))
                .collect(Collectors.toList());
    }
}
