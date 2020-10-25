package pl.mgrzech.alcohols_collection.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.mgrzech.alcohols_collection.alcohol.AlcoholService;
import pl.mgrzech.alcohols_collection.property.FindProperty;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
@AllArgsConstructor
public class StatisticService {

    private final FindProperty findProperty;
    private final FindStatisticsAllAlcoholInOnePlaceInStorage findStatisticsAllAlcoholInOnePlaceInStorage;
    private final AlcoholService alcoholService;
    private final PrepareAlcoholToSearchForStatisticInPlaceStorage prepareAlcoholToSearchForStatisticInPlaceStorage;

    /**
     * Method show number alcohols in one place in storage.
     * @param model model
     */
    public void showAllStatistics(Model model){
        Map<String, Integer> resultMapWithStatisticPlaceInStorage = new TreeMap<>();
        findProperty.findByName("placeInStorage")
                .forEach(place -> {
                    resultMapWithStatisticPlaceInStorage.put(place, findStatisticsAllAlcoholInOnePlaceInStorage.find(place));
                });
        model.addAttribute("statisticsForPlacesInStorage", resultMapWithStatisticPlaceInStorage);
    }

    /**
     * Method shows all alcohol for one place in storage.
     * @param model model
     * @param placeInStorage place in storage to show all alcohols
     * @param request request
     */
    public void showAllAlcoholsInOnePlaceStorage(Model model,
                                                 String placeInStorage,
                                                 HttpServletRequest request) {
        alcoholService.findSearchingAlcohols(model, request,
                prepareAlcoholToSearchForStatisticInPlaceStorage.prepare(placeInStorage),
                1, "nameA-Z", findProperty.findBasicNumberAlcoholsInOnePage());
    }
}
