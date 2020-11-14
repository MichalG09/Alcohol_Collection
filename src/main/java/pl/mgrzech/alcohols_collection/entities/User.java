package pl.mgrzech.alcohols_collection.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * In project are two role: User and Admin.
 * User role after login have "admin" permissions. User can adds,
 * edits alcohol/ manufacturer /pictures.
 * Admin role after login have "superadmin" permissions.
 * Admin have the same permission what user and e.g. additionally can add and edit properties, add and edit users.
 */
@Entity
@Table(name = "User")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * user name
     */
    @NotBlank(message = "{message.error.user.name}")
    private String username;

    /**
     * password
     */
    @NotBlank(message = "{message.error.user.password}")
    private String password;

    /**
     * if true account is active
     */
    private boolean active;

    /**
     * roles for this account
     */
    private String roles;
}
