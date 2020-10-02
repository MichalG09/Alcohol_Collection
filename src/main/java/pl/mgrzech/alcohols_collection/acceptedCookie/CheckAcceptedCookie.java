package pl.mgrzech.alcohols_collection.acceptedCookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class CheckAcceptedCookie {

    @Value("${name.cookie.accepted}")
    private String nameCookieWithAccepted;

    /**
     * method checks if the user has accepted cookies.
     * @param request request
     * @return true if user accepted cookie, false if not.
     */
    public boolean check(HttpServletRequest request){
        return Optional.ofNullable(WebUtils.getCookie(request, nameCookieWithAccepted)).map(cookie -> {
            return cookie.getValue().equals("accepted");
        }).orElse(false);
    }
}
