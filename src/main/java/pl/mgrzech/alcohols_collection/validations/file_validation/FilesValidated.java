package pl.mgrzech.alcohols_collection.validations.file_validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Class adds and validations pictures for alcohol.
 */
@NoArgsConstructor
@Setter
@Getter
public class FilesValidated {

    /**
     * Main picture to shows small picture in list all alcohol and in compare alcohols.
     * Main picture is necessary to add new picture.
     */
    @FilesConstraint
    private MultipartFile mainFile;

    /**
     * Additionally pictures for alcohol.
     */
    private List<MultipartFile> multipartFiles;
}
