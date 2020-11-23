package pl.mgrzech.alcohols_collection.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;

@Component
@AllArgsConstructor
public class StatisticsManufacturer {

    private final ManufacturerRepository manufacturerRepository;

    /**
     * Method returns numbers all company in collection.
     * Manufacturer with name company "nn" is not calculated, because is used when
     * manufacturer for alcohol is unknown.
     * @return Number manufacturers in collection
     */
    public long getNumberAllManufacturersInCollection(){
        return manufacturerRepository.countByNameCompanyNotLike("nn");
    }
}
