package pl.mgrzech.alcohols_collection.email.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Class of email message
 */
@NoArgsConstructor
@Getter
@Setter
public class EmailMessage {

    /**
     * name person who want send message.
     */
    private String name;

    /**
     * subject e-mail
     */
    @NotBlank(message = "{message.error.mail.subject}")
    private String subject;

    /**
     * e-mail address
     */
    @NotBlank(message = "{message.error.mail.email.blank}")
    @Email(message = "{message.error.mail.email.format}")
    private String email;

    /**
     * text e-mail
     */
    @NotBlank(message = "{message.error.mail.text}")
    private String text;

    /**
     * if user want copy message to his e-mail address
     */
    private boolean copy = false;
}
