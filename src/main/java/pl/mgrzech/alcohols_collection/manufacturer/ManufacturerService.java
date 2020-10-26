package pl.mgrzech.alcohols_collection.manufacturer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerService {

    private final DeleteManufacturerById deleteManufacturerById;
    private final AddManufacturer addManufacturer;
    private final FindManufacturer findManufacturer;

    /**
     * Method saves edited or new manufacturer.
     * @param manufacturer edited or new manufacturer
     * @param redirectAttributes redirect attributes
     */
    public void addManufacturer(Manufacturer manufacturer, RedirectAttributes redirectAttributes) {
        addManufacturer.save(manufacturer, redirectAttributes);
    }

    /**
     * Method finds manufacturer by Id.
     * @param id id manufacturer to search
     */
    public void findManufacturerById(Model model, int id) {
        model.addAttribute("manufacturer", findManufacturer.findById(id));
    }

    /**
     * Method returns all manufacturers.
     */
    public void findAllManufacturers(Model model) {
        model.addAttribute("manufacturers", findManufacturer.findAll());
    }

    /**
     * Method finds manufacturer by parameters.
     * @param manufacturerWithParamToSearch manufacturer with parameters to search
     * @return found list manufacturer
     */
    public List<Manufacturer> findManufacturerByParameters(Manufacturer manufacturerWithParamToSearch) {
        return findManufacturer.findManufacturerByParameters(manufacturerWithParamToSearch);
    }

    /**
     * Method deletes manufacturer by Id.
     * @param id id manufacturer to delete
     * @param redirectAttributes redirectAttributes
     */
    public void deleteManufacturerById(int id, RedirectAttributes redirectAttributes) {
        deleteManufacturerById.deleteManufacturerById(id, redirectAttributes);
    }
}
