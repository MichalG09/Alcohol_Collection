package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mgrzech.alcohols_collection.alcohol.AlcoholService;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.statistics.StatisticService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;
    private final AlcoholService alcoholService;

    @GetMapping("/user/statistic")
    public String showAllStatistic(Model model){
        statisticService.showAllStatistics(model);
        return "statistic/allStatistic";
    }

    @GetMapping("/user/statistic/InOnePlace/{place}")
    public String showAllAlcoholsInOnePlaceStorage(Model model,
                                                   @PathVariable("place") String placeInStorage,
                                                   HttpServletRequest request){
        statisticService.showAllAlcoholsInOnePlaceStorage(model, placeInStorage, request);
        return "alcohol/all_alcohols";
    }

    /**
     * Method check acceptance to use cookie.
     * Cookies are necessary to compare alcohols.
     * @param request request
     * @return acceptance to use cookie
     */
    @ModelAttribute("acceptCookie")
    public boolean acceptedCookieInWebsite(HttpServletRequest request) {
        return alcoholService.checkAcceptedCookieInWebsite(request);
    }

    /**
     * Method returns all types of sort from properties.
     * All types of sort are show in selector.
     * @return list of sort type
     */
    @ModelAttribute("typesOfSort")
    public Iterable<SortType> listAllTypesOfSort() {
        return alcoholService.allTypesSort();
    }

    /**
     * Method returns values of number alcohol in one page from properties.
     * All values are show in selector in form.
     * @return values number alcohol in one page
     */
    @ModelAttribute("numberAlcoholsInOnePageToSelect")
    public List<String> listNumbersAlcoholsInOnePage() {
        return alcoholService.allNumbersAlcoholsInOnePage();
    }
}
