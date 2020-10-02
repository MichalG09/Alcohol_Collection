package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.email.SendEmailToAdminFromContactPage;
import pl.mgrzech.alcohols_collection.email.model.EmailMessage;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ContactController {

    private final SendEmailToAdminFromContactPage sendEmailToAdminFromContactPage;

    /**
     * Method shows view with form to send message to admin.
     * @param emailMessage email
     * @param model model
     * @return name of the running html file with form to send message to admin
     */
    @GetMapping("/contact")
    public String sendMessageToAdminMethodGet(@ModelAttribute EmailMessage emailMessage,
                                              Model model) {
        return "contact";
    }

    /**
     * Method sends email to admin with data from contact page.
     * @param email email
     * @param redirectAttributes redirect attributes to add message after send email
     * @return redirect to main view after sent message
     */
    @PostMapping("/contact")
    public String sendMessageToAdminMethodPost(@Valid @ModelAttribute EmailMessage email,
                                               BindingResult resultEmail,
                                               RedirectAttributes redirectAttributes) {
        if (resultEmail.hasErrors()) {
            return "contact";
        }
        sendEmailToAdminFromContactPage.send(email, redirectAttributes);
        return "redirect:/";
    }
}