package pl.mgrzech.alcohols_collection.manufacturer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final DeleteManufacturerById deleteManufacturerById;
    private final AddManufacturer addManufacturer;
    private final FindManufacturer findManufacturer;

    @Value("${message.correct.manufacturer.add}")
    private String messageCorrectAddedManufacturer;

    @Value("${message.correct.manufacturer.edit}")
    private String messageCorrectEditedManufacturer;

    @Value("${message.fail.manufacturer.add}")
    private String messageFailAddedManufacturer;

    @Value("${message.fail.manufacturer.edit}")
    private String messageFailEditedManufacturer;

    /**
     * Method saves edited or new manufacturer.
     * @param manufacturer edited or new manufacturer
     * @param redirectAttributes redirect attributes
     */
    public void addManufacturer(Manufacturer manufacturer, RedirectAttributes redirectAttributes) {
        boolean isNew =  manufacturer.getID() == null;
        try{
            addManufacturer.save(manufacturer);
            redirectAttributes.addFlashAttribute("message", isNew ? messageCorrectAddedManufacturer : messageCorrectEditedManufacturer);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", isNew ? messageFailAddedManufacturer : messageFailEditedManufacturer);
            e.printStackTrace();
        }
    }

    /**
     * Method finds manufacturer by Id.
     * @param id id manufacturer to search
     * @return found manufacturer by id
     */
    public Manufacturer findManufacturerById(int id) {
        return findManufacturer.findById(id);
    }

    /**
     * Method returns all manufacturers.
     * @return all manufacturers
     */
    public Iterable<Manufacturer> findAllManufacturers() {
        return findManufacturer.findAll();
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
