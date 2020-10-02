package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.initValuesForNewDatabase.InitValueForNewDatabaseService;

@Controller
@AllArgsConstructor
public class InitValuesForNewDatabaseController {

    private final InitValueForNewDatabaseService initValueForNewDatabaseService;

    /**
     * Method initializes all necessary values in database to correct start project.
     * @param redirectAttributes redirectAttributes
     * @return redirect to start page after initialize all values.
     */
    @RequestMapping("/init/first/run/database/init/all/necessary/positions")
    public String initAllNecessaryPositionInDatabase(RedirectAttributes redirectAttributes) {
        initValueForNewDatabaseService.initNecessaryPositionsInDatabase(redirectAttributes);
        return "redirect:/";
    }
}
