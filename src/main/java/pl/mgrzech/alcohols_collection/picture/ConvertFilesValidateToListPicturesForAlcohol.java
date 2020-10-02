package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ConvertFilesValidateToListPicturesForAlcohol {

    private final ConvertMainPictureFromFilesValidatedToPicture convertMainPictureFromFilesValidatedToPicture;
    private final ConvertAdditionalPicturesFromFilesValidatedToPicture convertAdditionalPicturesFromFilesValidatedToPicture;

    /**
     * Method convert validated file added in form to list pictures for alcohol.
     * Methods converts first main picture and second other additional files.
     * @param filesValidated file with all pictures from form
     * @param alcohol alcohol for this pictures
     * @return list of picture
     */
    public List<Picture> convert(FilesValidated filesValidated, Alcohol alcohol) {
        List<Picture> listPicturesAlcohol = new ArrayList<>();

        if(!filesValidated.getMainFile().isEmpty()){
            try {
                listPicturesAlcohol.add(convertMainPictureFromFilesValidatedToPicture.convert(filesValidated, alcohol));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!filesValidated.getMultipartFiles().get(0).isEmpty()){
            listPicturesAlcohol.addAll(convertAdditionalPicturesFromFilesValidatedToPicture.convert(filesValidated, alcohol));
        }
        return listPicturesAlcohol;
    }
}
