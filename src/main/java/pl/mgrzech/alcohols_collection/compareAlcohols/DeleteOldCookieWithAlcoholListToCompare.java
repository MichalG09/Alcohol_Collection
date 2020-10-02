package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class DeleteOldCookieWithAlcoholListToCompare {

    private final GetCookieWithAlcoholsToCompare getCookieWithAlcoholsToCompare;

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        getCookieWithAlcoholsToCompare.get(request).map(cookie -> {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return cookie;
        });
    }
}
