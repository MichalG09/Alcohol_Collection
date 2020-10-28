package pl.mgrzech.alcohols_collection.statistics;

import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.alcohol.model.AlcoholToSearch;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;

@Component
public class PrepareAlcoholToSearchForStatisticInPlaceStorage {

    /**
     * Method prepares parameter for alcohol to search by place in storage.
     * @param placeInStorage place in storage to find all alcohols
     * @return alcohol to search with parameters to find alcohols
     */
    public AlcoholToSearch prepare(String placeInStorage){
        AlcoholToSearch alcoholToSearch = new AlcoholToSearch();
        alcoholToSearch.setPlaceInStorage(placeInStorage);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setNameCompany("");
        alcoholToSearch.setManufacturer(manufacturer);
        return alcoholToSearch;
    }
}
