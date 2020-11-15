package pl.mgrzech.alcohols_collection.alcoholdTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.alcohol.AlcoholService;
import pl.mgrzech.alcohols_collection.alcohol.model.AlcoholToSearch;
import pl.mgrzech.alcohols_collection.controllers.AlcoholController;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.entities.Picture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlcoholControllerTests {

    @Mock
    private AlcoholService alcoholService;

    @InjectMocks
    private AlcoholController alcoholController;

    private MockMvc mockMvc;
    private final List<Alcohol> listAlcohols = new ArrayList<>();
    private final Alcohol emptyAlcohol = new Alcohol();
    private Page<Alcohol> alcoholPage;
    private Alcohol correctAlcohol;

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alcoholController).build();

        Date nowDate = new Date();
        Picture picture = new Picture();
        List<Picture> listPictures = new ArrayList<>(Collections.singletonList(picture));
        List<Alcohol> listAlcohols = new ArrayList<>();
        listAlcohols.add(new Alcohol(1, "name1", "type1", 50, 40, nowDate, nowDate, "comment1", new Manufacturer(), "", "A1", listPictures));
        listAlcohols.add(new Alcohol(2, "name2", "type2", 50, 40, nowDate, nowDate, "comment2", new Manufacturer(), "", "A2", listPictures));

        alcoholPage = new PageImpl<>(listAlcohols);
        correctAlcohol = new Alcohol(3, "name", "type", 50, 40, nowDate, nowDate, "comment", new Manufacturer(), "", "A1", listPictures);
    }

    @Test
    public void shouldReturnListAlcoholsForFirstPage() throws Exception {
        when(alcoholService.findAllAlcoholsForFirstPage()).thenReturn(alcoholPage);

        mockMvc.perform(get("/alcohol/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("alcohol/all_alcohols"))
                .andExpect(model().attribute("alcohols", alcoholPage))
                .andExpect(model().attribute("sortBy", ""))
                .andExpect(model().attribute("numberAlcoholInOnePage", ""));
    }

    @Test
    public void shouldReturnAlcoholToEdit() throws Exception {
        when(alcoholService.findAlcoholById(1)).thenReturn(correctAlcohol);

        mockMvc.perform(get("/alcohol/detail/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("alcohol/detail_one_alcohol"))
                .andExpect(model().attribute("alcohol", correctAlcohol));
    }

    @Test
    public void shouldReturnListAlcoholsAfterSearch() throws Exception {
        when(alcoholService.findAlcoholById(1)).thenReturn(correctAlcohol);

        mockMvc.perform(post("/alcohol/search/1/14/nameA-Z"))
                .andExpect(status().isOk())
                .andExpect(view().name("alcohol/all_alcohols"))
                .andExpect(model().attribute("sortBy", "nameA-Z"))
                .andExpect(model().attribute("numberAlcoholInOnePage", "14"));
    }
}
