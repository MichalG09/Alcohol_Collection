package pl.mgrzech.alcohols_collection.manufacturer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class FindManufacturer {

    private final ManufacturerRepository manufacturerRepository;

    /**
     * Method finds manufacturerWithParamToSearch by parameters.
     * @param manufacturerWithParamToSearch manufacturerWithParamToSearch with parameters to search
     * @return found list manufacturerWithParamToSearch
     */
    public List<Manufacturer> findManufacturerByParameters(Manufacturer manufacturerWithParamToSearch) {
               return manufacturerRepository
                       .findByNameCompanyIsContainingAndTownIsContainingAndCountryIsContainingAndCommentsIsContainingOrderByNameCompanyAsc(
                               manufacturerWithParamToSearch.getNameCompany(),
                               manufacturerWithParamToSearch.getTown(),
                               manufacturerWithParamToSearch.getCountry(),
                               manufacturerWithParamToSearch.getComments()
                       );
    }

    /**
     * Method finds manufacturer by Id.
     * @param id id manufacturer to search
     * @return found manufacturer
     */
    public Manufacturer findById(int id) {
        return manufacturerRepository.findById(id).orElseGet(Manufacturer::new);
    }

    /**
     * Method returns all manufacturers.
     * @return all manufacturers
     */
    public Iterable<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }
}
