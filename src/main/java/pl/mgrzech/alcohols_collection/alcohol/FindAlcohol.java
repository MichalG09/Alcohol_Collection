
package pl.mgrzech.alcohols_collection.alcohol;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.alcohol.model.AlcoholToSearch;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;
import pl.mgrzech.alcohols_collection.picture.SortPicture;
import pl.mgrzech.alcohols_collection.property.FindProperty;

@Component
@AllArgsConstructor
public class FindAlcohol {

    private final AlcoholRepository alcoholRepository;
    private final FindProperty findProperty;
    private final SortPicture sortPicture;

    /**
     * Method returns alcohol by id
     * @param id alcohol id to find
     * @return found alcohol
     */
    public Alcohol findById(int id) {
        return sortPicture.sortPictureInAlcoholByIsMainPicture(alcoholRepository.findById(id).orElseGet(Alcohol::new));
    }

    /**
     * Method returns all alcohols from database for first page view with all collection.
     * Default settings: page number 0 (first page), 5 position in one page, sort by name alcohol ASC.
     * @return list of all alcohols
     */
    public Page<Alcohol> findAllAlcoholsForFirstPage() {
        return alcoholRepository.findAll(
                PageRequest.of(0,
                        Integer.parseInt(findProperty.findByName("startNumberAlcoholsInOnePage").get(0)),
                        Sort.by("name").ascending()));
    }

    /**
     * Method returns page with alcohols after search with parameter from form.
     * @param alcoholToSearch alcohol with parameters to search
     * @param page number page to show
     * @param sortBy sort result list by
     * @param numberAlcoholsInPage parameter number alcohol in one page
     * @return list alcohols meet the conditions
     */
    public Page<Alcohol> getSearchingAlcohols(AlcoholToSearch alcoholToSearch, int page, String sortBy, int numberAlcoholsInPage) {
        Page<Alcohol> resultSearchedAlcohols;
        String sortByValue = sortBy.substring(0, sortBy.length() - 3);

        if(!sortBy.contains("manufacturer")){
            resultSearchedAlcohols = alcoholRepository.searchAlcohols(alcoholToSearch.getName(),
                    alcoholToSearch.getType(),
                    alcoholToSearch.getAmountOfAlcoholMin(),
                    alcoholToSearch.getAmountOfAlcoholMax(),
                    alcoholToSearch.getCapacityMin(),
                    alcoholToSearch.getCapacityMax(),
                    alcoholToSearch.getPlaceInStorage(),
                    alcoholToSearch.getManufacturer().getNameCompany(),
                    sortSearchedAlcoholsByManufacturer(page, sortBy, numberAlcoholsInPage, sortByValue));
        }
        else {
            String sortType = "DESC";
            if(sortBy.contains("A-Z") || sortBy.contains("0-9")){
                sortType = "ASC";
            }
            resultSearchedAlcohols = alcoholRepository.searchAlcoholsOrderByManufacturer(alcoholToSearch.getName(),
                    alcoholToSearch.getType(),
                    alcoholToSearch.getAmountOfAlcoholMin(),
                    alcoholToSearch.getAmountOfAlcoholMax(),
                    alcoholToSearch.getCapacityMin(),
                    alcoholToSearch.getCapacityMax(),
                    alcoholToSearch.getPlaceInStorage(),
                    alcoholToSearch.getManufacturer().getNameCompany(),
                    sortBy,
                    sortType,
                    PageRequest.of(page - 1, numberAlcoholsInPage));
        }
        return resultSearchedAlcohols;
    }

    private PageRequest sortSearchedAlcoholsByManufacturer(int page, String sortBy,
                                                           int numberAlcoholsInPage,
                                                           String sortByValue){
        if(sortBy.contains("A-Z") || sortBy.contains("0-9")){
            return PageRequest.of(page - 1, numberAlcoholsInPage, Sort.by(sortByValue).ascending());
        }
        return PageRequest.of(page - 1, numberAlcoholsInPage, Sort.by(sortByValue).descending());
    }
}
