package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mgrzech.alcohols_collection.acceptedCookie.CheckAcceptedCookie;
import pl.mgrzech.alcohols_collection.alcohol.AlcoholService;
import pl.mgrzech.alcohols_collection.alcohol.FindAlcohol;
import pl.mgrzech.alcohols_collection.alcohol.model.AlcoholToSearch;
import pl.mgrzech.alcohols_collection.compareAlcohols.AddAlcoholToCompareList;
import pl.mgrzech.alcohols_collection.compareAlcohols.CompareAlcoholsService;
import pl.mgrzech.alcohols_collection.compareAlcohols.DeleteAlcoholFromCompareList;
import pl.mgrzech.alcohols_collection.compareAlcohols.GetAlcoholsToCompareFromCookie;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.property.FindProperty;
import pl.mgrzech.alcohols_collection.sortType.FindSortType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/alcohol/")
@AllArgsConstructor
public class CompareAlcoholsController {

    private final CompareAlcoholsService compareAlcoholsService;
    private final AlcoholService alcoholService;

    /**
     * Methods prepares alcohol chosen to prepare (saved in cookie).
     * @param request request
     * @return name of the running html file for show compare alcohols
     */
    @GetMapping("/compare")
    public String showAlcoholsInCompare(HttpServletRequest request,
                                        Model model){
        model.addAttribute("alcohols", compareAlcoholsService.getAlcoholsToCompareFromCookie(request));
        return "alcohol/compare_alcohols";
    }

    /**
     * Method deletes alcohol form compare list.
     * @param id id alcohol to delete from compare list
     * @param model model
     * @param request request
     * @param response response
     * @return name of the running html file after delete alcohol from compare list
     */
    @GetMapping("/deleteCompare/{id}")
    public String deleteAlcoholFromCompare(@PathVariable("id") int id,
                                           Model model,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        model.addAttribute("alcohols", compareAlcoholsService.deleteAlcoholFromCompare(id, request, response));
        return "redirect:/alcohol/compare";
    }

    /**
     * Method saves id chosen alcohol to compare (list id in cookie).
     * After saved alcohol to compare method returns list alcohols to show in view
     * (the same when alcohol was add to compare list).
     * @param page number page to show
     * @param numberAlcoholInOnePage number alcohol to shoe in one page
     * @param sortBy type sort of results
     * @param id id alcohol add to compare
     * @param alcoholToSearch parameters to search alcohol
     * @param model model
     * @param request request
     * @param response response
     * @return name of the running html file after add alcohol to compare
     */
    @PostMapping("/addToCompare/{page}/{numberAlcoholInOnePage}/{sortBy}/{id}")
    public String addAlcoholToCompare(@PathVariable("page") int page,
                                      @PathVariable("numberAlcoholInOnePage") String numberAlcoholInOnePage,
                                      @PathVariable("sortBy") String sortBy,
                                      @PathVariable("id") int id,
                                      @ModelAttribute("alcoholToSearch") AlcoholToSearch alcoholToSearch,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response){
        model.addAttribute("alcoholToSearch", alcoholToSearch);
        model.addAttribute("alcohols", alcoholService.findSearchingAlcohols(
                alcoholToSearch,
                page + 1,
                sortBy,
                Integer.parseInt(numberAlcoholInOnePage.trim())));
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("numberAlcoholInOnePage", numberAlcoholInOnePage);
        model.addAttribute("listAlcoholsToCompare", compareAlcoholsService.addAlcoholToCompareList(id, request, response));
        return "alcohol/all_alcohols";
    }

    /**
     * Method returns all types of sort from properties.
     * All types of sort are show in selector to pick type sorting result list.
     * @return list of sort type
     */
    @ModelAttribute("typesOfSort")
    public Iterable<SortType> listAllTypesOfSort() {
        return alcoholService.allTypesSort();
    }

    /**
     * Method returns values of number alcohol in one page from properties.
     * All values are show in selector.
     * @return values number alcohol in one page
     */
    @ModelAttribute("numberAlcoholsInOnePageToSelect")
    public List<String> listNumberAlcoholsInOnePage() {
        return alcoholService.allNumbersAlcoholsInOnePage();
    }

    /**
     * Method check acceptance to use cookie.
     * Cookies are necessary to compare alcohols.
     * @return acceptance to use cookie
     */
    @ModelAttribute("acceptCookie")
    public boolean acceptedCookieInWebsite(HttpServletRequest request) {
        return alcoholService.checkAcceptedCookieInWebsite(request);
    }
}
