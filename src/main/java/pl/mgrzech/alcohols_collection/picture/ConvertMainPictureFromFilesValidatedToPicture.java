package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.PicturesRepository;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import java.io.IOException;

@Component("ConvertMainPictureFromFilesValidatedToPictureImpl")
@AllArgsConstructor
public class ConvertMainPictureFromFilesValidatedToPicture {

    private final PicturesRepository picturesRepository;
    private final SavePicture savePicture;
    private final GetUniqueName getUniqueName;

    /**
     * Method converts filesValidated (insert in form) to main picture for alcohol.
     * Method sets all pictures unique name.
     * @param filesValidated file with main picture (insert in form and validated)
     * @param alcohol alcohol for main picture
     * @return new main picture
     * @throws IOException IOException
     */
    public Picture convert(FilesValidated filesValidated, Alcohol alcohol) throws IOException {
        if(alcohol.getId() != null){
            picturesRepository.save(picturesRepository.findMainPictureForAlcohol(alcohol.getId()).map(picture -> {
                picture.setMainPicture(false);
                return picture;
            }).orElseGet(Picture::new));
        }
        Picture pictureMain = savePicture.save(
                filesValidated.getMainFile().getBytes(),
                getUniqueName.get(alcohol.getName()),
                true,
                false);
        return pictureMain;
    }
}
