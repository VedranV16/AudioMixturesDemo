package thymeleafexamples.stsm.business.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author vvujcic on 23/06/2015.
 */
public class Location implements Serializable {

    private String name;
    private Date currentDate;   // TODO: you should just be a function!
    private List<AudioMixSchedule> audioMixSchedules;
    private Long id;

    public Location() {
        audioMixSchedules = new ArrayList<AudioMixSchedule>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AudioMixSchedule> getAudioMixSchedules() {
        return audioMixSchedules;
    }

    public void setAudioMixSchedules(List<AudioMixSchedule> audioMixSchedules) {
        this.audioMixSchedules = audioMixSchedules;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void add(AudioMixSchedule schedule) {
        this.audioMixSchedules.add(schedule);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the earliest date amongst the schedules, null if no schedules
     * @return
     */
    public Date getEarliestDate() {
        List<Date> dates = new ArrayList<Date>();
        for (AudioMixSchedule schedule : audioMixSchedules) {
            if (schedule.getScheduleDate() != null) {
                dates.add(schedule.getScheduleDate());
            }
        }

        Collections.sort(dates);
        if (dates.size() > 0) {
            return dates.get(0);
        } else {
            return null;
        }
    }

    public AudioMixSchedule getEarliestSchedule() {
        List<AudioMixSchedule> copy = new ArrayList<AudioMixSchedule>(audioMixSchedules);
        Collections.sort(copy);
        if (copy.size() > 0) {
            return copy.get(0);
        } else {
            return null;
        }
    }
}
