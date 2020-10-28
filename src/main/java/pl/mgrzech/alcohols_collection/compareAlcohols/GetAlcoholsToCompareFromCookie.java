package pl.mgrzech.alcohols_collection.compareAlcohols;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlcoholsToCompareFromCookie {

    private final GetCookieWithAlcoholsToCompare getCookieWithAlcoholsToCompare;
    private final AlcoholRepository alcoholRepository;

    @Value("${separator.cookie}")
    private String separatorCookie;

    /**
     * Method returns list alcohol to compare in view.
     * Method searches alcohol by alcohols id list in cookie.
     * @param request request
     * @return list alcohol to compare
     */
    public List<Alcohol> get(HttpServletRequest request) {
        return getCookieWithAlcoholsToCompare.get(request)
                .map(cookie -> (List<Alcohol>) alcoholRepository
                        .findAllById(Arrays.stream(cookie
                                .getValue()
                                .split(separatorCookie))
                                .mapToInt(Integer::parseInt)
                                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)))
                .orElseGet(ArrayList::new);
    }
}
