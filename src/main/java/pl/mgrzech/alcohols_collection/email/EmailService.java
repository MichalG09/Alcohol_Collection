package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mgrzech.alcohols_collection.email.model.EmailMessage;
import pl.mgrzech.alcohols_collection.entities.Newsletter;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendTestEmail sendTestEmail;
    private final SendEmailToAdminFromContactPage sendEmailToAdminFromContactPage;
    private final SendEmailToAdminWithNewNewsletter sendEmailToAdminWithNewNewsletter;
    private final SendEmailAfterSubscribedNewsletter sendEmailAfterSubscribedNewsletter;
    private final SendEmailToAllNewsletters sendEmailToAllNewsletters;

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${message.correct.email.toAdmin}")
    private String messageCorrectSentEmailToAdmin;

    @Value("${message.error.email.toAdmin}")
    private String messageErrorSentEmailToAdmin;

    @Value("${message.correct.email.toAllNewsletters}")
    private String messageCorrectSentEmailToAllNewsletter;

    @Value("${message.fail.email.toAllNewsletters}")
    private String messageFailSentEmailToAllNewsletter;

    /**
     * Method send test email to admin.
     */
    public void sendTestEmailWithHTML(RedirectAttributes redirectAttributes) {
        try {
            sendTestEmail.sendTestEmailWithHTML(adminMail,"test Email with HTML template", "test");
            redirectAttributes.addFlashAttribute("message", messageCorrectSentEmailToAdmin);
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("messageError", messageErrorSentEmailToAdmin);
            e.printStackTrace();
        }
    }

    /**
     * Method sends email to admin from contact page.
     * @param email values from form in contact page
     * @param redirectAttributes redirectAttributes
     */
    public void sendMessageToAdminFromContactPage(EmailMessage email, RedirectAttributes redirectAttributes){
        try {
            sendEmailToAdminFromContactPage.send(email);
            redirectAttributes.addFlashAttribute("message", messageCorrectSentEmailToAdmin);
        } catch (MessagingException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("messageError", messageErrorSentEmailToAdmin);
        }
    }

    /**
     * Method sends two email after add new newsletter.
     * First email is to admin with information about new newsletter.
     * Second email is to new newsletter with information of subscribe.
     * @param newsletter new newsletter
     * @throws MessagingException send email exception
     */
    public void sendEmailAfterAddNewNewsletter(Newsletter newsletter) throws MessagingException {
        sendEmailToAdminWithNewNewsletter.sendEmailToAdminWithNewNewsletter(newsletter);
        sendEmailAfterSubscribedNewsletter.sendEmailAfterSubscribedNewsletter(newsletter);
    }

    /**
     * Method sends e-mail to all user saved to list newsletter.
     * @param subject subject email
     * @param textMessage text email
     * @param redirectAttributes redirectAttributes
     */
    public void sendEmailToAllNewsletters(String subject, String textMessage, RedirectAttributes redirectAttributes) {
        try {
            sendEmailToAllNewsletters.sendMessageToAllPersonInNewsletter(subject, textMessage);
            redirectAttributes.addFlashAttribute("message", messageCorrectSentEmailToAllNewsletter);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", messageFailSentEmailToAllNewsletter);
            e.printStackTrace();
        }
    }
}
