package pl.mgrzech.alcohols_collection.pictureTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.PictureController;
import pl.mgrzech.alcohols_collection.picture.PictureService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PictureControllerTest {

    @Mock
    private PictureService pictureService;

    @InjectMocks
    private PictureController pictureController;

    private MockMvc mockMvc;

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pictureController).build();
    }

    @Test
    public void shouldReturnCorrectRedirectAfterDeletedPicture() throws Exception {
        mockMvc.perform(get("/user/alcohol/deletePicture/1/testName1"))
                .andExpect(redirectedUrl("/user/alcohol/edit/" + 1));
    }

    @Test
    public void shouldReturnCorrectRedirectAfterChangeMainPicture() throws Exception {
        mockMvc.perform(get("/user/alcohol/changeMainPicture/1/11"))
                .andExpect(redirectedUrl("/user/alcohol/edit/" + 1));
    }
}