package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mgrzech.alcohols_collection.entities.Alcohol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@AllArgsConstructor
public class CompareAlcoholsService {

    private final GetAlcoholsToCompareFromCookie getAlcoholsToCompareFromCookie;
    private final DeleteAlcoholFromCompareList deleteAlcoholFromCompareList;
    private final AddAlcoholToCompareList addAlcoholToCompareList;

    /**
     * Methods prepares alcohol chosen to prepare (saved in cookie).
     * @param request request
     * @return list alcohol to compare
     */
    public List<Alcohol> getAlcoholsToCompareFromCookie(HttpServletRequest request) {
        return getAlcoholsToCompareFromCookie.get(request);
    }

    /**
     *
     * Method deletes alcohol (id) from list alcohols selected to compare.
     * @param id id alcohol to delete from compare list
     * @param request request
     * @param response response
     * @return list alcohols to compare (without deleted position).
     */
    public List<Alcohol> deleteAlcoholFromCompare(int id, HttpServletRequest request, HttpServletResponse response) {
        return deleteAlcoholFromCompareList.delete(id, request, response);
    }

    /**
     * Method adds id alcohol to cookie with list alcohols (id) to compare.
     * List id alcohols is saves in one string separated by special sign (separatorCookie)
     * @param id id alcohol add to compare
     * @param request request
     * @param response response
     * @return list all alcohol id selected to compare from new cookie
     */
    public List<String> addAlcoholToCompareList(int id, HttpServletRequest request, HttpServletResponse response) {
        return addAlcoholToCompareList.add(id, request, response);
    }
}
