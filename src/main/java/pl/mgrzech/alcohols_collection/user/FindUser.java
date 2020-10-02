package pl.mgrzech.alcohols_collection.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.User;
import pl.mgrzech.alcohols_collection.repositories.UserRepository;

@Component
@AllArgsConstructor
public class FindUser {

    private final UserRepository userRepository;

    /**
     * Method returns all users.
     * @return list all users
     */
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Method returns user by Id.
     * @param id id user to find
     * @return found user
     */
    public User findById(int id) {
        return userRepository.findById(id).map(user -> {
            user.setPassword("");
            return user;})
                .orElseGet(User::new);
    }
}
