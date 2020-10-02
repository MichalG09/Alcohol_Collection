package pl.mgrzech.alcohols_collection.manufacturer;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;

@Component
@AllArgsConstructor
public class FindAllManufacturersInJSON {

    private final ManufacturerRepository manufacturerRepository;

    /**
     * Method returns all manufacturers and transform to JSONArray for javascript.
     * @return list all manufacturers in JSONArray
     */
    public JSONArray findAllManufacturersInJSON() {
        JSONArray result = new JSONArray();
        manufacturerRepository.findAll().forEach(manufacturer -> {
            result.put(convertManufacturerToJson(manufacturer));
        });
        return result;
    }

    /**
     * Method converts object Manufacturer to JSON object.
     * @param manufacturer manufacturer to transform
     * @return manufacturer in JSON object
     */
    private JSONObject convertManufacturerToJson(Manufacturer manufacturer){
        JSONObject jsonManufacturer = new JSONObject();
        jsonManufacturer.put("id", manufacturer.getID());
        jsonManufacturer.put("name", manufacturer.getNameCompany());
        jsonManufacturer.put("town", manufacturer.getTown());
        jsonManufacturer.put("country", manufacturer.getCountry());
        jsonManufacturer.put("comment", manufacturer.getComments());
        return jsonManufacturer;
    }
}
