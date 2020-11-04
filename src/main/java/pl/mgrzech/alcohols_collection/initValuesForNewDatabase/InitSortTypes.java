package pl.mgrzech.alcohols_collection.initValuesForNewDatabase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.sortType.AddSortType;
import pl.mgrzech.alcohols_collection.sortType.FindSortType;

@Component
@AllArgsConstructor
public class InitSortTypes {

    private final FindSortType findSortType;
    private final AddSortType addSortType;

    /**
     * Method initializes all necessary sort types for start project.
     */
    public void initSortTypes() {
        initSortType("nazwa A-Z", "nameA-Z");
        initSortType("nazwa Z-A", "nameZ-A");
        initSortType("pojemność butelki 0-9", "capacity0-9");
        initSortType("pojemność butelki 9-0", "capacity9-0");
        initSortType("zawartość alkoholu 0-9", "amount_of_alcohol0-9");
        initSortType("zawartość alkoholu 9-0", "amount_of_alcohol9-0");
        initSortType("typ alkoholu A-Z", "typeA-Z");
        initSortType("typ alkoholu Z-A", "typeZ-A");
        initSortType("producent A-Z", "m.name_companyA-Z");
        initSortType("producent Z-A", "m.name_companyZ-A");
    }

    /**
     * Method initializes one sort type.
     * If sort type exists, method don`t create it
     * @param nameSortType name sort type
     * @param nameEngSortType value sort type in english (to create SQl)
     */
    private void initSortType(String nameSortType, String nameEngSortType){
        if(!findSortType.findSortTypeByName(nameSortType).isPresent()){
            SortType sortType = new SortType();
            sortType.setValue(nameSortType);
            sortType.setValueTranslate(nameEngSortType);
            addSortType.save(sortType);
        }
    }
}