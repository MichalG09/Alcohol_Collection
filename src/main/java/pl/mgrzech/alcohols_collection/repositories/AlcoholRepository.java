package pl.mgrzech.alcohols_collection.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Newsletter;

import java.util.List;
import java.util.Optional;

public interface AlcoholRepository extends JpaRepository<Alcohol, Integer> {

    @Query("SELECT DISTINCT a.type FROM Alcohol a")
    List<String> findDistinctTypes();

    @Query(value = "SELECT * FROM alcohol a INNER JOIN manufacturer m ON m.id = a.manufacturer_id " +
            "WHERE name LIKE %?1% and " +
            "type LIKE %?2% and " +
            "amount_of_alcohol BETWEEN ?3 AND ?4 and " +
            "capacity BETWEEN ?5 AND ?6 and " +
            "place_in_storage LIKE %?7% and " +
            "m.name_company LIKE %?8%",
            nativeQuery = true)
    Page<Alcohol> searchAlcohols(String name, String type, int amountAlcoholMin, int amountAlcoholMax,
                                 int capacityMin, int capacityMax, String placeInStorage, String manufacturerName, PageRequest pageRequest);

    @Query(value = "SELECT * FROM alcohol a INNER JOIN manufacturer m ON m.id = a.manufacturer_id " +
            "WHERE name LIKE %?1% and " +
            "type LIKE %?2% and " +
            "amount_of_alcohol BETWEEN ?3 AND ?4 and " +
            "capacity BETWEEN ?5 AND ?6 and " +
            "place_in_storage LIKE %?7% and " +
            "m.name_company LIKE %?8% " +
            "ORDER BY ?9 ?10",
            nativeQuery = true)
    Page<Alcohol> searchAlcoholsOrderByManufacturer(String name, String type, int amountOfAlcoholMin,
                                                    int amountOfAlcoholMax, int capacityMin, int capacityMax, String placeInStorage,
                                                    String nameCompany, String sortBy, String sortType, PageRequest pageRequest);

    int countByPlaceInStorage(String placeInStorage);

    @Query("SELECT COUNT (DISTINCT a.type) FROM Alcohol a")
    int countDistinctType();

    @Query("SELECT SUM (a.capacity) FROM Alcohol a")
    int sumAllCapacity();
}
