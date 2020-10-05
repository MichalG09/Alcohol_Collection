package pl.mgrzech.alcohols_collection.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Picture of bottle alcohol
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Picture {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * unique name of picture
     */
    @NotBlank
    private String name;

    /**
     * Picture in byte code
     */
    @Lob
    @NotNull
    private byte[] file;

    /**
     * Date created picture
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date createdDate;

    /**
     * if true picture is for gallery.
     * If false picture is connected with some alcohol.
     */
    private boolean gallery = false;

    /**
     * If true picture is main picture for lacohol.
     */
    private boolean mainPicture = false;

    /**
     * Method returns a picture of alcohol in format Base64 (show in html file).
     * @return picture in String after format Base64
     */
    public String getPictureInBase64(){
        return org.jboss.util.Base64.encodeBytes(this.getFile());//Prod
//        return org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(this.getFile()); //Test in Tomcat
    }
}