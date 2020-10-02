package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Integer> {

    Optional<Manufacturer> findByNameCompanyOrderByNameCompanyAsc(String nameCompany);
    List<Manufacturer> findByNameCompanyIsContainingAndTownIsContainingAndCountryIsContainingAndCommentsIsContainingOrderByNameCompanyAsc(
            String nameCompany, String town, String country, String comments);
}
