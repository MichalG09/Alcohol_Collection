package pl.mgrzech.alcohols_collection.alcohol;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.repositories.AlcoholRepository;

@Component
@AllArgsConstructor
public class DeleteAlcoholById {

    private final AlcoholRepository alcoholRepository;

    /**
     * Method deletes alcohol by id.
     * @param id id alcohol to delete
     */
    public void deleteById(int id) {
        if(alcoholRepository.findById(id).isPresent()){
            alcoholRepository.deleteById(id);
        }
    }
}
