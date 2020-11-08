package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.email.EmailService;

@Controller
@AllArgsConstructor
public class EmailController {

    private final EmailService emailService;

    /**
     * Method prepares message to send for all newsletter.
     * @return name of the running html file for send message to all newsletter
     */
    @GetMapping("/user/sendEMailAllNewsletter")
    public String sendEmailToAllNewsletterMethodGet(){
        return "newsletter/send_message_newsletter";
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
        emailService.sendEmailToAllNewsletters(subject, message, redirectAttributes);
        return "redirect:/user";
    }

    /**
     * Method send test email to admin.
     * @return redirect to admin view if send email is done
     */
    @GetMapping("/admin/email/test")
    public String sendTestEmailWithHTMLMethodPost(RedirectAttributes redirectAttributes){
        emailService.sendTestEmailWithHTML(redirectAttributes);
        return "redirect:/admin";
    }
}
