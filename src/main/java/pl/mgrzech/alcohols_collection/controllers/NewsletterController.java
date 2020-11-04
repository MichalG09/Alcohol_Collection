package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.entities.Newsletter;
import pl.mgrzech.alcohols_collection.newsletter.NewsletterService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class NewsletterController {

    private final NewsletterService newsletterService;

    /**
     * Method saves new newsletter.
     * Method validates added newsletter.
     * @param newsletter new newsletter
     * @param resultNewsletter validations newsletter
     * @param model model
     * @return redirect to start view if save newsletter is done
     */
    @PostMapping("/newsletterAdd")
    public String addNewsletter(@Valid @ModelAttribute("newsletter") Newsletter newsletter,
                                BindingResult resultNewsletter,
                                Model model,
                                RedirectAttributes redirectAttributes){
        model.addAttribute("welcomeText", newsletterService.findWelcomeTextFromProperty());
        if (resultNewsletter.hasErrors() ) {
            return "index";
        }
        newsletterService.saveNewNewsletter(newsletter, redirectAttributes);
        return "redirect:/";
    }

    /**
     * Method returns and shows all newsletter.
     * @param model model
     * @return name of the running html file to show all newsletter
     */
    @GetMapping("/user/showAllNewsletter")
    public String showAllPositionsInNewsletter(Model model){
        model.addAttribute("newsletters",  newsletterService.findAllNewsletters());
        return "newsletter/all_newsletter";
    }

    /**
     * Method send message to all newsletter.
     * @param subject subject message
     * @param message text message
     * @param redirectAttributes redirect attributes
     * @return redirect to user view if message to newsletters is sent
     */
    @PostMapping("/user/sendEMailAllNewsletter")
    public String sendEmailToAllNewsletterMethodPost(@RequestParam String subject,
                                                     @RequestParam String message,
                                                     RedirectAttributes redirectAttributes){
        newsletterService.sendEmailToAllNewsletters(subject, message, redirectAttributes);
        return "redirect:/user";
    }

    /**
     * Method prepares newsletter by unique code.
     * @param code unique code for newsletter
     * @param model model
     * @return name of the running html file, to confirm delete from newsletter list
     */
    @GetMapping("/delete/newsletter/{code}")
    public String deleteNewsletterMethodGet(@PathVariable("code") int code,
                                            @ModelAttribute("newsletter") Newsletter newsletter,
                                            Model model) {
        newsletter = newsletterService.findNewsletterToDeleteByUniqueCode(code);
        model.addAttribute("newsletter", newsletter);
        return "newsletter/confirm_delete_newsletter";
    }

    /**
     * Method deletes newsletter by id after verified by unique code.
     * @param id id newsletter to delete
     * @return redirect to start view if save newsletter is done
     */
    @PostMapping("/delete/confirmed/newsletter/{id}")
    public String deleteNewsletterMethodPost(@PathVariable("id") int id,
                                             RedirectAttributes redirectAttributes) {
        newsletterService.deleteNewsletterById(id, redirectAttributes);
        return "redirect:/";
    }
}
