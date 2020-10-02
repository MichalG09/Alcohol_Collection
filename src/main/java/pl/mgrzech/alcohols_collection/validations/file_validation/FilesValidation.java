package pl.mgrzech.alcohols_collection.validations.file_validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validation for Files (Picture).
 */
public class FilesValidation implements ConstraintValidator<FilesConstraint, MultipartFile> {

    @Override
    public void initialize(FilesConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFiles,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !(multipartFiles == null || multipartFiles.isEmpty());
    }
}
