package pl.mgrzech.alcohols_collection.staticticsControllerTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mgrzech.alcohols_collection.controllers.StatisticController;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.statistics.StatisticService;

import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatisticsControllerTest {

    @Mock
    private StatisticService statisticService;

    @InjectMocks
    private StatisticController statisticController;

    private MockMvc mockMvc;
    private final Map<String, Integer> mapStatistics = new TreeMap<>();
    private final Property emptyProperty = new Property();

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticController).build();

        mapStatistics.put("A1", 10);
        mapStatistics.put("A2", 20);
    }

    @Test
    public void shouldReturnListProperties() throws Exception {
        when(statisticService.showAllStatistics()).thenReturn(mapStatistics);

        mockMvc.perform(get("/user/statistic"))
                .andExpect(status().isOk())
                .andExpect(view().name("statistic/allStatistic"))
                .andExpect(model().attribute("statisticsForPlacesInStorage", mapStatistics));
    }

}