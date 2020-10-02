package pl.mgrzech.alcohols_collection.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class of one manufacturer of alcohol.
 * Manufacturer can have multi alcohols.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iD;

    /**
     * Name company
     */
    @NotBlank(message = "{message.error.manufacturer.name}")
    private String nameCompany;

    /**
     * manufacturer city
     */
    private String town;

    /**
     * manufacturer country
     */
    private String country;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Alcohol> alcohols;

    /**
     * Additional comment for manufacturer
     */
    private String comments;

    /**
     * Date od created bottle
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * Date last changed bottle
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastChangeDate;
}
