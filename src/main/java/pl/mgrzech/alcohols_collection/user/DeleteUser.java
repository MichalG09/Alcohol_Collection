package pl.mgrzech.alcohols_collection.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class DeleteUser {

    private final UserRepository userRepository;

    @Value("${message.correct.user.delete}")
    private String messageCorrectDeleteUser;

    @Value("${message.fail.user.delete}")
    private String messageFailDeleteUser;

    /**
     * Method deletes user by Id.
     * @param id id user to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteById(int id, RedirectAttributes redirectAttributes) {
        try {
            userRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", messageCorrectDeleteUser);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailDeleteUser);
        }
    }
}
