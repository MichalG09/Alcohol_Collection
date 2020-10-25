package pl.mgrzech.alcohols_collection.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;

@Component
@AllArgsConstructor
public class StatisticsManufacturer {

    private final ManufacturerRepository manufacturerRepository;

    public long getStatisticForNumbersManufacturersInCollection(){
        return manufacturerRepository.count();
    }
}
