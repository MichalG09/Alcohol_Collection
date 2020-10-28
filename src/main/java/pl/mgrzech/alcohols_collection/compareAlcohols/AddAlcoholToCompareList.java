package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddAlcoholToCompareList {

    private final AddNewCookieWithListAlcoholToCompare addNewCookieWithListAlcoholToCompare;
    private final DeleteOldCookieWithAlcoholListToCompare deleteOldCookieWithAlcoholListToCompare;
    private final PrepareValueForNewCookieAfterAddAlcoholToCompare prepareValueForNewCookieWithAlcoholsToCompare;

    @Value("${name.cookie.alcohols.to.compare}")
    private String nameCookieWithAlcoholsToCompare;

    @Value("${separator.cookie}")
    private String separatorCookie;

    /**
     * Method adds id alcohol to cookie with list alcohols (id) to compare.
     * List id alcohols is saves in one string separated by special sign (separatorCookie)
     * @param id id alcohol add to compare
     * @param request request
     * @param response response
     * @return list all alcohol id selected to compare from new cookie
     */
    public List<String> add(int id, HttpServletRequest request, HttpServletResponse response) {
        String valuesForNewCookie = prepareValueForNewCookieWithAlcoholsToCompare.prepare(id, request);
        deleteOldCookieWithAlcoholListToCompare.delete(request, response);
        Cookie newCookie = addNewCookieWithListAlcoholToCompare.add(valuesForNewCookie, response);
        return getValueFromNewCookie(newCookie);
    }

    /**
     * Method returns list alcohols (id) selected to compare from cookie.
     * @param cookie cookie
     * @return list id alcohol select to compare
     */
    private List<String> getValueFromNewCookie(Cookie cookie){
        return Arrays.asList(cookie.getValue().split(separatorCookie));
    }
}
