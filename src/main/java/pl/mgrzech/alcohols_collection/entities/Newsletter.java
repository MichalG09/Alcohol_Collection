package pl.mgrzech.alcohols_collection.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Newsletter {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * Name person who want send message
     */
    @NotBlank(message = "{message.error.newsletter.name}")
    private String name;

    /**
     * E-mail address for newsletter
     */
    @NotBlank(message = "{message.error.newsletter.email}")
    @Email(message = "{message.error.mail.email.format}")
    private String email;

    /**
     * Unique value.
     * Person from newsletter only know this code (sent in each e-mail with link to delete from newsletter).
     * This code is necessary to find this newsletter in database and deletes from newsletter list.
     */
    private int codeToVerifyDelete;
}
