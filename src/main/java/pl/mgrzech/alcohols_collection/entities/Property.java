package pl.mgrzech.alcohols_collection.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Name of configuration
     */
    @NotBlank(message = "{message.error.properties.name}")
    private String nameProperty;

    /**
     * Value of configuration
     */
    @NotBlank(message = "{message.error.properties.value}")
    @Column(name="valueProperty",columnDefinition="LONGTEXT")
    private String valueProperty;
}
