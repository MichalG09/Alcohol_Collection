package pl.mgrzech.alcohols_collection.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;

@Component
@AllArgsConstructor
public class FindStatisticsAllAlcoholInOnePlaceInStorage {

    private final AlcoholRepository alcoholRepository;

    public Integer find(String placeInStorage){
        return alcoholRepository.countByPlaceInStorage(placeInStorage);
    }
}
