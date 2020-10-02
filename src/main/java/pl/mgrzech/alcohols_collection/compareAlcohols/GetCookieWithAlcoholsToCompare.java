package pl.mgrzech.alcohols_collection.compareAlcohols;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class GetCookieWithAlcoholsToCompare {

    @Value("${name.cookie.alcohols.to.compare}")
    private String nameCookieWithAlcoholsToCompare;

    /**
     * Method returns cookie with list alcohol (id) selected to compare.
     * @param request request
     * @return cookie witk list alcohol 9id) to compare
     */
    public Optional<Cookie> get(HttpServletRequest request) {
        return Optional.ofNullable(WebUtils.getCookie(request, nameCookieWithAlcoholsToCompare));
    }
}
