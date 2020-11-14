package pl.mgrzech.alcohols_collection.manufacturerTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.manufacturer.AddManufacturer;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;

import java.util.*;

public class ManufacturerAfterEditAlcoholTest {

    @InjectMocks
    private AddManufacturer addManufacturer;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    private final Date nowDate = new Date();
    Manufacturer oldManufacturer;
    Manufacturer newManufacturer;
    Alcohol alcohol;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        Picture picture = new Picture();
        List<Picture> listPictures = new ArrayList<>(Collections.singletonList(picture));
        oldManufacturer = new Manufacturer(1, "test manufacturer 1", "", "", null, "", nowDate, nowDate);
        newManufacturer = new Manufacturer(null, "test manufacturer 2", "", "", null, "", nowDate, nowDate);
        alcohol = new Alcohol(1L, "name", "type", 50, 40, nowDate, nowDate, "comment", oldManufacturer, "", "A1", listPictures);
        List<Alcohol> alcoholList = new ArrayList<>(Collections.singletonList(alcohol));
        oldManufacturer.setAlcohols(alcoholList);
    }

    @Test
    public void shouldChangeManufacturerForAlcohol(){
        Mockito.when(manufacturerRepository.findById(1)).thenReturn(Optional.ofNullable(oldManufacturer));

        newManufacturer = addManufacturer.saveAfterAddOrEditAlcohol(newManufacturer, oldManufacturer, alcohol, nowDate);
        Assert.assertTrue(oldManufacturer.getAlcohols().isEmpty());
    }
}
