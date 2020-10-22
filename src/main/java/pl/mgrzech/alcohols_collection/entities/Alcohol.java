package pl.mgrzech.alcohols_collection.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class of one alcohol bottle.
 * Alcohol have one manufacturer.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Alcohol {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * Name bottle
     */
    @NotBlank(message = "{message.error.alcohol.name}")
    private String name;

    /**
     * Type of alcohol in bottle
     */
    @NotBlank(message = "{message.error.alcohol.type}")
    private String type;

    /**
     * Capacity alcohol bottle [ml]
     */
    @Min(value = 1, message = "{message.error.alcohol.capacity.min}")
    @Max(value = 700, message = "{message.error.alcohol.capacity.min}")
    private int capacity;

    /**
     * Amount of alcohol [%]
     */
    @Min(value = 1, message = "{message.error.alcohol.amountOfAlcohol.min}")
    @Max(value = 100, message = "{message.error.alcohol.amountOfAlcohol.max}")
    private int amountOfAlcohol;

    /**
     * Date created bottle
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * Date last changed bottle
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastChangeDate;

    /**
     * Additional comment for bottle alcohol
     */
    private String comment;

    /**
     * Producer who made the bottle
     */
    @NotNull
    @ManyToOne
    private Manufacturer manufacturer;

    /**
     * Place when bottle was bought
     */
    private String placePurchase;

    /**
     * The exact place where it is stored
     */
    @NotBlank(message = "{message.error.alcohol.placeInStorage}")
    private String placeInStorage;

    /**
     * Pictures of alcohol bottle
     */
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> pictures;

    /**
     * Method returns main picture of alcohol to show in a list all alcohols.
     * @return picture in String after format Base64
     */
    public String getMainPicturesInBase64List(){
        List<String> result = new ArrayList<>();
            this.pictures.forEach(pic -> {
                if(pic.isMainPicture()){
                    result.add(pic.getPictureInBase64());
                }
            });
        return result.get(0);
    }
}
