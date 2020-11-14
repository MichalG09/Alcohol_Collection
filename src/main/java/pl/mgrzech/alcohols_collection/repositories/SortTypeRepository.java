package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgrzech.alcohols_collection.entities.SortType;

import java.util.Optional;

public interface SortTypeRepository extends JpaRepository<SortType, Integer> {
    Optional<SortType> findByValue(String value);
}
