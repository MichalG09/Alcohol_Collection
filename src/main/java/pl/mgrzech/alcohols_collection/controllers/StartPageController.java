package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.startPage.StartPageService;

@Controller
@AllArgsConstructor
public class StartPageController {

    private final StartPageService startPageService;

    /**
     * Method shows start page. Start page shows welcome text, and visitor can save to newsletter
     * @param newsletter newsletter to save new newsletter
     * @param model model
     * @return name of the running html file to start page
     */
    @GetMapping(value = {"", "/", "/start"})
    public String start(@ModelAttribute Newsletter newsletter,
                        Model model) {
        startPageService.startPage(model);
        return "index";
    }
}
