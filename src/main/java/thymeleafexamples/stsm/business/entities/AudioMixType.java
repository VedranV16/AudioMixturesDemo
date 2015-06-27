package thymeleafexamples.stsm.business.entities;

import java.io.Serializable;

/**
 * @author vvujcic on 24/06/2015.
 */
public enum AudioMixType implements Serializable {

    ROCK("Rock"),
    HIP_HOP("Hip-Hop"),
    POP("Pop"),
    CLASSICAL("Classical"),
    BLUES("Blues"),
    COUNTRY("Country"),
    ELECTRONIC("Electronic"),
    JAZZ("Jazz"),
    SOUL("Soul"),
    REGGAE("Reggae"),
    DEATH_METAL("Death Metal");

    private String name;

    AudioMixType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    AudioMixType by(String name) {
        for (AudioMixType audioMixType : values()) {
            if (audioMixType.getName().equals(audioMixType)) {
                return audioMixType;
            }
        }
        throw new IllegalArgumentException("Unknown audio mix type: " + name);
    }

}
