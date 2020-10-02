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
public class ConvertAdditionalPicturesFromFilesValidatedToPicture {

    private final SavePicture savePicture;
    private final GetUniqueName getUniqueName;

    /**
     * Method converts filesValidated (insert in form) to picture for alcohol.
     * Method sets all pictures unique name.
     * @param filesValidated file with additional pictures (insert in form and validated)
     * @param alcohol alcohol for pictures
     * @return list converted file to picture
     */
    public List<Picture> convert(FilesValidated filesValidated, Alcohol alcohol) {
        List<Picture> listPicturesForAlcohol = new ArrayList<>();
        filesValidated.getMultipartFiles().forEach(pic ->
                {
                    try {
                        listPicturesForAlcohol.add(
                                savePicture.save(pic.getBytes(),
                                getUniqueName.get(alcohol.getName()),
                                false,
                                false)
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return listPicturesForAlcohol;
    }
}
