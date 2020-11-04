package pl.mgrzech.alcohols_collection.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.mgrzech.alcohols_collection.alcohol.AlcoholService;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.property.FindProperty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@AllArgsConstructor
public class StatisticService {

    private final AlcoholService alcoholService;
    private final FindProperty findProperty;
    private final FindStatisticsAllAlcoholInOnePlaceInStorage findStatisticsAllAlcoholInOnePlaceInStorage;
    private final PrepareAlcoholToSearchForStatisticInPlaceStorage prepareAlcoholToSearchForStatisticInPlaceStorage;

    /**
     * Method show number alcohols in one place in storage.
     * @return map with all alcohols in each place in storage
     */
    public Map<String, Integer> showAllStatistics(){
        Map<String, Integer> resultMapWithStatisticPlaceInStorage = new TreeMap<>();
        findProperty.findByNameAndGetValuesInList("placeInStorage")
                .forEach(place -> {
                    resultMapWithStatisticPlaceInStorage.put(place, findStatisticsAllAlcoholInOnePlaceInStorage.find(place));
                });
        return resultMapWithStatisticPlaceInStorage;
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

    /**
     * Method checks if the user has accepted cookies.
     * @param request request
     * @return true if user accepted cookie, false if not.
     */
    public boolean checkAcceptedCookieInWebsite(HttpServletRequest request) {
        return alcoholService.checkAcceptedCookieInWebsite(request);
    }

    /**
     * Method returns all sort type sorted in list by value.
     * List is necessary to select type sort in form.
     * @return all sort type in list
     */
    public Iterable<SortType> allTypesSort() {
        return alcoholService.allTypesSort();
    }

    /**
     * Method returns values of number alcohol in one page from properties.
     * All values are show in selector in form.
     * @return values number alcohol in one page
     */
    public List<String> allNumbersAlcoholsInOnePage() {
        return alcoholService.allNumbersAlcoholsInOnePage();
    }
}
