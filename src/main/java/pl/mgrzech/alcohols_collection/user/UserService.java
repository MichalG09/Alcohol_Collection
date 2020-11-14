package pl.mgrzech.alcohols_collection.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AddUser addUser;
    private final FindUser findUser;
    private final DeleteUser deleteUser;

    @Value("${message.correct.user.add}")
    private String messageCorrectAddUser;

    @Value("${message.fail.user.add}")
    private String messageFailAddUser;

    /**
     * Method saves user.
     * @param user user to save
     * @param redirectAttributes redirectAttributes
     */
    public void addUser(User user, RedirectAttributes redirectAttributes) {
        try{
            addUser.save(user);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddUser);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageCorrectAddUser);
            e.printStackTrace();
        }
    }

    /**
     * Method returns all users.
     * @return list all users
     */
    public Iterable<User> findAllUser() {
        return findUser.findAll();
    }

    /**
     * Method returns user by Id.
     * @param id id user to find
     * @return found user by id
     */
    public User findUserById(int id) {
        return findUser.findById(id);
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
