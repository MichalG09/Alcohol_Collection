package pl.mgrzech.alcohols_collection.alcohol;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.manufacturer.AddManufacturer;
import pl.mgrzech.alcohols_collection.picture.ConvertFilesValidateToListPicturesForAlcohol;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import java.util.Date;

@Component
@AllArgsConstructor
public class AddAlcohol{

    private final ConvertFilesValidateToListPicturesForAlcohol convertFilesValidateToListPicturesForAlcohol;
    private final AddManufacturer addManufacturer;
    private final AlcoholRepository alcoholRepository;
    private final ManufacturerRepository manufacturerRepository;

    /**
     * Methods saves alcohol after add new or edit.
     * User choose manufacturer from list all manufacturer in form (autocompleted).
     * Method checks whether manufacturer is new or exists in database by id. If Id is null this is new manufacturer.
     * Methods compares oldManufacturer and newManufacturer. If manufacturers are not equals methods saves new manufacturer
     * (and alcohol is connect with new manufacturer).
     * If old manufacturer is null (during add new alcohol) new manufacturer is added to database and connect with new alcohol.
     * manufacturer this manufacturer have id == null.
     * @param alcohol new alcohol
     * @param oldManufacturer manufacturer from alcohol
     * @param newManufacturer manufacturer from form to edited or add alcohol
     * @param filesValidated pictures for new or edited alcohol
     */
    public void editOrAddNewAlcoholWithManufacturer(Alcohol alcohol, Manufacturer oldManufacturer,
                                                    Manufacturer newManufacturer, FilesValidated filesValidated) {
        boolean isNew = alcohol.getId() == 0;
        Date nowDate = new Date();
        if(isNew){
            alcohol.setCreatedDate(nowDate);
            alcohol.setPictures(convertFilesValidateToListPicturesForAlcohol.convert(filesValidated, alcohol));
        }
        else{
            if(!filesValidated.getMultipartFiles().get(0).isEmpty() || !filesValidated.getMainFile().isEmpty()){
                alcohol.getPictures().addAll(convertFilesValidateToListPicturesForAlcohol.convert(filesValidated, alcohol));
            }
        }
        alcohol.setLastChangeDate(nowDate);
        alcohol.setManufacturer(newManufacturer);
        newManufacturer = addManufacturer.saveAfterAddOrEditAlcohol(newManufacturer, oldManufacturer, alcohol, nowDate);
        manufacturerRepository.save(newManufacturer);
        alcoholRepository.save(alcohol);
    }
}
