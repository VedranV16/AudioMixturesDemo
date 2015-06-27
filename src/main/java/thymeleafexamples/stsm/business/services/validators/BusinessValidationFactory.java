package thymeleafexamples.stsm.business.services.validators;

import org.springframework.stereotype.Service;
import thymeleafexamples.stsm.business.entities.AudioMixSchedule;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The validation factory that contains all validators.
 * @author vvujcic on 26/06/2015.
 */
@Service
public class BusinessValidationFactory {

    public static final String SCHEDULE_VALIDATORS = "schedule_validators";
    Map<Class<?>, List<BusinessValidator>> validators = new HashMap<Class<?>, List<BusinessValidator>>();

    @PostConstruct
    void registerValidators() {
        // register all the validators for the audio mix schedule
        List<BusinessValidator> scheduleValidators = new ArrayList<BusinessValidator>();
        scheduleValidators.add(new AudioMixScheduleSumValidator());
        scheduleValidators.add(new MultipleGenresValidator());
        validators.put(AudioMixSchedule.class, scheduleValidators);
    }

    public <T> List<ValidationResult> validate(T businessObject) {
        List<BusinessValidator> validators = this.validators.get(businessObject.getClass());
        List<ValidationResult> errors = new ArrayList<ValidationResult>();

        for (BusinessValidator<T> val : validators) {
            ValidationResult validationResult = val.validate(businessObject);
            if (validationResult.getValue().equals(ValidationResult.ValidationResultValue.FAILURE)) {
                errors.add(validationResult);
            }
        }
        return errors;
    }



}
