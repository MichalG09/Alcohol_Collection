package pl.mgrzech.alcohols_collection.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendTestEmail sendTestEmail;

    @Value("${admin.mail}")
    private String adminMail;

    /**
     * Method send test email to admin.
     */
    public void sendTestEmailWithHTML() {
        sendTestEmail.sendTestEmailWithHTML(adminMail,"test Email with HTML template", "test");
    }
}
