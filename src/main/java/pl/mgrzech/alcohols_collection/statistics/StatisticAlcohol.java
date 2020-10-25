package pl.mgrzech.alcohols_collection.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;

@Component
@AllArgsConstructor
public class StatisticAlcohol {

    private final AlcoholRepository alcoholRepository;

    public long getStatisticForNumbersAlcoholsInCollection(){
        return alcoholRepository.count();
    }
}
