package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PrepareValueForNewCookieAfterAddAlcoholToCompare {

    private final GetCookieWithAlcoholsToCompare getCookieWithAlcoholsToCompare;

    @Value("${separator.cookie}")
    private String separatorCookie;

    /**
     * Method prepares only three last added id to save in cookie (list alcohols to compare).
     * @param id id alcohol to add to list
     * @return last three added id alcohol to compare
     */
    public String prepare(int id, HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        return getCookieWithAlcoholsToCompare.get(request).map(cookie -> {
            String[] allIdFromCookie = cookie.getValue().split(separatorCookie);
            if(allIdFromCookie.length > 2){
                result.append(allIdFromCookie[allIdFromCookie.length - 1])
                        .append(separatorCookie)
                        .append(allIdFromCookie[allIdFromCookie.length - 2])
                        .append(separatorCookie);
            }
            else {
                result.append(cookie.getValue());
            }
            return result.toString() + id + separatorCookie;
        }).orElseGet(String::new);

//        Optional<Cookie> cookie = getCookieWithAlcoholsToCompare.get(request);
//        String result = "";
//        if(cookie.isPresent()){
//            String[] allIdFromCookie = cookie.get().getValue().split(separatorCookie);
//            if(allIdFromCookie.length > 2){
//                result = allIdFromCookie[allIdFromCookie.length - 1] + separatorCookie
//                        + allIdFromCookie[allIdFromCookie.length - 2] + separatorCookie;
//            }
//            else {
//                result = cookie.get().getValue();
//            }
//        }
//        return result + id + separatorCookie;
    }
}
