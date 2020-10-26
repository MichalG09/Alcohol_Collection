package pl.mgrzech.alcohols_collection.manufacturer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Manufacturer;
import pl.mgrzech.alcohols_collection.repositories.ManufacturerRepository;

import java.util.Date;

@Component("SaveManufacturerImpl")
@RequiredArgsConstructor
public class AddManufacturer {

    private final ManufacturerRepository manufacturerRepository;

    @Value("${message.correct.manufacturer.add}")
    private String messageCorrectAddedManufacturer;

    @Value("${message.correct.manufacturer.edit}")
    private String messageCorrectEditedManufacturer;

    @Value("${message.fail.manufacturer.add}")
    private String messageFailAddedManufacturer;

    @Value("${message.fail.manufacturer.edit}")
    private String messageFailEditedManufacturer;

    /**
     * Method saves manufacturer after added new or edited.
     * Method add manufacturer from form only for create manufacturer.
     * @param manufacturer manufacturer to save
     * @param redirectAttributes redirectAttributes
     */
    public void save(Manufacturer manufacturer, RedirectAttributes redirectAttributes) {
        boolean isNew =  manufacturer.getID() == null;
        try{
            redirectAttributes.addFlashAttribute("message", isNew ? messageCorrectAddedManufacturer : messageCorrectEditedManufacturer);
            Date nowDate = new Date();
            if(manufacturer.getID() == null){
                manufacturer.setCreatedDate(nowDate);
            }
            manufacturer.setLastChangeDate(nowDate);
            manufacturerRepository.save(manufacturer);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", isNew ? messageFailAddedManufacturer : messageFailEditedManufacturer);
            e.printStackTrace();
        }
    }

    /**
     * Method saves manufacturer during add new or edit alcohol.
     * If alcohol is edited and change manufacturer, method deletes alcohol from
     * list alcohol for old manufacturer and add alcohol to list alcohols new manufacturer.
     * If new manufacturer id is equal null, manufacturer is new.
     * @param newManufacturer ne manufacturer
     * @param oldManufacturer old manufacturer
     * @param alcohol alcohol
     * @param nowDate current date
     * @return manufacturer to save for edit or add new alcohol
     */
    public Manufacturer saveAfterAddOrEditAlcohol(Manufacturer newManufacturer, Manufacturer oldManufacturer, Alcohol alcohol, Date nowDate) {
        if(oldManufacturer.getID() != null){
            if(!oldManufacturer.getID().equals(newManufacturer.getID())){
                oldManufacturer = manufacturerRepository.findById(oldManufacturer.getID()).get();
                if(!oldManufacturer.getAlcohols().isEmpty()){
                    oldManufacturer.getAlcohols().remove(alcohol);
                }
                oldManufacturer.setLastChangeDate(nowDate);
                manufacturerRepository.save(oldManufacturer);
            }
        }
        if(newManufacturer.getCreatedDate() == null){
            newManufacturer.setCreatedDate(nowDate);
        }
        newManufacturer.setLastChangeDate(nowDate);
        return newManufacturer;
    }
}
