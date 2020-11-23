package pl.mgrzech.alcohols_collection.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@AllArgsConstructor
public class StatisticAlcohol {

    private final AlcoholRepository alcoholRepository;

    /**
     * Method returns number all alcohol in collection
     * @return number all alcohol in collection
     */
    public long getNumberAllAlcoholsInCollection(){
        return alcoholRepository.count();
    }

    /**
     * Method returns number all distinct alcohol types in collection
     * @return number all distinct alcohol types in collection
     */
    public long getNumberDistinctAlcoholType(){
        return alcoholRepository.countDistinctType();
    }

    /**
     * Method returns sum capacity all alcohols in collection.
     * @return sum capacity all alcohols
     */
    public BigDecimal getSumCapacityAllAlcohols(){
        return new BigDecimal(alcoholRepository.sumAllCapacity()).divide(new BigDecimal(1000)).setScale(2, RoundingMode.UP);
    }
}
