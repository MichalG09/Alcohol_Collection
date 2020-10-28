package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.mgrzech.alcohols_collection.alcohol.AlcoholService;
import pl.mgrzech.alcohols_collection.alcohol.model.AlcoholToSearch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@AllArgsConstructor
public class CompareAlcoholsService {

    private final GetAlcoholsToCompareFromCookie getAlcoholsToCompareFromCookie;
    private final DeleteAlcoholFromCompareList deleteAlcoholFromCompareList;
    private final AddAlcoholToCompareList addAlcoholToCompareList;
    private final AlcoholService alcoholService;

    /**
     * Methods prepares alcohol chosen to prepare (saved in cookie).
     * @param request request
     */
    public void getAlcoholsToCompareFromCookie(Model model, HttpServletRequest request) {
        model.addAttribute("alcohols", getAlcoholsToCompareFromCookie.get(request));
    }

    /**
     *
     * Method deletes alcohol (id) from list alcohols selected to compare.
     * @param id id alcohol to delete from compare list
     * @param request request
     * @param response response
     */
    public void deleteAlcoholFromCompare(Model model, int id, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("alcohols", deleteAlcoholFromCompareList.delete(id, request, response));
    }

    /**
     * Method adds id alcohol to cookie with list alcohols (id) to compare.
     * List id alcohols is saves in one string separated by special sign (separatorCookie)
     * @param id id alcohol add to compare
     * @param request request
     * @param response response
     */
    public void addAlcoholToCompareList(Model model, int id, HttpServletRequest request,
                                        HttpServletResponse response, AlcoholToSearch alcoholToSearch,
                                        int page, String sortBy, int numberAlcoholInOnePage) {
        model.addAttribute("listAlcoholsToCompare", addAlcoholToCompareList.add(id, request, response));
        alcoholService.prepareInformationAfterSearchAlcohol(model, alcoholToSearch, page, sortBy, numberAlcoholInOnePage);
    }
}
