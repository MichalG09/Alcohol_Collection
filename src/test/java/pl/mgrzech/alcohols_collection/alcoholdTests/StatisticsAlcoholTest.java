package pl.mgrzech.alcohols_collection.alcoholdTests;

import org.junit.Before;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class StatisticsAlcoholTest {

    @Autowired
    private AlcoholRepository alcoholRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Before
    public void init(){
        createOneAlcoholToH2Database(3, "A2");
        createOneAlcoholToH2Database(4, "A2");
        createOneAlcoholToH2Database(5, "A2");
        createOneAlcoholToH2Database(6, "A2");
        createOneAlcoholToH2Database(7, "A3");
        createOneAlcoholToH2Database(8, "A5");
        createOneAlcoholToH2Database(9, "A5");
    }

    @Test
    public void shouldCorrectCalculationNumbersAlcoholInEachPlaceInStorageA0(){
        assertEquals(0, alcoholRepository.countByPlaceInStorage("A0"));
    }

    @Test
    public void shouldCorrectCalculationNumbersAlcoholInEachPlaceInStorageA2(){
        assertEquals(4, alcoholRepository.countByPlaceInStorage("A2"));
    }

    @Test
    public void shouldCorrectCalculationNumbersAlcoholInEachPlaceInStorageA3(){
        assertEquals(1, alcoholRepository.countByPlaceInStorage("A3"));
    }

    @Test
    public void shouldCorrectCalculationNumbersAlcoholInEachPlaceInStorageA5(){
        assertEquals(2, alcoholRepository.countByPlaceInStorage("A5"));
    }

    private void createOneAlcoholToH2Database(int nextId, String placeInStorage){
        Date now = new Date();
        Manufacturer manufacturer = new Manufacturer(null, "name"+nextId, "", "", null, "", now, now);
        manufacturerRepository.save(manufacturer);
        Picture picture = new Picture(null, "test"+nextId, new byte[10], now, false, false);
        pictureRepository.save(picture);
        List<Picture> listPictures = new ArrayList<>();
        listPictures.add(picture);
        alcoholRepository.save(new Alcohol(null, "name1"+nextId, "type1", 50, 40, now, now, "", manufacturer, "", placeInStorage, listPictures));
    }
}
