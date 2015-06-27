package thymeleafexamples.stsm.business.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vvujcic on 24/06/2015.
 */
public class AudioMixSchedule implements Serializable, Comparable<AudioMixSchedule> {

    private Date scheduleDate;
    private List<AudioMixValue> audioMix;

    public AudioMixSchedule() {
        audioMix = new ArrayList<AudioMixValue>();
    }

    public AudioMixSchedule(Date scheduleDate, List<AudioMixValue> audioMix) {
        this.scheduleDate = scheduleDate;
        this.audioMix = audioMix;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public List<AudioMixValue> getAudioMix() {
        return audioMix;
    }

    public void setAudioMix(List<AudioMixValue> audioMix) {
        this.audioMix = audioMix;
    }

    public void add(AudioMixValue audioMixValue) {
        audioMix.add(audioMixValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudioMixSchedule that = (AudioMixSchedule) o;

        return scheduleDate.equals(that.getScheduleDate());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public int compareTo(AudioMixSchedule o) {
        if (o.getScheduleDate() == null) {
            return -1;
        } else {
            return this.getScheduleDate().compareTo(o.getScheduleDate());
        }
    }
}
