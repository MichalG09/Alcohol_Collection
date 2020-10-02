package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Property;
import pl.mgrzech.alcohols_collection.property.PropertyService;

import javax.validation.Valid;

/**
 * User have access to WelcomeText property (can edit and save).
 * For other mapping only ADMIN role have access.
 */
@Controller
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    /**
     * Method prepares view to add new property.
     * @param property new property
     * @return name of the running html file
     */
    @GetMapping("/admin/property/add")
    public String savePropertyMethodGet(@ModelAttribute("property") Property property){
        return "property/add_property";
    }

    /**
     * Method saves property after add new or edit property.
     * @param property new property
     * @param result validations property
     * @param redirectAttributes to add message after save property to database
     * @return name of the running html file if validate is not correct, redirect to admin view if save property is done
     */
    @PostMapping("/admin/property/add")
    public String savePropertyMethodPost(@Valid @ModelAttribute("property") Property property,
                                         BindingResult result,
                                         RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "property/add_property";
        }
        propertyService.saveProperty(property, redirectAttributes);
        return "redirect:/admin";
    }

    /**
     * Method returns and shows all properties.
     * @param model model
     * @return name of the running html file
     */
    @RequestMapping("/admin/property/all")
    public String allProperty(Model model){
        model.addAttribute("properties", propertyService.findAllProperties());
        return "property/all_properties";
    }

    /**
     * Method prepares property to edit.
     * @param id id property to edit
     * @param property property to edit, loaded from database
     * @param model model
     * @return name of the running html file
     */
    @GetMapping("/admin/property/edit/{id}")
    public String editPropertyMethodGet(@PathVariable("id") int id,
                                        @ModelAttribute("property") Property property,
                                        Model model){
        model.addAttribute("property", propertyService.findPropertyById(id));
        return "property/add_property";
    }

    /**
     * Method deletes property by id.
     * @param id id property to delete
     * @param redirectAttributes to add message after correct delete property
     * @return redirect to admin view if delete property is done
     */
    @RequestMapping("/admin/property/delete/{id}")
    public String deleteProperty(@PathVariable("id") int id,
                                 RedirectAttributes redirectAttributes){
        propertyService.deletePropertyById(id, redirectAttributes);
        return "redirect:/admin";
    }
    /**
     * Method prepares welcome text to edit.
     * Welcome text is saved in properties (name: welcomeText).
     * @param model model
     * @return name of the running html file with form to change welcome text
     */
    @GetMapping("/user/welcomeEdit")
    public String editWelcomeTextMethodGet(Model model){
        model.addAttribute("welcomeText", propertyService.getPropertyWithWelcomeText());
        return "property/edit_welcome_text";
    }

    /**
     * Method saves edited welcome text.
     * @param property property with welcome text
     * @param result validations property with welcome text
     * @param redirectAttributes redirect attributes
     * @return redirect to main view after edit welcome text
     */
    @PostMapping("/user/welcomeEdit")
    public String editWelcomeTextMethodPost(@Valid @ModelAttribute("welcomeText") Property property,
                                            BindingResult result,
                                            RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "welcomeText/edit_welcome_text";
        }
        propertyService.saveProperty(property, redirectAttributes);
        return "redirect:/";
    }
}
