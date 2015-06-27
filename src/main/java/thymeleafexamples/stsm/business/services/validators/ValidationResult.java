package thymeleafexamples.stsm.business.services.validators;

/**
 * @author vvujcic on 26/06/2015.
 */
public class ValidationResult {

    ValidationResultValue value;
    String validationMessage;

    public ValidationResultValue getValue() {
        return value;
    }

    public void setValue(ValidationResultValue value) {
        this.value = value;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public enum ValidationResultValue {
        SUCCESS,
        FAILURE;
    }
}
