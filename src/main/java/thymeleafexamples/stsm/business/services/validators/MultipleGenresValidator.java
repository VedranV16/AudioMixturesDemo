package thymeleafexamples.stsm.business.services.validators;

import thymeleafexamples.stsm.business.entities.AudioMixSchedule;
import thymeleafexamples.stsm.business.entities.AudioMixType;
import thymeleafexamples.stsm.business.entities.AudioMixValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vvujcic on 26/06/2015.
 */
public class MultipleGenresValidator implements BusinessValidator<AudioMixSchedule> {

    public ValidationResult validate(AudioMixSchedule businessObject) {
        ValidationResult result = new ValidationResult();
        result.setValue(ValidationResult.ValidationResultValue.SUCCESS);

        List<AudioMixType> audioMixTypes = new ArrayList<AudioMixType>();
        for (AudioMixValue value : businessObject.getAudioMix()) {
            if (audioMixTypes.contains(value.getAudioMixType())) {
                // we have a duplicate, will be lazy here and simply warn them of the first one
                result.setValidationMessage("There can be no duplicates of any music genre. Duplicated genre is " + value.getAudioMixType().getName());
                result.setValue(ValidationResult.ValidationResultValue.FAILURE);
                return result;
            }
        }

        return result;
    }
}
