package thymeleafexamples.stsm.business.services.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thymeleafexamples.stsm.business.entities.AudioMixSchedule;
import thymeleafexamples.stsm.business.entities.Location;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.interfaces.LocationHandlerService;
import thymeleafexamples.stsm.business.services.interfaces.UserHandlerService;

/**
 * @author vvujcic on 25/06/2015.
 */
@Service
public class MockLocationHandlerService implements LocationHandlerService {

    @Autowired
    UserHandlerService userHandlerService;

    /**
     * Adds a schedule to a location if it does not already contain it
     * @param location
     * @param schedule
     */
    public void addScheduleTo(Location location, AudioMixSchedule schedule) {
        // replace the schedule if it has the same date
        if (location.getAudioMixSchedules().contains(schedule)) {
            int index = location.getAudioMixSchedules().indexOf(schedule);
            location.getAudioMixSchedules().set(index, schedule);
        } else {
            location.getAudioMixSchedules().add(schedule);
        }
    }

    public Location getLocationBy(String name, Long userId) {
        User currentSessionUser = userHandlerService.getCurrentSessionUser(userId);
        for (Location location : currentSessionUser.getLocations()) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        throw new IllegalArgumentException("Unknown location name: " + name);
    }
}
