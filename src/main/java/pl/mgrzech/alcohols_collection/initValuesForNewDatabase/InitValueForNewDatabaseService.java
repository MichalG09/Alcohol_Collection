package pl.mgrzech.alcohols_collection.initValuesForNewDatabase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@RequiredArgsConstructor
public class InitValueForNewDatabaseService {

    @Value("${message.correct.database.init}")
    private String messageCorrectInitDatabase;

    @Value("${message.fail.database.init}")
    private String messageFailInitDatabase;

    private final InitUsers initUsers;
    private final InitSortTypes initSortTypes;
    private final InitProperties initProperties;

    /**
     * Method initializes all necessary values (users, properties and sort types)
     * in new database to correct start project.
     * @param redirectAttributes redirectAttributes
     */
    public void initNecessaryPositionsInDatabase(RedirectAttributes redirectAttributes) {
        try{
            initUsers.initUsers();
            initSortTypes.initSortTypes();
            initProperties.initProperties();
            redirectAttributes.addFlashAttribute("message", messageCorrectInitDatabase);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageCorrectInitDatabase);
        }
    }
}
