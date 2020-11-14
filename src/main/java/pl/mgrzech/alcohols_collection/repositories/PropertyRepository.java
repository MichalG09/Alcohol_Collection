package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mgrzech.alcohols_collection.entities.Property;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Optional<Property> findByNameProperty(String name);
}
