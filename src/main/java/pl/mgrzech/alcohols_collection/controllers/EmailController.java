package pl.mgrzech.alcohols_collection.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mgrzech.alcohols_collection.email.EmailService;
import pl.mgrzech.alcohols_collection.email.SendTestEmail;

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
     * Method send test email to admin.
     * @return redirect to admin view if send email is done
     */
    @GetMapping("/admin/email/test")
    public String editAlcoholMethodPost(){
        emailService.sendTestEmailWithHTML();
        return "redirect:/admin";
    }
}
