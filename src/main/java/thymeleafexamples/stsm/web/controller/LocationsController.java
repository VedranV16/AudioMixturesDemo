package thymeleafexamples.stsm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import thymeleafexamples.stsm.business.entities.AudioMixSchedule;
import thymeleafexamples.stsm.business.entities.Location;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.interfaces.UserHandlerService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vvujcic on 24/06/2015.
 */
@Controller
@SessionAttributes("newLocation")
public class LocationsController {

    @Autowired
    private UserHandlerService userHandlerService;

    // this probably should go, maybe?
    @ModelAttribute(value ="newLocation")
    private Location getLocation(final HttpServletRequest req) {
        // maybe check if the location exsts on the user first, then return an new location?
        return new Location();
    }

    @ModelAttribute(value="user")
    private User getCurrentUser(final HttpServletRequest req) {
        Long id = (Long) req.getSession().getAttribute("user-session");
        // user has not been set up yet, just return an empty user for now
        if (id == null) {
            throw new IllegalArgumentException("User must have logged in by now.");
        }
        return userHandlerService.getCurrentSessionUser(id);
    }

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public String test(final User user) {
        return "singlelocation";
    }

    @RequestMapping("/locations")
    public String showLocations(final User user) {
        // not much to be done here I guess...
        return "locations";
    }

    @RequestMapping(value = "/locations", params ={"add"})
    public String addLocation(final User user, final ModelMap model, final HttpServletRequest req) {
        User currentUser = getCurrentUser(req);
        Location newLocation = new Location();
        newLocation.setId(16l);
        currentUser.add(newLocation);
        
        // create a blank audio mix schedule
        AudioMixSchedule newSchedule = new AudioMixSchedule();
        model.addAttribute("newSchedule", newSchedule);
        // this location should now persist in the HTTP session until we have finished adding it
        model.addAttribute("newLocation", newLocation);
        return "singlelocation";
    }

}
