package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.mgrzech.alcohols_collection.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
//public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
