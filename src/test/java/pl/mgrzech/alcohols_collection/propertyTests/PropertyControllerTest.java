package pl.mgrzech.alcohols_collection.propertyTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.PropertyController;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.property.PropertyService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    private MockMvc mockMvc;
    private final List<Property> listProperties = new ArrayList<>();
    private final Property emptyProperty = new Property();

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();

        listProperties.add(new Property(1, "testNameProperty1", "TestValueProperty1"));
        listProperties.add(new Property(2, "testNameProperty2", "TestValueProperty2"));
    }

    @Test
    public void shouldReturnListProperties() throws Exception {
        when(propertyService.findAllProperties()).thenReturn(listProperties);

        mockMvc.perform(get("/admin/property/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("property/all_properties"))
                .andExpect(model().attribute("properties", hasSize(listProperties.size())));
    }

    @Test
    public void shouldReturnOnePropertySearchedById() throws Exception {
        when(propertyService.findPropertyById(1)).thenReturn(listProperties.get(0));

        mockMvc.perform(get("/admin/property/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("property/add_property"))
                .andExpect(model().attribute("property", instanceOf(Property.class)))
                .andExpect(model().attribute("property", listProperties.get(0)));
    }

    @Test
    public void shouldReturnOneEmptyProperty() throws Exception {
        when(propertyService.findPropertyById(3)).thenReturn(emptyProperty);

        mockMvc.perform(get("/admin/property/edit/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("property/add_property"))
                .andExpect(model().attribute("property", instanceOf(Property.class)))
                .andExpect(model().attribute("property", emptyProperty));
    }

    @Test
    public void shouldShowAddPropertyView() throws Exception {
        mockMvc.perform(get("/admin/property/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("property", instanceOf(Property.class)))
                .andExpect(view().name("property/add_property"));
    }

    @Test
    public void shouldSavePropertyAfterCorrectValidation() throws Exception {
        mockMvc.perform(post("/admin/property/add")
                    .flashAttr("property", listProperties.get(0)))
                .andExpect(redirectedUrl("/admin"));
    }

    @Test
    public void shouldReturnToAddPropertyViewAfterIncorrectValidation() throws Exception {
        mockMvc.perform(post("/admin/property/add")
                .flashAttr("property", emptyProperty))
                .andExpect(status().isOk())
                .andExpect(view().name("property/add_property"));
    }
}