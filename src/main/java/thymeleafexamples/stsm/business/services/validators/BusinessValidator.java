package thymeleafexamples.stsm.business.services.validators;

/**
 * @author vvujcic on 26/06/2015.
 */
public interface BusinessValidator<T> {

    /**
     * Validates a business object and returns a validation result
     * @param businessObject
     * @return
     */
    ValidationResult validate(T businessObject);
}
