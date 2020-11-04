package pl.mgrzech.alcohols_collection.sortTypeTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.SortTypeController;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.sortType.SortTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SortTypeControllerTest {

    @Mock
    private SortTypeService sortTypeService;

    @InjectMocks
    private SortTypeController sortTypeController;

    private MockMvc mockMvc;
    private final List<SortType> listSortTypes = new ArrayList<>();
    private final SortType emptySortType = new SortType();

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sortTypeController).build();

        listSortTypes.add(new SortType(1, "test1", "TestValue1"));
        listSortTypes.add(new SortType(2, "test2", "TestValue2"));
    }

    @Test
    public void shouldReturnListSortTypes() throws Exception {
        when(sortTypeService.findAllSortType()).thenReturn(listSortTypes);

        mockMvc.perform(get("/admin/sortType/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("sortType/all_sort_type"))
                .andExpect(model().attribute("sortTypes", hasSize(listSortTypes.size())));
    }

    @Test
    public void shouldReturnOneSortTypeSearchedById() throws Exception {
        when(sortTypeService.findSortTypeById(1)).thenReturn(listSortTypes.get(0));

        mockMvc.perform(get("/admin/sortType/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("sortType/add_sort_type"))
                .andExpect(model().attribute("sortType", instanceOf(SortType.class)))
                .andExpect(model().attribute("sortType", listSortTypes.get(0)));
    }

    @Test
    public void shouldReturnOneEmptySortType() throws Exception {
        when(sortTypeService.findSortTypeById(3)).thenReturn(emptySortType);

        mockMvc.perform(get("/admin/sortType/edit/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("sortType/add_sort_type"))
                .andExpect(model().attribute("sortType", instanceOf(SortType.class)))
                .andExpect(model().attribute("sortType", emptySortType));
    }

    @Test
    public void shouldShowAddSortTypeView() throws Exception {
        mockMvc.perform(get("/admin/sortType/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("sortType", instanceOf(SortType.class)))
                .andExpect(view().name("sortType/add_sort_type"));
    }

    @Test
    public void shouldSaveSortTypeAfterCorrectValidation() throws Exception {
        mockMvc.perform(post("/admin/sortType/add")
                    .flashAttr("sortType", listSortTypes.get(0)))
                .andExpect(redirectedUrl("/admin"));
    }

    @Test
    public void shouldReturnToAddSortTypeViewAfterIncorrectValidation() throws Exception {
        mockMvc.perform(post("/admin/sortType/add")
                .flashAttr("sortType", emptySortType))
                .andExpect(status().isOk())
                .andExpect(view().name("sortType/add_sort_type"));
    }
}