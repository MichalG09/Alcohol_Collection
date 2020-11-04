package pl.mgrzech.alcohols_collection.alcohol;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.acceptedCookie.CheckAcceptedCookie;
import pl.mgrzech.alcohols_collection.alcohol.model.AlcoholToSearch;
import pl.mgrzech.alcohols_collection.compareAlcohols.GetListAlcoholsIdToCompare;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.manufacturer.FindAllManufacturersInJSON;
import pl.mgrzech.alcohols_collection.picture.FindPicture;
import pl.mgrzech.alcohols_collection.property.FindProperty;
import pl.mgrzech.alcohols_collection.sortType.FindSortType;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@AllArgsConstructor
public class AlcoholService {

    private final FindSortType findSortType;
    private final FindProperty findProperty;
    private final GetListAlcoholsIdToCompare getListAlcoholsIdToCompare;
    private final FindAlcohol findAlcohol;
    private final CheckAcceptedCookie checkAcceptedCookie;
    private final FindAllManufacturersInJSON findAllManufacturersInJSON;
    private final FindListTypesAlcoholToAutoCompleted findListTypesAlcoholToAutoCompleted;
    private final AddAlcohol addAlcohol;
    private final DeleteAlcoholById deleteAlcoholById;
    private final FindPicture findPicture;

    /**
     * Method returns all alcohols for first page collection.
     */
    public void findAllAlcoholsForFirstPage(Model model, HttpServletRequest request){
        model.addAttribute("alcoholToSearch", new AlcoholToSearch());
        model.addAttribute("alcohols", findAlcohol.findAllAlcoholsForFirstPage());
        model.addAttribute("sortBy", "");
        model.addAttribute("numberAlcoholInOnePage", "");
        model.addAttribute("listAlcoholsToCompare", getListAlcoholsToCompare(request));
    }

    /**
     * Method returns all id alcohols from list alcohol to compare.
     * @param request request
     * @return list alchols id to compare
     */
    public List<String> getListAlcoholsToCompare(HttpServletRequest request){
        return getListAlcoholsIdToCompare.getListAlcoholsToCompare(request);
    }

    /**
     * Method returns alcohol by id
     * @param id alcohol id to find
     */
    public void findAlcoholByIdToShowAllDetail(Model model, int id){
        Alcohol alcohol = findAlcoholById(model, id);
        model.addAttribute("manufacturer", alcohol.getManufacturer());
        model.addAttribute("file", new FilesValidated());
    }

    /**
     * Method returns alcohol by id
     * @param id alcohol id to find
     */
    public Alcohol findAlcoholById(Model model, int id){
        Alcohol alcohol = findAlcohol.findById(id);
        model.addAttribute("alcohol", alcohol);
        return alcohol;
    }

    /**
     * Method returns page with found alcohols with parameter from form.
     * @param alcoholToSearch alcohol with parameters to search
     * @param page number page to show
     * @param sortBy sort result list by
     * @param numberAlcoholInOnePage parameter number alcohol in one page
     */
    public void findSearchingAlcohols(Model model, HttpServletRequest request,
                                      AlcoholToSearch alcoholToSearch, int page,
                                      String sortBy, int numberAlcoholInOnePage) {
        prepareInformationAfterSearchAlcohol(model, alcoholToSearch, page, sortBy, numberAlcoholInOnePage);
        model.addAttribute("listAlcoholsToCompare", getListAlcoholsToCompare(request));
    }

    /**
     * Method prepares model to show information after search alcohols.
     * @param model model
     * @param alcoholToSearch alcohol with parameters to search
     * @param page page
     * @param sortBy type sort
     * @param numberAlcoholInOnePage number alcohol in one page
     */
    public void prepareInformationAfterSearchAlcohol(Model model, AlcoholToSearch alcoholToSearch,
                                                     int page, String sortBy, int numberAlcoholInOnePage ){
        model.addAttribute("alcoholToSearch", alcoholToSearch);
        model.addAttribute("alcohols", findAlcohol.getSearchingAlcohols(alcoholToSearch, page,
                sortBy, numberAlcoholInOnePage));
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("numberAlcoholInOnePage", numberAlcoholInOnePage);
    }

    /**
     * Method returns all sort type sorted in list by value.
     * List is necessary to select type sort in form.
     * @return all sort type in list
     */
    public Iterable<SortType> allTypesSort() {
        return findSortType.allSortedByValueTypesSort();
    }

    /**
     * Method returns values of number alcohol in one page from properties.
     * All values are show in selector in form.
     * @return values number alcohol in one page
     */
    public List<String> allNumbersAlcoholsInOnePage() {
        return findProperty.findByNameAndGetValuesInList("valuesNumberAlcoholInOnePage");
    }

    /**
     * Method checks if the user has accepted cookies.
     * Cookies are necessary to save id alcohols to compare.
     * @param request request
     * @return true if user accepted cookie, false if not.
     */
    public boolean checkAcceptedCookieInWebsite(HttpServletRequest request) {
        return checkAcceptedCookie.check(request);
    }

    /**
     * Methods saves alcohol after add new or edit.
     * User choose manufacturer from list all manufacturer in form (autocompleted).
     * @param alcohol new alcohol
     * @param oldManufacturer manufacturer from alcohol
     * @param newManufacturer manufacturer from form to edited or add alcohol
     * @param filesValidated pictures for new or edited alcohol
     * @param redirectAttributes redirectAttributes
     */
    public void editOrAddNewAlcoholWithManufacturer(Alcohol alcohol,
                                                    Manufacturer newManufacturer,
                                                    Manufacturer oldManufacturer,
                                                    FilesValidated filesValidated,
                                                    RedirectAttributes redirectAttributes) {
        addAlcohol.editOrAddNewAlcoholWithManufacturer(alcohol, newManufacturer, oldManufacturer, filesValidated, redirectAttributes);
    }

    /**
     * Method deletes alcohol by id.
     * @param id id alcohol to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteAlcoholById(int id, RedirectAttributes redirectAttributes) {
        deleteAlcoholById.deleteById(id, redirectAttributes);
    }

    /**
     * Method returns all types (distinct) of alcohol in database
     * and add basic types from property if this values don`t be in list.
     * @return all type of alcohol in JSONArray
     */
    public JSONArray allListTypesAlcoholToAutoCompleted() {
        return findListTypesAlcoholToAutoCompleted.find();
    }

    /**
     * Method returns all manufacturers and transform to JSONArray for javascript.
     * @return list all manufacturers in JSONArray
     */
    public JSONArray findAllManufacturersInJSON() {
        return findAllManufacturersInJSON.findAllManufacturersInJSON();
    }

    /**
     * Method returns a all places in storage.
     * @return list all places in storage.
     */
    public List<String> findPlacesInStorageInProperty() {
        return findProperty.findByNameAndGetValuesInList("placeInStorage");
    }

    /**
     * Method returns picture by id.
     * @param idPicture picture id to find
     */
    public void findPicture(Model model, int idPicture, int idAlcohol) {
        model.addAttribute("picture", findPicture.findById(idPicture));
        model.addAttribute("idAlcohol", idAlcohol);
    }
}

