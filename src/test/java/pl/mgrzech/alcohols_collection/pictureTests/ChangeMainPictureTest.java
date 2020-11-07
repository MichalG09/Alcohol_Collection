package pl.mgrzech.alcohols_collection.pictureTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.picture.ChangeMainPictureForAlcohol;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.Date;

import static org.junit.Assert.*;

public class ChangeMainPictureTest {

    @InjectMocks
    private ChangeMainPictureForAlcohol changeMainPictureForAlcohol;

    private Picture newMainPicture;
    private Picture oldMainPicture;

    @Mock
    private PictureRepository pictureRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        oldMainPicture = new Picture(null, "testPicture_1", new byte[10], new Date(), false, true);;
        newMainPicture = new Picture(null, "testPicture_2", new byte[10], new Date(), false, false);;
    }

    @Test
    public void shouldCorrectChangeMainPictureForAlcohol(){
        Mockito.when(pictureRepository.findMainPictureForAlcohol(1)).thenReturn(java.util.Optional.ofNullable(oldMainPicture));
        Mockito.when(pictureRepository.findById(2)).thenReturn(java.util.Optional.ofNullable(newMainPicture));

        changeMainPictureForAlcohol.change(1, 2);

        assertFalse(oldMainPicture.isMainPicture());
        assertTrue(newMainPicture.isMainPicture());
    }
}
