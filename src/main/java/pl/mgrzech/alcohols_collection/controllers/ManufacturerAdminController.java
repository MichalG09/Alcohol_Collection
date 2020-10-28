package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.manufacturer.*;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/manufacturer/")
@AllArgsConstructor
public class ManufacturerAdminController {

    private final ManufacturerService manufacturerService;

    /**
     * Method prepares to add new manufacturer.
     * @param manufacturer new manufacturer
     * @return name of the running html file for add manufacturer
     */
    @GetMapping("add")
    public String addNewManufacturerMethodGet(@ModelAttribute("manufacturer") Manufacturer manufacturer){
        return "manufacturer/add_manufacturer";
    }

    /**
     * Method saves edited or new manufacturer.
     * @param manufacturer edited or new manufacturer
     * @param resultManufacturer validations manufacturer
     * @param redirectAttributes redirect attributes
     * @return name of the running html file if validate is not correct, redirect to user view if save manufacturer is done
     */
    @PostMapping("add")
    public String addNewManufacturer(@Valid @ModelAttribute("manufacturer") Manufacturer manufacturer,
                                         BindingResult resultManufacturer,
                                         RedirectAttributes redirectAttributes){
        if(resultManufacturer.hasErrors()){
            return "manufacturer/add_manufacturer";
        }
        manufacturerService.addManufacturer(manufacturer, redirectAttributes);
        return "redirect:/user";
    }

    /**
     * Methods prepares chosen manufacturer to edit.
     * @param id id manufacturer to edit
     * @param manufacturer manufacturer to edit, loaded from database
     * @param model model
     * @return name of the running html file for edit manufacturer
     */
    @GetMapping("edit/{id}")
    public String editManufacturer(@PathVariable("id") int id,
                                      @ModelAttribute("manufacturer") Manufacturer manufacturer,
                                      Model model){
        manufacturerService.findManufacturerById(model, id);
        return "manufacturer/add_manufacturer";
    }

    /**
     * Method returns all manufacturers.
     * @param model model
     * @return name of the running html file for show all manufacturers
     */
    @GetMapping("all")
    public String allManufacturers(@ModelAttribute("manufacturer") Manufacturer manufacturer,
                                   Model model){
        manufacturerService.findAllManufacturers(model);
        return "manufacturer/all_manufacturers";
    }

    /**
     * Method returns all manufacturers after search.
     * @param model model
     * @param manufacturerWithParamToSearch manufacturerWithParamToSearch with parameter to search
     * @return name of the running html file for show all found manufacturers
     */
    @PostMapping("search")
    public String allFoundManufacturers(@ModelAttribute("manufacturer") Manufacturer manufacturerWithParamToSearch,
                                        Model model){
        model.addAttribute("manufacturers", manufacturerService.findManufacturerByParameters(manufacturerWithParamToSearch));
        return "manufacturer/all_manufacturers";
    }

    /**
     * Method deletes manufacturer by id.
     * @param id id manufacturer to delete
     * @param redirectAttributes redirect attributes
     * @return redirect to user view if delete manufacturer is done
     */
    @GetMapping("delete/{id}")
    public String deleteManufacturer(@PathVariable("id") int id,
                                        RedirectAttributes redirectAttributes){
        manufacturerService.deleteManufacturerById(id, redirectAttributes);
        return "redirect:/user";
    }
}