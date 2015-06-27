package thymeleafexamples.stsm.business.entities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author vvujcic on 24/06/2015.
 */
public class AudioMixValue implements Serializable {

    private AudioMixType audioMixType;
    private BigDecimal value;

    public AudioMixValue() {
    }

    public AudioMixValue(AudioMixType audioMixType, BigDecimal value) {
        this.audioMixType = audioMixType;
        this.value = value;
    }

    public AudioMixType getAudioMixType() {
        return audioMixType;
    }

    public void setAudioMixType(AudioMixType audioMixType) {
        this.audioMixType = audioMixType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
