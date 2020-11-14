package pl.mgrzech.alcohols_collection.sortType;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
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
    public List<SortType> allSortedByValueTypesSort() {
        return sortTypeRepository.findAll().stream()
                .sorted(Comparator.comparing(SortType::getValue))
                .collect(Collectors.toList());
    }

    /**
     * Methodd returns sort type by name.
     * @param nameSortType found sort type
     * @return optional of found sort type
     */
    public Optional<SortType> findSortTypeByName(String nameSortType){
        return sortTypeRepository.findByValue(nameSortType);
    }
}
