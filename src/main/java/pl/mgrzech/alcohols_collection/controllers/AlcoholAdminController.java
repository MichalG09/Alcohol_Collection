package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mgrzech.alcohols_collection.alcohol.*;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import javax.validation.Valid;
import java.util.List;

/**
 * For all mapping in this class necessary is role USER or ADMIN.
 */
@Controller
@RequestMapping("/user/alcohol")
@AllArgsConstructor
public class AlcoholAdminController {

    private final AlcoholService alcoholService;

    /**
     * Method prepares to add new alcohol.
     * @param alcohol new alcohol
     * @param manufacturer manufacturer for new alcohol
     * @param filesValidated pictures for new alcohol
     * @return name of the running html file for add alcohol
     */
    @GetMapping("/add")
    public String addNewAlcoholMethodGet(@ModelAttribute("alcohol") Alcohol alcohol,
                                         @ModelAttribute("manufacturer") Manufacturer manufacturer,
                                         @ModelAttribute("file") FilesValidated filesValidated){
        return "alcohol/add_alcohol";
    }

    /**
     * Method saves a edited or new alcohol.
     * Method validates inserted values. If not validated returns to view add alcohol.
     * @param alcohol edited or new alcohol
     * @param resultAlcohol validations alcohol
     * @param manufacturer manufacturer for edited or new alcohol
     * @param resultManufacturer validations manufacturer
     * @param filesValidated pictures for edited or new alcohol
     * @param resultFileValidated validations file
     * @param redirectAttributes redirect attributes
     * @return name of the running html file if validate is not correct, redirect to user view if save alcohol is done
     */
    @PostMapping("/add")
    public String addOrEditAlcoholMethodPost(@Valid @ModelAttribute("alcohol") Alcohol alcohol,
                                             BindingResult resultAlcohol,
                                             @Valid @ModelAttribute("manufacturer") Manufacturer manufacturer,
                                             BindingResult resultManufacturer,
                                             @Valid @ModelAttribute("file") FilesValidated filesValidated,
                                             BindingResult resultFileValidated,
                                             RedirectAttributes redirectAttributes){
        if (resultAlcohol.hasErrors() || resultManufacturer.hasErrors() ||
                (resultFileValidated.hasErrors() && (alcohol.getPictures() == null || alcohol.getPictures().isEmpty()))) {
            return "alcohol/add_alcohol";
        }
        alcoholService.editOrAddNewAlcoholWithManufacturer(alcohol, alcohol.getManufacturer(),
                manufacturer, filesValidated, redirectAttributes);
        return "redirect:/user";
    }

    /**
     * Method deletes alcohol by Id.
     * @param id id alcohol to delete
     * @param redirectAttributes redirect attributes
     * @return redirect to user view if delete alcohol is done
     */
    @GetMapping("/delete/{id}")
    public String deleteAlcohol(@PathVariable("id") int id,
                                RedirectAttributes redirectAttributes){
        alcoholService.deleteAlcoholById(id, redirectAttributes);
        return "redirect:/user";
    }

    /**
     * Method prepares chosen alcohol to edit.
     * @param id id alcohol to edit
     * @param model model
     * @return name of the running html file for edit alcohol
     */
    @GetMapping("/edit/{id}")
    public String editAlcoholMethodPost(@PathVariable("id") int id,
                                        @ModelAttribute("manufacturer") Manufacturer manufacturer,
                                        @ModelAttribute("file") FilesValidated filesValidated,
                                        Model model){
        Alcohol alcohol = alcoholService.findAlcoholById(id);
        model.addAttribute("alcohol", alcohol);
        model.addAttribute("manufacturer", alcohol.getManufacturer());
        return "alcohol/add_alcohol";
    }

    /**
     * Method returns all manufacturers transformed to JSONArray for javascript.
     * This list uses to autocomplete name manufacturer during add or edit alcohol.
     * After choose manufacturer name all other data (country, town, comment) manufacturer is auto update in form.
     * @return list all manufacturers in JSONArray
     */
    @ModelAttribute("allManufacturers")
    public JSONArray listAllManufacturers() {
        return alcoholService.findAllManufacturersInJSON();
    }

    /**
     * Method finds all types of alcohol in database.
     * This values is use to autocomplete field alcohol type for add or edit alcohol.
     * @return all type of alcohol in JSONArray
     */
    @ModelAttribute("listTypesOfAlcoholToAutoComplete")
    public JSONArray listTypesOfAlcoholToAutoCompleted() {
        return alcoholService.allListTypesAlcoholToAutoCompleted();
    }

    /**
     * Method returns a all places in storage.
     * This values is use to autocomplete field place in storage for add or edit alcohol.
     * @return all places in storage.
     */
    @ModelAttribute("placeInStorage")
    public List<String> listPlaceInStorage() {
        return alcoholService.findPlacesInStorageInProperty();
    }
}
