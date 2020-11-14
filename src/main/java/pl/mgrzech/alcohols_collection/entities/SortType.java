package pl.mgrzech.alcohols_collection.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class lets to show name type sort in polish language and in code are used a english version.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class SortType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Value
     */
    @NotBlank(message = "{message.error.sortType.value}")
    private String value;

    /**
     * translated value
     */
    @NotBlank(message = "{message.error.sortType.translate}")
    private String valueTranslate;
}
