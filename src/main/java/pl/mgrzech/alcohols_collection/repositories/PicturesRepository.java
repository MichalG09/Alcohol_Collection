package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.mgrzech.alcohols_collection.entities.Picture;

import java.util.List;
import java.util.Optional;

public interface PicturesRepository extends JpaRepository<Picture, Integer> {
//public interface PicturesRepository extends CrudRepository<Picture, Integer> {

    Optional<Picture> findByName(String name);

    List<Picture> findByGallery(Boolean isGallery);

    Optional<Picture> findByNameIsContaining(String uniqueNumberToName);

    @Query(value = "SELECT * FROM picture p INNER JOIN alcohol_pictures a " +
            "ON p.id = a.pictures_id WHERE a.alcohol_id = ?1 and p.main_picture = 1",
            nativeQuery = true)
    Optional<Picture> findMainPictureForAlcohol(int idAlcohol);
}
