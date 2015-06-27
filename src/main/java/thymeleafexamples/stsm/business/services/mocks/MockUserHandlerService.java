package thymeleafexamples.stsm.business.services.mocks;

import org.springframework.stereotype.Service;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.interfaces.UserHandlerService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vvujcic on 23/06/2015.
 */
@Service
public class MockUserHandlerService extends UserHandlerService {

    private List<User> users = new ArrayList<User>();

    @Override
    public User add(User newUser) {
        users.add(newUser);
        return newUser;
    }

    @Override
    public User retrieve(User existingUser) {
        // so cool, bro
        return users.indexOf(existingUser) == -1 ? null : users.get(users.indexOf(existingUser));
    }

}
