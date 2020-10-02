package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.mgrzech.alcohols_collection.entities.Property;

import java.util.Optional;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
    Optional<Property> findByNameProperties(String name);

}
