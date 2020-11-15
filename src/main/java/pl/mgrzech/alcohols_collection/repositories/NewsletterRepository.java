package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.mgrzech.alcohols_collection.entities.Newsletter;

import java.util.Optional;

public interface NewsletterRepository extends JpaRepository<Newsletter, Integer> {

    Optional<Newsletter> findByCodeToVerifyDelete(int code);
}
