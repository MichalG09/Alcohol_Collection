package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.picture.PictureService;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    /**
     * Method deletes chosen picture for edited alcohol.
     * @param alcoholId id alcohol which the picture is deleted
     * @param pictureName name picture to delete
     * @return redirect to edited alcohol after deleted picture
     */
    @GetMapping("/user/alcohol/deletePicture/{alcoholId}/{pictureName}")
    public String deletePictureForAlcohol(@PathVariable("alcoholId") int alcoholId,
                                          @PathVariable("pictureName") String pictureName,
                                          RedirectAttributes redirectAttributes){
        pictureService.deletePictureForAlcohol(alcoholId, pictureName, redirectAttributes);
        return "redirect:/user/alcohol/edit/" + alcoholId;
    }

    /**
     * Method changes a main picture for alcohol.
     * @param alcoholId id alcohol which the picture is changed
     * @param pictureId picture id to change to main picture
     * @param redirectAttributes to add message after save change main picture
     * @return redirect to edited alcohol after changed main picture
     */
    @GetMapping("/user/alcohol/changeMainPicture/{alcoholId}/{pictureId}")
    public String getChangeMainPictureForAlcohol(@PathVariable("alcoholId") int alcoholId,
                                                 @PathVariable("pictureId") int pictureId,
                                                 RedirectAttributes redirectAttributes){
        pictureService.changeMainPictureForAlcohol(alcoholId, pictureId, redirectAttributes);
        return "redirect:/user/alcohol/edit/" + alcoholId;
    }

    /**
     * Method return picture founded by id.
     * Picture to show in big size.
     * @param idAlcohol id Alcohol to back button
     * @param idPicture picture id to find
     * @param model model
     * @return found picture
     */
    @GetMapping("alcohol/picture/{idAlcohol}/{idPicture}")
    public String showOnePictureForAlcohol(@PathVariable("idAlcohol") int idAlcohol,
                                           @PathVariable("idPicture") int idPicture,
                                           Model model){
        model.addAttribute("picture", pictureService.findPicture(idPicture, idAlcohol));
        model.addAttribute("idAlcohol", idAlcohol);
        return "alcohol/alcohol_big_picture";
    }
}
