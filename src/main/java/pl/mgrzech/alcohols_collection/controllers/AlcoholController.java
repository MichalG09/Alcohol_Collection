package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mgrzech.alcohols_collection.alcohol.*;
import pl.mgrzech.alcohols_collection.alcohol.model.AlcoholToSearch;
import pl.mgrzech.alcohols_collection.entities.SortType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/alcohol/")
@AllArgsConstructor
public class AlcoholController {

    private final AlcoholService alcoholService;

    /**
     * Method loads first page of all alcohol.
     * @param model model
     * @param request request
     * @return name of the running html file for show all alcohol
     */
    @GetMapping("/all")
    public String allAlcohols(Model model, HttpServletRequest request){
        alcoholService.findAllAlcoholsForFirstPage(model, request);
        return "alcohol/all_alcohols";
    }

    /**
     * Method prepares to show all detail of alcohol.
     * @param id id alcohol to show detail
     * @param model model
     * @return name of the running html file for show alcohol detail
     */
    @GetMapping("/detail/{id}")
    public String detailOneAlcohol(@PathVariable("id") int id,
                                   Model model){
        alcoholService.findAlcoholByIdToShowAllDetail(model, id);
        return "alcohol/detail_one_alcohol";
    }

    /**
     * Method loads all alcohol filtered and sorted by parameters from form.
     * @param page number page to show
     * @param numberAlcoholInOnePage number alcohol to shoe in one page
     * @param sortBy type sort of results
     * @param alcoholToSearch parameters to search alcohol
     * @param model model
     * @param request request
     * @return name of the running html file for show all searched alcohol
     */
    @PostMapping("/search/{page}/{numberAlcoholInOnePage}/{sortBy}")
    public String searchAlcohol(@PathVariable("page") int page,
                                @PathVariable("numberAlcoholInOnePage") String numberAlcoholInOnePage,
                                @PathVariable("sortBy") String sortBy,
                                @ModelAttribute("alcoholToSearch") AlcoholToSearch alcoholToSearch,
                                Model model,
                                HttpServletRequest request){
        alcoholService.findSearchingAlcohols(
                model,
                request,
                alcoholToSearch,
                page,
                sortBy,
                Integer.parseInt(numberAlcoholInOnePage.trim()));
        return "alcohol/all_alcohols";
    }

    /**
     * Method return picture founded by id.
     * Picture to show in big size.
     * @param idAlcohol id Alcohol to back button
     * @param idPicture picture id to find
     * @param model model
     * @return found picture
     */
    @GetMapping("/picture/{idAlcohol}/{idPicture}")
    public String showOnePictureForAlcohol(@PathVariable("idAlcohol") int idAlcohol,
                                           @PathVariable("idPicture") int idPicture,
                                           Model model){
        alcoholService.findPicture(model, idPicture, idAlcohol);
        return "alcohol/alcohol_big_picture";
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
}
