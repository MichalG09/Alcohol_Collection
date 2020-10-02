package pl.mgrzech.alcohols_collection.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.gallery.AddNewPictureToGallery;
import pl.mgrzech.alcohols_collection.gallery.DeletePictureFromGallery;
import pl.mgrzech.alcohols_collection.gallery.FindAllPicturesToGallery;
import pl.mgrzech.alcohols_collection.gallery.GalleryService;
import pl.mgrzech.alcohols_collection.validations.file_validation.FilesValidated;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @Value("${message.error.gallery.picture.add}")
    private String messageErrorValidateFormAddPicture;
    /**
     * Method returns all pictures to gallery.
     * @param model model
     * @param message optional message after delete or add picture from/to gallery.
     * @return name of the running html file
     */
    @RequestMapping("/gallery")
    public String showGallery(@ModelAttribute("message") String message,
                              Model model) {
        model.addAttribute("picturesGallery", galleryService.findALlPicturesToGallery());
        return "gallery/gallery";
    }

    /**
     * Method prepares view to add new picture to gallery.
     * @param filesValidated param for picture to save
     * @return name of the running html file for add picture to gallery
     */
    @GetMapping("/user/gallery/add")
    public String addNewPictureToGalleryMethodGet(@ModelAttribute("file") FilesValidated filesValidated) {
        return "gallery/add_picture_to_gallery";
    }

    /**
     * Method saves a picture for gallery.
     * Methods validates file from form (min one picture have to add to form).
     * @param filesValidated param for picture to save
     * @param model model
     * @param redirectAttributes to add message after correct save picture to database or message after valid attribute
     * @return name of the running html file if validate is not correct, redirect to gallery view if save picture is done
     */
    @PostMapping ("/user/gallery/add")
    public String addNewPictureToGalleryMethodPost(@ModelAttribute("file") FilesValidated filesValidated,
                                                   Model model,
                                                   RedirectAttributes redirectAttributes) {
        if(filesValidated.getMultipartFiles().get(0).isEmpty()) {
            model.addAttribute("errorMessage", messageErrorValidateFormAddPicture);
            return "gallery/add_picture_to_gallery";
        }
        galleryService.savePicturesToGallery(filesValidated, redirectAttributes);
        return "redirect:/gallery";
    }

    /**
     * Method deletes picture by id.
     * @param id id deleting picture
     * @param redirectAttributes to add message after correct delete picture
     * @return redirect to gallery view if delete picture is done
     */
    @RequestMapping ("/user/gallery/delete/{id}")
    public String deletePictureFromGallery(@PathVariable("id") int id,
                                           RedirectAttributes redirectAttributes) {
        galleryService.deletePictureFromGallery(id, redirectAttributes);
        return "redirect:/gallery";
    }
}
