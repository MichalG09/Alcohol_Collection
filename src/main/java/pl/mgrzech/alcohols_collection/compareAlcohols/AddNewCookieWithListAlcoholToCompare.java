package pl.mgrzech.alcohols_collection.compareAlcohols;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class AddNewCookieWithListAlcoholToCompare {

    @Value("${name.cookie.alcohols.to.compare}")
    private String nameCookieWithAlcoholsToCompare;

    /**
     * Method creates cookie with list alcohols (id) selected to compare.
     * @param alcoholsId all alcohols id to save in cookie
     * @param response response
     * @return created cookie
     */
    public Cookie add(String alcoholsId, HttpServletResponse response) {
        Cookie cookie = new Cookie(nameCookieWithAlcoholsToCompare, alcoholsId);
        cookie.setMaxAge(2 * 60 * 60);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }
}
