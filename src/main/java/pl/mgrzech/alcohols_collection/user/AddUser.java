package pl.mgrzech.alcohols_collection.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.User;
import pl.mgrzech.alcohols_collection.repositories.UserRepository;

@Component
@AllArgsConstructor
public class AddUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Method saves user.
     * @param user user to save
     */
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
