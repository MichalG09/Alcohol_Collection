package pl.mgrzech.alcohols_collection.manufacturerTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.ManufacturerController;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.manufacturer.ManufacturerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManufacturerControllerTests {

    @Mock
    private ManufacturerService manufacturerService;

    @InjectMocks
    private ManufacturerController manufacturerController;

    private MockMvc mockMvc;
    private final List<Manufacturer> listManufacturers = new ArrayList<>();
    private final Manufacturer emptyNewsletter = new Manufacturer();

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(manufacturerController).build();

        Date nowDate = new Date();
        listManufacturers.add(new Manufacturer(1, "test manufacturer1", "", "", null, "", nowDate, nowDate));
        listManufacturers.add(new Manufacturer(2, "test manufacturer2", "", "", null, "", nowDate, nowDate));
    }

    @Test
    public void shouldReturnListManufacturers() throws Exception {
        when(manufacturerService.findAllManufacturers()).thenReturn(listManufacturers);

        mockMvc.perform(get("/user/manufacturer/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("manufacturer/all_manufacturers"))
                .andExpect(model().attribute("manufacturers", hasSize(listManufacturers.size())));
    }

    @Test
    public void shouldSaveManufacturerAfterCorrectValidation() throws Exception {
        mockMvc.perform(post("/user/manufacturer/add")
                .flashAttr("manufacturer", listManufacturers.get(0)))
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    public void shouldReturnToAddManufacturerViewAfterIncorrectValidation() throws Exception {
        mockMvc.perform(post("/user/manufacturer/add")
                .flashAttr("manufacturer", emptyNewsletter))
                .andExpect(status().isOk())
                .andExpect(view().name("manufacturer/add_manufacturer"));
    }

    @Test
    public void shouldPrepareManufacturerToEdit() throws Exception {
        when(manufacturerService.findManufacturerById(1)).thenReturn(listManufacturers.get(0));

        mockMvc.perform(get("/user/manufacturer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("manufacturer/add_manufacturer"))
                .andExpect(model().attribute("manufacturer", listManufacturers.get(0)));
    }

    @Test
    public void shouldPrepareManufacturerToAdd() throws Exception {
        mockMvc.perform(get("/user/manufacturer/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("manufacturer/add_manufacturer"));
    }
}
