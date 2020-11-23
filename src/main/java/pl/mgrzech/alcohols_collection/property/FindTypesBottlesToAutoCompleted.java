package pl.mgrzech.alcohols_collection.property;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindTypesBottlesToAutoCompleted {

    private final FindProperty findProperty;

    /**
     * Method returns all types of bottles.
     * @return all types of bottle in JSONArray
     */
    public JSONArray find() {
        JSONArray resultJSON = new JSONArray();
        findProperty.findByNameAndGetValuesInList("TypesBottles").forEach(typeBottle -> {
            JSONObject jsonTypeAlcohol = new JSONObject();
            jsonTypeAlcohol.put("name", typeBottle);
            resultJSON.put(jsonTypeAlcohol);
        });

        return resultJSON;
    }
}
