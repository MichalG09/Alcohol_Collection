package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.property.FindProperty;
import pl.mgrzech.alcohols_collection.statistics.StatisticAlcohol;
import pl.mgrzech.alcohols_collection.statistics.StatisticsManufacturer;

@Controller
@AllArgsConstructor
public class StartPageController {

    private final FindProperty findProperty;
    private final StatisticAlcohol statisticAlcohol;
    private final StatisticsManufacturer statisticsManufacturer;

    /**
     * Method shows start page. Start page shows welcome text, and visitor can save to newsletter
     * @param newsletter newsletter to save new newsletter
     * @param model model
     * @return name of the running html file to start page
     */
    @GetMapping(value = {"", "/", "/start"})
    public String start(@ModelAttribute Newsletter newsletter,
                        Model model) {
        model.addAttribute("welcomeText", findProperty.findWelcomeTextFromProperty());
        model.addAttribute("numberAllAlcohols", statisticAlcohol.getStatisticForNumbersAlcoholsInCollection());
        model.addAttribute("numberAllManufacturers", statisticsManufacturer.getStatisticForNumbersManufacturersInCollection());
        return "index";
    }
}
