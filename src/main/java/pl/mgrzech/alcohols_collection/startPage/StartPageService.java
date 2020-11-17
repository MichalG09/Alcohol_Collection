package pl.mgrzech.alcohols_collection.startPage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.mgrzech.alcohols_collection.property.FindProperty;
import pl.mgrzech.alcohols_collection.statistics.StatisticAlcohol;
import pl.mgrzech.alcohols_collection.statistics.StatisticsManufacturer;

@Component
@AllArgsConstructor
public class StartPageService {

    private final FindProperty findProperty;
    private final StatisticAlcohol statisticAlcohol;
    private final StatisticsManufacturer statisticsManufacturer;

    /**
     * Method adds information for start page.
     * Start page shows welcome text, and visitor can save to newsletter
     * @param model model
     */
    public void startPage(Model model){
        model.addAttribute("welcomeText", findProperty.findWelcomeTextFromProperty());
        model.addAttribute("numberAllAlcohols", statisticAlcohol.getNumberAllAlcoholsInCollection());
        model.addAttribute("numberAllManufacturers", statisticsManufacturer.getNumberAllManufacturersInCollection());
        model.addAttribute("numberAllAlcoholTypes", statisticAlcohol.getNumberDistinctAlcoholType());
        model.addAttribute("numberAllCapacity", statisticAlcohol.getSumCapacityAllAlcohols());
    }
}
