package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class PrepareValueForNewCookieAfterDeleteAlcoholToCompare {

    private final GetCookieWithAlcoholsToCompare getCookieWithAlcoholsToCompare;

    @Value("${separator.cookie}")
    private String separatorCookie;

    /**
     * Method prepares list alcohols (id) to compare after
     * delete selected alcohol (id) from compare.
     * @param id id alcohol to add to list
     * @return last three added id alcohol to compare
     */
    public String prepare(int id, HttpServletRequest request) {
        return getCookieWithAlcoholsToCompare.get(request)
                .map(cookie -> cookie.getValue().replace(id + separatorCookie, ""))
                .orElseGet(() -> id + separatorCookie);
    }
}
