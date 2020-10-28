package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetListAlcoholsIdToCompare {

    private final GetCookieWithAlcoholsToCompare getCookieWithAlcoholsToCompare;

    @Value("${separator.cookie}")
    private String separatorCookie;

    /**
     * Method returns all id alcohols from list alcohol to compare (cookie).
     * @param request request
     * @return list alcohols to compare
     */
    public List<String> getListAlcoholsToCompare(HttpServletRequest request) {
        return getCookieWithAlcoholsToCompare.get(request)
                .map(cookie -> Arrays.asList(cookie.getValue().split(separatorCookie)))
                .orElseGet(ArrayList::new);
    }
}
