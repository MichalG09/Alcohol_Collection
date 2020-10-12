package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.sortType.AddSortType;
import pl.mgrzech.alcohols_collection.sortType.DeleteSortType;
import pl.mgrzech.alcohols_collection.sortType.FindSortType;
import pl.mgrzech.alcohols_collection.sortType.SortTypeService;

import javax.validation.Valid;

/**
 * For all mapping in this class necessary is ADMIN role.
 */
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class SortTypeController {

    private final SortTypeService sortTypeService;

    /**
     * Method prepares view to add new sort type.
     * @param sortType new sort type
     * @return name of the running html file
     */
    @GetMapping("/sortType/add")
    public String saveSortTypeMethodGet(@ModelAttribute("sortType") SortType sortType){
        return "sortType/add_sort_type";
    }

    /**
     * Method saves sort type after add new or edit sort type.
     * Method validates values from form.
     * @param sortType new sort type
     * @param result validations sort type
     * @param redirectAttributes to add message after save sort type to database
     * @return name of the running html file if validate is not correct, redirect to admin view if save sort type is done
     */
    @PostMapping("/sortType/add")
    public String saveSortTypeMethodPost(@Valid @ModelAttribute("sortType") SortType sortType,
                                         BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "sortType/add_sort_type";
        }
        sortTypeService.saveSortType(sortType, redirectAttributes);
        return "redirect:/admin";
    }

    /**
     * Method returns and shows all sort type.
     * @param model model
     * @return name of the running html file
     */
    @GetMapping("/sortType/all")
    public String allSortType(Model model){
        model.addAttribute("sortTypes", sortTypeService.findAllSortType());
        return "sortType/all_sort_type";
    }

    /**
     * Method prepares sort type to edit.
     * @param id property id to edit
     * @param sortType sort type to edit
     * @param model model
     * @return name of the running html file
     */
    @GetMapping("/sortType/edit/{id}")
    public String editSortTypeMethodGet(@PathVariable("id") int id,
                                        @ModelAttribute("sortType") SortType sortType,
                                        Model model){
        model.addAttribute("sortType", sortTypeService.findSortTypeById(id));
        return "sortType/add_sort_type";
    }

    /**
     * Method deletes sort type by id.
     * @param id id sort type to delete
     * @param redirectAttributes to add message after correct delete sort type
     * @return redirect to admin view if delete sort type is done
     */
    @GetMapping("/sortType/delete/{id}")
    public String deleteSortType(@PathVariable("id") int id,
                                 RedirectAttributes redirectAttributes){
        sortTypeService.deleteSortTypeById(id, redirectAttributes);
        return "redirect:/admin";
    }
}
