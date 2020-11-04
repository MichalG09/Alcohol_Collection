package pl.mgrzech.alcohols_collection.pictureTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import pl.mgrzech.alcohols_collection.entities.Picture;
import pl.mgrzech.alcohols_collection.picture.GetUniqueNameForPicture;
import pl.mgrzech.alcohols_collection.repositories.PictureRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PictureUniqueNameTest {

    @InjectMocks
    private GetUniqueNameForPicture getUniqueNameForPicture;

    @Mock
    private PictureRepository pictureRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnUniqueNumber(){
        Picture Picture = new Picture(null, "testPicture_1", new byte[10], new Date(), false, false);
        ReflectionTestUtils.setField(getUniqueNameForPicture, "scopeToRandomNumber", 2);
        ReflectionTestUtils.setField(getUniqueNameForPicture, "minRandValue", 1);

        Mockito.when(pictureRepository.findByNameIsContaining("1")).thenReturn(new ArrayList<>(Collections.singletonList(Picture)));

        assertEquals("testPicture_2", getUniqueNameForPicture.get("testPicture"));
    }

}
