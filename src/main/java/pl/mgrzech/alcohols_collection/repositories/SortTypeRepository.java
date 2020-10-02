package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.mgrzech.alcohols_collection.entities.SortType;

import java.util.Optional;

public interface SortTypeRepository extends CrudRepository<SortType, Integer> {
    Optional<SortType> findByValue(String value);
}
