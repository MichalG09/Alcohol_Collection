package pl.mgrzech.alcohols_collection.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.User;
import pl.mgrzech.alcohols_collection.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class AddUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${message.correct.user.add}")
    private String messageCorrectAddUser;

    @Value("${message.fail.user.add}")
    private String messageFailAddUser;

    /**
     * Method saves user.
     * @param user user to save
     * @param redirectAttributes redirectAttributes
     */
    public void save(User user, RedirectAttributes redirectAttributes) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("message", messageCorrectAddUser);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageCorrectAddUser);
        }
    }
}
