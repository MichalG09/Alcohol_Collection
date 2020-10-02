package pl.mgrzech.alcohols_collection.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;


/**
 * In property admin can save important settings and easily modify when application is running.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * Name of configuration
     */
    @NotBlank(message = "{message.error.properties.name}")
    private String nameProperties;

    /**
     * Value of configuration
     */
    @NotBlank(message = "{message.error.properties.value}")
    @Column(name="valueProperties",columnDefinition="LONGTEXT")
    private String valueProperties;
}
