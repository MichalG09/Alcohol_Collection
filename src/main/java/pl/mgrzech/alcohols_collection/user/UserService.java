package pl.mgrzech.alcohols_collection.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.User;

@Service
@AllArgsConstructor
public class UserService {

    private final AddUser addUser;
    private final FindUser findUser;
    private final DeleteUser deleteUser;

    /**
     * Method saves user.
     * @param user user to save
     * @param redirectAttributes redirectAttributes
     */
    public void addUser(User user, RedirectAttributes redirectAttributes) {
        addUser.save(user, redirectAttributes);
    }

    /**
     * Method returns all users.
     */
    public void findAllUser(Model model) {
        model.addAttribute("users", findUser.findAll());
    }

    /**
     * Method returns user by Id.
     * @param id id user to find
     */
    public void findUserById(Model model, int id) {
        model.addAttribute("user", findUser.findById(id));
    }

    /**
     * Method deletes user by Id.
     * @param id id user to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteUserById(int id, RedirectAttributes redirectAttributes) {
        deleteUser.deleteById(id, redirectAttributes);
    }
}
