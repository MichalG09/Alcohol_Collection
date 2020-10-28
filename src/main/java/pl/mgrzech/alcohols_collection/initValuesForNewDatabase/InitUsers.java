package pl.mgrzech.alcohols_collection.initValuesForNewDatabase;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.User;
import pl.mgrzech.alcohols_collection.repositories.UserRepository;

@Component
@AllArgsConstructor
public class InitUsers {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    /**
     * Method initializes all necessary users for start project.
     */
    public void initUsers() {
        if(!userRepository.findAll().iterator().hasNext()){
            initUser("admin","admin","ROLE_ADMIN");
            initUser("user","user","ROLE_USER");
        }
    }

    /**
     * Method initializes one user.
     * Method encode password.
     * If user exists, method don`t create it
     * @param nameUser name user
     * @param passwordUser password
     * @param role role or roles user
     */
    private void initUser(String nameUser, String passwordUser, String role){
        if (!userRepository.findByUsername(nameUser).isPresent()) {
            User user = new User();
            user.setUsername(nameUser);
            user.setPassword(passwordEncoder.encode(passwordUser));
            user.setActive(true);
            user.setRoles(role);
            userRepository.save(user);
        }
    }
}
