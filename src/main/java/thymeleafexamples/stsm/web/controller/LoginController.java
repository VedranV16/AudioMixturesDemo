package thymeleafexamples.stsm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleafexamples.stsm.business.entities.*;
import thymeleafexamples.stsm.business.services.SeedStarterService;
import thymeleafexamples.stsm.business.services.VarietyService;
import thymeleafexamples.stsm.business.services.interfaces.UserHandlerService;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * @author vvujcic on 23/06/2015.
 */
@Controller
public class LoginController {

    @Autowired
    private VarietyService varietyService;

    @Autowired
    private SeedStarterService seedStarterService;

    @Autowired
    private UserHandlerService userHandlerService;

    @RequestMapping({"/", "/login/"})
    public String login(final User user, final HttpServletRequest req) {
        // go to the login page...
        return "login";
    }

    @RequestMapping(value = "/login", params = {"save"})
    public String attemptLogin(final User user, final HttpServletRequest req, final ModelMap model) {

        SeedStarter st = new SeedStarter();
        model.addAttribute("seedStarter", st);
        st.setDatePlanted(Calendar.getInstance().getTime());

        userHandlerService.handleUserOnLogin(user);
        // start the user session here, we'll use the user id as the unique identifier
        req.getSession().setAttribute("user-session", user.getId());

        //createMockLocation(user);
        return "locations";
    }

//    private void createMockLocation(User user) {
//        Location loc = new Location();
//        loc.setName("MockLoc");
//        loc.setCurrentDate(new Date());
//        user.add(loc);
//
//        AudioMixSchedule sched = new AudioMixSchedule();
//        sched.setScheduleDate(new Date());
//        loc.add(sched);
//
//        AudioMixValue val = new AudioMixValue();
//        val.setAudioMixType(AudioMixType.BLUES);
//        val.setValue(0.5f);
//        sched.add(val);
//    }

}
