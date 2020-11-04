package pl.mgrzech.alcohols_collection.pictureTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class PictureRepositoryTest {

    @Autowired
    private AlcoholRepository alcoholRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    private final Picture additionalPicture1 = new Picture(null, "test1", new byte[10], new Date(), false, false);
    private final Picture pictureToGallery = new Picture(null, "test2", new byte[10], new Date(), true, false);
    private final Picture mainPicture = new Picture(null, "test3", new byte[10], new Date(), false, true);
    private final Picture additionalPicture2 = new Picture(null, "test11", new byte[10], new Date(), false, false);

    private final Date now = new Date();

    @Test
    public void shouldCorrectSavePicture(){
        pictureRepository.save(additionalPicture1);
        assertNotNull(additionalPicture1.getId());
    }

    @Test
    public void shouldReturnPictureByName(){
        pictureRepository.save(additionalPicture1);
        pictureRepository.save(pictureToGallery);
        Optional<Picture> picture = pictureRepository.findByName(additionalPicture1.getName());
        assertTrue(picture.isPresent());
        assertEquals(additionalPicture1.getName(), picture.get().getName());
    }

    @Test
    public void shouldReturnPictureToGallery(){
        pictureRepository.save(additionalPicture1);
        pictureRepository.save(pictureToGallery);
        pictureRepository.save(mainPicture);
        List<Picture> pictures = pictureRepository.findByGallery(true);
        assertFalse(pictures.isEmpty());
        assertEquals(1, pictures.size());
        assertEquals(pictureToGallery.getName(), pictures.get(0).getName());
    }

    @Test
    public void shouldReturnPictureToGalleryContainingString(){
        pictureRepository.save(additionalPicture1);
        pictureRepository.save(pictureToGallery);
        pictureRepository.save(mainPicture);
        pictureRepository.save(additionalPicture2);
        List<Picture> pictures = pictureRepository.findByNameIsContaining("1");
        assertFalse(pictures.isEmpty());
        assertEquals(2, pictures.size());
    }

    @Test
    public void shouldReturnMainPicture(){
        pictureRepository.save(additionalPicture1);
        pictureRepository.save(mainPicture);
        pictureRepository.save(additionalPicture2);
        List<Picture> listPictures = new ArrayList<>(Arrays.asList(additionalPicture1, pictureToGallery, mainPicture, additionalPicture2));
        Manufacturer manufacturer = new Manufacturer(null, "name", "", "", null, "", now, now);
        manufacturerRepository.save(manufacturer);
        Alcohol alcohol = new Alcohol(null, "name1", "type1", 50, 40, now, now, "", manufacturer, "", "A1", listPictures);
        alcoholRepository.save(alcohol);

        Optional<Picture> picture = pictureRepository.findMainPictureForAlcohol(alcohol.getId());
        assertTrue(picture.isPresent());
        assertEquals(mainPicture.getName(), picture.get().getName());
    }
}
