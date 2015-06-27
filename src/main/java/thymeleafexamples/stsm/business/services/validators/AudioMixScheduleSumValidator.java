package thymeleafexamples.stsm.business.services.validators;

import thymeleafexamples.stsm.business.entities.AudioMixSchedule;
import thymeleafexamples.stsm.business.entities.AudioMixValue;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @author vvujcic on 26/06/2015.
 */
public class AudioMixScheduleSumValidator implements BusinessValidator<AudioMixSchedule> {

    /**
     * The total sum of all the music genre values must add up to 100%
     * @param businessObject
     * @return
     */
    public ValidationResult validate(AudioMixSchedule businessObject) {
        ValidationResult result = new ValidationResult();
        result.setValue(ValidationResult.ValidationResultValue.SUCCESS);

        BigDecimal totalSum = BigDecimal.ONE;
        for (AudioMixValue value : businessObject.getAudioMix()) {
            totalSum = totalSum.add(value.getValue());
        }

        NumberFormat percent = NumberFormat.getPercentInstance();

        if (totalSum.compareTo(BigDecimal.ONE) > 1) {
            result.setValidationMessage("The schedule mix must equal 100% . It equals : " + percent.format(totalSum.doubleValue()));
            result.setValue(ValidationResult.ValidationResultValue.FAILURE);
        }
        return result;
    }
}
