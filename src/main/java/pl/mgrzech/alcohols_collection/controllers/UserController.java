package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.user.AddUser;
import pl.mgrzech.alcohols_collection.user.DeleteUser;
import pl.mgrzech.alcohols_collection.entities.User;
import pl.mgrzech.alcohols_collection.user.FindUser;
import pl.mgrzech.alcohols_collection.user.UserService;

import javax.validation.Valid;

/**
 * For all mapping in this class necessary is ADMIN role.
 */
@Controller
@AllArgsConstructor
public class UserController {

    private final AddUser addUser;
    private final FindUser findUser;
    private final DeleteUser deleteUser;

    private final UserService userService;

    /**
     * Method prepares view to add new user.
     * @param user new user
     * @return name of the running html file
     */
    @GetMapping("/admin/user/add")
    public String saveUserMethodGet(@ModelAttribute("user") User user){
        return "user/add_user";
    }

    /**
     * Method saves user after add new or edit user.
     * @param user new user
     * @param result validations user
     * @param redirectAttributes to add message after save user to database
     * @return name of the running html file if validate is not correct, redirect to admin view if save user is done
     */
    @PostMapping("/admin/user/add")
    public String saveUserMethodPost(@Valid @ModelAttribute("user") User user,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "user/add_user";
        }
        userService.addUser(user, redirectAttributes);
        return "redirect:/admin";
    }

    /**
     * Method returns and shows all users.
     * @param model model
     * @return name of the running html file
     */
    @RequestMapping("/admin/user/all")
    public String allUsers(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "user/all_users";
    }

    /**
     * Method prepares user to edit. Method finds user to edit by Id.
     * @param id user to edit
     * @param user user to edit
     * @param model model
     * @return name of the running html file
     */
    @GetMapping("/admin/user/edit/{id}")
    public String editSortTypeMethodGet(@PathVariable("id") int id,
                                        @ModelAttribute("user") User user,
                                        Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "user/add_user";
    }

    /**
     * Method deletes user by id.
     * @param id id user to delete
     * @param redirectAttributes to add message after delete user
     * @return redirect to admin view if delete user is done
     */
    @RequestMapping("/admin/user/delete/{id}")
    public String deleteSortType(@PathVariable("id") int id,
                                 RedirectAttributes redirectAttributes){
        userService.deleteUserById(id, redirectAttributes);
        return "redirect:/admin";
    }

    /**
     * Method shows login page.
     * @return name of the running html file to login
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Method shows admin page.
     * @return name of the running html file for admin
     */
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    /**
     * Method shows user page.
     * @return name of the running html file for user
     */
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
}
