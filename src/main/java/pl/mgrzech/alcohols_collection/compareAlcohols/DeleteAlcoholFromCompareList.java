package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Alcohol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteAlcoholFromCompareList {

    private final AddNewCookieWithListAlcoholToCompare addNewCookieWithListAlcoholToCompare;
    private final DeleteOldCookieWithAlcoholListToCompare deleteOldCookieWithAlcoholListToCompare;
    private final PrepareValueForNewCookieAfterDeleteAlcoholToCompare prepareValueForNewCookieAfterDeleteAlcoholToCompare;
    private final GetAlcoholsToCompareFromCookie getAlcoholsToCompareFromCookie;

    @Value("${name.cookie.alcohols.to.compare}")
    private String nameCookieWithAlcoholsToCompare;

    @Value("${separator.cookie}")
    private String separatorCookie;

    /**
     * Method deletes alcohol (id) from list alcohols selected to compare.
     * @param id id alcohol to delete from compare list
     * @param request request
     * @param response response
     * @return list alcohols to compare (without deleted position).
     */
    public List<Alcohol> delete(int id, HttpServletRequest request, HttpServletResponse response) {
        String valuesForNewCookie = prepareValueForNewCookieAfterDeleteAlcoholToCompare.prepare(id, request);
        deleteOldCookieWithAlcoholListToCompare.delete(request, response);
        addNewCookieWithListAlcoholToCompare.add(valuesForNewCookie, response);
        return getAlcoholsToCompareFromCookie.get(request);
    }
}
