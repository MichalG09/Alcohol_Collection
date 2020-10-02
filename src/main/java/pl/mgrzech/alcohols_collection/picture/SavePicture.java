package pl.mgrzech.alcohols_collection.picture;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.PicturesRepository;

import java.util.Date;

@Component("ConvertByteCodeToPictureImpl")
@AllArgsConstructor
public class SavePicture {

    private final PicturesRepository picturesRepository;

    /**
     * Method converts validated file to picture.
     * @param bytes picture in byte code
     * @param namePicture name picture
     * @param isMain if true picture is main
     * @param isGallery if true picture is too gallery
     * @return converted picture
     */
    public Picture save(byte[] bytes, String namePicture, boolean isMain, boolean isGallery) {
        Picture picture = new Picture();
        picture.setFile(bytes);
        picture.setName(namePicture);
        picture.setCreatedDate(new Date());
        picture.setMainPicture(isMain);
        picture.setGallery(isGallery);
        picturesRepository.save(picture);
        return picture;
    }
}
