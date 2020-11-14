package pl.mgrzech.alcohols_collection.alcoholdTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class AlcoholRepositoryTest {

    @Autowired
    private AlcoholRepository alcoholRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    private final Date nowDate = new Date();

    @Before
    public void init(){
        alcoholRepository.save(prepareAlcoholToTests("test1", "test1", "A1", 30, 30));

        alcoholRepository.save(prepareAlcoholToTests("test2", "test1", "A1", 50, 40));

        alcoholRepository.save(prepareAlcoholToTests("test3", "test2", "A2", 90, 50));
    }

    @Test
    public void shouldReturnDistinctAlcoholsType(){
        assertNotNull(alcoholRepository.findDistinctTypes());
        assertEquals(2, alcoholRepository.findDistinctTypes().toArray().length);
    }

    @Test
    public void shouldReturnNumberAlcoholInOnPlaceInStorage(){
        assertEquals(2, alcoholRepository.countByPlaceInStorage("A1"));
        assertEquals(1, alcoholRepository.countByPlaceInStorage("A2"));
    }

    @Test
    public void shouldReturnFoundAlcoholsBySearch(){
        assertEquals(2, alcoholRepository.searchAlcohols("", "", 39, 61, 49, 91, "", "", PageRequest.of(0, 10)).getNumberOfElements());
    }

    private Alcohol prepareAlcoholToTests(String name, String type, String placeInStorage, int capacity, int amountOfAlcohol){
        Picture additionalPicture1 = new Picture(null, "test1", new byte[10], new Date(), false, false);
        Picture pictureToGallery = new Picture(null, "test2", new byte[10], new Date(), true, false);
        List<Picture> listPictures = new ArrayList<>(Arrays.asList(additionalPicture1, pictureToGallery));

        Manufacturer manufacturer = new Manufacturer(null, "name" + name, "", "", null, "", nowDate, nowDate);
        manufacturerRepository.save(manufacturer);

        return new Alcohol(null, "name" + name, "type" + type, capacity, amountOfAlcohol, nowDate, nowDate, "", manufacturer, "", placeInStorage, listPictures);
    }
}
