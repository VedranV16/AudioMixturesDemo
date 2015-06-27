package thymeleafexamples.stsm.business.services.interfaces;

import thymeleafexamples.stsm.business.entities.AudioMixSchedule;
import thymeleafexamples.stsm.business.entities.Location;

/**
 * @author vvujcic on 25/06/2015.
 */
public interface LocationHandlerService {

    void addScheduleTo(Location location, AudioMixSchedule schedule);

    Location getLocationBy(String name, Long userId);
}
