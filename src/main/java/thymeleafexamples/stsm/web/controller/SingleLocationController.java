package thymeleafexamples.stsm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import thymeleafexamples.stsm.business.entities.*;
import thymeleafexamples.stsm.business.services.interfaces.LocationHandlerService;
import thymeleafexamples.stsm.business.services.interfaces.UserHandlerService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author vvujcic on 24/06/2015.
 */
@Controller
public class SingleLocationController {

    @Autowired
    private UserHandlerService userHandlerService;

    @Autowired
    private LocationHandlerService locationHandlerService;

    @ModelAttribute("allGenres")
    public List<AudioMixType> getAllGenres() {
        return Arrays.asList(AudioMixType.values());
    }

    @RequestMapping("/singlelocation")
    public String showSingleLocation(final AudioMixSchedule schedule, final ModelMap model) {
        return "singlelocation";
    }

    @RequestMapping(value ="/singlelocation", params = {"addGenre"})
    public String addNewSchedule(final AudioMixSchedule schedule, final ModelMap model, final HttpServletRequest request) {
        Location newLocation = (Location) request.getSession().getAttribute("newLocation");

        setLocationName(newLocation, request);

        // these must be passed into the model map
        model.addAttribute("newSchedule", schedule);
        model.addAttribute("newLocation", newLocation);

        // now add the mix value which will bind to the input
        schedule.add(new AudioMixValue());

        return "singlelocation";
    }

    @RequestMapping(value ="/singlelocation", params = {"save"})
    public String saveSchedule(final AudioMixSchedule schedule, final ModelMap model, final HttpServletRequest request) {
        Location newLocation = (Location) request.getSession().getAttribute("newLocation");
        // remove the new location session
        request.getSession().removeAttribute("newLocation");
        // do i need this?? maybe...
        model.clear();
        // even though we've bound the schedule to a form, adding the schedule between different posts like this is dangerous
        // essentially we can keep adding the same "schedule", so we need to ensure that based on its date (using the equals()) method
        // we replace the latest equivalent instance
        locationHandlerService.addScheduleTo(newLocation, schedule);
        // redirect to clear the map
        return "redirect:/locations";
    }

    private void setLocationName(Location newLocation, final HttpServletRequest request) {
        // this should probably be another form that binds to the location, but it's a single field so let's just do this
        if (request.getParameter("locationName") != null) {
            newLocation.setName((String) request.getParameter("locationName"));
        }
    }

    @RequestMapping(value="/singlelocation/update{locationName}")
    public String updateLocation(@RequestParam String locationName, final ModelMap model, final HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user-session");
        Location existingLocation =  locationHandlerService.getLocationBy(locationName, userId);

        model.addAttribute("newLocation", existingLocation);
        // so now the tricky part, the user clicked on the location so the assumption is that they will want to modify the earliest schedule
        // however, they can at any point change the schedule to another one and save it..should keep the date, we override the mix
        model.addAttribute("newSchedule", existingLocation.getEarliestSchedule());
        request.getSession().setAttribute("newLocation", existingLocation);
        return "singlelocation";
    }
}
