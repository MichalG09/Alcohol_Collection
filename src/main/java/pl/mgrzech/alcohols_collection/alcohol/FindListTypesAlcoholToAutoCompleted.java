package pl.mgrzech.alcohols_collection.alcohol;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;
import pl.mgrzech.alcohols_collection.property.FindProperty;

import java.util.List;

@Component
@AllArgsConstructor
public class FindListTypesAlcoholToAutoCompleted {

    private final FindProperty findProperty;
    private final AlcoholRepository alcoholRepository;

    /**
     * Method returns all types (distinct) of alcohol in database
     * and add basic types from property if this values don`t be in list.
     * @return all type of alcohol in JSONArray
     */
    public JSONArray find() {
        JSONArray resultJSON = new JSONArray();
        List<String> uniqueTypesFromDatabase = alcoholRepository.findDistinctTypes();

        findProperty.findByNameAndGetValuesInList("basicTypesAlcohol").forEach(type -> {
            if(!uniqueTypesFromDatabase.contains(type)){
                uniqueTypesFromDatabase.add(type);
            }
        });

        uniqueTypesFromDatabase.forEach(type -> {
            JSONObject jsonTypeAlcohol = new JSONObject();
            jsonTypeAlcohol.put("name", type);
            resultJSON.put(jsonTypeAlcohol);
        });

        return resultJSON;
    }
}
